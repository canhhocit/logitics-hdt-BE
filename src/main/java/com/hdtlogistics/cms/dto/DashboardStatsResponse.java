package com.hdtlogistics.cms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardStatsResponse {
    private long newLeads;
    private long activeServices;
    private long totalPosts;
    private long totalOffices;
    private long totalVisits;
    private long newApplications;
    private long totalApplications;
}
