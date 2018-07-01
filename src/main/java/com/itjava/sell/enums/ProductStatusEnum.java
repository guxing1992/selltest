package com.itjava.sell.enums;

public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");
    private Integer code;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
    private String message;
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
