package com.zjr.assistant.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Object data;
    private int code;
    private String msg;

    public Result(Object data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
