package com.hdlogistics.cms.service;

import com.hdlogistics.cms.entity.*;
import com.hdlogistics.cms.repository.*;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import com.hdlogistics.lead.entity.ContactPerson;
import com.hdlogistics.lead.entity.Office;
import com.hdlogistics.lead.repository.ContactPersonRepository;
import com.hdlogistics.lead.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CmsPublicService {

    // CMS repositories
    private final LogisticsServiceRepository logisticsServiceRepository;
    private final MenuItemRepository menuItemRepository;
    private final SiteSettingRepository siteSettingRepository;
    private final PostRepository postRepository;
    private final PartnerRepository partnerRepository;
    private final TestimonialRepository testimonialRepository;
    private final CategoryRepository categoryRepository;
    private final JobPostingRepository jobPostingRepository;
    private final SiteAwardRepository siteAwardRepository;
    private final CompanyValueRepository companyValueRepository;

    // Lead repositories (offices & contacts are public-facing data)
    private final OfficeRepository officeRepository;
    private final ContactPersonRepository contactPersonRepository;

    // ─────────────────────────────────────────────
    // Services (Dịch vụ logistics)
    // ─────────────────────────────────────────────

    public List<LogisticsService> getPublicServices() {
        return logisticsServiceRepository.findByStatusOrderByOrderNoAsc("PUBLISHED");
    }

    public LogisticsService getServiceBySlug(String slug) {
        return logisticsServiceRepository.findBySlug(slug)
                .filter(s -> "PUBLISHED".equals(s.getStatus()))
                .orElseThrow(() -> new BaseException(
                        "Dịch vụ không tồn tại hoặc chưa public",
                        ErrorCode.RESOURCE_NOT_FOUND, 404));
    }

    // ─────────────────────────────────────────────
    // Menu
    // ─────────────────────────────────────────────

    public List<MenuItem> getMenuByPosition(String position) {
        return menuItemRepository.findByPositionOrderByOrderNoAsc(position);
    }

    // ─────────────────────────────────────────────
    // Site Settings
    // ─────────────────────────────────────────────

    @Cacheable(value = "siteSettings")
    public Map<String, String> getPublicSettings() {
        return siteSettingRepository.findAll().stream()
                .collect(Collectors.toMap(SiteSetting::getKey, SiteSetting::getValue));
    }

    // ─────────────────────────────────────────────
    // Posts & Categories
    // ─────────────────────────────────────────────

    public List<Post> getPublicPosts(int size) {
        return postRepository.findByStatus(
                "PUBLISHED",
                PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "publishedAt"))
        ).getContent();
    }

    public Page<Post> getPublicPostsPaged(String categorySlug, Pageable pageable) {
        if (categorySlug != null && !categorySlug.isBlank()) {
            Category cat = categoryRepository.findBySlug(categorySlug)
                    .orElseThrow(() -> new BaseException(
                            "Chuyên mục không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
            return postRepository.findByCategoryIdAndStatus(cat.getId(), "PUBLISHED", pageable);
        }
        return postRepository.findByStatus("PUBLISHED", pageable);
    }

    public Post getPublicPostBySlug(String slug) {
        return postRepository.findBySlug(slug)
                .filter(p -> "PUBLISHED".equals(p.getStatus()))
                .orElseThrow(() -> new BaseException(
                        "Bài viết không tồn tại hoặc chưa public",
                        ErrorCode.RESOURCE_NOT_FOUND, 404));
    }

    public List<Category> getPublicCategories() {
        return categoryRepository.findAll();
    }

    // ─────────────────────────────────────────────
    // Partners & Testimonials
    // ─────────────────────────────────────────────

    public List<Partner> getPartners(String type) {
        if (type != null && !type.isEmpty()) {
            return partnerRepository.findByTypeOrderByOrderNoAsc(type);
        }
        return partnerRepository.findAllByOrderByOrderNoAsc();
    }

    public List<Testimonial> getTestimonials() {
        return testimonialRepository.findAllByOrderByOrderNoAsc();
    }

    // ─────────────────────────────────────────────
    // Offices & Contact Persons
    // ─────────────────────────────────────────────

    public List<Office> getPublicOffices() {
        return officeRepository.findAllByOrderByOrderNoAsc();
    }

    public List<Office> getPublicOfficesByRegion(String region) {
        return officeRepository.findByRegionOrderByOrderNoAsc(region);
    }

    public List<ContactPerson> getPublicContacts() {
        return contactPersonRepository.findAllByOrderByFullNameAsc();
    }

    // ─────────────────────────────────────────────
    // Job Postings (Tuyển dụng)
    // ─────────────────────────────────────────────

    public List<JobPosting> getPublicJobs() {
        return jobPostingRepository.findByStatusOrderByOrderNoAsc("OPEN");
    }

    // ─────────────────────────────────────────────
    // Site Awards (Giải thưởng)
    // ─────────────────────────────────────────────

    public List<SiteAward> getPublicAwards() {
        return siteAwardRepository.findAllByOrderByOrderNoAsc();
    }

    // ─────────────────────────────────────────────
    // Company Values (Giá trị cốt lõi)
    // ─────────────────────────────────────────────

    public List<CompanyValue> getPublicCompanyValues() {
        return companyValueRepository.findAllByOrderByOrderNoAsc();
    }
}
