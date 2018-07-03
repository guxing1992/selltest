package com.itjava.sell.dao;

import com.itjava.sell.bean.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void findByOrderId() {

    }

    @Test
    public void save(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("123");
        orderDetail.setProductId("wang11");
        orderDetail.setProductName("wang11");
        orderDetail.setProductPrice(new BigDecimal(16.5));
        orderDetail.setProductQuantity(5);
        orderDetail.setProductIcon("www.163.com");

        orderDetailRepository.save(orderDetail);
    }
}