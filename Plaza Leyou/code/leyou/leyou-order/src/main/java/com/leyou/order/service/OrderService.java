package com.leyou.order.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderStatus;

import java.util.List;


public interface OrderService {

    public Long createOrder(Order order);

    public Order queryById(Long id);

    public PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status);

    public Boolean updateStatus(Long id, Integer status);

    public List<Long> querySkuIdByOrderId(Long orderId);

    public OrderStatus queryOrderStatusById(Long orderId);
}

