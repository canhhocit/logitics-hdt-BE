package com.hdtlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyValueRequest {

    @NotBlank(message = "Tiêu đề giá trị không được để trống")
    @Size(max = 255)
    private String title;

    private String description;

    /** Tên icon Lucide React: clock, eye, briefcase, heart, zap, shield... */
    @Size(max = 50)
    private String icon;

    private Integer orderNo = 0;
}
