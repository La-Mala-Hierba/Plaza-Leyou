package com.leyou.order.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.order.pojo.Order;
import com.leyou.order.pojo.OrderStatus;
import com.leyou.order.service.OrderService;
import com.leyou.utils.PayHelper;
import com.leyou.utils.PayState;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
@Api("Order Service API")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayHelper payHelper;

    /**
     * Crear un pedido
     *
     * @param order
     * @return Order Id
     */
    @PostMapping
    @ApiOperation(value = "Creación del pedido，return orderId", notes = "Creación del pedido")
    @ApiImplicitParam(name = "order", required = true, value = "json de order")
    public ResponseEntity<Long> createOrder(@RequestBody @Valid Order order) {
        Long id = this.orderService.createOrder(order);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    /**
     * Consultar al pedido con orderId
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation(value = "Consultar al pedido con orderId，return order", notes = "Consulta al pedido")
    @ApiImplicitParam(name = "id", required = true, value = "orderId")
    public ResponseEntity<Order> queryOrderById(@PathVariable("id") Long id) {
        Order order = this.orderService.queryById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(order);
    }

   /**
     * Consultar a los pedidos de usuarios por página
     *
     * @param status
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "Consultar a los pedidos de usuarios por página, y filtrar los pedidos con orderStatus", notes = "Consultar a los pedidos de usuarios por página")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "currentPage", defaultValue = "1", type = "Integer"),
            @ApiImplicitParam(name = "rows", value = "rows", defaultValue = "5", type = "Integer"),
            @ApiImplicitParam(name = "status", value = "orderStatus: 1 Pago pendient, 2 Pago hecho、Envío pendiente, 3 Envío hecho, 4 Recepción confirmada, 5 Cierre del pedido, 6 Transacción con éxito, comentario del usuario hecho", type = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Resultados de pedidos por página"),
            @ApiResponse(code = 404, message = "Resultado no encontrado"),
            @ApiResponse(code = 500, message = "Consulta fracasada"),
    })
    public ResponseEntity<PageResult<Order>> queryUserOrderList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "status", required = false) Integer status) {
        PageResult<Order> result = this.orderService.queryUserOrderList(page, rows, status);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Actualizar estado del pedido
     *
     * @param id
     * @param status
     * @return
     */
    @PutMapping("{id}/{status}")
    @ApiOperation(value = "Actualizar estado del pedido", notes = "Actualizar estado del pedido")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "orderId", type = "Long"),
            @ApiImplicitParam(name = "status", value = "orderStatus：1 Pago pendient, 2 Pago hecho、Envío pendiente, 3 Envío hecho, 4 Recepción confirmada, 5 Cierre del pedido, 6 Transacción con éxito, comentario del usuario hecho.", type = "Integer"),
    })

    @ApiResponses({
            @ApiResponse(code = 204, message = "true：Estado de pedido actualizado con éxito；false：Estado de pedido actualizado fallado"),
            @ApiResponse(code = 400, message = "Error de paramétros"),
            @ApiResponse(code = 500, message = "Consulta fallada")
    })
   public ResponseEntity<Boolean> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        if (id == null || status == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Boolean flag = this.orderService.updateStatus(id, status);
        if (flag == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Generar url del pago
     *
     * @param orderId
     * @return
     */
    @GetMapping("url/{id}")
    @ApiOperation(value = "Generar url del pago", notes = "Generar url del pago")
    @ApiImplicitParam(name = "id", value = "orderId", type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Generar url del pago con éxito"),
            @ApiResponse(code = 404, message = "Generar url del pago con fallado"),
            @ApiResponse(code = 500, message = "Error internal del servidor"),
    })
    public ResponseEntity<String> generateUrl(@PathVariable("id") Long orderId) {
        // Generar url del pago
        String url = this.payHelper.createPayUrl(orderId);
        if (StringUtils.isBlank(url)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(url);
    }

    /**
     * Consultar el estado del pago
     *
     * @param orderId
     * @return 0, No resultados de consultar el estado del pago 1,Pago hecho 2,Pago fallado
     */
    @GetMapping("state/{id}")
    @ApiOperation(value = "Consultar el estado del pago", notes = "Consultar el estado del pago")
    @ApiImplicitParam(name = "id", value = "orderId", type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "0, No resultados de consultar el estado del pago 1,Pago hecho 2,Pago fallado"),
            @ApiResponse(code = 500, message = "Error interno del servidor"),
    })
    public ResponseEntity<Integer> queryPayState(@PathVariable("id") Long orderId) {
        PayState payState = this.payHelper.queryOrder(orderId);
        return ResponseEntity.ok(payState.getValue());
    }

    /**
     * Buscar todos los skuId según orderId
     * @param orderId
     * @return
     */
    @GetMapping("skuId/{orderId}")
    @ApiOperation(value = "Buscar todos los skuId según orderId",notes = "Buscar skuId")
    @ApiImplicitParam(name = "orderId",value = "orderId",type = "Long")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Lista de skuId"),
            @ApiResponse(code = 404,message = "No resultado encontrado"),
            @ApiResponse(code = 500,message = "Error del servidor")
    })
    public ResponseEntity<List<Long>> querySkuIdByOrderId(@PathVariable("orderId") Long orderId){
        if (orderId == null){
            return ResponseEntity.notFound().build();
        }
        List<Long> skuList = this.orderService.querySkuIdByOrderId(orderId);
        if (skuList == null || skuList.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skuList);
    }

    /**
     * Consultar al estado de pedido con orderId
     *
     * @param orderId
     * @return
     */
    @GetMapping("orderstatus/{id}")
    @ApiOperation(value = "Consultar al estado de pedido con orderId，return orderSatus", notes = "Consultar al estado de pedido con orderId")
    @ApiImplicitParam(name = "id", required = true, value = "orderId")
    public ResponseEntity<OrderStatus> queryOrderStatusById(@PathVariable("id") Long orderId) {
        if (orderId == null){
            return ResponseEntity.badRequest().build();
        }
        OrderStatus orderStatus = this.orderService.queryOrderStatusById(orderId);
        if (orderStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(orderStatus);
    }
}
