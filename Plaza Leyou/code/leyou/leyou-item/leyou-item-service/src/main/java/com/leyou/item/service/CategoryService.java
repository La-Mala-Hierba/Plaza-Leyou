package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> queryCategoriesByPid(Long pid);

    public void save(Category category);

    public void deleteCategory(Long id);

    public void updateCategory(Long id, String name);

    public List<Category> queryCategoriesByBrandId(Long bid);

    public List<String> queryNamesByIds(List<Long> ids);

    public List<Category> queryAllCategoryLevelByCid3(Long cid3);
}
