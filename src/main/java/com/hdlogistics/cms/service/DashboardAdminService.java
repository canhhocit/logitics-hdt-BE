package com.hdlogistics.cms.service;

import com.hdlogistics.cms.dto.DashboardStatsResponse;
import com.hdlogistics.cms.repository.LogisticsServiceRepository;
import com.hdlogistics.lead.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardAdminService {

    private final LeadRepository leadRepository;
    private final LogisticsServiceRepository logisticsServiceRepository;

    public DashboardStatsResponse getStats() {
        // Count new leads
        long newLeads = leadRepository.countByStatus("NEW");
        
        // Count active services
        long activeServices = logisticsServiceRepository.countByStatus("PUBLISHED");

        return DashboardStatsResponse.builder()
                .newLeads(newLeads)
                .activeServices(activeServices)
                .totalVisits(1250) // Mock logic for now
                .build();
    }
}
