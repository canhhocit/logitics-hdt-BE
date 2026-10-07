package com.hdlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TestimonialRequest {

    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(max = 150)
    private String customerName;

    @Size(max = 150)
    private String customerType;

    @Size(max = 500)
    private String logo;

    @NotBlank(message = "Nội dung đánh giá không được để trống")
    private String content;

    private Integer orderNo = 0;
}
