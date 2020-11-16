package com.leyou.cart.service.impl;

import com.leyou.cart.client.GoodsClient;
import com.leyou.cart.interceptor.LoginInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.cart.service.CartService;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.Sku;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:cart";

    /**
     * Añadir cart record in Redis
     * @param cart
     * @return
     */
    @Override
    @Transactional
    public void addCart(Cart cart) {

        //obtener UserInfo
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        //chequear cart record in Redis
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

        String key = cart.getSkuId().toString();
        Integer num = cart.getNum();

        if (hashOps.hasKey(key)){
            // cart record ya existe en Redis --> actualizar qty
            String cartJson = hashOps.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum()+num);

        }else {
            // cart record no existe en Redis --> añadir cart
            Sku sku = this.goodsClient.querySkuBySkuId(cart.getSkuId());
            cart.setUserId(userInfo.getId());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
        }

        hashOps.put(key, JsonUtils.serialize(cart));
    }

    /**
     * Consulta cart record del usuario in Redis
     * @return
     */
    @Override
    public List<Cart> queryCart() {
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // chequear si hay cart record bajo cuenta del usuario
        if (!this.redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())){
            return null;
        }

        //obtener cart record del usuario in Redis
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

        List<Object> cartsJson = hashOps.values();

        // chequear si la cesta está vacía
        if (CollectionUtils.isEmpty(cartsJson)){
            return null;
        }

        return cartsJson.stream().map(cartJson -> JsonUtils.parse(cartJson.toString(), Cart.class)).collect(Collectors.toList());
    }

    /**
     * Actualizar la cantidad de artículos en la cesta
     * @param cart
     * @return
     */
    @Override
    @Transactional
    public void updateNum(Cart cart) {
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        if (!this.redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())){
            return;
        }
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());

        String key = cart.getSkuId().toString();
        Integer num = cart.getNum();

        String cartJson = hashOps.get(key).toString();
        cart = JsonUtils.parse(cartJson, Cart.class);
        cart.setNum(num);
        hashOps.put(key, JsonUtils.serialize(cart));

    }

    @Override
    @Transactional
    public void deleteCart(String id) {
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        hashOps.delete(id);
    }
}
