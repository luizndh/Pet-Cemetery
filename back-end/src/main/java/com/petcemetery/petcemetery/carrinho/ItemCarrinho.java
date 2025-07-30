package com.petcemetery.petcemetery.carrinho;

import com.petcemetery.petcemetery.usuario.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;
import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_carrinho")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemCarrinho {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "id_jazigo")
    private Long idJazigo;

    @Column(name = "endereco_jazigo")
    private String enderecoJazigo;

    @Column(name = "tipo_servico")
    private ServicoEnum tipoServico;

    @Column(name = "valor_servico")
    private BigDecimal valorServico;

    @Column(name = "plano_selecionado")
    private ServicoEnum planoSelecionado;

    @Column(name = "valor_plano")
    private BigDecimal valorPlano;
}
