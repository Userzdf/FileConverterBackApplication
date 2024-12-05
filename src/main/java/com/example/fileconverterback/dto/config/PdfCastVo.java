package com.example.fileconverterback.dto.config;

import org.springframework.stereotype.Component;

@Component
public class PdfCastVo {
    private String OnServerPath;
    private String proxyServerPath;

    public String getOnServerPath() {
        return OnServerPath;
    }

    public void setOnServerPath(String onServerPath) {
        OnServerPath = onServerPath;
    }

    public String getProxyServerPath() {
        return proxyServerPath;
    }

    public void setProxyServerPath(String proxyServerPath) {
        this.proxyServerPath = proxyServerPath;
    }

    @Override
    public String toString() {
        return "PdfCastVo{" +
                "OnServerPath='" + OnServerPath + '\'' +
                ", proxyServerPath='" + proxyServerPath + '\'' +
                '}';
    }
}
