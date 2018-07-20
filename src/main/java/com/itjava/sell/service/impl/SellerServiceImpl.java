package com.itjava.sell.service.impl;

import com.itjava.sell.bean.SellerInfo;
import com.itjava.sell.dao.SellerRepository;
import com.itjava.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openId) {
        return sellerRepository.findByOpenid(openId);
    }
}
