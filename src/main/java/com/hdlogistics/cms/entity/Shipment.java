package com.hdlogistics.cms.entity;

import com.hdlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String trackingCode;

    @Column(nullable = false)
    private String serviceName;

    private String weightInfo;
    private String origin;
    private String destination;
    private String etaDate;

    @Builder.Default
    private String status = "PENDING";
}
