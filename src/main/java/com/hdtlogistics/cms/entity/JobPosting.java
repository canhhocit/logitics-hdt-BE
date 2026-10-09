package com.hdtlogistics.cms.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "job_postings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false)
    private String location;

    @Column(name = "job_type", length = 50)
    @Builder.Default
    private String jobType = "FULL_TIME";

    @Column(length = 100)
    private String salary;

    @Column
    private LocalDate deadline;

    @Column(columnDefinition = "TEXT")
    private String description;

    /** Lưu JSON array dạng string, ví dụ: ["Yêu cầu 1","Yêu cầu 2"] */
    @Column(columnDefinition = "TEXT")
    private String requirements;

    /** Lưu JSON array dạng string, ví dụ: ["Phúc lợi 1","Phúc lợi 2"] */
    @Column(columnDefinition = "TEXT")
    private String benefits;

    @Column(length = 20)
    @Builder.Default
    private String status = "OPEN";

    @Column(name = "order_no")
    @Builder.Default
    private Integer orderNo = 0;
}
