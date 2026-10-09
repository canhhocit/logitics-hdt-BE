package com.hdtlogistics.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "shipment_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Column(nullable = false)
    private String eventTime;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    private Integer orderNo = 0;
}
