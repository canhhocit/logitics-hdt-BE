package com.hdlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Map;

@Data
public class ServiceRequest {
    @NotBlank(message = "Tên dịch vụ không được để trống")
    private String name;
    
    @NotBlank(message = "Slug không được để trống")
    private String slug;
    
    private Integer orderNo;
    private String thumbnail;
    private String shortDesc;
    private String intro;
    private String icon;
    private String color;
    private Map<String, Object> contentBlocks;
    private String seoTitle;
    private String seoDesc;
    private String status;
}
