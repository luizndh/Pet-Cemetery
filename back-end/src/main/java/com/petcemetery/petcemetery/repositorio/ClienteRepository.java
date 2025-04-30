package com.petcemetery.petcemetery.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcemetery.petcemetery.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmailAndSenha(String email, String senha);
    Optional<Cliente> findByEmail(String email);
    Cliente findByCpf(String cpf);
    List<Cliente> findByInadimplenteTrue();
}