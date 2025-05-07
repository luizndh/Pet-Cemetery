package com.petcemetery.petcemetery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity(name = "Cliente")
@Table(name = "Cliente")
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Cliente extends Usuario {

    @Column(name = "desativado")
    private Boolean desativado;

    @Column(name = "inadimplente")
    private Boolean inadimplente;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Pagamento> pagamentos;

    public Cliente(String email, String telefone, String nome, String cpf, String senha) {
        super(email, telefone, nome, cpf, senha);
        this.desativado = false;
        this.inadimplente = false;
    }

    public Cliente(String email, String telefone, String nome, String cpf, String cep, String rua, String numero, String complemento, String senha, Role role) {
        super(cpf, email, telefone, nome, cep, rua, numero, complemento, senha, role);
        this.desativado = false;
        this.inadimplente = false;
    }

    public void addPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }
    public void removePagamento(Pagamento pagamento) {
        this.pagamentos.remove(pagamento);
    }

    @Override
    public boolean isEnabled() {
        return !this.desativado;
    }
}
