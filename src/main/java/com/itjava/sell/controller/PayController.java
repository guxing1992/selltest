package com.itjava.sell.controller;

import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.OrderService;
import com.itjava.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl")String returnUrl,
                               Map<String,Object> map){
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NO_EXIT);
        }

        //2.发起支付
//        PayResponse payResponse = payService.create(orderDTO);
//        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 接受微信支付的异步通知
     */
    @PostMapping("/notify")
    public ModelAndView wxnotify(@RequestBody String notifyData){
        payService.wxnotify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
