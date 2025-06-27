package com.petcemetery.petcemetery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "horario_funcionamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HorarioFuncionamento {

    @Id
    private Long id;
    private String diaSemana;
    private String abertura;
    private String fechamento;
    private boolean fechado;
}
