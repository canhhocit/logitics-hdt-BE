package com.hdlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PartnerRequest {

    /** CARRIER | ASSOCIATION */
    @NotBlank(message = "Loại đối tác không được để trống")
    @Size(max = 50)
    private String type;

    @NotBlank(message = "Tên đối tác không được để trống")
    @Size(max = 150)
    private String name;

    @NotBlank(message = "Đường dẫn logo không được để trống")
    @Size(max = 500)
    private String logo;

    @Size(max = 500)
    private String url;

    private Integer orderNo = 0;
}
