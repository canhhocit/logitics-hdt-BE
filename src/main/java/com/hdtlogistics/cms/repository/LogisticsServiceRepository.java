package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.LogisticsService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogisticsServiceRepository extends JpaRepository<LogisticsService, UUID> {
    Optional<LogisticsService> findBySlug(String slug);
    boolean existsBySlug(String slug);
    List<LogisticsService> findByStatusOrderByOrderNoAsc(String status);
    long countByStatus(String status);
    org.springframework.data.domain.Page<LogisticsService> findByStatusNot(String status, org.springframework.data.domain.Pageable pageable);
}
