
package com.petcemetery.petcemetery.jazigo;

import java.util.ArrayList;
import java.util.List;

import com.petcemetery.petcemetery.pet.Pet;
import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

import com.petcemetery.petcemetery.usuario.cliente.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Jazigo")
@Getter
@Setter
public class Jazigo {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "endereco")
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "id_proprietario", referencedColumnName = "id")
    private Cliente proprietario;

    @OneToMany(fetch=FetchType.EAGER)
    @Column(name = "historico_pets")
    private List<Pet> historicoPets;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "foto")
    private String foto;

    @Column(name = "plano")
    @Enumerated(EnumType.STRING)
    private ServicoEnum plano;

    @OneToOne
    @JoinColumn(name = "id_pet_enterrado", referencedColumnName = "id")
    private Pet petEnterrado;

    public enum StatusEnum {
        DISPONIVEL,
        OCUPADO
    }

    public Jazigo() {
    }

    public Jazigo(String endereco, Cliente proprietario, Long id, StatusEnum status, ServicoEnum plano) {
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.id = id;
        this.status = status;
        this.plano = plano;
        this.historicoPets = new ArrayList<>();
    }

    public Jazigo(String endereco, Cliente proprietario, Long id, StatusEnum status,
                String mensagem, String foto, String notas, ServicoEnum plano, Pet petEnterrado) {
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.id = id;
        this.status = status;

        this.mensagem = mensagem;
        this.foto = foto;
        this.plano = plano;
        this.petEnterrado = petEnterrado;
        this.historicoPets = new ArrayList<>();
    }
}