package com.hdlogistics.cms.service;

import com.hdlogistics.cms.entity.Category;
import com.hdlogistics.cms.repository.CategoryRepository;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy chuyên mục", ErrorCode.RESOURCE_NOT_FOUND, 404));
    }

    public Category findBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new BaseException("Không tìm thấy chuyên mục", ErrorCode.RESOURCE_NOT_FOUND, 404));
    }

    @Transactional
    public Category create(Category category) {
        if (categoryRepository.existsBySlug(category.getSlug())) {
            throw new BaseException("Slug đã tồn tại", ErrorCode.SLUG_EXISTS, 409);
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(UUID id, Category updated) {
        Category existing = findById(id);
        existing.setName(updated.getName());
        if (updated.getSlug() != null && !updated.getSlug().equals(existing.getSlug())) {
            if (categoryRepository.existsBySlug(updated.getSlug())) {
                throw new BaseException("Slug đã tồn tại", ErrorCode.SLUG_EXISTS, 409);
            }
            existing.setSlug(updated.getSlug());
        }
        if (updated.getParent() != null) {
            existing.setParent(updated.getParent());
        }
        return categoryRepository.save(existing);
    }

    @Transactional
    public void delete(UUID id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
