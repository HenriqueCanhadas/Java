import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.*;

public class CriarExcel {
    public static void main(String[] args) {
        gerarPlanilha("/Java/arquivo.xlsx");
    }

    public static void gerarPlanilha(String caminhoArquivo) {
        // Criar um novo arquivo do Excel (.xlsx)
        Workbook workbook = new XSSFWorkbook();

        // Acessar uma planilha dentro do arquivo ou criar uma nova
        Sheet sheet = workbook.createSheet("MinhaPlanilha");

        // Criar uma linha e adicionar células a ela
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("Nome");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Idade");

        // Criar outra linha e adicionar mais dados
        Row row2 = sheet.createRow(1);
        Cell cell3 = row2.createCell(0);
        cell3.setCellValue("João");
        Cell cell4 = row2.createCell(1);
        cell4.setCellValue(30);

        // Salvar o arquivo Excel
        try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivo)) {
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Arquivo Excel criado com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao criar o arquivo Excel.");
        }
    }
}