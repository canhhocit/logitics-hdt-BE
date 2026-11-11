package com.hdtlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobApplicationStatusRequest {
    @NotBlank(message = "Trạng thái không được để trống")
    private String status;

    private String notes;
}
