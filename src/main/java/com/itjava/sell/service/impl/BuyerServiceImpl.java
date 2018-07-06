package com.itjava.sell.service.impl;

import com.itjava.sell.controller.BuyerOrderController;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.BuyerService;
import com.itjava.sell.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    Logger log=LoggerFactory.getLogger(BuyerServiceImpl.class);
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);

        return orderDTO;
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致.openid={}",openid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO==null){
            log.error("【取消订单】查不到订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NO_EXIT);
        }
        return orderService.cancel(orderDTO);
    }
}
