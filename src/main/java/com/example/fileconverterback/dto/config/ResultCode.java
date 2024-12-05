package com.example.fileconverterback.dto.config;

public enum ResultCode {
    SUCCESS(1,"操作成功"),
    FAILURE(-1,"操作失败");
    private Integer code;
    private String msg;


    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
