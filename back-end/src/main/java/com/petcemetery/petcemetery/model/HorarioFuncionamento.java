package com.petcemetery.petcemetery.model;

import java.time.LocalTime;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "horario_funcionamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioFuncionamento {

    @Id
    private Long id;
    private String diaSemana;
    private LocalTime abertura;
    private LocalTime fechamento;
    private boolean fechado;
}
