package com.petcemetery.petcemetery.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.petcemetery.petcemetery.config.JwtService;
import com.petcemetery.petcemetery.dto.*;
import com.petcemetery.petcemetery.model.Lembrete;
import com.petcemetery.petcemetery.repositorio.LembreteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.model.Cliente;
import com.petcemetery.petcemetery.repositorio.ClienteRepository;

import io.micrometer.common.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final JwtService jwtService;
    private final LembreteRepository lembreteRepository;
    private final PasswordEncoder passwordEncoder;


    public List<ClienteInadimplenteDTO> findInadimplentes() {
        List<Cliente> clientesInadimplentes = repository.findByInadimplenteTrue();

        return clientesInadimplentes.stream().map(cliente -> new ClienteInadimplenteDTO(
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getNome(),
                cliente.getDesativado(),
                cliente.getInadimplente()
        )).collect(Collectors.toList());
    }

    public Cliente save(Cliente cliente) {
        return this.repository.save(cliente);
    }

    public Cliente findByEmail(String email) {
        return this.repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Cliente findById(Long id) {
        Optional<Cliente> c = this.repository.findById(id);

        if (c.isPresent()) {
            return c.get();
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public Cliente editarPerfil(String token, EditarPerfilDTO dto) {
        Long id = this.jwtService.extractId(token);
        Cliente cliente = repository.findById(id).get();

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setRua(dto.getRua());
        cliente.setNumero(dto.getNumero());
        cliente.setComplemento(dto.getComplemento());
        cliente.setCep(dto.getCep());

        return this.repository.save(cliente);
    }

    public boolean desativarPerfil(String token) {
        Long id = this.jwtService.extractId(token);
        Cliente cliente = repository.findById(id).get();

        cliente.setDesativado(true);
        repository.save(cliente);
        return true;
    }

    public ClienteDTO recuperaInformacoesAlteracao(String token) {
        Long id = this.jwtService.extractId(token);
        Cliente cliente = repository.findById(id).get();

        return ClienteDTO.builder()
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .nome(cliente.getNome())
                .rua(cliente.getRua())
                .numero(cliente.getNumero())
                .complemento(cliente.getComplemento())
                .cep(cliente.getCep())
                .cpf(cliente.getCpf())
                .build();
    }

    public ClientePerfilDTO recuperaInformacoesPerfil(String token) {
        Long id = this.jwtService.extractId(token);
        Cliente cliente = repository.findById(id).get();

        return ClientePerfilDTO.builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

    public Lembrete adicionaLembrete(String token, LocalDate dataLembrete) {
        Long id = this.jwtService.extractId(token);
        Cliente cliente = this.findById(id);

        Lembrete lembrete = new Lembrete(dataLembrete, cliente);
        return this.lembreteRepository.save(lembrete);
    }

    public void trocarSenha(String token, TrocarSenhaDTO dto) {
        Long id = this.jwtService.extractId(token);
        Cliente cliente = repository.findById(id).get();

        if (passwordEncoder.matches(dto.getSenhaAtual(), cliente.getSenha())) {
            cliente.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
            repository.save(cliente);
        } else {
            throw new IllegalArgumentException("Senha atual incorreta");
        }
    }

    public List<Cliente> findAll() {
        return this.repository.findAll();
    }
}