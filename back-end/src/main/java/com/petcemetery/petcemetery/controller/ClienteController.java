package com.petcemetery.petcemetery.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.petcemetery.petcemetery.dto.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.petcemetery.petcemetery.model.Cliente;
import com.petcemetery.petcemetery.model.Lembrete;
import com.petcemetery.petcemetery.model.Usuario;
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
                                @AuthenticationPrincipal Usuario usuario) {

        return this.clienteService.editarPerfil(usuario.getId(), requestBody);
    }

    // Desativa o perfil do Cliente quando solicitado
    @PostMapping("/desativar")
    public boolean desativarPerfil(@AuthenticationPrincipal Usuario usuario) {
        return this.clienteService.desativarPerfil(usuario.getId());
    }

    // Retorna os dados do cliente, menos a senha e CPF, na tela AlterarPerfil.js, para serem exibidos no editar perfil. Isso evita o cliente ter que escrever tudo de novo.
    @GetMapping("/alterar")
    public ClienteDTO getAlterarPerfil(@AuthenticationPrincipal Usuario usuario) {
        return this.clienteService.recuperaInformacoesAlteracao(usuario.getId());
    }

    // Exibe o nome e o email do perfil do Cliente na página ExibirPerfil.js
    @GetMapping("")
    public ClientePerfilDTO exibirPerfil(@AuthenticationPrincipal Usuario usuario) {
        return this.clienteService.recuperaInformacoesPerfil(usuario.getId());
    }

    // Recebe uma data no formato YYYY-mm-dd do front quando o cliente adiciona um novo lembrete de visita e adiciona no banco de dados com seu usuario.getId() e data.
    @PostMapping("/lembrete")
    public ResponseEntity<Lembrete> adicionarLembrete(@AuthenticationPrincipal Usuario usuario, @RequestBody String dataStr) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataStr, dateFormat);
        if (LocalDate.now().isAfter(data)) {
            throw new IllegalArgumentException("A data informada não pode ser no passado");
        }

        Lembrete lembrete = this.clienteService.adicionaLembrete(usuario.getId(), data);
        return ResponseEntity.ok(lembrete);
    }

    // Retorna para o front um objeto despesasDTO contendo o tipo de serviço, valor, data do ultimo pagamento e data do vencimento.
    @GetMapping("/despesas")
    public List<VisualizarDespesasDTO> visualizarDespesas(@AuthenticationPrincipal Usuario usuario){
        return contratoService.visualizarDespesas(usuario.getId());
    }

    @PutMapping("/trocar-senha")
    public void trocarSenha(@AuthenticationPrincipal Usuario usuario, @Valid @RequestBody TrocarSenhaDTO dto) {
        this.clienteService.trocarSenha(usuario.getId(), dto);
    }
}
