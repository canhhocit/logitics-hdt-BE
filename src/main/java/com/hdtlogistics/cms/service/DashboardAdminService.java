package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.DashboardStatsResponse;
import com.hdtlogistics.cms.repository.LogisticsServiceRepository;
import com.hdtlogistics.cms.repository.PostRepository;
import com.hdtlogistics.lead.repository.LeadRepository;
import com.hdtlogistics.lead.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardAdminService {

    private final LeadRepository leadRepository;
    private final LogisticsServiceRepository logisticsServiceRepository;
    private final PostRepository postRepository;
    private final OfficeRepository officeRepository;

    public DashboardStatsResponse getStats() {
        // Count new leads
        long newLeads = leadRepository.countByStatus("NEW");
        
        // Count active services
        long activeServices = logisticsServiceRepository.countByStatus("PUBLISHED");

        // Count posts & offices
        long totalPosts = postRepository.count();
        long totalOffices = officeRepository.count();

        return DashboardStatsResponse.builder()
                .newLeads(newLeads)
                .activeServices(activeServices)
                .totalPosts(totalPosts)
                .totalOffices(totalOffices)
                .totalVisits(1250) // Mock logic for now
                .build();
    }
}
