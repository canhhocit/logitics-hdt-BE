package com.hdtlogistics.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationResponse {
    private UUID id;
    private UUID jobId;
    private String jobTitle;
    private String department;
    private String fullName;
    private String phone;
    private String email;
    private String cvUrl;
    private String cvFileName;
    private String coverLetter;
    private String status;
    private String notes;
    private Instant createdAt;
    private Instant updatedAt;
}
