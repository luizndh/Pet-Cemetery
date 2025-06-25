package com.petcemetery.petcemetery.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.petcemetery.petcemetery.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petcemetery.petcemetery.model.Cliente;
import com.petcemetery.petcemetery.model.Lembrete;
import com.petcemetery.petcemetery.services.ClienteService;
import com.petcemetery.petcemetery.services.ContratoService;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ContratoService contratoService;

    // Recebe as informações que o cliente deseja mudar, em JSON, e altera no banco de dados
    @PutMapping("/alterar")
    public Cliente editarPerfil(@Valid @RequestBody EditarPerfilDTO requestBody,
                                @RequestHeader("Authorization") String authHeader) {

        return this.clienteService.editarPerfil(authHeader.substring(7), requestBody);
    }

    // Desativa o perfil do Cliente quando solicitado
    @PostMapping("/desativar")
    public boolean desativarPerfil(@RequestHeader("Authorization") String authHeader) {
        return this.clienteService.desativarPerfil(authHeader.substring(7));
    }

    // Retorna os dados do cliente, menos a senha e CPF, na tela AlterarPerfil.js, para serem exibidos no editar perfil. Isso evita o cliente ter que escrever tudo de novo.
    @GetMapping("/alterar")
    public ClienteDTO getAlterarPerfil(@RequestHeader("Authorization") String authHeader) {
        return this.clienteService.recuperaInformacoesAlteracao(authHeader.substring(7));
    }

    // Exibe o nome e o email do perfil do Cliente na página ExibirPerfil.js
    @GetMapping("")
    public ClientePerfilDTO exibirPerfil(@RequestHeader("Authorization") String authHeader) {
        return this.clienteService.recuperaInformacoesPerfil(authHeader.substring(7));
    }

    // Recebe uma data no formato YYYY-mm-dd do front quando o cliente adiciona um novo lembrete de visita e adiciona no banco de dados com seu authHeader.substring(7) e data.
    @PostMapping("/lembrete")
    public ResponseEntity<Lembrete> adicionarLembrete(@RequestHeader("Authorization") String authHeader, @RequestBody String dataStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = dateFormat.parse(dataStr);
        if (new Date().after(data)) {
            throw new IllegalArgumentException("A data informada não pode ser no passado");
        }

        Lembrete lembrete = this.clienteService.adicionaLembrete(authHeader.substring(7), data);
        return ResponseEntity.ok(lembrete);
    }

    // Retorna para o front um objeto despesasDTO contendo o tipo de serviço, valor, data do ultimo pagamento e data do vencimento.
    @GetMapping("/despesas")
    public List<VisualizarDespesasDTO> visualizarDespesas(@RequestHeader("Authorization") String authHeader){
        return contratoService.visualizarDespesas(authHeader.substring(7));
    }

    @PutMapping("/trocar-senha")
    public void trocarSenha(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody TrocarSenhaDTO dto) {
        this.clienteService.trocarSenha(authHeader.substring(7), dto);
    }
}
