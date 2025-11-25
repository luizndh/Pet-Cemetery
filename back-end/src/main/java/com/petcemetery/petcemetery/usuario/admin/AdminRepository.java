package com.petcemetery.petcemetery.usuario.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailAndSenha(String email, String senha);

    Optional<Admin> findByEmail(String email);
}