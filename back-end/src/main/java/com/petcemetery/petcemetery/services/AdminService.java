package com.petcemetery.petcemetery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.dto.HistoricoJazigoDTO;
import com.petcemetery.petcemetery.dto.HorarioFuncionamentoDTO;
import com.petcemetery.petcemetery.model.Admin;
import com.petcemetery.petcemetery.model.Cliente;
import com.petcemetery.petcemetery.model.Jazigo;
import com.petcemetery.petcemetery.model.Pet;
import com.petcemetery.petcemetery.repositorio.AdminRepository;
import com.petcemetery.petcemetery.repositorio.ClienteRepository;
import com.petcemetery.petcemetery.repositorio.HorarioFuncionamentoRepository;

@Service
public class AdminService {

    private final JazigoService jazigoService;
    private final HorarioFuncionamentoService horarioFuncionamentoService;
    private final EmailService emailService;
    private final ClienteRepository clienteRepository;
    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;
    private final AdminRepository repository;

    public AdminService(JazigoService jazigoService,
                        HorarioFuncionamentoService horarioFuncionamentoService,
                        EmailService emailService,
                        ClienteRepository clienteRepository,
                        HorarioFuncionamentoRepository horarioFuncionamentoRepository,
                        AdminRepository repository) {
        this.jazigoService = jazigoService;
        this.horarioFuncionamentoService = horarioFuncionamentoService;
        this.emailService = emailService;
        this.clienteRepository = clienteRepository;
        this.horarioFuncionamentoRepository = horarioFuncionamentoRepository;
        this.repository = repository;
    }

    public List<HistoricoJazigoDTO> visualizarHistorico(Long id) {
        Jazigo jazigo = this.jazigoService.findById(id);
        List<HistoricoJazigoDTO> historico = new ArrayList<>();

        if(jazigo != null) {
            for(Pet pet: jazigo.getHistoricoPets()) {
                historico.add(new HistoricoJazigoDTO(id, pet.getNome(), pet.getDataNascimento(), pet.getEspecie(), pet.getProprietario().getNome(), pet.getDataEnterro().toLocalDate(), pet.getDataExumacao().toLocalDate()));
            }

        } else {
            throw new IllegalArgumentException("Jazigo não encontrado");
        }

        return historico;
    }

    public void alterarHorarioFuncionamento(List<HorarioFuncionamentoDTO> horarios) {
        this.horarioFuncionamentoService.alterarHorarioFuncionamento(horarios);
        this.notificaClientes();
    }


    // Método que é chamado depois que o admin altera o horário de funcionamento do cemitério, e envia para todos os clientes os novos horários.
    @Async
    private void notificaClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
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

    public Admin findById(Long id) {
        Optional<Admin> admin = this.repository.findById(id);

        if(admin.isPresent()) {
            return admin.get();
        } else {
            throw new IllegalArgumentException("Admin não encontrado");
        }
    }

    public Admin findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
