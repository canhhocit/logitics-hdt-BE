package com.hdlogistics.cms.repository;

import com.hdlogistics.cms.entity.SiteAward;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SiteAwardRepository extends JpaRepository<SiteAward, UUID> {

    /** Lấy tất cả giải thưởng sắp xếp theo orderNo */
    List<SiteAward> findAllByOrderByOrderNoAsc();
}
