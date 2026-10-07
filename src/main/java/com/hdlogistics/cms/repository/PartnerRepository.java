package com.hdlogistics.cms.repository;

import com.hdlogistics.cms.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, UUID> {
    List<Partner> findByTypeOrderByOrderNoAsc(String type);
    List<Partner> findAllByOrderByOrderNoAsc();
}
