package com.hdtlogistics.lead.service;

import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import com.hdtlogistics.lead.dto.OfficeRequest;
import com.hdtlogistics.lead.entity.Office;
import com.hdtlogistics.lead.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfficeAdminService {

    private final OfficeRepository officeRepository;

    @Transactional(readOnly = true)
    public Page<Office> getAll(Pageable pageable) {
        return officeRepository.findAll(pageable);
    }

    @Transactional
    public Office create(OfficeRequest request) {
        Office office = Office.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .region(request.getRegion())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .build();
        return officeRepository.save(office);
    }

    @Transactional
    public Office update(UUID id, OfficeRequest request) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new BaseException("Chi nhánh không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        office.setName(request.getName());
        office.setAddress(request.getAddress());
        office.setPhone(request.getPhone());
        office.setEmail(request.getEmail());
        office.setRegion(request.getRegion());
        office.setLatitude(request.getLatitude());
        office.setLongitude(request.getLongitude());
        if (request.getOrderNo() != null) office.setOrderNo(request.getOrderNo());

        return officeRepository.save(office);
    }

    @Transactional
    public void delete(UUID id) {
        if (!officeRepository.existsById(id)) {
            throw new BaseException("Chi nhánh không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404);
        }
        officeRepository.deleteById(id);
    }
}
