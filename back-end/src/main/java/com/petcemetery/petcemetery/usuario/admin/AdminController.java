package com.petcemetery.petcemetery.usuario.admin;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petcemetery.petcemetery.usuario.cliente.dto.ClienteInadimplenteDTO;
import com.petcemetery.petcemetery.utils.PdfGeneratorService;
import com.petcemetery.petcemetery.contrato.dto.ContratoDTO;
import com.petcemetery.petcemetery.jazigo.dto.HistoricoJazigoDTO;
import com.petcemetery.petcemetery.jazigo.dto.OcupacaoJazigoDTO;
import com.petcemetery.petcemetery.servico.Servico;
import com.petcemetery.petcemetery.core.outros.VerificadorData;
import com.petcemetery.petcemetery.usuario.cliente.ClienteService;
import com.petcemetery.petcemetery.contrato.ContratoService;
import com.petcemetery.petcemetery.jazigo.JazigoService;
import com.petcemetery.petcemetery.servico.ServicoService;

import lombok.RequiredArgsConstructor;

@EnableAsync
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ServicoService servicoService;
    private final ClienteService clienteService;
    private final ContratoService contratoService;
    private final JazigoService jazigoService;
    private final PdfGeneratorService pdfGeneratorService;

    // Retorna, em formato JSON, informações sobre todos os pets que já passaram no
    // jazigo passado pelo seu id.
    @GetMapping("/{id}/historico")
    public List<HistoricoJazigoDTO> visualizarHistorico(@PathVariable Long id) {
        return this.adminService.visualizarHistorico(id);
    }

    // Retorna o valor de todos os servicos em formato JSON
    @GetMapping("/servicos")
    public List<Servico> exibirServicos() {
        return servicoService.exibirServicos(); // redireciona para a página de serviços
    }

    // Atualiza o valor do plano no banco com base no seu nome
    @PutMapping("/servico/{nomeServico}")
    public boolean alterarServicos(@PathVariable String nomeServico, @RequestParam BigDecimal valor) {
        return this.servicoService.alterarServicos(nomeServico, valor);
    }

    // Visualizar clientes inadimplentes
    @GetMapping("/inadimplentes")
    public List<ClienteInadimplenteDTO> findInadimplentes() {
        return this.clienteService.findInadimplentes();
    }

    // Relatório de enterros
    @GetMapping("/enterros")
    public List<ContratoDTO> findEnterros() {
        return this.contratoService.findEnterros();
    }

    // Gerar PDF de enterros
    @GetMapping("/enterros/pdf")
    public ResponseEntity<byte[]> gerarPDFEnterros() {
        // Pega o retorno do método acima
        List<ContratoDTO> enterros = this.contratoService.findEnterros();

        if (enterros == null) {
            throw new NoSuchElementException("Não existem enterros na nossa base de dados");
        }

        byte[] pdfBytes = this.pdfGeneratorService.gerarPDFEnterros(enterros);

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "relatorio_enterros.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Relatório de exumações
    @GetMapping("/exumacoes")
    public List<ContratoDTO> findExumacoes() {
        return this.contratoService.findExumacoes();
    }

    // Gerar PDF de exumações
    @GetMapping("/exumacoes/pdf")
    public ResponseEntity<byte[]> gerarPDFExumacoes() {
        // Pega o retorno do método acima
        List<ContratoDTO> exumacoes = this.contratoService.findExumacoes();

        if (exumacoes == null) {
            throw new NoSuchElementException("Não existem exumações na nossa base de dados");
        }

        byte[] pdfBytes = this.pdfGeneratorService.gerarPDFExumacoes(exumacoes);

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "relatorio_exumacoes.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Relatório de jazigos
    @GetMapping("/jazigos")
    public List<OcupacaoJazigoDTO> getJazigos() {
        return this.jazigoService.getJazigos();
    }

    // Gerar PDF de jazigos
    @GetMapping("/jazigos/pdf")
    public ResponseEntity<byte[]> gerarPDFJazigos() {
        // Pega o retorno do método acima
        List<OcupacaoJazigoDTO> jazigos = this.jazigoService.getJazigos();

        if (jazigos == null) {
            throw new NoSuchElementException("Não existem jazigos na nossa base de dados");
        }

        byte[] pdfBytes = this.pdfGeneratorService.gerarPDFJazigos(jazigos);

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "relatorio_jazigos.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Recebe um request parameter "data" no formato "yyyy-MM-dd" e seta a data
    // atual do sistema para a data recebida. Retorna "ERR;data_invalida"
    // caso o formato da data seja inválido, e "OK" se tudo ocorrer bem.
    @PostMapping("/time-travel")
    public boolean timeTravel(@RequestParam String data) {
        LocalDate dataAvancada;

        try {
            dataAvancada = LocalDate.parse(data);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Formato de data inválido");
        }

        VerificadorData.setCurrentDate(dataAvancada);

        return true;
    }
}