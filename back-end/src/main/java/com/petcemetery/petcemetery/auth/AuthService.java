package com.petcemetery.petcemetery.auth;

import java.util.Collections;

import com.petcemetery.petcemetery.usuario.admin.AdminService;
import com.petcemetery.petcemetery.usuario.cliente.ClienteService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.core.config.JwtService;
import com.petcemetery.petcemetery.auth.dto.AuthResponseDTO;
import com.petcemetery.petcemetery.auth.dto.CadastroRequestDTO;
import com.petcemetery.petcemetery.auth.dto.LoginRequestDTO;
import com.petcemetery.petcemetery.usuario.cliente.Cliente;
import com.petcemetery.petcemetery.usuario.Role;
import com.petcemetery.petcemetery.usuario.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ClienteService clienteService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AdminService adminService;

    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getSenha())
        );

        Usuario usuario = clienteService.findByEmail(loginRequest.getEmail());
        if (usuario == null) {
            usuario = adminService.findByEmail(loginRequest.getEmail());
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuário não encontrado com o email: " + loginRequest.getEmail());
            }
        }

        String jwtToken = jwtService.createToken(usuario.getEmail(), usuario.getId(), usuario.getAuthorities());
        return new AuthResponseDTO(jwtToken);
    }

    public AuthResponseDTO cadastro(CadastroRequestDTO cadastroRequest) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        Cliente cliente = Cliente.builder()
                .email(cadastroRequest.getEmail())
                .telefone(cadastroRequest.getTelefone())
                .nome(cadastroRequest.getNome())
                .cpf(cadastroRequest.getCpf())
                .cep(cadastroRequest.getCep())
                .rua(cadastroRequest.getRua())
                .numero(cadastroRequest.getNumero())
                .complemento(cadastroRequest.getComplemento())
                .senha(encoder.encode(cadastroRequest.getSenha()))
                .desativado(false)
                .inadimplente(false)
                .pagamentos(Collections.emptyList())
                .role(Role.CLIENTE)
                .build();

        var novoCliente = clienteService.save(cliente);
        var jwtToken = jwtService.createToken(novoCliente.getEmail(), novoCliente.getId(), novoCliente.getAuthorities());
        return new AuthResponseDTO(jwtToken);
    }

}
