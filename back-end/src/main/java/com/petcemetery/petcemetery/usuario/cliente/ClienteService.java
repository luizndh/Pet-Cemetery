package com.petcemetery.petcemetery.usuario.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.petcemetery.petcemetery.lembrete.Lembrete;
import com.petcemetery.petcemetery.lembrete.LembreteRepository;

import com.petcemetery.petcemetery.usuario.cliente.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final LembreteRepository lembreteRepository;
    private final PasswordEncoder passwordEncoder;

    public List<ClienteInadimplenteDTO> findInadimplentes() {
        List<Cliente> clientesInadimplentes = repository.findByInadimplenteTrue();

        return clientesInadimplentes.stream().map(cliente -> new ClienteInadimplenteDTO(
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getNome(),
                cliente.getDesativado(),
                cliente.getInadimplente())).collect(Collectors.toList());
    }

    public Cliente save(Cliente cliente) {
        return this.repository.save(cliente);
    }

    public Cliente findByEmail(String email) {
        Optional<Cliente> optCliente = this.repository.findByEmail(email);
        return optCliente.orElse(null);
    }

    public Cliente findById(Long id) {
        Optional<Cliente> c = this.repository.findById(id);

        if (c.isPresent()) {
            return c.get();
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public Cliente editarPerfil(Long id, EditarPerfilDTO dto) {
        Cliente cliente = repository.findById(id).get();

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setRua(dto.getRua());
        cliente.setNumero(dto.getNumero());
        cliente.setComplemento(dto.getComplemento());
        cliente.setCep(dto.getCep());

        return this.repository.save(cliente);
    }

    public boolean desativarPerfil(Long id) {
        Cliente cliente = repository.findById(id).get();

        cliente.setDesativado(true);
        repository.save(cliente);
        return true;
    }

    public ClienteDTO recuperaInformacoesAlteracao(Long id) {
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

    public ClientePerfilDTO recuperaInformacoesPerfil(Long id) {
        Cliente cliente = repository.findById(id).get();

        return ClientePerfilDTO.builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

    public Lembrete adicionaLembrete(Long id, LocalDate dataLembrete) {
        Cliente cliente = this.findById(id);

        Lembrete lembrete = new Lembrete(dataLembrete, cliente);
        return this.lembreteRepository.save(lembrete);
    }

    public void trocarSenha(Long id, TrocarSenhaDTO dto) {
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