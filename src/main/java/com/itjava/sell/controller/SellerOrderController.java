package com.itjava.sell.controller;

import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端控制器
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    /**
     *
     * @param page 第几页
     * @param size 每页显示多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "size",defaultValue = "2") Integer size,
                             Map<String,Object> map){
        Pageable pageable=PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageable);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try{
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】查询不到订单");
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
//        orderService.cancel(orderDTO);
        map.put("msg",ResultEnum.Order_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端查询订单】查询异常，{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try{
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】查询不到订单");
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
//        orderService.cancel(orderDTO);
        map.put("msg",ResultEnum.ORDER_FINISHED_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
