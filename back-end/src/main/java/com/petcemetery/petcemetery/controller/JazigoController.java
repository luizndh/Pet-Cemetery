 package com.petcemetery.petcemetery.controller;

import java.math.BigDecimal;
import java.util.List;

import com.petcemetery.petcemetery.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petcemetery.petcemetery.model.Jazigo;
import com.petcemetery.petcemetery.model.Servico;
import com.petcemetery.petcemetery.model.Servico.ServicoEnum;
import com.petcemetery.petcemetery.services.JazigoService;
import com.petcemetery.petcemetery.services.ServicoService;

@RestController
@RequestMapping("/api/jazigo")
@RequiredArgsConstructor
public class JazigoController {

    private final JazigoService jazigoService;
    private final ServicoService servicoService;

    // Igual ao do cliente, porém o admin não vai conseguir selecionar um jazigo
    @GetMapping("/mapa")
    public ResponseEntity<List<JazigoMapaDTO>> mapaJazigos() {
        return ResponseEntity.ok(this.jazigoService.getMapaJazigos());
    }

    // Envia para o front uma lista dos jazigos do proprietário, contendo o nome do pet e a data do enterro, ou "Vazio" e null caso não tenha pet enterrado.
    @GetMapping("/cliente")
    public ResponseEntity<List<JazigoDTO>> recuperaJazigosProprietario(@RequestHeader("Authorization") String authHeader) {
        // passa apenas o token, sem o "Bearer "
        return ResponseEntity.ok(this.jazigoService.recuperaJazigosProprietario(authHeader.substring(7)));
    }

    // Envia para o front o endereco do jazigo selecionado, o id dele e o preço de compra, para ser exibido na tela antes da compra do ornamento
    @GetMapping("/{id}")
    public AquisicaoJazigoDTO comprarJazigo(@PathVariable Long id, @RequestParam String tipo) {
        return this.jazigoService.comprarJazigo(id, tipo);
    }

    // Envia para o front os precos dos planos atuais do sistema, para ser exibido na tela de seleção de planos
    @GetMapping("/planos")
    public List<Servico> listarPlanos() {
        return this.servicoService.listarPlanos();
    }

    // //adiciona no carrinho o jazigo selecionado pelo cliente
    // // TODO: alterar OU excluir metodo que caiu em desuso ao excluir carrinho (tem varios nessa classe assim)
    @PostMapping("/finalizar-compra") //tipo == COMPRA ou ALUGUEL
    public Boolean finalizarCompra(@RequestHeader("Authorization") String authHeader, @RequestBody List<CarrinhoDTO> carrinho) {
        return this.jazigoService.finalizarCompra(authHeader.substring(7), carrinho);
    }

    // Retorna a mensagem e a foto atual para serem exibidas no front quando o usuário quiser alterar as informações do jazigo
    // Tem que ver a foto ainda
    @GetMapping("/cliente/{id}")
    public JazigoPerfilDTO exibirMensagemFotoJazigo(@RequestHeader("Authorization") String authHeader, @PathVariable Long id) {
        return this.jazigoService.exibeMensagemFotoJazigo(authHeader.substring(7), id);
    }

    //edita só a mensagem do jazigo, nao sei a situação da foto ainda
    @PutMapping("/{id}")
    public Boolean editarMensagemFotoJazigo(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody String mensagem) {
        if (mensagem.length() > 80) {
            throw new IllegalArgumentException("Limite de 80 caracteres excedido");
        }

        return this.jazigoService.editarMensagemFotoJazigo(authHeader.substring(7), id, mensagem);
    }

    // Recebe a data e hora do enterro e também os dados do pet a ser enterrado.
    // Cria um novo pet e um novo servico de enterro
    @PostMapping("/{id}/enterro")
    public Boolean agendarEnterro(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestParam String data, @RequestParam String hora,
        @RequestParam String nomePet, @RequestParam String especie, @RequestParam String dataNascimento) {

        return this.jazigoService.agendarEnterro(authHeader.substring(7), id, data, hora, nomePet, especie, dataNascimento);
    }

    // Recebe os parâmetros data (yyyy-mm-dd) e hora (hh-mm) da exumacao, no formato correto, e salva no banco
    // Não estamos utilizando o cpf pra nada :D - utiliza sim, p saber se o jazigo é do kra ou nao
    @PostMapping("/{id}/exumacao")
    public Boolean agendarExumacao(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestParam String data, @RequestParam String hora) {
        return this.jazigoService.agendarExumacao(authHeader.substring(7), id, data, hora);
    }

    @PostMapping("/{id}/manutencao")
    public Boolean agendarManutencao(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestParam String data) {
        return this.jazigoService.agendarManutencao(authHeader.substring(7), id, data);
    }

    // Retorna o valor atual do preço de um servico
    @GetMapping("/{id}/servico/{nomeServico}")
    public BigDecimal precoEnterro(@PathVariable String nomeServico) {
        return servicoService.findByTipoServico(ServicoEnum.valueOf(nomeServico.toUpperCase())).getValor();
    }

    //retorna os detalhes do jazigo especificado para ser exibido na tela de visualizar detalhes de jazifo
    @GetMapping("/{id}/detalhe")
    public Jazigo detalharJazigo(@PathVariable Long id){
        return this.jazigoService.detalharJazigo(id);
    }

    //metodo com o servico PERSONALIZACAO, que troca o plano do jazigo
    @PutMapping("/{id}/plano")
    public Boolean trocarPlano (@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestParam String tipo){
        return this.jazigoService.trocarPlano(authHeader.substring(7), id, tipo);
    }
}
