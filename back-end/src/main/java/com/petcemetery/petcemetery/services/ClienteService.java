package com.petcemetery.petcemetery.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.petcemetery.petcemetery.config.JwtService;
import com.petcemetery.petcemetery.dto.EditarPerfilDTO;
import com.petcemetery.petcemetery.model.Lembrete;
import com.petcemetery.petcemetery.repositorio.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.dto.ClienteDTO;
import com.petcemetery.petcemetery.dto.ClienteInadimplenteDTO;
import com.petcemetery.petcemetery.dto.ClientePerfilDTO;
import com.petcemetery.petcemetery.model.Cliente;
import com.petcemetery.petcemetery.repositorio.ClienteRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final JwtService jwtService;
    private final LembreteRepository lembreteRepository;

    public ClienteService(ClienteRepository repository, JwtService jwtService, LembreteRepository lembreteRepository) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.lembreteRepository = lembreteRepository;
    }

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

    public Cliente findByEmailAndSenha(String email, String senha) {
        return this.repository.findByEmailAndSenha(email, senha);
    }

    public void save(Cliente cliente) {
        this.repository.save(cliente);
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

    public boolean editarPerfil(String cpf, EditarPerfilDTO dto) {
        Cliente cliente = this.repository.findByCpf(cpf);

        // Atualiza os campos preenchidos no DTO
        if (!StringUtils.isBlank(dto.getNome())) {
            cliente.setNome(dto.getNome());
        }
        if (!StringUtils.isBlank(dto.getEmail())) {
            cliente.setEmail(dto.getEmail());
        }
        if (!StringUtils.isBlank(dto.getTelefone())) {
            cliente.setTelefone(dto.getTelefone());
        }
        if (!StringUtils.isBlank(dto.getRua())) {
            cliente.setRua(dto.getRua());
        }
        if (!StringUtils.isBlank(dto.getNumero())) {
            cliente.setNumero(dto.getNumero());
        }
        if (!StringUtils.isBlank(dto.getComplemento())) {
            cliente.setComplemento(dto.getComplemento());
        }
        if (!StringUtils.isBlank(dto.getCep())) {
            cliente.setCep(dto.getCep());
        }
        if (!StringUtils.isBlank(dto.getSenha()) && !StringUtils.isBlank(dto.getSenhaRepetida())) {
            if (!dto.getSenha().equals(dto.getSenhaRepetida())) {
                throw new IllegalArgumentException("As senhas devem ser iguais");
            }
            cliente.setSenha(dto.getSenha());
        }

        this.repository.save(cliente);
        return true;
    }

    public boolean desativarPerfil(String cpf) {
        Cliente cliente = repository.findByCpf(cpf);

        if (cliente.getDesativado()) {
            throw new IllegalArgumentException("Cliente já desativado");
        }

        cliente.setDesativado(true);
        repository.save(cliente);
        return true;
    }

    public ClienteDTO recuperaInformacoesAlteracao(String cpf) {
        Cliente cliente = repository.findByCpf(cpf);

        if (cliente.getDesativado()) {
            throw new IllegalArgumentException("Cliente já desativado");
        }

        return new ClienteDTO(
            cliente.getEmail(),
            cliente.getTelefone(),
            cliente.getNome(),
            cliente.getRua(),
            cliente.getNumero(),
            cliente.getComplemento(),
            cliente.getCep()
        );
    }

    public ClientePerfilDTO recuperaInformacoesPerfil(String cpf) {
        Cliente cliente = repository.findByCpf(cpf);

        if (cliente.getDesativado()) {
            throw new IllegalArgumentException("Cliente já desativado");
        }

        return new ClientePerfilDTO(
            cliente.getNome(),
            cliente.getEmail()
        );
    }

    public Lembrete adicionaLembrete(String token, LocalDate dataLembrete) {
        Long id = Long.valueOf(this.jwtService.extractId(token));
        Cliente cliente = this.findById(id);

        Lembrete lembrete = new Lembrete(dataLembrete, cliente);
        return this.lembreteRepository.save(lembrete);
    }
}