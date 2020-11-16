package com.leyou.cart.controller;

import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Añadir cart record in Redis
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        this.cartService.addCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Consulta cart record in Redis
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cart>> queryCart(){
        List<Cart> carts = this.cartService.queryCart();
        if (CollectionUtils.isEmpty(carts)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carts);
    }

    /**
     * Actualizar la cantidad de artículos en la cesta
     * @param cart
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateNum(@RequestBody Cart cart){
        this.cartService.updateNum(cart);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{skuId}")
    public ResponseEntity<Void> deleteCart(@PathVariable("skuId") String skuId) {
        if (StringUtils.isBlank(skuId)){
            return ResponseEntity.notFound().build();
        }

        String separator = "-";
        if (StringUtils.contains(skuId, separator)){
            String[] ids = StringUtils.split(skuId, separator);
            for (String id : ids) {
                this.cartService.deleteCart(id);
            }
        }else{
            this.cartService.deleteCart(skuId);
        }

        return ResponseEntity.noContent().build();
    }
}
