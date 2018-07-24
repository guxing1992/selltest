package com.itjava.sell.service.impl;

import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.dao.ProductInfoRepository;
import com.itjava.sell.dta.CartDTO;
import com.itjava.sell.enums.ProductStatusEnum;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
//    @Cacheable(cacheNames = "product",key = "123")
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
//    @CachePut(cacheNames = "product",key = "123")
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

    @Override
    public ProductInfo onSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findById(productId).get();
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
        }
        if (productInfo.getProductStatusEnum()==ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).get();
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
        }
        if (productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());

        return productInfoRepository.save(productInfo);
    }
}
