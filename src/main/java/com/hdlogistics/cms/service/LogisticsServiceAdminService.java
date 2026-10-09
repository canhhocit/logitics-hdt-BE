package com.hdlogistics.cms.service;

import com.hdlogistics.cms.dto.request.ServiceRequest;
import com.hdlogistics.cms.entity.LogisticsService;
import com.hdlogistics.cms.repository.LogisticsServiceRepository;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogisticsServiceAdminService {

    private final LogisticsServiceRepository logisticsServiceRepository;

    @Transactional(readOnly = true)
    public Page<LogisticsService> getAll(Pageable pageable) {
        return logisticsServiceRepository.findByStatusNot("DELETED", pageable);
    }

    @Transactional
    public LogisticsService create(ServiceRequest request) {
        if (logisticsServiceRepository.existsBySlug(request.getSlug())) {
            throw new BaseException("Slug đã tồn tại", ErrorCode.SLUG_EXISTS, 409);
        }

        LogisticsService service = LogisticsService.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .thumbnail(request.getThumbnail())
                .intro(request.getIntro())
                .shortDesc(request.getShortDesc())
                .icon(request.getIcon())
                .color(request.getColor())
                .contentBlocks(request.getContentBlocks())
                .seoTitle(request.getSeoTitle())
                .seoDesc(request.getSeoDesc())
                .status(request.getStatus() != null ? request.getStatus() : "DRAFT")
                .build();

        return logisticsServiceRepository.save(service);
    }

    @Transactional
    public LogisticsService update(UUID id, ServiceRequest request) {
        LogisticsService service = logisticsServiceRepository.findById(id)
                .orElseThrow(() -> new BaseException("Dịch vụ không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        if (!service.getSlug().equals(request.getSlug()) && logisticsServiceRepository.existsBySlug(request.getSlug())) {
            throw new BaseException("Slug đã tồn tại", ErrorCode.SLUG_EXISTS, 409);
        }

        service.setName(request.getName());
        service.setSlug(request.getSlug());
        service.setOrderNo(request.getOrderNo() != null ? request.getOrderNo() : 0);
        service.setThumbnail(request.getThumbnail());
        service.setIntro(request.getIntro());
        service.setShortDesc(request.getShortDesc());
        service.setIcon(request.getIcon());
        service.setColor(request.getColor());
        service.setContentBlocks(request.getContentBlocks());
        service.setSeoTitle(request.getSeoTitle());
        service.setSeoDesc(request.getSeoDesc());
        if (request.getStatus() != null) {
            service.setStatus(request.getStatus());
        }

        return logisticsServiceRepository.save(service);
    }

    @Transactional
    public void delete(UUID id) {
        LogisticsService service = logisticsServiceRepository.findById(id)
                .orElseThrow(() -> new BaseException("Dịch vụ không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        
        // Soft delete for service to avoid breaking existing Leads
        service.setStatus("DELETED");
        service.setSlug(service.getSlug() + "-deleted-" + System.currentTimeMillis()); // Free up the slug
        logisticsServiceRepository.save(service);
    }
}
