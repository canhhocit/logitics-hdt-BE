package com.hdtlogistics.cms.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobPosting job;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String phone;

    @Column(length = 150)
    private String email;

    @Column(name = "cv_url", nullable = false, columnDefinition = "TEXT")
    private String cvUrl;

    @Column(name = "cv_file_name")
    private String cvFileName;

    @Column(name = "cover_letter", columnDefinition = "TEXT")
    private String coverLetter;

    @Column(length = 50, nullable = false)
    @Builder.Default
    private String status = "APPLIED";

    @Column(columnDefinition = "TEXT")
    private String notes;
}
