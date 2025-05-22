package com.petcemetery.petcemetery.controller;

import com.petcemetery.petcemetery.dto.HorarioFuncionamentoDTO;
import com.petcemetery.petcemetery.services.HorarioFuncionamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/horario-funcionamento")
public class HorarioFuncionamentoController {

    private final HorarioFuncionamentoService horarioFuncionamentoService;

    @GetMapping("")
    public List<HorarioFuncionamentoDTO> getHorarios() {
        return this.horarioFuncionamentoService.getHorarios();
    }

    // Altera o horário de funcionamento do cemitério de acordo com o horário passado no RequestBody. O front deve passar um body no formato:
    // {
    //  "segunda": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "false" }, "terca": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "false" },
    // "quarta": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "false" }, "quinta": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "false" },
    // "sexta": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "true" }, "sabado": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "false" },
    // "domingo": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "true" }, "feriado": {"abertura": "HH:MM", "fechamento": "HH:MM", "fechado": "true" }
    @PutMapping("")
    public void alterarHorarioFuncionamento(@RequestBody List<HorarioFuncionamentoDTO> horarios) {
        this.horarioFuncionamentoService.alterarHorarioFuncionamento(horarios);
    }
}
