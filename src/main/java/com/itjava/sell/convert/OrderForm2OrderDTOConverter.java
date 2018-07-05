package com.itjava.sell.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itjava.sell.bean.OrderDetail;
import com.itjava.sell.controller.BuyerOrderController;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.enums.ResultEnum;
import com.itjava.sell.exception.SellException;
import com.itjava.sell.form.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {
    public static Logger log=LoggerFactory.getLogger(BuyerOrderController.class);
    public static OrderDTO covert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
        try{
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误，String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
