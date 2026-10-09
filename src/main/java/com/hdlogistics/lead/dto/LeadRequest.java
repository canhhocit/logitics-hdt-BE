package com.hdlogistics.lead.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class LeadRequest {
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;
    
    private String email;
    private String company;
    private UUID serviceId;
    private String serviceName;
    private String message;
    private String source;
    private String sourcePage;
}
