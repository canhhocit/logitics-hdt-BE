package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.JobApplicationResponse;
import com.hdtlogistics.cms.service.JobApplicationPublicService;
import com.hdtlogistics.common.dto.ApiResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/public/careers")
@RequiredArgsConstructor
public class JobApplicationPublicController {

    private final JobApplicationPublicService applicationService;

    @PostMapping(value = "/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<JobApplicationResponse>> applyMultipart(
            @RequestParam("fullName") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "jobId", required = false) UUID jobId,
            @RequestParam(value = "jobTitle", required = false) String jobTitle,
            @RequestParam(value = "coverLetter", required = false) String coverLetter,
            @RequestParam(value = "cvUrl", required = false) String cvUrl,
            @RequestPart(value = "cvFile", required = false) MultipartFile cvFile) {

        JobApplicationResponse res = applicationService.apply(
                fullName, phone, email, jobId, jobTitle, coverLetter, cvUrl, cvFile);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(res, "Hồ sơ ứng tuyển đã được gửi thành công"));
    }

    @PostMapping(value = "/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<JobApplicationResponse>> applyJson(
            @RequestBody JsonApplyRequest req) {

        JobApplicationResponse res = applicationService.apply(
                req.getFullName(), req.getPhone(), req.getEmail(),
                req.getJobId(), req.getJobTitle(), req.getCoverLetter(),
                req.getCvUrl(), null);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(res, "Hồ sơ ứng tuyển đã được gửi thành công"));
    }

    @Data
    public static class JsonApplyRequest {
        private String fullName;
        private String phone;
        private String email;
        private UUID jobId;
        private String jobTitle;
        private String coverLetter;
        private String cvUrl;
    }
}
