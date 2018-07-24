package com.itjava.sell.vo;

import java.io.Serializable;
import java.util.List;

public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -5361662847667093305L;
    private Integer code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
