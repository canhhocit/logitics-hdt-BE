package com.hdtlogistics.cms.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testimonial extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_type")
    private String customerType;

    @Column(length = 500)
    private String logo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "order_no")
    @Builder.Default
    private Integer orderNo = 0;
}
