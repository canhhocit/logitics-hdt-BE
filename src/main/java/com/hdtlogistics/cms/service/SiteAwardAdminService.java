package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.request.SiteAwardRequest;
import com.hdtlogistics.cms.entity.SiteAward;
import com.hdtlogistics.cms.repository.SiteAwardRepository;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteAwardAdminService {

    private final SiteAwardRepository siteAwardRepository;

    @Transactional(readOnly = true)
    public List<SiteAward> getAll() {
        return siteAwardRepository.findAllByOrderByOrderNoAsc();
    }

    @Transactional
    public SiteAward create(SiteAwardRequest request) {
        SiteAward award = SiteAward.builder()
                .title(request.getTitle())
                .year(request.getYear())
                .icon(request.getIcon())
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .build();
        return siteAwardRepository.save(award);
    }

    @Transactional
    public SiteAward update(UUID id, SiteAwardRequest request) {
        SiteAward award = siteAwardRepository.findById(id)
                .orElseThrow(() -> new BaseException("Giải thưởng không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        award.setTitle(request.getTitle());
        award.setYear(request.getYear());
        award.setIcon(request.getIcon());
        if (request.getOrderNo() != null) award.setOrderNo(request.getOrderNo());

        return siteAwardRepository.save(award);
    }

    @Transactional
    public void delete(UUID id) {
        if (!siteAwardRepository.existsById(id)) {
            throw new BaseException("Giải thưởng không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404);
        }
        siteAwardRepository.deleteById(id);
    }
}
