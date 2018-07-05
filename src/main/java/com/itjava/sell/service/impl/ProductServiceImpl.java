package com.itjava.sell.service.impl;

import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.dao.ProductInfoRepository;
import com.itjava.sell.dta.CartDTO;
import com.itjava.sell.enums.ProductStatusEnum;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Override
    @Transactional
    public ProductInfo findOne(String productId) {
//        return productInfoRepository.getOne(productId);
        return productInfoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAllByPage(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
            }
            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
