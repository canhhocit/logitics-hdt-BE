package com.hdlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class JobPostingRequest {

    @NotBlank(message = "Tiêu đề vị trí không được để trống")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Phòng ban không được để trống")
    @Size(max = 100)
    private String department;

    @NotBlank(message = "Địa điểm không được để trống")
    @Size(max = 255)
    private String location;

    /** FULL_TIME | PART_TIME | INTERNSHIP | CONTRACT */
    private String jobType = "FULL_TIME";

    @Size(max = 100)
    private String salary;

    private LocalDate deadline;

    private String description;

    /** JSON array string: ["Yêu cầu 1","Yêu cầu 2"] */
    private String requirements;

    /** JSON array string: ["Phúc lợi 1","Phúc lợi 2"] */
    private String benefits;

    /** OPEN | CLOSED | DRAFT */
    private String status = "OPEN";

    private Integer orderNo = 0;
}
