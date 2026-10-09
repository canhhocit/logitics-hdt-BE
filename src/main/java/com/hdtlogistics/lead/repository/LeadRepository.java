package com.hdtlogistics.lead.repository;

import com.hdtlogistics.lead.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LeadRepository extends JpaRepository<Lead, UUID> {
    long countByStatus(String status);
    Page<Lead> findByStatus(String status, Pageable pageable);
}
