package com.petcemetery.petcemetery.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}