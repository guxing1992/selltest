package com.itjava.sell.service.impl;

import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.enums.ProductStatusEnum;
import com.itjava.sell.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findOne() {
        ProductInfo one = productService.findOne("123456");
        System.out.println("one:"+one);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> all = productService.findUpAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findAllByPage() {
        int pageNo=3+1;
        int pageSize=10;
        Pageable pageable=PageRequest.of(pageNo,pageSize);
        Page<ProductInfo> allByPage = productService.findAllByPage(pageable);
        System.out.println(allByPage.getContent());
    }

    @Test
    public void save() {
        for (int i=0;i<100;i++){
            ProductInfo productInfo=new ProductInfo("wang"+i,"wang"+i,new BigDecimal(5.5+i),100,"非常好的","www.163.com",1,new Integer(ProductStatusEnum.UP.getCode()));
            productService.save(productInfo);
        }

    }
}