package com.hdtlogistics.lead.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class ContactPersonRequest {

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 120)
    private String fullName;

    @Size(max = 150)
    private String title;

    @Email(message = "Email không đúng định dạng")
    @Size(max = 150)
    private String email;

    @Size(max = 50)
    private String phone;

    @Size(max = 10)
    private String initials;

    @Size(max = 100)
    private String region;

    private UUID officeId;
}
