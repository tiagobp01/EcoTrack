package br.com.unicsul.ecotrack.service;

import br.com.unicsul.ecotrack.model.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    public byte[] gerarRelatorioPontosPorEstado(List<PontoColeta> pontos) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        // Título
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Relatório de Pontos de Coleta por Estado", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // Gráfico
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        Map<String, Long> pontosPorEstado = pontos.stream()
                .filter(p -> p.getEndereco() != null && p.getEndereco().getEstado() != null)
                .collect(Collectors.groupingBy(p -> p.getEndereco().getEstado().toUpperCase(), Collectors.counting()));

        pontosPorEstado.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                "Distribuição por Estado",
                dataset,
                true, true, false);

        // Customização básica do gráfico
        chart.setBackgroundPaint(Color.white);

        BufferedImage chartImage = chart.createBufferedImage(500, 300);
        ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
        ImageIO.write(chartImage, "png", chartOut);
        Image pdfImage = Image.getInstance(chartOut.toByteArray());
        pdfImage.setAlignment(Element.ALIGN_CENTER);
        pdfImage.setSpacingAfter(20);
        document.add(pdfImage);

        // Tabela de Dados
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        addTableCell(table, "Nome", true);
        addTableCell(table, "Cidade", true);
        addTableCell(table, "Estado", true);

        for (PontoColeta p : pontos) {
            addTableCell(table, p.getNome(), false);
            addTableCell(table, p.getEndereco() != null ? p.getEndereco().getCidade() : "-", false);
            addTableCell(table, p.getEndereco() != null ? p.getEndereco().getEstado() : "-", false);
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    public byte[] gerarRelatorioDescartes(List<Descarte> descartes) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Relatório de Solicitações de Descarte", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // Gráfico de Pizza por Situação
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        Map<String, Long> porSituacao = descartes.stream()
                .collect(Collectors.groupingBy(d -> d.getSituacao().getNome(), Collectors.counting()));
        porSituacao.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart("Situação das Solicitações", dataset, true, true, false);
        chart.setBackgroundPaint(Color.white);

        BufferedImage chartImage = chart.createBufferedImage(500, 300);
        ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
        ImageIO.write(chartImage, "png", chartOut);
        Image pdfImage = Image.getInstance(chartOut.toByteArray());
        pdfImage.setAlignment(Element.ALIGN_CENTER);
        pdfImage.setSpacingAfter(20);
        document.add(pdfImage);

        // Tabela de Dados
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        addTableCell(table, "Data", true);
        addTableCell(table, "Usuário", true);
        addTableCell(table, "Ponto", true);
        addTableCell(table, "Categoria", true);
        addTableCell(table, "Situação", true);

        for (Descarte d : descartes) {
            addTableCell(table, d.getDataDescarte().toString().split("T")[0], false);
            addTableCell(table, d.getUsuario().getNome(), false);
            addTableCell(table, d.getPontoColeta().getNome(), false);
            addTableCell(table, d.getCategoria().getNome(), false);
            addTableCell(table, d.getSituacao().getNome(), false);
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    public byte[] gerarRelatorioUsuarios(List<Usuario> usuarios) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titulo = new Paragraph("Relatório de Usuários do Sistema", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        addTableCell(table, "Nome", true);
        addTableCell(table, "E-mail", true);
        addTableCell(table, "Tipo", true);
        addTableCell(table, "Data Cadastro", true);

        for (Usuario u : usuarios) {
            addTableCell(table, u.getNome(), false);
            addTableCell(table, u.getEmail(), false);
            addTableCell(table, u.getTipo().getNome(), false);
            addTableCell(table, u.getDataCriacao().toString().split("T")[0], false);
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private void addTableCell(PdfPTable table, String text, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Phrase(text,
                isHeader ? FontFactory.getFont(FontFactory.HELVETICA_BOLD)
                        : FontFactory.getFont(FontFactory.HELVETICA)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        if (isHeader) {
            cell.setBackgroundColor(Color.LIGHT_GRAY);
        }
        table.addCell(cell);
    }
}
