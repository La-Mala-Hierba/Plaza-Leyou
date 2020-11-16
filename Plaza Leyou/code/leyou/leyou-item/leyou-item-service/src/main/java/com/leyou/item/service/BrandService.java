package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;

import java.util.List;

public interface BrandService {

    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    public void save(Brand brand, List<Long> cids);

    public void edit(Brand brand, List<Long> cids);

    public void deleteBrand(long id);

    public List<Brand> queryBrandsByCid(Long cid);

    public Brand queryBrandById(Long id);
}
