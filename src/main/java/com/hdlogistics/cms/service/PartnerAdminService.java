package com.hdlogistics.cms.service;

import com.hdlogistics.cms.dto.request.PartnerRequest;
import com.hdlogistics.cms.entity.Partner;
import com.hdlogistics.cms.repository.PartnerRepository;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerAdminService {

    private final PartnerRepository partnerRepository;

    @Transactional(readOnly = true)
    public List<Partner> getAll() {
        return partnerRepository.findAllByOrderByOrderNoAsc();
    }

    @Transactional
    public Partner create(PartnerRequest request) {
        Partner p = Partner.builder()
                .type(request.getType())
                .name(request.getName())
                .logo(request.getLogo())
                .url(request.getUrl())
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .build();
        return partnerRepository.save(p);
    }

    @Transactional
    public Partner update(UUID id, PartnerRequest request) {
        Partner p = partnerRepository.findById(id)
                .orElseThrow(() -> new BaseException("Đối tác không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        p.setType(request.getType());
        p.setName(request.getName());
        p.setLogo(request.getLogo());
        p.setUrl(request.getUrl());
        if (request.getOrderNo() != null) p.setOrderNo(request.getOrderNo());
        return partnerRepository.save(p);
    }

    @Transactional
    public void delete(UUID id) {
        if (!partnerRepository.existsById(id))
            throw new BaseException("Đối tác không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404);
        partnerRepository.deleteById(id);
    }
}
