package com.hdtlogistics.cms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SiteAwardRequest {

    @NotBlank(message = "Tên giải thưởng không được để trống")
    @Size(max = 255)
    private String title;

    @Size(max = 10)
    private String year;

    /** Tên icon Lucide React: award, shield-check, users, handshake... */
    @Size(max = 50)
    private String icon;

    private Integer orderNo = 0;
}
