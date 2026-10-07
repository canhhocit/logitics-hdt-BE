package com.hdlogistics.cms.entity;

import com.hdlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String logo;

    @Column(length = 500)
    private String url;

    @Column(name = "order_no")
    @Builder.Default
    private Integer orderNo = 0;
}
