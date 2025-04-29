package com.petcemetery.petcemetery.services;

import com.petcemetery.petcemetery.config.JwtService;
import com.petcemetery.petcemetery.dto.AuthResponseDTO;
import com.petcemetery.petcemetery.dto.CadastroRequestDTO;
import com.petcemetery.petcemetery.dto.LoginRequestDTO;
import com.petcemetery.petcemetery.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.model.Admin;
import com.petcemetery.petcemetery.model.Cliente;

import io.micrometer.common.util.StringUtils;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClienteService clienteService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getSenha())
        );

        var cliente = clienteService.findByEmail(loginRequest.getEmail());
        var jwtToken = jwtService.createToken(cliente.getEmail());
        return new AuthResponseDTO(jwtToken);
    }

    public AuthResponseDTO cadastro(CadastroRequestDTO cadastroRequest) {
        PasswordEncoder enconder = new BCryptPasswordEncoder();

        Cliente cliente = new Cliente(
                cadastroRequest.getEmail(),
                cadastroRequest.getTelefone(),
                cadastroRequest.getNome(),
                cadastroRequest.getCpf(),
                cadastroRequest.getCep(),
                cadastroRequest.getRua(),
                cadastroRequest.getNumero(),
                cadastroRequest.getComplemento(),
                enconder.encode(cadastroRequest.getSenha()),
                Role.CLIENTE
        );

        clienteService.save(cliente);
        var jwtToken = jwtService.createToken(cliente.getEmail());
        return new AuthResponseDTO(jwtToken);
    }

}
