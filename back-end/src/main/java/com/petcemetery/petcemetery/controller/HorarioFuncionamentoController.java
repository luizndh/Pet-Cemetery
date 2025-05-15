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
    public void alterarHorarioFuncionamento(@RequestBody Map<String, Map<String, Object>> body) {
        List<HorarioFuncionamentoDTO> horarios = new ArrayList<>();

        for (Map.Entry<String, Map<String, Object>> entry : body.entrySet()) {
            String diaSemana = entry.getKey();
            Map<String, Object> horario = entry.getValue();
            String abertura = (String) horario.get("abertura");
            String fechamento = (String) horario.get("fechamento");
            boolean fechado = (boolean) horario.get("fechado");

            HorarioFuncionamentoDTO horarioDTO = new HorarioFuncionamentoDTO(diaSemana, abertura, fechamento, fechado);
            horarios.add(horarioDTO);
        }

        this.horarioFuncionamentoService.alterarHorarioFuncionamento(horarios);
    }
}
