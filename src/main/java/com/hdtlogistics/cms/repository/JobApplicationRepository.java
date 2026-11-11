package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {

    @Query("SELECT j FROM JobApplication j WHERE " +
           "(:status IS NULL OR j.status = :status) AND " +
           "(:jobId IS NULL OR j.job.id = :jobId) AND " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "LOWER(j.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.phone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<JobApplication> searchApplications(
            @Param("status") String status,
            @Param("jobId") UUID jobId,
            @Param("keyword") String keyword,
            Pageable pageable);

    long countByStatus(String status);
}
