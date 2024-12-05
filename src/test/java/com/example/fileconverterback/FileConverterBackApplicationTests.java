package com.example.fileconverterback;

import com.example.fileconverterback.core.ConverterFile;
import com.example.fileconverterback.dto.config.PdfCastVo;
import com.example.fileconverterback.dto.config.SystemConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class FileConverterBackApplicationTests {
    @Resource
    SystemConfig systemConfig;
    @Resource
    ConverterFile converterFile;

    @Test
    void contextLoads() {
        String pdfFilePath = "/Users/zhoudafeng/desktop/打工日记/周达锋/每周作业计划1.pdf";
        // 输出 Word 文件路径
         String wordFilePath = systemConfig.getWordFilePath();
        UUID randomName = UUID.randomUUID();
         wordFilePath+= randomName +".docx";
         PdfCastVo pdfCastVo = new PdfCastVo();
         pdfCastVo.setProxyServerPath(systemConfig.getProxyPath()+randomName+".docx");
        // 转换 PDF 到 Word
         pdfCastVo.setOnServerPath(converterFile.convertPdfToWord(pdfFilePath, wordFilePath));
         System.out.println(pdfCastVo);
//        System.out.println(systemConfig);
    }
}
