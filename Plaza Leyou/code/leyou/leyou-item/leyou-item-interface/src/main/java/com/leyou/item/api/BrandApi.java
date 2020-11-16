package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("brand")
public interface BrandApi {

    /**
     * query Brands by Cid
     * @param cid
     * @return
     */
    @GetMapping("cid/{cid}")
    public List<Brand> queryBrandsByCid(@PathVariable("cid") Long cid);

    /**
     * Buscar marca con brandId
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id")Long id);
}
