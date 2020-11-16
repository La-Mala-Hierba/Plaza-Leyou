package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

public interface GoodsService {
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, String sortBy, Boolean desc,Integer page, Integer rows);

    public void saveGoods(SpuBo spuBo);

    public SpuDetail querySpuDetailBySpuId(Long spuId);

    public List<Sku> querySkusBySpuId(Long spuId);

    public void updateGoods(SpuBo spuBo);

    public void deleteGood(long id);

    public void changeSaleableStatus(Long spuId);

    public Spu querySpuBySpuId(Long spuId);

    public Sku querySkuBySkuId(Long skuId);
}
