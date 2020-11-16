package com.leyou.item.dao;

import com.leyou.item.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    /**
     * Query category's list by parentId
     * @param pid
     * @return
     */
    public List<Category> findByParentId(Long pid);


    @Query(value = "delete from tb_category_brand where category_id = ?", nativeQuery=true)
    @Modifying
    public void deleteCategoryAndBrand(Long id);

    /**
     * buscar category con el parámetro brandId
     * @param bid
     * @return
     */
    @Query(value = "SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id=?)",
            nativeQuery = true)
    public List<Category> findCategoriesByBrandId(Long bid);

}
