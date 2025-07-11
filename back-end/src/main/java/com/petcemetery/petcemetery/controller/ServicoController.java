package com.petcemetery.petcemetery.controller;

import com.petcemetery.petcemetery.model.Servico;
import com.petcemetery.petcemetery.services.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servico")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    // Envia para o front os precos dos planos atuais do sistema, para ser exibido na tela de seleção de planos
    @GetMapping("/planos")
    public List<Servico> listarPlanos() {
        return this.servicoService.listarPlanos();
    }

    // Envia para o front os precos dos planos atuais do sistema, para ser exibido na tela de seleção de planos
    @GetMapping("/ornamentacao")
    public List<Servico> buscarServicosParaOrnamentacao(@RequestParam("tipoServico") String compraOuAluguel) {
        return this.servicoService.buscarServicosParaOrnamentacao(compraOuAluguel);
    }
}
