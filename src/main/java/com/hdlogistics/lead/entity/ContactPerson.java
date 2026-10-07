package com.hdlogistics.lead.entity;

import com.hdlogistics.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "contact_persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactPerson extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(length = 150)
    private String title;

    @Column(length = 150)
    private String email;

    @Column(length = 50)
    private String phone;

    @Column(length = 10)
    private String initials;

    @Column(length = 100)
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;
}
