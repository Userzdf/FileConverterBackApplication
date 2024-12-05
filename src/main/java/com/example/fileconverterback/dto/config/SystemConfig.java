package com.example.fileconverterback.dto.config;

import com.example.fileconverterback.core.FileType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private String wordFilePath;
    private String proxyPath;
    private String tempPath;
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public void setFileName(UUID fileName) {
        this.fileName = fileName;
    }

    private UUID fileName = UUID.randomUUID();

    public  UUID getFileName() {
        return fileName;
    }

    public String getProxyPath() {
        return proxyPath + getFileName() + getFileType();
    }

    public void setProxyPath(String proxyPath) {
        this.proxyPath = proxyPath;
    }

    public String getWordFilePath() {
        return wordFilePath + getFileName() + getFileType();
    }

    public void setWordFilePath(String wordFilePath) {
        this.wordFilePath = wordFilePath;
    }

}
