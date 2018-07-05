package com.itjava.sell.service;

import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.dta.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在线商品
     * @return
     */
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAllByPage(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
