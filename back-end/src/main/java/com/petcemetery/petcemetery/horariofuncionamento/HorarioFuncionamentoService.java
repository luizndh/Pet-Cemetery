package com.petcemetery.petcemetery.horariofuncionamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.petcemetery.petcemetery.core.outros.EmailService;
import com.petcemetery.petcemetery.usuario.cliente.ClienteService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.horariofuncionamento.dto.HorarioFuncionamentoDTO;
import com.petcemetery.petcemetery.usuario.cliente.Cliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HorarioFuncionamentoService {

    private final HorarioFuncionamentoRepository repository;
    private final ClienteService clienteService;
    private final EmailService emailService;
    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public void alterarHorarioFuncionamento(List<HorarioFuncionamentoDTO> horarios) {
        for (HorarioFuncionamentoDTO horarioDTO : horarios) {

            HorarioFuncionamento horario = repository.findByDiaSemana(horarioDTO.getDiaSemana());
            horario.setAbertura(horarioDTO.getAbertura());
            horario.setFechamento(horarioDTO.getFechamento());
            horario.setFechado(horarioDTO.isFechado());

            repository.save(horario);
        }

        this.notificaClientes();
    }

    @Async
    private void notificaClientes() {
        List<Cliente> clientes = clienteService.findAll();
        String[] emails = new String[clientes.size()];
        for(Cliente cliente : clientes) {
            emails[clientes.indexOf(cliente)] = cliente.getEmail();
        }

        String subject = "Horário de funcionamento do cemitério alterado";

        List<HorarioFuncionamento> horarios = horarioFuncionamentoRepository.findAll();
        Map<String, HorarioFuncionamento> horarioMap = horarios.stream()
                .collect(Collectors.toMap(HorarioFuncionamento::getDiaSemana, h -> h));

        String body = "Olá! Os horários de funcionamento do cemítério foram alterados para essa semana. Seguem os novos horários:\n" +
                "Segunda: " + getHorario(horarioMap, "Segunda-Feira") + "\n" +
                "Terça: " + getHorario(horarioMap, "Terça-Feira") + "\n" +
                "Quarta: " + getHorario(horarioMap, "Quarta-Feira") + "\n" +
                "Quinta: " + getHorario(horarioMap, "Quinta-feira") + "\n" +
                "Sexta: " + getHorario(horarioMap, "Sexta-feira") + "\n" +
                "Sábado: " + getHorario(horarioMap, "Sábado") + "\n" +
                "Domingo: " + getHorario(horarioMap, "Domingo") + "\n" +
                "Feriado: " + getHorario(horarioMap, "Feriados") + "\n" +
                "Atenciosamente, Pet Cemetery.";

        emailService.sendEmail(emails, subject, body);
    }

    private String getHorario(Map<String, HorarioFuncionamento> horarioMap, String diaSemana) {
        HorarioFuncionamento horario = horarioMap.get(diaSemana);
        if (horario == null || horario.isFechado()) {
            return "Fechado";
        }
        return horario.getAbertura() + " - " + horario.getFechamento();
    }

    public List<HorarioFuncionamentoDTO> getHorarios() {
        System.out.println("entrei no getHorarios");
        List<HorarioFuncionamento> horarios = repository.findAll();
        System.out.println("Horários de funcionamento: " + horarios);
        List<HorarioFuncionamentoDTO> horariosDTO = new ArrayList<>();

        for(HorarioFuncionamento horario : horarios) {
            HorarioFuncionamentoDTO horarioDTO = new HorarioFuncionamentoDTO(horario.getDiaSemana(), horario.getAbertura(), horario.getFechamento(), horario.isFechado());
            horariosDTO.add(horarioDTO);
        }

        return horariosDTO;
    }
}
