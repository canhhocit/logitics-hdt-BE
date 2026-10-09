package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PageRepository extends JpaRepository<Page, UUID> {
    Optional<Page> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
