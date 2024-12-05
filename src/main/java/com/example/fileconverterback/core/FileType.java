package com.example.fileconverterback.core;

public enum FileType {
    DOC(1,".doc"),
    DOCX(2,".docx"),
    EXCEL(3,".xlsx"),
    PDF(4,".pdf");
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    FileType(int code,String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
