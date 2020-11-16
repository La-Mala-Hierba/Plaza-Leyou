package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecificationService {

    public List<SpecGroup> queryGroupsByCid(Long cid);

    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching);

    public void saveGroup(SpecGroup specGroup);

    public void updateGroup(SpecGroup specGroup);

    public void deleteGroup(Long id);

    public void saveParam(SpecParam specParam);

    public void updateParam(SpecParam specParam);

    public void deleteParam(Long id);

    public List<SpecGroup> queryGroupsWithParam(Long cid);
}
