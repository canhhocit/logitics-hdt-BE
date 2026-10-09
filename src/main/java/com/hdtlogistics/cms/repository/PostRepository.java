package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.Post;
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

    @org.springframework.data.jpa.repository.Query("SELECT p FROM Post p WHERE " +
            "(LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.slug) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(p.createdAt >= :fromDate) AND " +
            "(p.createdAt <= :toDate)")
    Page<Post> searchAdminPosts(
            @org.springframework.data.repository.query.Param("keyword") String keyword,
            @org.springframework.data.repository.query.Param("fromDate") java.time.Instant fromDate,
            @org.springframework.data.repository.query.Param("toDate") java.time.Instant toDate,
            Pageable pageable);
}
