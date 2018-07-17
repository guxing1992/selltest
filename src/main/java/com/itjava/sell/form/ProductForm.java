package com.itjava.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class ProductForm {
    private String productId;

    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /**
     * 类目编号
     */
    private Integer categoryType;
//    /**
//     * 商品状况 0，正常，1，下架
//     */
//    private Integer productStatus;

}
