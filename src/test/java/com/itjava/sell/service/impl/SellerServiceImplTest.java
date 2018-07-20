package com.itjava.sell.service.impl;

import com.itjava.sell.bean.SellerInfo;
import com.itjava.sell.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {
    @Autowired
    private SellerService sellerService;
    private  static final  String OPENID="abc";
    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(OPENID);
        System.out.println("sellerInfo:"+sellerInfo);
    }
}