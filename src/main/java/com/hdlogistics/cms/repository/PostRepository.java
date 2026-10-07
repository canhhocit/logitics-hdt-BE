package com.hdlogistics.cms.repository;

import com.hdlogistics.cms.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findBySlug(String slug);
    boolean existsBySlug(String slug);
    Page<Post> findByStatus(String status, Pageable pageable);
    Page<Post> findByCategoryIdAndStatus(UUID categoryId, String status, Pageable pageable);
}
