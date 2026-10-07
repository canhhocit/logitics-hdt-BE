package com.hdlogistics.cms.service;

import com.hdlogistics.cms.dto.request.CompanyValueRequest;
import com.hdlogistics.cms.entity.CompanyValue;
import com.hdlogistics.cms.repository.CompanyValueRepository;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyValueAdminService {

    private final CompanyValueRepository companyValueRepository;

    @Transactional(readOnly = true)
    public List<CompanyValue> getAll() {
        return companyValueRepository.findAllByOrderByOrderNoAsc();
    }

    @Transactional
    public CompanyValue create(CompanyValueRequest request) {
        CompanyValue value = CompanyValue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .icon(request.getIcon())
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .build();
        return companyValueRepository.save(value);
    }

    @Transactional
    public CompanyValue update(UUID id, CompanyValueRequest request) {
        CompanyValue value = companyValueRepository.findById(id)
                .orElseThrow(() -> new BaseException("Giá trị cốt lõi không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        value.setTitle(request.getTitle());
        value.setDescription(request.getDescription());
        value.setIcon(request.getIcon());
        if (request.getOrderNo() != null) value.setOrderNo(request.getOrderNo());

        return companyValueRepository.save(value);
    }

    @Transactional
    public void delete(UUID id) {
        if (!companyValueRepository.existsById(id)) {
            throw new BaseException("Giá trị cốt lõi không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404);
        }
        companyValueRepository.deleteById(id);
    }
}
