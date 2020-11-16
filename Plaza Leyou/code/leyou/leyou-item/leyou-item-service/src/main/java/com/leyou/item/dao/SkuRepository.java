package com.leyou.item.dao;

import com.leyou.item.pojo.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface SkuRepository extends JpaRepository<Sku, Long>, JpaSpecificationExecutor<Sku> {

    public List<Sku> findBySpuId(Long spuId);

    public void deleteBySpuId(Long id);

}
