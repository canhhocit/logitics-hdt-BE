package com.hdlogistics.lead.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OfficeRequest {

    @NotBlank(message = "Tên chi nhánh không được để trống")
    @Size(max = 150)
    private String name;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 50)
    private String phone;

    @Size(max = 150)
    private String email;

    /** BAC | TRUNG | NAM */
    @NotBlank(message = "Khu vực không được để trống")
    @Size(max = 20)
    private String region;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private Integer orderNo = 0;
}
