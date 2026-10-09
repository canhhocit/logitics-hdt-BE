package com.hdtlogistics.cms.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "pages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(length = 50)
    @Builder.Default
    private String template = "default";

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "seo_title")
    private String seoTitle;

    @Column(name = "seo_desc", columnDefinition = "TEXT")
    private String seoDesc;

    @Column(length = 20)
    @Builder.Default
    private String status = "DRAFT";
}
