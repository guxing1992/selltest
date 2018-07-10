package com.itjava.sell.service.impl;

import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.service.OrderService;
import com.itjava.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Test
    public void create() {

        OrderDTO orderDTO = orderService.findOne("1530781881809531831");
        payService.create(orderDTO);
    }
}