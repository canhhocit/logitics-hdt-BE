package com.hdtlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class PostRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    
    @NotBlank(message = "Slug không được để trống")
    private String slug;
    
    private String excerpt;
    private String content;
    private String thumbnail;
    private UUID categoryId;
    private Integer readMinutes;
    private String seoTitle;
    private String seoDesc;
    private String status;
}
