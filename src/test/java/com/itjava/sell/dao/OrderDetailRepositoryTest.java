package com.itjava.sell.dao;

import com.itjava.sell.bean.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void findByOrOrderId() {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId(String.valueOf(UUID.randomUUID()));
        orderDetail.setOrderId("123");
        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void save(){

    }
}