package com.hdlogistics.lead.repository;

import com.hdlogistics.lead.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OfficeRepository extends JpaRepository<Office, UUID> {

    /** Lấy toàn bộ chi nhánh sắp xếp theo orderNo */
    List<Office> findAllByOrderByOrderNoAsc();

    /** Lọc theo khu vực: BAC, TRUNG, NAM */
    List<Office> findByRegionOrderByOrderNoAsc(String region);
}
