package com.itjava.sell.service;

import com.itjava.sell.dta.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    public PayResponse wxnotify(String notifyData);
}
