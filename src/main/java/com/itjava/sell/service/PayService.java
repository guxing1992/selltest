package com.itjava.sell.service;

import com.itjava.sell.dta.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    public PayResponse wxnotify(String notifyData);


    RefundResponse refund(OrderDTO orderDTO);

}
