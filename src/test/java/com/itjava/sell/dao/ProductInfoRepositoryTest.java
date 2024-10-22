package com.itjava.sell.dao;

import com.itjava.sell.bean.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Test
    public void testSave(){
        ProductInfo productInfo=new ProductInfo("123456","皮蛋",new BigDecimal(3.3),100,"皮蛋",
                "http://www.baidu.com",new Integer(1),0);
        productInfoRepository.save(productInfo);
    }
    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }
}