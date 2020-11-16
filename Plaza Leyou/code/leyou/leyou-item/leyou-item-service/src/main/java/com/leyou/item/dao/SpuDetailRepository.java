package com.leyou.item.dao;

import com.leyou.item.pojo.SpuDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpuDetailRepository extends JpaRepository<SpuDetail, Long>, JpaSpecificationExecutor<SpuDetail> {

}
