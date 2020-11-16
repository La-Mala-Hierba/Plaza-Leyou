package com.leyou.item.dao;

import com.leyou.item.pojo.SpecParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SpecParamRepository extends JpaRepository<SpecParam, Long>, JpaSpecificationExecutor<SpecParam> {

    public List<SpecParam> findByGroupId(Long gid);

    //public void deleteByGroupId(Long gid);


}
