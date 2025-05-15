package com.petcemetery.petcemetery.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.petcemetery.petcemetery.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.dto.HorarioFuncionamentoDTO;
import com.petcemetery.petcemetery.model.HorarioFuncionamento;
import com.petcemetery.petcemetery.repositorio.HorarioFuncionamentoRepository;

@Service
@RequiredArgsConstructor
public class HorarioFuncionamentoService {

    private final HorarioFuncionamentoRepository repository;
    private final ClienteService clienteService;
    private final EmailService emailService;
    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public void alterarHorarioFuncionamento(List<HorarioFuncionamentoDTO> horarios) {
        /*for(HorarioFuncionamentoDTO horarioDTO : horarios) {
            HorarioFuncionamento horario = convertToModel(horarioDTO);
            repository.save(horario);
        }*/

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
        String body = "Olá! Os horários de funcionamento do cemítério foram alterados para essa semana. Seguem os novos horários:\n" +
                "Segunda: " + horarioFuncionamentoRepository.findByDiaSemana("segunda").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("segunda").getFechamento() + "\n" +
                "Terça: " + horarioFuncionamentoRepository.findByDiaSemana("terca").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("terca").getFechamento() + "\n" +
                "Quarta: " + horarioFuncionamentoRepository.findByDiaSemana("quarta").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("quarta").getFechamento() + "\n" +
                "Quinta: " + horarioFuncionamentoRepository.findByDiaSemana("quinta").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("quinta").getFechamento() + "\n" +
                "Sexta: " + horarioFuncionamentoRepository.findByDiaSemana("sexta").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("sexta").getFechamento() + "\n" +
                "Sábado: " + horarioFuncionamentoRepository.findByDiaSemana("sabado").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("sabado").getFechamento() + "\n" +
                "Domingo: " + horarioFuncionamentoRepository.findByDiaSemana("domingo").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("domingo").getFechamento() + "\n" +
                "Feriado: " + horarioFuncionamentoRepository.findByDiaSemana("feriado").getAbertura() + " - " + horarioFuncionamentoRepository.findByDiaSemana("feriado").getFechamento() + "\n" +
                "Atenciosamente, Pet Cemetery.";

        emailService.sendEmail(emails, subject, body);
    }

    public List<HorarioFuncionamentoDTO> getHorarios() {
        List<HorarioFuncionamento> horarios = repository.findAll();
        List<HorarioFuncionamentoDTO> horariosDTO = new ArrayList<>();

        for(HorarioFuncionamento horario : horarios) {
            HorarioFuncionamentoDTO horarioDTO = new HorarioFuncionamentoDTO(horario.getDiaSemana(), horario.getAbertura(), horario.getFechamento(), horario.isFechado());
            horariosDTO.add(horarioDTO);
        }

        return horariosDTO;
    }
}
