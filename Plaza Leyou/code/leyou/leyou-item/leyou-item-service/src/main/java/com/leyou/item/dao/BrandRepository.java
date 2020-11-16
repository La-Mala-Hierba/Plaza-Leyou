package com.leyou.item.dao;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {

    @Query(value = "INSERT INTO tb_category_brand (category_id, brand_id) VALUES (?1, ?2)", nativeQuery = true)
    @Modifying
    public void saveCategoryAndBrand(Long cid, Long bid);


    @Query(value = "delete from tb_category_brand where brand_id = ?;", nativeQuery = true)
    @Modifying
    public void deleteByBrandIdInCategoryAndBrand(Long bid);

    @Query(value = "SELECT * FROM tb_brand WHERE id IN (SELECT brand_id FROM tb_category_brand WHERE category_id=?);", nativeQuery = true)
    @Modifying
    public List<Brand> findAllByCid(Long cid);

}
