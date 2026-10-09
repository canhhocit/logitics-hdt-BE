package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.request.PostRequest;
import com.hdtlogistics.cms.entity.Category;
import com.hdtlogistics.cms.entity.Post;
import com.hdtlogistics.cms.repository.CategoryRepository;
import com.hdtlogistics.cms.repository.PostRepository;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostAdminService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<Post> getAll(String keyword, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable) {
        String kw = (keyword == null) ? "" : keyword;
        java.time.Instant from = fromDate != null ? fromDate.atZone(java.time.ZoneId.systemDefault()).toInstant() : java.time.Instant.EPOCH;
        java.time.Instant to = toDate != null ? toDate.atZone(java.time.ZoneId.systemDefault()).toInstant() : java.time.Instant.parse("2999-12-31T23:59:59Z");
        return postRepository.searchAdminPosts(kw, from, to, pageable);
    }

    @Transactional
    public Post create(PostRequest request) {
        if (postRepository.existsBySlug(request.getSlug())) {
            throw new BaseException("Slug đã tồn tại", ErrorCode.SLUG_EXISTS, 409);
        }

        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new BaseException("Chuyên mục không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        }

        Post post = Post.builder()
                .title(request.getTitle())
                .slug(request.getSlug())
                .excerpt(request.getExcerpt())
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .category(category)
                .readMinutes(request.getReadMinutes() != null ? request.getReadMinutes() : 5)
                .seoTitle(request.getSeoTitle())
                .seoDesc(request.getSeoDesc())
                .status(request.getStatus() != null ? request.getStatus() : "DRAFT")
                .publishedAt("PUBLISHED".equals(request.getStatus()) ? LocalDateTime.now() : null)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public Post update(UUID id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException("Bài viết không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        if (!post.getSlug().equals(request.getSlug()) && postRepository.existsBySlug(request.getSlug())) {
            throw new BaseException("Slug đã tồn tại", ErrorCode.SLUG_EXISTS, 409);
        }

        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new BaseException("Chuyên mục không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        }

        post.setTitle(request.getTitle());
        post.setSlug(request.getSlug());
        post.setExcerpt(request.getExcerpt());
        post.setContent(request.getContent());
        post.setThumbnail(request.getThumbnail());
        post.setCategory(category);
        if (request.getReadMinutes() != null) post.setReadMinutes(request.getReadMinutes());
        post.setSeoTitle(request.getSeoTitle());
        post.setSeoDesc(request.getSeoDesc());
        
        if (request.getStatus() != null) {
            if ("PUBLISHED".equals(request.getStatus()) && !"PUBLISHED".equals(post.getStatus())) {
                post.setPublishedAt(LocalDateTime.now());
            }
            post.setStatus(request.getStatus());
        }

        return postRepository.save(post);
    }

    @Transactional
    public void delete(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BaseException("Bài viết không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        postRepository.delete(post);
    }
}
