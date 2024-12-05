package com.example.fileconverterback.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.fileconverterback.dto.config.ResultCode;

public final class Result {

    private int code;
    private String msg;
    private Object data;

    public Result() {

    }

    public static Result newInstance() {
        return new Result();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public Result success() {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        return this;
    }

    public Result success(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
        return this;
    }

    public Result success(String customMsg) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = customMsg;
        return this;
    }

    public Result failure() {
        this.code = ResultCode.FAILURE.getCode();
        this.msg = ResultCode.FAILURE.getMsg();
        return this;
    }

    public Result failure(String customMsg) {
        this.code = ResultCode.FAILURE.getCode();
        this.msg = customMsg;
        return this;
    }

    @Override
    public String toString() {
//		SerializerFeature[] serializerFeatures = { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
//				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse };
        SerializerFeature[] serializerFeatures = { };
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", serializerFeatures);
    }
}

