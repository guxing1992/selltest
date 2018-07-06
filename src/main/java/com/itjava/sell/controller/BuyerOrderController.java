package com.itjava.sell.controller;

import com.itjava.sell.convert.OrderForm2OrderDTOConverter;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.form.OrderForm;
import com.itjava.sell.service.BuyerService;
import com.itjava.sell.service.OrderService;
import com.itjava.sell.service.impl.OrderServiceImpl;
import com.itjava.sell.util.ResultVOUtils;
import com.itjava.sell.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    Logger log=LoggerFactory.getLogger(BuyerOrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO=OrderForm2OrderDTOConverter.covert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_NOT_NULL);
        }
        OrderDTO resultOrderDTO = orderService.create(orderDTO);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",resultOrderDTO.getOrderId());
        return ResultVOUtils.success(map);
    }
    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultVO<OrderDTO> list(@RequestParam("openid") String openid,@RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Pageable pageable=PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageable);
        return ResultVOUtils.success(orderDTOPage.getContent());
    }
    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        //TODO不安全做法
//        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtils.success(orderDTO);
    }
    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        //TODO不安全做法
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        orderService.cancel(orderDTO);
        buyerService.cancelOrderOne(openid,orderId);
        return ResultVOUtils.success();
    }
}
