package com.leyou.goods.service.impl;

import com.leyou.goods.client.BrandClient;
import com.leyou.goods.client.CategoryClient;
import com.leyou.goods.client.GoodsClient;
import com.leyou.goods.client.SpecificationClient;
import com.leyou.goods.service.GoodsService;
import com.leyou.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    public Map<String, Object> loadData(Long spuId){
        Map<String, Object> model = new HashMap<>();

        //query spu
        Spu spu = this.goodsClient.querySpuBySpuId(spuId);

        //quert spuDetail
        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spuId);

        //query categories -> Map<String, Object>
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> names = this.categoryClient.queryNamesByIds(cids);
        //create an ArrayList
        List<Map<String, Object>> categories = new ArrayList<>();
        for (int i = 0; i < cids.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", cids.get(i));
            map.put("name", names.get(i));
            categories.add(map);
        }

        //query brand
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());

        //query skus
        List<Sku> skus = this.goodsClient.querySkusBySpuId(spuId);

        //query specGroups
        List<SpecGroup> groups = this.specificationClient.queryGroupsWithParam(spu.getCid3());

        //query specParams -> map
        List<SpecParam> params = this.specificationClient.queryParams(
                null, spu.getCid3(), false, null);
        //create a hashmap <specParamId, specParamName>
        Map<Long, String> paramMap = new HashMap<>();
        params.forEach(specParam -> {
            paramMap.put(specParam.getId(), specParam.getName());
        });

        model.put("spu", spu);
        model.put("spuDetail", spuDetail);
        model.put("categories", categories);
        model.put("brand", brand);
        model.put("skus", skus);
        model.put("groups", groups);
        model.put("paramMap", paramMap);

        return model;
    }

}
