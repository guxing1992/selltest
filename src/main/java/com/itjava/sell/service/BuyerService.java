package com.itjava.sell.service;

import com.itjava.sell.dta.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);
    OrderDTO cancelOrderOne(String openid,String orderId);

}
