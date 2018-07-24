package com.itjava.sell.service.impl;

import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.service.OrderService;
import com.itjava.sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private OrderService orderService;
    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1530781881809531831");
        pushMessageService.orderStatus(orderDTO);
    }
}