package com.petcemetery.petcemetery.usuario.admin;

import com.petcemetery.petcemetery.usuario.Role;
import com.petcemetery.petcemetery.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "Administrador")
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Admin extends Usuario {

    public Admin(String email, String telefone, String nome, String cpf, String senha) {
        super(email, telefone, nome, cpf, senha);
    }

    public Admin(String email, String telefone, String nome, String cpf, String cep,  String rua, String numero,
        String complemento, String senha, Role role) {

        super(email, telefone, nome, cpf, cep, rua, numero, complemento, senha, role);
    }
}
