package com.itjava.sell.enums;

public enum ResultEnum {
    PRODUCT_NO_EXIT(10,"商品不存在"),PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NO_EXIT(12,"订单不存在"),ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    ORDER_PAY_ERROR(17,"支付状态错误"),
    PARAM_ERROR(18,"参数不正确"),
    CART_NOT_NULL(19,"购物车不能为空"),
    ORDER_OWNER_ERROR(20,"该订单不属于当前用户")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}