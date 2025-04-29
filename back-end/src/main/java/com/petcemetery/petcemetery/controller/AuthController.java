package com.petcemetery.petcemetery.controller;

import com.petcemetery.petcemetery.dto.AuthResponseDTO;
import com.petcemetery.petcemetery.dto.CadastroRequestDTO;
import com.petcemetery.petcemetery.dto.LoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petcemetery.petcemetery.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<AuthResponseDTO> cadastro(@Valid @RequestBody CadastroRequestDTO cadastroRequest) {
        return ResponseEntity.ok(authService.cadastro(cadastroRequest));
    }
}