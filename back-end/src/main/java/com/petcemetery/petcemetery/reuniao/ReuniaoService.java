package com.petcemetery.petcemetery.reuniao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.petcemetery.petcemetery.core.outros.EmailService;
import com.petcemetery.petcemetery.usuario.cliente.ClienteService;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.core.config.JwtService;
import com.petcemetery.petcemetery.reuniao.dto.ReuniaoAdminDTO;
import com.petcemetery.petcemetery.reuniao.dto.ReuniaoDTO;
import com.petcemetery.petcemetery.usuario.cliente.Cliente;

@Service
public class ReuniaoService {

    private final ReuniaoRepository repository;
    private final EmailService emailService;
    private final ClienteService clienteService;
    private final JwtService jwtService;

    public ReuniaoService(ReuniaoRepository repository, EmailService emailService, ClienteService clienteService, JwtService jwtService) {
        this.repository = repository;
        this.emailService = emailService;
        this.clienteService = clienteService;
        this.jwtService = jwtService;
    }

    public List<ReuniaoAdminDTO> visualizarReunioes() {
        List<Reuniao> reunioes = repository.findAllByOrderByDataAsc();
        List<ReuniaoAdminDTO> reunioesDTO = new ArrayList<>();

        for (Reuniao reuniao : reunioes) {
            ReuniaoAdminDTO reuniaoDTO = ReuniaoAdminDTO.builder()
                    .cpf(reuniao.getCliente().getCpf())
                    .email(reuniao.getCliente().getEmail())
                    .data(reuniao.getData().toLocalDate().toString())
                    .assunto(reuniao.getAssunto())
                    .hora(reuniao.getData().toLocalTime().toString())
                    .build();

            reunioesDTO.add(reuniaoDTO);
        }

        return reunioesDTO;
    }

    public boolean agendarReuniao(String token, ReuniaoDTO reuniaoDTO) {
        Long id = jwtService.extractId(token);
        System.out.println(reuniaoDTO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Reuniao reuniao = new Reuniao();
        reuniao.setAssunto(reuniaoDTO.getAssunto());
        reuniao.setData(LocalDateTime.parse(reuniaoDTO.getData() + " " + reuniaoDTO.getHora(), formatter));

        // Verificando se a reunião está sendo agendada com uma antecedência de dois dias
        if(reuniao.getData().toLocalDate().isBefore(LocalDate.now().minusDays(2))) {
            throw new IllegalArgumentException("A reunião deve ser agendada com uma antecedência mínima de dois dias");
        }

        Cliente cliente = clienteService.findById(id);
        reuniao.setCliente(cliente);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        repository.save(reuniao);

        String[] to = {cliente.getEmail()};
        emailService.sendEmail(to, "Agendamento de reunião", "Sua reunião foi agendada com sucesso para o dia " + reuniao.getData().toLocalDate().format(formatter2) + "!");
        return true;
    }

}
