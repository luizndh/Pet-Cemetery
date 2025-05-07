package com.petcemetery.petcemetery.services;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.petcemetery.petcemetery.config.JwtService;
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
import com.petcemetery.petcemetery.dto.AquisicaoJazigoDTO;
import com.petcemetery.petcemetery.dto.CarrinhoDTO;
import com.petcemetery.petcemetery.dto.JazigoDTO;
import com.petcemetery.petcemetery.dto.JazigoPerfilDTO;
import com.petcemetery.petcemetery.model.Cliente;
import com.petcemetery.petcemetery.model.Contrato;
import com.petcemetery.petcemetery.model.Jazigo;
import com.petcemetery.petcemetery.model.Jazigo.StatusEnum;
import com.petcemetery.petcemetery.model.Pagamento;
import com.petcemetery.petcemetery.model.Pet;
import com.petcemetery.petcemetery.model.Servico;
import com.petcemetery.petcemetery.model.Servico.ServicoEnum;
import com.petcemetery.petcemetery.repositorio.JazigoRepository;

@Service
public class JazigoService {

    private final JazigoRepository repository;
    private final ServicoService servicoService;
    private final ClienteService clienteService;
    private final ContratoService contratoService;
    private final PetService petService;
    private final PagamentoService pagamentoService;
    private final JwtService jwtService;

    public JazigoService(JazigoRepository repository,
                         ServicoService servicoService,
                         ClienteService clienteService,
                         ContratoService contratoService,
                         PetService petService,
                         PagamentoService pagamentoService,
                         JwtService jwtService) {
        this.repository = repository;
        this.servicoService = servicoService;
        this.clienteService = clienteService;
        this.contratoService = contratoService;
        this.petService = petService;
        this.pagamentoService = pagamentoService;
        this.jwtService = jwtService;
    }

    public Jazigo findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<JazigoDTO> getJazigos() {
        List<Jazigo> jazigos = repository.findAllByOrderByIdAsc();
        List<JazigoDTO> jazigosDTO = new ArrayList<>();

        for (Jazigo jazigo : jazigos) {
            JazigoDTO jazigoDto;

            if(jazigo.getPetEnterrado() != null) { // Caso tenha pet enterrado
                jazigoDto = JazigoDTO.builder()
                        .nomePet(jazigo.getPetEnterrado().getNome())
                        .dataEnterro(jazigo.getPetEnterrado().getDataEnterro().toLocalDate())
                        .dataNascimento(jazigo.getPetEnterrado().getDataNascimento())
                        .especie(jazigo.getPetEnterrado().getEspecie())
                        .endereco(jazigo.getEndereco())
                        .id(jazigo.getId())
                        .mensagem(jazigo.getMensagem())
                        .plano(jazigo.getPlano().toString())
                        .idCliente(jazigo.getProprietario().getId())
                        .build();
            } else if(jazigo.getProprietario() != null) { // Caso não tenha pet enterrado mas tenha proprietario
                jazigoDto = JazigoDTO.builder()
                        .endereco(jazigo.getEndereco())
                        .id(jazigo.getId())
                        .mensagem(jazigo.getMensagem())
                        .plano(jazigo.getPlano().toString())
                        .idCliente(jazigo.getProprietario().getId())
                        .build();
            } else { // Caso não tenha pet enterrado nem proprietario
                jazigoDto = JazigoDTO.builder()
                        .endereco(jazigo.getEndereco())
                        .id(jazigo.getId())
                        .mensagem(jazigo.getMensagem())
                        .build();
            }

            jazigosDTO.add(jazigoDto);
        }

        return jazigosDTO;
    }

    public byte[] gerarPDFJazigos(List<JazigoDTO> jazigos) {
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

            for (JazigoDTO jazigo : jazigos) {
                table.addCell(jazigo.getNomePet());
                if(jazigo.getDataEnterro() == null) {
                    table.addCell("");
                } else {
                    table.addCell(jazigo.getDataEnterro().toString());
                }
                table.addCell(jazigo.getEndereco());
                table.addCell(jazigo.getId().toString());
                if(jazigo.getDataNascimento() == null) {
                    table.addCell("");
                } else {
                    table.addCell(jazigo.getDataNascimento().toString());
                }
                table.addCell(jazigo.getEspecie());
                table.addCell(jazigo.getPlano());
                table.addCell(new PdfPCell(new Phrase(String.valueOf(jazigo.getIdCliente()), font)));
            }

            document.add(table);

            document.close();
            writer.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao gerar o pdf.");
        }
    }

    // TODO: consertar essa merda
    public String getMapaJazigos() {
        String str = "";

        // Busque todos os jazigos do banco de dados e adicione sua disponibilidade à lista.
        for (Jazigo i : repository.findAllByOrderByIdAsc()) {
            str = str + String.valueOf(i.getStatus() == Jazigo.StatusEnum.DISPONIVEL) + (i == repository.findAllByOrderByIdAsc().get(repository.findAllByOrderByIdAsc().size() - 1) ? "" : ";");
        }

        return str;
    }

    public List<JazigoDTO> recuperaJazigosProprietario(String token) {
        Long id = jwtService.extractId(token);
        List<Jazigo> listaJazigos = repository.findAllByProprietarioId(id);

        List<JazigoDTO> listaJazigosDTO = new ArrayList<>();

        for (Jazigo jazigo : listaJazigos) {
            JazigoDTO jazigoDTO;

            jazigoDTO = JazigoDTO.builder()
                    .idCliente(id)
                    .endereco(jazigo.getEndereco())
                    .id(jazigo.getId())
                    .mensagem(jazigo.getMensagem())
                    .plano(jazigo.getPlano().toString())
                    .build();

            if(jazigo.getPetEnterrado() != null) {
                jazigoDTO.setNomePet(jazigo.getPetEnterrado().getNome());
                jazigoDTO.setEspecie(jazigo.getPetEnterrado().getEspecie());
                jazigoDTO.setDataNascimento(jazigo.getPetEnterrado().getDataNascimento());
                jazigoDTO.setDataEnterro(jazigo.getPetEnterrado().getDataEnterro().toLocalDate());
            }

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

    public JazigoPerfilDTO exibeMensagemFotoJazigo(String token, Long id) {
        Optional<Jazigo> optionalJazigo = repository.findById(id);

        if (optionalJazigo.isPresent()) {
            Long idCliente = jwtService.extractId(token);
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

    public boolean editarMensagemFotoJazigo(String token, Long id, String mensagem) {
        Optional<Jazigo> optionalJazigo = repository.findById(id);

        if (optionalJazigo.isPresent()) {
            Long idCliente = jwtService.extractId(token);
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

    public boolean agendarEnterro(String token, Long id, String data, String hora, String nomePet, String especie,
            String dataNascimento) {

        Optional<Jazigo> jazigoOpt = repository.findById(id);
        if(jazigoOpt.isEmpty()) throw new NoSuchElementException("Esse jazigo não existe");

        Jazigo jazigo = jazigoOpt.get();

        if(jazigo.getStatus() == StatusEnum.OCUPADO) {
            throw new IllegalArgumentException("Jazigo selecionado já ocupado");
        }

        Long idCliente = jwtService.extractId(token);
        Cliente cliente = clienteService.findById(idCliente);

        BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.ENTERRO).getValor();

        jazigo.setStatus(StatusEnum.OCUPADO);

        Pet pet = new Pet(nomePet, LocalDateTime.parse(data + "T" + hora), LocalDate.parse(dataNascimento), especie, cliente);
        petService.save(pet); //! o pet é setado no banco mesmo q o kra nao pague o enterro e n prossiga c nada, vao ter pets setados sem estar no cemiterio

        Contrato enterroServico = Contrato.builder()
                .valor(valor)
                .cliente(cliente)
                .jazigo(jazigo)
                .pet(pet)
                .dataServico(LocalDateTime.parse(data + "T" + hora))
                .servico(Servico.builder()
                        .tipoServico(ServicoEnum.ENTERRO)
                        .valor(valor)
                        .build())
                .build();

        contratoService.save(enterroServico);
        return true;
    }

    public boolean agendarExumacao(String token, Long id, String data, String hora) {
        Optional<Jazigo> jazigoOpt = repository.findById(id);
        if(jazigoOpt.isEmpty()) throw new NoSuchElementException("Esse jazigo não existe");
        Jazigo jazigo = jazigoOpt.get();

        Pet pet = jazigo.getPetEnterrado();

        BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.EXUMACAO).getValor();

        pet.setDataExumacao(LocalDateTime.parse(data + "T" + hora));
        petService.save(pet);

        Long idCliente = jwtService.extractId(token);
        Cliente cliente = clienteService.findById(idCliente);

        Contrato exumacao = Contrato.builder()
                .valor(valor)
                .cliente(cliente)
                .jazigo(jazigo)
                .pet(pet)
                .dataServico(LocalDateTime.parse(data + "T" + hora))
                .servico(Servico.builder()
                        .tipoServico(ServicoEnum.EXUMACAO)
                        .valor(valor)
                        .build())
                .build();
        contratoService.save(exumacao);
        return true;
    }

    public boolean agendarManutencao(String token, Long id, String data) {
        Optional<Jazigo> optJazigo = repository.findById(id);
        if(optJazigo.isEmpty()) {
            throw new NoSuchElementException("Jazigo não encontrado");
        }

        Jazigo jazigo = optJazigo.get();
        BigDecimal valor = servicoService.findByTipoServico(ServicoEnum.MANUTENCAO).getValor();

        Long idCliente = jwtService.extractId(token);
        Cliente cliente = clienteService.findById(idCliente);

        Contrato manutencaoServico = Contrato.builder()
                .valor(valor)
                .cliente(cliente)
                .jazigo(jazigo)
                .pet(jazigo.getPetEnterrado())
                .dataServico(LocalDateTime.parse(data + "T00:00:00"))
                .servico(Servico.builder()
                        .tipoServico(ServicoEnum.MANUTENCAO)
                        .valor(valor)
                        .build())
                .build();

        contratoService.save(manutencaoServico);
        return true;
    }

    public Jazigo detalharJazigo(Long id) {
        return repository.findById(id).get();
    }


    public boolean trocarPlano(String token, Long id, String tipo) {
        Optional<Jazigo> optionalJazigo = repository.findById(id);

        if(optionalJazigo.isEmpty()){
            throw new NoSuchElementException("Jazigo não encontrado");
        } else {
            Jazigo jazigo = optionalJazigo.get();

            Servico plano = this.servicoService.findByTipoServico(ServicoEnum.valueOf(tipo));

            Long idCliente = jwtService.extractId(token);
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

    public boolean finalizarCompra(String token, List<CarrinhoDTO> carrinho) {

        Long idCliente = jwtService.extractId(token);

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
}
