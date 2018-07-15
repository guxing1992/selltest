package com.itjava.sell.service.impl;

import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.OrderService;
import com.itjava.sell.service.PayService;
import com.itjava.sell.util.JsonUtil;
import com.itjava.sell.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付request={}",payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付response】={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse wxnotify(String notifyData) {
        //1.验证签名
        //2.验证支付的状态
        //3.支付金额
        //4.支付的人（下单人==支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，PayResponse={}",JsonUtil.toJson(payResponse));
        //接受到通知后修改订单状态
        OrderDTO orderDTO=orderService.findOne(payResponse.getOrderId());
        if (orderDTO==null){
            log.error("微信支付】异步通知，订单不存在,orderId={}",payResponse.getOrderId());
            throw  new SellException(ResultEnum.ORDER_NO_EXIT);
        }
        //判断金额是否一致
        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("微信支付】异步通知，金额不一致,orderId={}",payResponse.getOrderId());
            throw  new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 request={}",refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 response={}",refundResponse);
        return refundResponse;
    }
}
