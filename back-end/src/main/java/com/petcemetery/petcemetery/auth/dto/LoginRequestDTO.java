package com.petcemetery.petcemetery.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}


