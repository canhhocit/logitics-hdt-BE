package com.hdlogistics.cms.entity;

import com.hdlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "logistics_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogisticsService extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "order_no")
    @Builder.Default
    private Integer orderNo = 0;

    @Column(length = 500)
    private String thumbnail;

    @Column(columnDefinition = "TEXT")
    private String intro;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content_blocks", columnDefinition = "jsonb")
    private Map<String, Object> contentBlocks;

    @Column(name = "short_desc", columnDefinition = "TEXT")
    private String shortDesc;

    /** Tên icon Lucide React, vd: plane, ship, boxes, truck, file-text, star */
    @Column(length = 50)
    private String icon;

    /** Tên màu Tailwind, vd: blue, cyan, teal, green, orange, rose */
    @Column(length = 30)
    private String color;

    @Column(name = "seo_title")
    private String seoTitle;

    @Column(name = "seo_desc", columnDefinition = "TEXT")
    private String seoDesc;

    @Column(length = 20)
    @Builder.Default
    private String status = "DRAFT";
}
