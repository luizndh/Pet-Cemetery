package com.petcemetery.petcemetery.jazigo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.petcemetery.petcemetery.carrinho.dto.CarrinhoDTO;
import com.petcemetery.petcemetery.contrato.ContratoService;
import com.petcemetery.petcemetery.core.config.JwtService;
import com.petcemetery.petcemetery.integracao.gcs.GcsStorageService;
import com.petcemetery.petcemetery.jazigo.dto.*;
import com.petcemetery.petcemetery.pagamento.PagamentoService;
import com.petcemetery.petcemetery.pet.PetService;
import com.petcemetery.petcemetery.servico.ServicoService;
import com.petcemetery.petcemetery.usuario.cliente.ClienteService;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.petcemetery.petcemetery.usuario.cliente.Cliente;
import com.petcemetery.petcemetery.contrato.Contrato;
import com.petcemetery.petcemetery.jazigo.Jazigo.StatusEnum;
import com.petcemetery.petcemetery.pagamento.Pagamento;
import com.petcemetery.petcemetery.pet.Pet;
import com.petcemetery.petcemetery.servico.Servico;
import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

@Service
public class JazigoService {

    private final JazigoRepository repository;
    private final ServicoService servicoService;
    private final ClienteService clienteService;
    private final ContratoService contratoService;
    private final PetService petService;
    private final PagamentoService pagamentoService;
    private final GcsStorageService gcsStorageService;

    public JazigoService(JazigoRepository repository,
                         ServicoService servicoService,
                         ClienteService clienteService,
                         ContratoService contratoService,
                         PetService petService,
                         PagamentoService pagamentoService,
                         JwtService jwtService,
                         GcsStorageService gcsStorageService) {
        this.repository = repository;
        this.servicoService = servicoService;
        this.clienteService = clienteService;
        this.contratoService = contratoService;
        this.petService = petService;
        this.pagamentoService = pagamentoService;
        this.gcsStorageService = gcsStorageService;
    }

    DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dataHoraFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Jazigo findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<OcupacaoJazigoDTO> getJazigos() {
        List<Jazigo> jazigos = repository.findAllByOrderByIdAsc(Jazigo.class);
        List<OcupacaoJazigoDTO> jazigosDTO = new ArrayList<>();

        for (Jazigo jazigo : jazigos) {
            OcupacaoJazigoDTO jazigoDto = OcupacaoJazigoDTO.builder()
                    .jazigo(jazigo.getEndereco())
                    .build();

            if(jazigo.getProprietario() != null) {
                jazigoDto.setCpf(jazigo.getProprietario().getCpf());
            }

            if(jazigo.getPetEnterrado() != null) {
                jazigoDto.setPet(jazigo.getPetEnterrado().getNome());
                jazigoDto.setEspecie(jazigo.getPetEnterrado().getEspecie());
                jazigoDto.setDataEnterro(jazigo.getPetEnterrado().getDataEnterro().toString());
            }

            if(jazigo.getPlano() != null) {
                jazigoDto.setPlano(jazigo.getPlano().toString());
            }

            jazigosDTO.add(jazigoDto);
        }

        return jazigosDTO;
    }

    public byte[] gerarPDFJazigos(List<OcupacaoJazigoDTO> jazigos) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Relatório de Jazigos", FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Chunk space = new Chunk("\n");
            document.add(space);

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);

            table.addCell(new PdfPCell(new Phrase("PET", font)));
            table.addCell(new PdfPCell(new Phrase("DATA ENTERRO", font)));
            table.addCell(new PdfPCell(new Phrase("ENDEREÇO", font)));
            table.addCell(new PdfPCell(new Phrase("ID JAZIGO", font)));
            table.addCell(new PdfPCell(new Phrase("DATA NASC.", font)));
            table.addCell(new PdfPCell(new Phrase("ESPÉCIE", font)));
            table.addCell(new PdfPCell(new Phrase("PLANO", font)));
            table.addCell(new PdfPCell(new Phrase("CPF CLIENTE", font)));

            for (OcupacaoJazigoDTO jazigo : jazigos) {
                table.addCell(jazigo.getPet());
                if(jazigo.getDataEnterro() == null) {
                    table.addCell("");
                } else {
                    table.addCell(jazigo.getDataEnterro());
                }
                table.addCell(jazigo.getJazigo());
                table.addCell(jazigo.getEspecie());
                table.addCell(jazigo.getPlano());
                table.addCell(new PdfPCell(new Phrase(String.valueOf(jazigo.getCpf()), font)));
            }

            document.add(table);

            document.close();
            writer.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao gerar o pdf.");
        }
    }

    public List<JazigoMapaDTO> getMapaJazigos() {
        return this.repository.findAllByOrderByIdAsc(JazigoMapaDTO.class);
    }

    public List<JazigoProprietarioDTO> recuperaJazigosProprietario(Long id) {
        List<Jazigo> listaJazigos = repository.findAllByProprietarioId(id);

        List<JazigoProprietarioDTO> listaJazigosDTO = new ArrayList<>();

        for (Jazigo jazigo : listaJazigos) {
            JazigoProprietarioDTO jazigoDTO;

            jazigoDTO = JazigoProprietarioDTO.builder()
                    .id(jazigo.getId())
                    .nomePet(jazigo.getPetEnterrado() != null ? jazigo.getPetEnterrado().getNome() : "Vazio")
                    .dataEnterro(jazigo.getPetEnterrado() != null ? jazigo.getPetEnterrado().getDataEnterro() : null)
                    .build();

            listaJazigosDTO.add(jazigoDTO);
        }

        return listaJazigosDTO;
    }

    public AquisicaoJazigoDTO comprarJazigo(Long idJazigo, String tipo) {
        Optional<Jazigo> jazigo = repository.findById(idJazigo);
        if(jazigo.isEmpty()) {
            throw new NoSuchElementException("Jazigo nao existe.");
        }
        BigDecimal valor;

        if(tipo.equals("compra")){
            valor = servicoService.findByTipoServico(ServicoEnum.COMPRA).getValor();
        } else {
            valor = servicoService.findByTipoServico(ServicoEnum.ALUGUEL).getValor();
        }

        return new AquisicaoJazigoDTO(jazigo.get().getEndereco(), valor);
    }

    public JazigoPerfilDTO exibeMensagemFotoJazigo(Long idCliente, Long id) {
        Optional<Jazigo> optionalJazigo = repository.findById(id);

        if (optionalJazigo.isPresent()) {
            Jazigo jazigo = optionalJazigo.get();
            if(jazigo.getProprietario().equals(clienteService.findById(idCliente))){
                return JazigoPerfilDTO.builder()
                        .mensagem(jazigo.getMensagem())
                        .foto(jazigo.getFoto())
                        .plano(jazigo.getPlano().toString())
                        .build();
            }
        }

        return null;
    }

    public boolean editarMensagemFotoJazigo(Long idCliente, Long id, String mensagem) {
        Optional<Jazigo> optionalJazigo = repository.findById(id);

        if (optionalJazigo.isPresent()) {
            Jazigo jazigo = optionalJazigo.get();

            BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.PERSONALIZACAO).getValor();
            Cliente cliente = clienteService.findById(idCliente);
            if (jazigo.getProprietario().equals(cliente)) {
                jazigo.setMensagem(mensagem);
                repository.save(jazigo);

                Contrato personalizacaoServico = Contrato.builder()
                        .valor(valor)
                        .cliente(cliente)
                        .jazigo(jazigo)
                        .dataServico(LocalDateTime.now())
                        .servico(Servico.builder()
                                .tipoServico(ServicoEnum.PERSONALIZACAO)
                                .valor(valor)
                                .build())
                        .build();

                contratoService.save(personalizacaoServico);
                return true;
            } else {
                throw new IllegalArgumentException("Jazigo não pertence ao cliente");
            }
        } else {
            throw new NoSuchElementException("Jazigo não encontrado");
        }
    }

    public boolean agendarEnterro(Long idCliente, Long id, String data, String hora, String nomePet, String especie,
            String dataNascimento) {

        Optional<Jazigo> jazigoOpt = repository.findById(id);
        if(jazigoOpt.isEmpty()) throw new NoSuchElementException("Esse jazigo não existe");

        Jazigo jazigo = jazigoOpt.get();

        if(jazigo.getStatus() == StatusEnum.OCUPADO) {
            throw new IllegalArgumentException("Jazigo selecionado já ocupado");
        }

        Cliente cliente = clienteService.findById(idCliente);

        BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.ENTERRO).getValor();

        jazigo.setStatus(StatusEnum.OCUPADO);

        LocalDateTime dataEnterro = LocalDateTime.parse(data + " " + hora, dataHoraFormatter);

        Pet pet = new Pet(nomePet, dataEnterro, LocalDate.parse(dataNascimento, dataFormatter), especie, cliente);
        petService.save(pet); //! o pet é setado no banco mesmo q o kra nao pague o enterro e n prossiga c nada, vao ter pets setados sem estar no cemiterio

        Contrato enterroServico = Contrato.builder()
                .valor(valor)
                .cliente(cliente)
                .jazigo(jazigo)
                .pet(pet)
                .dataServico(dataEnterro)
                .servico(Servico.builder()
                        .tipoServico(ServicoEnum.ENTERRO)
                        .valor(valor)
                        .build())
                .build();

        contratoService.save(enterroServico);
        return true;
    }

    public boolean agendarExumacao(Long idCliente, Long id, String data, String hora) {
        Optional<Jazigo> jazigoOpt = repository.findById(id);
        if(jazigoOpt.isEmpty()) throw new NoSuchElementException("Esse jazigo não existe");
        Jazigo jazigo = jazigoOpt.get();

        Pet pet = jazigo.getPetEnterrado();

        BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.EXUMACAO).getValor();

        pet.setDataExumacao(LocalDateTime.parse(data + " " + hora));
        petService.save(pet);

        Cliente cliente = clienteService.findById(idCliente);

        Contrato exumacao = Contrato.builder()
                .valor(valor)
                .cliente(cliente)
                .jazigo(jazigo)
                .pet(pet)
                .dataServico(LocalDateTime.parse(data + " " + hora, dataHoraFormatter))
                .servico(Servico.builder()
                        .tipoServico(ServicoEnum.EXUMACAO)
                        .valor(valor)
                        .build())
                .build();
        contratoService.save(exumacao);
        return true;
    }

    public boolean agendarManutencao(Long idCliente, Long id, String data) {
        Optional<Jazigo> optJazigo = repository.findById(id);
        if(optJazigo.isEmpty()) {
            throw new NoSuchElementException("Jazigo não encontrado");
        }

        Jazigo jazigo = optJazigo.get();
        BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.MANUTENCAO).getValor();

        Cliente cliente = clienteService.findById(idCliente);

        Contrato manutencaoServico = Contrato.builder()
                .valor(valor)
                .cliente(cliente)
                .jazigo(jazigo)
                .pet(jazigo.getPetEnterrado())
                .dataServico(LocalDateTime.parse(data + " 00:00", dataHoraFormatter))
                .servico(Servico.builder()
                        .tipoServico(ServicoEnum.MANUTENCAO)
                        .valor(valor)
                        .build())
                .build();

        contratoService.save(manutencaoServico);
        return true;
    }

    public DetalharJazigoDTO detalharJazigo(Long id) throws IOException {
        Jazigo jazigo = this.repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Jazigo não encontrado"));

        return new DetalharJazigoDTO(jazigo, this.obterUrlImagem(jazigo.getEndereco()));
    }


    public boolean trocarPlano(Long idCliente, Long id, String tipo) {
        Optional<Jazigo> optionalJazigo = repository.findById(id);

        if(optionalJazigo.isEmpty()){
            throw new NoSuchElementException("Jazigo não encontrado");
        } else {
            Jazigo jazigo = optionalJazigo.get();

            Servico plano = this.servicoService.findByTipoServico(ServicoEnum.valueOf(tipo));

            Cliente cliente = clienteService.findById(idCliente);

            Contrato contratos = Contrato.builder()
                    .valor(BigDecimal.ZERO) // O valor do servico é 0 pois será somado com o valor do plano selecionado no construtor do Serviço
                    .cliente(cliente)
                    .jazigo(jazigo)
                    .pet(jazigo.getPetEnterrado())
                    .dataServico(LocalDateTime.now())
                    .servico(plano)
                    .build();
            contratoService.save(contratos);
            return true;
        }
    }

    public boolean finalizarCompra(Long idCliente, List<CarrinhoDTO> carrinho) {

        for (CarrinhoDTO carrinhoDTO : carrinho) {
            Optional<Jazigo> optJazigo = repository.findById(Long.valueOf(carrinhoDTO.getJazigoId()));
            if (optJazigo.isEmpty()) throw new NoSuchElementException("Jazigo não encontrado");
            Jazigo jazigo = optJazigo.get();
            Cliente cliente = this.clienteService.findById(idCliente);
            ServicoEnum servico = ServicoEnum.valueOf(carrinhoDTO.getTipo().toUpperCase());
            Pet pet = null;
            if (jazigo.getPetEnterrado() != null) pet = jazigo.getPetEnterrado();

            Contrato contrato = Contrato.builder()
                    .valor(carrinhoDTO.getValor())
                    .cliente(cliente)
                    .jazigo(jazigo)
                    .pet(pet)
                    .dataServico(LocalDateTime.now())
                    .servico(Servico.builder()
                            .tipoServico(servico)
                            .valor(carrinhoDTO.getValor())
                            .build())
                    .build();
            contratoService.save(contrato);

            if (contrato.getServico().getTipoServico().equals(ServicoEnum.COMPRA) || contrato.getServico().getTipoServico().equals(ServicoEnum.ALUGUEL)) {
                jazigo.setProprietario(cliente);
                jazigo.setPlano(ServicoEnum.valueOf(carrinhoDTO.getSelectedOrnament().toUpperCase()));
                jazigo.setStatus(StatusEnum.OCUPADO);
                repository.save(jazigo);
            }

            LocalDate dataVencimento = null;

            if (contrato.getServico().getTipoServico().equals(ServicoEnum.ALUGUEL)) {
                dataVencimento = LocalDate.now().plusMonths(1);
            }

            Pagamento pagamento = new Pagamento(cliente, carrinhoDTO.getValor(), LocalDate.now(), dataVencimento, true, contrato, null);
            pagamentoService.save(pagamento);
        }

        return true;
    }

    private String obterUrlImagem(String enderecoJazigo) throws IOException {
        return gcsStorageService.gerarUrlImagem(enderecoJazigo);
    }
}
