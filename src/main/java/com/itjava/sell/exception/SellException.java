package com.itjava.sell.exception;

import com.itjava.sell.enums.ResultEnum;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code=code;
    }
}
