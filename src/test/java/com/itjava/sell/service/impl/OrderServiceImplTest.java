package com.itjava.sell.service.impl;

import com.itjava.sell.bean.OrderDetail;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.service.OrderService;
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    Logger log=LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("王先森");
        orderDTO.setBuyerPhone("119119");
        orderDTO.setBuyerAddress("四川");
        orderDTO.setBuyerOpenid("110110110");
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("wang10");
        o1.setProductQuantity(3);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        System.out.println("result{}:"+result);
    }
    @Test
    public void findOne(){
        OrderDTO result = orderService.findOne("1530690195376435575");
        log.info("【查询单个订单】result={}",result.toString());
    }


}