package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.JobApplicationResponse;
import com.hdtlogistics.cms.dto.request.JobApplicationStatusRequest;
import com.hdtlogistics.cms.entity.JobApplication;
import com.hdtlogistics.cms.repository.JobApplicationRepository;
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
public class JobApplicationAdminService {

    private final JobApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public Page<JobApplicationResponse> getAll(Pageable pageable, String status, UUID jobId, String keyword) {
        String queryStatus = (status != null && !status.trim().isEmpty()) ? status.trim().toUpperCase() : null;
        String queryKeyword = (keyword != null && !keyword.trim().isEmpty()) ? keyword.trim() : null;

        Page<JobApplication> page = applicationRepository.searchApplications(queryStatus, jobId, queryKeyword, pageable);
        return page.map(JobApplicationPublicService::toResponse);
    }

    @Transactional(readOnly = true)
    public JobApplicationResponse getById(UUID id) {
        JobApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy hồ sơ ứng tuyển", ErrorCode.RESOURCE_NOT_FOUND, 404));
        return JobApplicationPublicService.toResponse(application);
    }

    @Transactional
    public JobApplicationResponse updateStatus(UUID id, JobApplicationStatusRequest request) {
        JobApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy hồ sơ ứng tuyển", ErrorCode.RESOURCE_NOT_FOUND, 404));

        application.setStatus(request.getStatus().trim().toUpperCase());
        if (request.getNotes() != null) {
            application.setNotes(request.getNotes().trim());
        }

        JobApplication saved = applicationRepository.save(application);
        return JobApplicationPublicService.toResponse(saved);
    }

    @Transactional
    public void delete(UUID id) {
        JobApplication application = applicationRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy hồ sơ ứng tuyển", ErrorCode.RESOURCE_NOT_FOUND, 404));
        applicationRepository.delete(application);
    }
}
