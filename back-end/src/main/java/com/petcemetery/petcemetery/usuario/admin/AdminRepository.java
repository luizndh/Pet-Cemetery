package com.petcemetery.petcemetery.usuario.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailAndSenha(String email, String senha);
    Admin findByEmail(String email);

}