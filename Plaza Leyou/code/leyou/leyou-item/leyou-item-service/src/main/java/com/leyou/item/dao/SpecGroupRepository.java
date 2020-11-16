package com.leyou.item.dao;

import com.leyou.item.pojo.SpecGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecGroupRepository extends JpaRepository<SpecGroup, Long>, JpaSpecificationExecutor<SpecGroup> {

    public List<SpecGroup> findByCid(Long cid);


}
