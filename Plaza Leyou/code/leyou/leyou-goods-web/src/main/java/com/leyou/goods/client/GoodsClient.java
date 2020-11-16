package com.leyou.goods.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service") //后期创建一个实现类，即熔断类
public interface GoodsClient extends GoodsApi {

}
