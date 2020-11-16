package com.leyou.order.service.impl;

import com.leyou.common.pojo.PageResult;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.IdWorker;
import com.leyou.order.interceptor.LoginInterceptor;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderDetail;
import com.leyou.order.pojo.OrderStatus;
import com.leyou.order.repository.OrderDetailRepository;
import com.leyou.order.repository.OrderRepository;
import com.leyou.order.repository.OrderStatusRepository;
import com.leyou.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Order order) {
        // generar orderId
        long orderId = idWorker.nextId();
        // Obtener userinfo
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        // set info del usuario
        order.setBuyerNick(userInfo.getUsername());
        order.setBuyerRate(false);
        order.setCreateTime(new Date());
        order.setOrderId(orderId);
        order.setUserId(userInfo.getId());
        // guardar order
        this.orderRepository.save(order);

        // guardar order status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCreateTime(order.getCreateTime());
        orderStatus.setStatus(1);// el estado inicial es unpaid

        this.orderStatusRepository.save(orderStatus);

        // añadir orderId en orderDetails
        order.getOrderDetails().forEach(orderDetail ->  orderDetail.setOrderId(orderId));
        // guardar orderDetails
        this.orderDetailRepository.saveAll(order.getOrderDetails());
        // disminuir el stock de sku

        logger.debug("Create order，orderId：{}，userId：{}", orderId, userInfo.getId());

        return orderId;
    }

    /**
     * Consulta al pedido con orderId
     *
     * @param id
     * @return
     */
    public Order queryById(Long id) {

        // consulta al order
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();

            // consulta al orderDetail
            List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrderId(id);
            order.setOrderDetails(orderDetails);

            // consulta al orderStatus
            Optional<OrderStatus> statusOptional = this.orderStatusRepository.findById(id);
            if (statusOptional.isPresent()){
                OrderStatus orderStatus = statusOptional.get();
                order.setStatus(orderStatus.getStatus());
            }
            return order;
        }
        return null;
    }

    /**
     * Consultar a los pedidos de usuarios por página
     *
     * @param status
     * @return
     */
    public PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status) {

        UserInfo userInfo = LoginInterceptor.getLoginUser();
        Pageable pageable = PageRequest.of(page - 1, rows);

        Page<Order> orderPage = null;
        try {
            if (status == null || status == 0){
                orderPage = this.orderRepository.queryOrderList(userInfo.getId(), pageable);
            }else {
                orderPage = this.orderRepository.queryOrderList(userInfo.getId(), status, pageable);
            }

            List<Order> orderList = orderPage.getContent();
            orderList.forEach(order -> {
                // añadir orderDetails al order
                List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrderId(order.getOrderId());
                order.setOrderDetails(orderDetails);

                // añadir orderStatus al order
                Optional<OrderStatus> statusOptional = this.orderStatusRepository.findById(order.getOrderId());
                if (statusOptional.isPresent()){
                    OrderStatus orderStatus = statusOptional.get();
                    order.setStatus(orderStatus.getStatus());
                }
            });
            return new PageResult<>(orderPage.getTotalElements(), orderPage.getTotalPages(), orderPage.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error de consulta a pedidos", e);
            return null;
        }
    }

    /**
     * Actualizar estado del pedido
     *
     * @param id
     * @param status
     * @return
     */
    @Transactional
    public Boolean updateStatus(Long id, Integer status) {

        Optional<OrderStatus> statusOptional = this.orderStatusRepository.findById(id);
        if (statusOptional.isPresent()){
            OrderStatus orderStatus = statusOptional.get();
            Integer originalStatus = orderStatus.getStatus();

            //Actualizar el estado con condiciones -> Evitar el salto de cambio de estado
            if (status - originalStatus == 1){
                orderStatus.setStatus(status);
                // Modificar el pedido según el status
                switch (status) {
                    case 2:
                        orderStatus.setPaymentTime(new Date());// Pagado
                        break;
                    case 3:
                        orderStatus.setConsignTime(new Date());// Enviado
                        break;
                    case 4:
                        orderStatus.setEndTime(new Date());// Recepcionado
                        break;
                    case 5:
                        orderStatus.setCloseTime(new Date());// Cerrado
                        break;
                    case 6:
                        orderStatus.setCommentTime(new Date());// Comentado
                        break;
                    default:
                        return null;
                }
                this.orderStatusRepository.save(orderStatus);
                return true;
            }
            return null;
        }

        return null;
    }

    /**
     * Buscar todos los skuId según orderId
     * @param orderId
     * @return
     */
    @Override
    public List<Long> querySkuIdByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrderId(orderId);
        ArrayList<Long> skuList = new ArrayList<>();
        orderDetails.forEach(orderDetail -> skuList.add(orderDetail.getSkuId()));
        return skuList;
    }

    @Override
    public OrderStatus queryOrderStatusById(Long orderId) {
        Optional<OrderStatus> optional = this.orderStatusRepository.findById(orderId);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
