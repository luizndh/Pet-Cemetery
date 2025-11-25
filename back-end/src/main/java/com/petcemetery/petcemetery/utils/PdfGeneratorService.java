package com.petcemetery.petcemetery.utils;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.petcemetery.petcemetery.contrato.dto.ContratoDTO;
import com.petcemetery.petcemetery.jazigo.dto.OcupacaoJazigoDTO;

@Service
public class PdfGeneratorService {

    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLD);
    private static final Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
    private static final Font DATA_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);

    public byte[] gerarPDFJazigos(List<OcupacaoJazigoDTO> jazigos) {
        return generatePdf(document -> {
            addTitle(document, "Relatório de Jazigos");

            PdfPTable table = createTable(6);
            addTableHeader(table, "PET", "DATA ENTERRO", "ENDEREÇO", "ESPÉCIE", "PLANO", "CPF CLIENTE");

            for (OcupacaoJazigoDTO jazigo : jazigos) {
                table.addCell(createCell(jazigo.getPet()));
                table.addCell(createCell(jazigo.getDataEnterro() != null ? jazigo.getDataEnterro() : ""));
                table.addCell(createCell(jazigo.getJazigo()));
                table.addCell(createCell(jazigo.getEspecie()));
                table.addCell(createCell(jazigo.getPlano()));
                table.addCell(createCell(String.valueOf(jazigo.getCpf())));
            }
            document.add(table);
        });
    }

    public byte[] gerarPDFEnterros(List<ContratoDTO> enterros) {
        return gerarPDFContratos(enterros, "Relatório de Enterros");
    }

    public byte[] gerarPDFExumacoes(List<ContratoDTO> exumacoes) {
        return gerarPDFContratos(exumacoes, "Relatório de Exumações");
    }

    private byte[] gerarPDFContratos(List<ContratoDTO> contratos, String titulo) {
        return generatePdf(document -> {
            addTitle(document, titulo);

            PdfPTable table = createTable(4);
            addTableHeader(table, "VALOR", "JAZIGO", "CPF", "DATA");

            for (ContratoDTO contrato : contratos) {
                table.addCell(createCell(String.valueOf(contrato.getValor())));
                table.addCell(createCell(contrato.getEnderecoJazigo()));
                table.addCell(createCell(contrato.getCpfCliente()));
                table.addCell(createCell(contrato.getDataServico()));
            }
            document.add(table);
        });
    }

    private byte[] generatePdf(DocumentConsumer consumer) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            consumer.accept(document);
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private void addTitle(Document document, String text) throws DocumentException {
        Paragraph title = new Paragraph(text, TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
    }

    private PdfPTable createTable(int numColumns) {
        PdfPTable table = new PdfPTable(numColumns);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        return table;
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setPhrase(new Phrase(header, HEADER_FONT));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
        }
    }

    private PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content != null ? content : "", DATA_FONT));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    @FunctionalInterface
    private interface DocumentConsumer {
        void accept(Document document) throws DocumentException;
    }
}
