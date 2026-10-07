package com.hdlogistics.common.security;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * SRS v6.4 — Downstream permission evaluator.
 *
 * <p>Each service uses this bean in @PreAuthorize expressions to enforce fine-grained
 * authorization. It fetches effective permissions from Redis (perm:{userId}) and falls back to
 * auth-service on cache miss.
 *
 * <p>Usage in
 * controller: @PreAuthorize("@permEval.hasPermission('user:read')") @PreAuthorize("@permEval.hasAnyRole('SUPER_ADMIN','ORG_ADMIN')")
 */
@Slf4j
@Component("permEval")
public class PermissionEvaluator {

  @org.springframework.beans.factory.annotation.Autowired(required = false)
  private StringRedisTemplate redisTemplate;

  private static final String PERM_KEY_PREFIX = "perm:";
  private static final Duration PERM_CACHE_TTL = Duration.ofMinutes(5);

    // Auth-service URL supports local Spring profiles and Docker environment variables.
    @org.springframework.beans.factory.annotation.Value(
      "${auth.service.url:${AUTH_SERVICE_URL:http://dnt-hub-auth-service:8081}}")
    private String authServiceUrl;

  private final ObjectMapper objectMapper = new ObjectMapper();

  // -----------------------------------------------------------------------
  // Public API — used in @PreAuthorize expressions
  // -----------------------------------------------------------------------

  /** Returns true if the current user has all of the specified permission codes. */
  public boolean hasPermission(String... permissionCodes) {
    if (hasAnyRole("SUPER_ADMIN")) return true;
    Set<String> effective = getEffectivePermissions();
    for (String code : permissionCodes) {
      if (!effective.contains(code)) return false;
    }
    return true;
  }

  /** Returns true if the current user has at least one of the specified permission codes. */
  public boolean hasAnyPermission(String... permissionCodes) {
    if (hasAnyRole("SUPER_ADMIN")) return true;
    Set<String> effective = getEffectivePermissions();
    for (String code : permissionCodes) {
      if (effective.contains(code)) return true;
    }
    return false;
  }

  /** Returns true if the current user has at least one of the specified role codes. */
  public boolean hasAnyRole(String... roleCodes) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) return false;
    return auth.getAuthorities().stream()
        .anyMatch(
            a -> {
              String authority = a.getAuthority().replace("ROLE_", "");
              return Arrays.asList(roleCodes).contains(authority);
            });
  }

  /** Returns true if the current user IS the specified userId (ownership check). */
  public boolean isSelf(String userId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) return false;
    return userId.equals(auth.getName());
  }

  // -----------------------------------------------------------------------
  // Internal helpers
  // -----------------------------------------------------------------------

  private String getCurrentUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth != null ? auth.getName() : null;
  }

  @SuppressWarnings("unchecked")
  private Set<String> getEffectivePermissions() {
    String userId = getCurrentUserId();
    if (userId == null) return Collections.emptySet();

    String cacheKey = PERM_KEY_PREFIX + userId;

    // 1. Try Redis cache first (if redis is available)
    if (redisTemplate != null) {
      try {
        String cached = redisTemplate.opsForValue().get(cacheKey);
        if (cached != null && !cached.isBlank()) {
          List<String> list = cached.trim().startsWith("[")
              ? objectMapper.readValue(cached, new TypeReference<List<String>>() {})
              : Arrays.stream(cached.split(","))
                  .map(String::trim)
                  .filter(value -> !value.isEmpty())
                  .toList();
          return new HashSet<>(list);
        }
      } catch (Exception e) {
        log.warn("Failed to read from Redis cache for userId={}: {}", userId, e.getMessage());
      }
    }

    // 2. Cache miss — call auth-service to get effective permissions
    try {
      RestTemplate rt = new RestTemplate();
      String url = authServiceUrl + "/api/v1/users/" + userId + "/permissions";

      // Propagate the bearer token from current request
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
      // X-User-Id is already set by Gateway; auth-service reads it
      headers.set("X-User-Id", userId);
      if (auth != null) {
        java.util.List<String> roles = auth.getAuthorities().stream()
            .map(a -> a.getAuthority().replace("ROLE_", ""))
            .collect(java.util.stream.Collectors.toList());
        headers.set("X-Role", roles.toString());
      }

      org.springframework.http.HttpEntity<Void> entity =
          new org.springframework.http.HttpEntity<>(headers);

      org.springframework.http.ResponseEntity<Map> response =
          rt.exchange(url, org.springframework.http.HttpMethod.GET, entity, Map.class);

      if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
        Map<String, Object> body = response.getBody();
        Object data = body.get("data");
        if (data instanceof List<?> permList) {
          Set<String> perms = new HashSet<>();
          permList.forEach(p -> perms.add(p.toString()));
          // Cache result if redis is available
          if (redisTemplate != null) {
            try {
              redisTemplate.opsForValue().set(cacheKey, objectMapper.writeValueAsString(perms), PERM_CACHE_TTL);
            } catch (Exception e) {
              log.warn("Failed to write to Redis cache for userId={}: {}", userId, e.getMessage());
            }
          }
          return perms;
        }
      }
    } catch (Exception e) {
      log.warn(
          "Failed to fetch permissions for userId={} from auth-service: {}",
          userId,
          e.getMessage());
    }
    return Collections.emptySet();
  }
}
