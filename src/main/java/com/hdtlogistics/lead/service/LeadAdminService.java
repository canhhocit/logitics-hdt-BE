package com.hdtlogistics.lead.service;

import com.hdtlogistics.lead.dto.LeadStatusUpdateRequest;
import com.hdtlogistics.lead.entity.Lead;
import com.hdtlogistics.lead.repository.LeadRepository;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadAdminService {

    private final LeadRepository leadRepository;

    @Transactional(readOnly = true)
    public Page<Lead> getAllLeads(Pageable pageable, String status) {
        if (status != null && !status.isBlank()) {
            return leadRepository.findByStatus(status.toUpperCase(), pageable);
        }
        return leadRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Lead getById(UUID id) {
        return leadRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy Lead", ErrorCode.RESOURCE_NOT_FOUND, 404));
    }

    @Transactional
    public Lead updateStatus(UUID id, LeadStatusUpdateRequest request) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy Lead", ErrorCode.RESOURCE_NOT_FOUND, 404));

        String newStatus = request.getStatus().trim().toUpperCase();

        // Cập nhật trạng thái CRM linh hoạt cho kinh doanh (NEW, QUOTING, CLOSED_WON, CLOSED_LOST)
        lead.setStatus(newStatus);
        return leadRepository.save(lead);
    }
}
