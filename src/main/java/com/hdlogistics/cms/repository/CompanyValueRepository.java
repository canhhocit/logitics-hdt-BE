package com.hdlogistics.cms.repository;

import com.hdlogistics.cms.entity.CompanyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CompanyValueRepository extends JpaRepository<CompanyValue, UUID> {

    /** Lấy tất cả giá trị cốt lõi sắp xếp theo orderNo */
    List<CompanyValue> findAllByOrderByOrderNoAsc();
}
