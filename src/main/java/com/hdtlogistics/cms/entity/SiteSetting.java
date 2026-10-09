package com.hdtlogistics.cms.entity;

import com.hdtlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteSetting extends BaseEntity {
    @Id
    @Column(length = 100)
    private String key;

    @Column(columnDefinition = "TEXT")
    private String value;

    @Column(columnDefinition = "TEXT")
    private String description;
}
