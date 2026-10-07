package com.hdlogistics.cms.service;

import com.hdlogistics.cms.dto.request.JobPostingRequest;
import com.hdlogistics.cms.entity.JobPosting;
import com.hdlogistics.cms.repository.JobPostingRepository;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobPostingAdminService {

    private final JobPostingRepository jobPostingRepository;

    @Transactional(readOnly = true)
    public Page<JobPosting> getAll(Pageable pageable) {
        return jobPostingRepository.findAll(pageable);
    }

    @Transactional
    public JobPosting create(JobPostingRequest request) {
        JobPosting job = JobPosting.builder()
                .title(request.getTitle())
                .department(request.getDepartment())
                .location(request.getLocation())
                .jobType(request.getJobType() != null ? request.getJobType() : "FULL_TIME")
                .salary(request.getSalary())
                .deadline(request.getDeadline())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .benefits(request.getBenefits())
                .status(request.getStatus() != null ? request.getStatus() : "OPEN")
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .build();
        return jobPostingRepository.save(job);
    }

    @Transactional
    public JobPosting update(UUID id, JobPostingRequest request) {
        JobPosting job = jobPostingRepository.findById(id)
                .orElseThrow(() -> new BaseException("Vị trí tuyển dụng không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        job.setTitle(request.getTitle());
        job.setDepartment(request.getDepartment());
        job.setLocation(request.getLocation());
        if (request.getJobType() != null) job.setJobType(request.getJobType());
        job.setSalary(request.getSalary());
        job.setDeadline(request.getDeadline());
        job.setDescription(request.getDescription());
        job.setRequirements(request.getRequirements());
        job.setBenefits(request.getBenefits());
        if (request.getStatus() != null) job.setStatus(request.getStatus());
        if (request.getOrderNo() != null) job.setOrderNo(request.getOrderNo());

        return jobPostingRepository.save(job);
    }

    @Transactional
    public void delete(UUID id) {
        JobPosting job = jobPostingRepository.findById(id)
                .orElseThrow(() -> new BaseException("Vị trí tuyển dụng không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        // Soft delete — đặt status = CLOSED
        job.setStatus("CLOSED");
        jobPostingRepository.save(job);
    }
}
