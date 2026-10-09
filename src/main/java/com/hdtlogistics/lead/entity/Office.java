package com.hdtlogistics.lead.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "offices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Office extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false, length = 50)
    private String phone;

    @Column(length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String region;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "order_no")
    @Builder.Default
    private Integer orderNo = 0;
}
