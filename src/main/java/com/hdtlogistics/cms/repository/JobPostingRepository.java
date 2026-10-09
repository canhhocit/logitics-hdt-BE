package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JobPostingRepository extends JpaRepository<JobPosting, UUID> {

    /** Lấy danh sách vị trí đang mở, sắp xếp theo orderNo */
    List<JobPosting> findByStatusOrderByOrderNoAsc(String status);

    /** Danh sách có phân trang dành cho admin */
    Page<JobPosting> findByStatus(String status, Pageable pageable);

    /** Đếm theo trạng thái */
    long countByStatus(String status);
}
