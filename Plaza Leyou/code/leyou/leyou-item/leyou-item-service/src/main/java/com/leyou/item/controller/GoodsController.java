package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Incluye Spu, SpuDetails, Sku
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * query Spus with Pageable by conditions
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    ){
        PageResult<SpuBo> result = this.goodsService.querySpuByPage(key, saleable, sortBy, desc, page, rows);
        if (result == null || CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * save good
     * @param spuBo
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo){
        if (spuBo == null){
            return ResponseEntity.badRequest().build();
        }
        this.goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * query SpuDetail by SpuId
     * @param spuId
     * @return
     */
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId") Long spuId){
        if (spuId == null || spuId < 0){
            return ResponseEntity.badRequest().build();
        }
        SpuDetail spuDetail = this.goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    /**
     * query skus by spuId
     * @param spuId
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id") Long spuId){
        if (spuId == null || spuId < 0){
            return ResponseEntity.badRequest().build();
        }
        List<Sku> skus = this.goodsService.querySkusBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }

    /**
     * update good
     * @param spuBo
     * @return
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        if (spuBo == null){
            return ResponseEntity.badRequest().build();
        }
        this.goodsService.updateGoods(spuBo);
        // 204
        return ResponseEntity.noContent().build();
    }

    /**
     * Eliminación lógica de producto según spuId(No se trata de eliminación física)
     * @param spuId
     * @return
     */
    @PutMapping("goods/spu/{spuId}")
    public ResponseEntity<Void> deleteGoods(@PathVariable("spuId") String spuId){
        if (spuId == null){
            return ResponseEntity.badRequest().build();
        }
        String separator = "-";
        if (spuId.contains(separator)){
            String[] spuIds = spuId.split(separator);
            for (String id : spuIds) {
                this.goodsService.deleteGood(Long.parseLong(id));
            }
        }else{
                this.goodsService.deleteGood(Long.parseLong(spuId));
            }
        return ResponseEntity.noContent().build();
    }

    /**
     * Dar de baja el producto con el spuId
     * @param spuId
     * @return
     */
    @PutMapping("goods/spu/status/{spuId}")
    public ResponseEntity<Void> changeSaleableStatus(@PathVariable("spuId") Long spuId){
        if (spuId == null || spuId < 0){
            return ResponseEntity.badRequest().build();
        }
        this.goodsService.changeSaleableStatus(spuId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar Spu con SpuId
     * @param spuId
     * @return
     */
    @GetMapping("{spuId}")
    public ResponseEntity<Spu> querySpuBySpuId(@PathVariable("spuId") Long spuId){
        if (spuId == null || spuId < 0){
            return ResponseEntity.badRequest().build();
        }
        Spu spu = this.goodsService.querySpuBySpuId(spuId);
        if (spu == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spu);
    }

    @GetMapping("sku/{skuId}")
    public ResponseEntity<Sku> querySkuBySkuId(@PathVariable("skuId") Long skuId){
        if (skuId == null || skuId < 0){
            return ResponseEntity.badRequest().build();
        }
        Sku sku = this.goodsService.querySkuBySkuId(skuId);
        if (sku == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sku);
    }
}
