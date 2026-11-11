package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.JobApplicationResponse;
import com.hdtlogistics.cms.entity.JobApplication;
import com.hdtlogistics.cms.entity.JobPosting;
import com.hdtlogistics.cms.repository.JobApplicationRepository;
import com.hdtlogistics.cms.repository.JobPostingRepository;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobApplicationPublicService {

    private final JobApplicationRepository applicationRepository;
    private final JobPostingRepository jobPostingRepository;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".pdf", ".doc", ".docx", ".png", ".jpg", ".jpeg");

    @Transactional
    public JobApplicationResponse apply(
            String fullName,
            String phone,
            String email,
            UUID jobId,
            String jobTitle,
            String coverLetter,
            String fallbackCvUrl,
            MultipartFile cvFile) {

        if (fullName == null || fullName.trim().isEmpty()) {
            throw new BaseException("Họ và tên không được để trống", ErrorCode.VALIDATION_ERROR, 400);
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new BaseException("Số điện thoại không được để trống", ErrorCode.VALIDATION_ERROR, 400);
        }

        String finalCvUrl = fallbackCvUrl;
        String originalFileName = null;

        if (cvFile != null && !cvFile.isEmpty()) {
            if (cvFile.getSize() > MAX_FILE_SIZE) {
                throw new BaseException("File CV không được vượt quá 10MB", ErrorCode.VALIDATION_ERROR, 400);
            }

            originalFileName = cvFile.getOriginalFilename();
            String extension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                extension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
            }

            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new BaseException("Định dạng file không hỗ trợ. Vui lòng tải file PDF, DOC, DOCX hoặc ảnh", ErrorCode.VALIDATION_ERROR, 400);
            }

            try {
                String subDir = "uploads/cv/";
                File dir = new File(subDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String safeName = UUID.randomUUID().toString() + extension;
                Path targetPath = Paths.get(subDir, safeName);
                Files.write(targetPath, cvFile.getBytes());

                finalCvUrl = "/uploads/cv/" + safeName;
            } catch (IOException e) {
                log.error("Failed to save CV file", e);
                throw new BaseException("Lỗi lưu file CV trên hệ thống", ErrorCode.INTERNAL_SERVER_ERROR, 500);
            }
        }

        if (finalCvUrl == null || finalCvUrl.trim().isEmpty()) {
            throw new BaseException("Vui lòng tải file đính kèm CV hoặc cung cấp link CV", ErrorCode.VALIDATION_ERROR, 400);
        }

        JobPosting job = null;
        String resolvedJobTitle = jobTitle;

        if (jobId != null) {
            job = jobPostingRepository.findById(jobId).orElse(null);
            if (job != null && (resolvedJobTitle == null || resolvedJobTitle.trim().isEmpty())) {
                resolvedJobTitle = job.getTitle();
            }
        }

        JobApplication application = JobApplication.builder()
                .job(job)
                .jobTitle(resolvedJobTitle != null ? resolvedJobTitle : "Vị trí ứng tuyển tự do")
                .fullName(fullName.trim())
                .phone(phone.trim())
                .email(email != null ? email.trim() : null)
                .cvUrl(finalCvUrl.trim())
                .cvFileName(originalFileName != null ? originalFileName : "CV_online")
                .coverLetter(coverLetter != null ? coverLetter.trim() : null)
                .status("APPLIED")
                .build();

        JobApplication saved = applicationRepository.save(application);

        return toResponse(saved);
    }

    public static JobApplicationResponse toResponse(JobApplication app) {
        return JobApplicationResponse.builder()
                .id(app.getId())
                .jobId(app.getJob() != null ? app.getJob().getId() : null)
                .jobTitle(app.getJobTitle() != null ? app.getJobTitle() : (app.getJob() != null ? app.getJob().getTitle() : null))
                .department(app.getJob() != null ? app.getJob().getDepartment() : null)
                .fullName(app.getFullName())
                .phone(app.getPhone())
                .email(app.getEmail())
                .cvUrl(app.getCvUrl())
                .cvFileName(app.getCvFileName())
                .coverLetter(app.getCoverLetter())
                .status(app.getStatus())
                .notes(app.getNotes())
                .createdAt(app.getCreatedAt())
                .updatedAt(app.getUpdatedAt())
                .build();
    }
}
