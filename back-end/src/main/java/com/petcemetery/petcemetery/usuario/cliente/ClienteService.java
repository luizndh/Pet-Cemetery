package com.petcemetery.petcemetery.usuario.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.petcemetery.petcemetery.core.constants.ValidationConstants;
import com.petcemetery.petcemetery.core.exceptions.InvalidPasswordException;
import com.petcemetery.petcemetery.core.exceptions.ResourceNotFoundException;
import com.petcemetery.petcemetery.lembrete.Lembrete;
import com.petcemetery.petcemetery.lembrete.LembreteRepository;
import com.petcemetery.petcemetery.usuario.cliente.dto.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final LembreteRepository lembreteRepository;
    private final PasswordEncoder passwordEncoder;

    public List<ClienteInadimplenteDTO> findInadimplentes() {
        log.debug("Buscando clientes inadimplentes");
        List<Cliente> clientesInadimplentes = repository.findByInadimplenteTrue();

        return clientesInadimplentes.stream()
                .map(cliente -> new ClienteInadimplenteDTO(
                        cliente.getEmail(),
                        cliente.getTelefone(),
                        cliente.getNome(),
                        cliente.getDesativado(),
                        cliente.getInadimplente()))
                .collect(Collectors.toList());
    }

    public Cliente save(Cliente cliente) {
        log.info("Salvando cliente: {}", cliente.getEmail());
        return repository.save(cliente);
    }

    public Cliente findByEmail(String email) {
        log.debug("Buscando cliente por email: {}", email);
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "email", email));
    }

    public Cliente findById(Long id) {
        log.debug("Buscando cliente com ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    public Cliente editarPerfil(Long id, EditarPerfilDTO dto) {
        log.info("Editando perfil do cliente ID: {}", id);
        Cliente cliente = findById(id);

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setRua(dto.getRua());
        cliente.setNumero(dto.getNumero());
        cliente.setComplemento(dto.getComplemento());
        cliente.setCep(dto.getCep());

        return repository.save(cliente);
    }

    public boolean desativarPerfil(Long id) {
        log.info("Desativando perfil do cliente ID: {}", id);
        Cliente cliente = findById(id);

        cliente.setDesativado(true);
        repository.save(cliente);
        return true;
    }

    public ClienteDTO recuperaInformacoesAlteracao(Long id) {
        log.debug("Recuperando informações para alteração do cliente ID: {}", id);
        Cliente cliente = findById(id);

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
        log.debug("Recuperando perfil do cliente ID: {}", id);
        Cliente cliente = findById(id);

        return ClientePerfilDTO.builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

    public Lembrete adicionaLembrete(Long id, LocalDate dataLembrete) {
        log.info("Adicionando lembrete para cliente ID: {} na data: {}", id, dataLembrete);
        Cliente cliente = findById(id);

        Lembrete lembrete = new Lembrete(dataLembrete, cliente);
        return lembreteRepository.save(lembrete);
    }

    public void trocarSenha(Long id, TrocarSenhaDTO dto) {
        log.info("Tentativa de troca de senha para cliente ID: {}", id);
        Cliente cliente = findById(id);

        if (!passwordEncoder.matches(dto.getSenhaAtual(), cliente.getSenha())) {
            log.warn("Senha atual incorreta para cliente ID: {}", id);
            throw new InvalidPasswordException(ValidationConstants.ERRO_SENHA_ATUAL_INCORRETA);
        }

        cliente.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        repository.save(cliente);
        log.info("Senha alterada com sucesso para cliente ID: {}", id);
    }

    public List<Cliente> findAll() {
        log.debug("Buscando todos os clientes");
        return repository.findAll();
    }
}