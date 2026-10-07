package com.hdlogistics.cms.repository;

import com.hdlogistics.cms.entity.LogisticsService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LogisticsServiceRepository extends JpaRepository<LogisticsService, UUID> {
    Optional<LogisticsService> findBySlug(String slug);
    boolean existsBySlug(String slug);
    List<LogisticsService> findByStatusOrderByOrderNoAsc(String status);
    long countByStatus(String status);
}
