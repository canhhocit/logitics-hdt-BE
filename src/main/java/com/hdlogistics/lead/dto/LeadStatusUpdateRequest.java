package com.hdlogistics.lead.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeadStatusUpdateRequest {
    @NotBlank(message = "Trạng thái không được để trống")
    private String status;
}
