package com.itjava.sell.dao;

import com.itjava.sell.bean.OrderMaster;
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
import java.util.UUID;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    private static final String OPENID = "123";
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Test
    public void save(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId(String.valueOf(UUID.randomUUID()));
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("139");
        orderMaster.setBuyerAddress("四川");
        orderMaster.setBuyerOpenid("1234566");
        orderMaster.setOrderAmount(new BigDecimal(333.33));
        orderMaster.setOrderAmount(new BigDecimal(333.33));
        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
        Assert.assertNotEquals(null,orderMaster1);
    }
    @Test
    public void findByBuyerOpenid() throws  Exception{
        int pageNo=1;
        int pageSize=20;
        Pageable pageable=PageRequest.of(pageNo,pageSize);
        Page<OrderMaster> orderMasterPages = orderMasterRepository.findByBuyerOpenid(OPENID, pageable);
        System.out.println(orderMasterPages);

    }
}