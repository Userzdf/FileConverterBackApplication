package com.example.fileconverterback.core;

import com.example.fileconverterback.dto.config.SystemConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class ConverterFile {

    private final static Logger LOG = LoggerFactory.getLogger(ConverterFile.class);
    public String convertPdfToWord(String pdfFilePath, String wordFilePath) {
        try {
            // 1. 加载 PDF 文件
            PDDocument pdfDocument = PDDocument.load(new File(pdfFilePath));

            // 2. 提取 PDF 文本
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String pdfText = pdfTextStripper.getText(pdfDocument);

            // 3. 创建 Word 文档
            XWPFDocument wordDocument = new XWPFDocument();

            // 4. 按段落写入 Word
            String[] paragraphs = pdfText.split("\n");
            for (String paragraph : paragraphs) {
                // 创建段落并写入内容
                XWPFParagraph wordParagraph = wordDocument.createParagraph();
                wordParagraph.createRun().setText(paragraph);
            }

            File file = new File(wordFilePath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            // 5. 保存 Word 文档
            FileOutputStream outputStream = new FileOutputStream(wordFilePath);
            wordDocument.write(outputStream);

            // 6. 关闭资源
            outputStream.close();
            wordDocument.close();
            pdfDocument.close();
            LOG.info("PDF 转换为 Word 成功，文件保存在：{}",wordFilePath);
            return wordFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("PDF 转换为 Word 失败：{}",e.getMessage());
        }
        return null;
    }

    public String convertPdfToExcel(String pdfFilePath, String excelFilePath) throws IOException {
        // 1. 加载 PDF 文件
        PDDocument pdfDocument = PDDocument.load(new File(pdfFilePath));

        // 2. 提取 PDF 文本
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfText = pdfStripper.getText(pdfDocument);

        // 3. 创建 Excel 工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PDF Data");

        // 4. 按行分割文本（假设 PDF 表格内容按行存储）
        String[] rows = pdfText.split("\n");

        int rowIndex = 0;
        for (String row : rows) {
            Row excelRow = sheet.createRow(rowIndex++);
            String[] cells = row.split("\\s+"); // 假设表格数据用空格分隔

            int cellIndex = 0;
            for (String cell : cells) {
                Cell excelCell = excelRow.createCell(cellIndex++);
                excelCell.setCellValue(cell);
            }
        }

        // 5. 保存为 Excel 文件
        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }

        // 6. 关闭资源
        workbook.close();
        pdfDocument.close();
        return excelFilePath;
    }
}
