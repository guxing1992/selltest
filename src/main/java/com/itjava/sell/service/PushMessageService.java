package com.itjava.sell.service;

import com.itjava.sell.dta.OrderDTO;

public interface PushMessageService {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
