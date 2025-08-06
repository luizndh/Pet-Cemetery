 package com.petcemetery.petcemetery.jazigo;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.petcemetery.petcemetery.carrinho.dto.CarrinhoDTO;
import com.petcemetery.petcemetery.jazigo.dto.*;
import com.petcemetery.petcemetery.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;
import com.petcemetery.petcemetery.servico.ServicoService;

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
    public ResponseEntity<List<JazigoProprietarioDTO>> recuperaJazigosProprietario(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(this.jazigoService.recuperaJazigosProprietario(usuario.getId()));
    }

    // Envia para o front o endereco do jazigo selecionado, o id dele e o preço de compra, para ser exibido na tela antes da compra do ornamento
    @GetMapping("/{id}")
    public AquisicaoJazigoDTO comprarJazigo(@PathVariable Long id, @RequestParam String tipo) {
        return this.jazigoService.comprarJazigo(id, tipo);
    }

    // //adiciona no carrinho o jazigo selecionado pelo cliente
    // // TODO: alterar OU excluir metodo que caiu em desuso ao excluir carrinho (tem varios nessa classe assim)
    @PostMapping("/finalizar-compra") //tipo == COMPRA ou ALUGUEL
    public Boolean finalizarCompra(@AuthenticationPrincipal Usuario usuario, @RequestBody List<CarrinhoDTO> carrinho) {
        return this.jazigoService.finalizarCompra(usuario.getId(), carrinho);
    }

    // Retorna a mensagem e a foto atual para serem exibidas no front quando o usuário quiser alterar as informações do jazigo
    // Tem que ver a foto ainda
    @GetMapping("/cliente/{id}")
    public JazigoPerfilDTO exibirMensagemFotoJazigo(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id) {
        return this.jazigoService.exibeMensagemFotoJazigo(usuario.getId(), id);
    }

    //edita só a mensagem do jazigo, nao sei a situação da foto ainda
    @PutMapping("/{id}")
    public Boolean editarMensagemFotoJazigo(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id, @RequestBody String mensagem) {
        if (mensagem.length() > 80) {
            throw new IllegalArgumentException("Limite de 80 caracteres excedido");
        }

        return this.jazigoService.editarMensagemFotoJazigo(usuario.getId(), id, mensagem);
    }

    // Recebe a data e hora do enterro e também os dados do pet a ser enterrado.
    // Cria um novo pet e um novo servico de enterro
    @PostMapping("/{id}/enterro")
    public Boolean agendarEnterro(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id, @RequestParam String data, @RequestParam String hora,
        @RequestParam String nomePet, @RequestParam String especie, @RequestParam String dataNascimento) throws ParseException {

        return this.jazigoService.agendarEnterro(usuario.getId(), id, data, hora, nomePet, especie, dataNascimento);
    }

    // Recebe os parâmetros data (yyyy-mm-dd) e hora (hh-mm) da exumacao, no formato correto, e salva no banco
    // Não estamos utilizando o cpf pra nada :D - utiliza sim, p saber se o jazigo é do kra ou nao
    @PostMapping("/{id}/exumacao")
    public Boolean agendarExumacao(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id, @RequestParam String data, @RequestParam String hora) throws ParseException {
        return this.jazigoService.agendarExumacao(usuario.getId(), id, data, hora);
    }

    @PostMapping("/{id}/manutencao")
    public Boolean agendarManutencao(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id, @RequestParam String data) throws ParseException {
        return this.jazigoService.agendarManutencao(usuario.getId(), id, data);
    }

    // Retorna o valor atual do preço de um servico
    @GetMapping("/{id}/servico/{nomeServico}")
    public BigDecimal precoEnterro(@PathVariable String nomeServico) {
        return servicoService.findByTipoServico(ServicoEnum.valueOf(nomeServico.toUpperCase())).getValor();
    }

    //retorna os detalhes do jazigo especificado para ser exibido na tela de visualizar detalhes de jazigo
    @GetMapping("/{id}/detalhe")
    public DetalharJazigoDTO detalharJazigo(@PathVariable Long id) throws IOException{
        return this.jazigoService.detalharJazigo(id);
    }

    //metodo com o servico PERSONALIZACAO, que troca o plano do jazigo
    @PutMapping("/{id}/plano")
    public Boolean trocarPlano (@AuthenticationPrincipal Usuario usuario, @PathVariable Long id, @RequestParam String tipo){
        return this.jazigoService.trocarPlano(usuario.getId(), id, tipo);
    }
}
