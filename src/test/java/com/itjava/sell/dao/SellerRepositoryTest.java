package com.itjava.sell.dao;

import com.itjava.sell.bean.SellerInfo;
import com.itjava.sell.util.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerRepositoryTest {
    @Autowired
    private SellerRepository sellerRepository;
    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtils.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo result = sellerRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerRepository.findByOpenid("abc");
        Assert.assertEquals("abc",sellerInfo.getOpenid());
    }
}