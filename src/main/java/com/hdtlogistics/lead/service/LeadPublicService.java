package com.hdtlogistics.lead.service;

import com.hdtlogistics.lead.dto.LeadRequest;
import com.hdtlogistics.lead.entity.Lead;
import com.hdtlogistics.lead.repository.LeadRepository;
import com.hdtlogistics.cms.repository.LogisticsServiceRepository;
import com.hdtlogistics.cms.entity.LogisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LeadPublicService {

    private final LeadRepository leadRepository;
    private final LogisticsServiceRepository logisticsServiceRepository;

    @Transactional
    public Lead submitLead(LeadRequest request, String ipAddress) {
        Lead lead = Lead.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .company(request.getCompany())
                .message(request.getMessage())
                .source(request.getSource() != null ? request.getSource() : "WEBSITE")
                .sourcePage(request.getSourcePage())
                .ipAddress(ipAddress)
                .status("NEW")
                .serviceName(request.getServiceName())
                .build();

        if (request.getServiceId() != null) {
            logisticsServiceRepository.findById(request.getServiceId()).ifPresent(service -> {
                lead.setService(service);
                lead.setServiceName(service.getName());
            });
        }

        return leadRepository.save(lead);
    }
}
