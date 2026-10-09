package com.hdtlogistics.lead.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import com.hdtlogistics.cms.entity.LogisticsService;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, length = 50)
    private String phone;

    @Column(length = 150)
    private String email;

    private String company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private LogisticsService service;

    @Column(name = "service_name")
    private String serviceName;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(length = 50)
    @Builder.Default
    private String status = "NEW";

    @Column(length = 100)
    private String source;

    @Column(name = "source_page", length = 255)
    private String sourcePage;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;
}
