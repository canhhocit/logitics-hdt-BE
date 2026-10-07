package com.hdlogistics.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Enables @PreAuthorize on controller methods for all downstream services. Import this
 * configuration in each service's SecurityConfig or Application class.
 *
 * <p>SRS v6.4 — Downstream services perform fine-grained authorization
 * via @PreAuthorize("@permEval.hasAnyRole(...)") using the PermissionEvaluator bean.
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {}
