package com.itjava.sell.service.impl;

import com.itjava.sell.bean.OrderDetail;
import com.itjava.sell.bean.ProductInfo;
import com.itjava.sell.dao.OrderDetailRepository;
import com.itjava.sell.dao.OrderMasterRepository;
import com.itjava.sell.dao.ProductInfoRepository;
import com.itjava.sell.dta.OrderDTO;
import com.itjava.sell.service.OrderService;
import com.itjava.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo==null){
            }
        }
        //2.计算总价
        //3.写入数据库
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }
}
