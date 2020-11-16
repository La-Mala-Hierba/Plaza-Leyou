package com.leyou.item.service.impl;

import com.leyou.item.dao.SpecGroupRepository;
import com.leyou.item.dao.SpecParamRepository;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

/**
 * Inculye SpecGroup y SpecParam
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupRepository groupRepository;

    @Autowired
    private SpecParamRepository paramRepository;

    /**
     * buscar grupos de especificación bajo un mismo cid
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        return this.groupRepository.findByCid(cid);
    }

    /**
     * query spec params by conditions
     * @param gid
     * @return
     */
    @Override
    public List<SpecParam> queryParams(Long gid,Long cid, Boolean generic, Boolean searching) {

        Specification<SpecParam> spec = ((root,criteriaQuery,criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (gid != null && gid > 0){
                Predicate pGid = criteriaBuilder.equal(root.get("groupId"), gid);
                predicate = criteriaBuilder.and(predicate, pGid);
            }

            if (cid != null && cid > 0){
                Predicate pCid = criteriaBuilder.equal(root.get("cid"), cid);
                predicate = criteriaBuilder.and(predicate, pCid);
            }

            if (generic != null){
                Predicate pGeneric = criteriaBuilder.equal(root.get("generic"), generic);
                predicate = criteriaBuilder.and(predicate, pGeneric);
            }

            if (searching != null){
                Predicate pSearching = criteriaBuilder.equal(root.get("searching"), searching);
                predicate = criteriaBuilder.and(predicate, pSearching);
            }
            return predicate;
        });

        return this.paramRepository.findAll(spec);
    }

    /**
     * Save a new specgroup
     * @param specGroup
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveGroup(SpecGroup specGroup) {
        specGroup.setId(null);
        this.groupRepository.save(specGroup);
    }

    /**
     * update a specgroup
     * @param specGroup
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void updateGroup(SpecGroup specGroup) {
        Optional<SpecGroup> optional = this.groupRepository.findById(specGroup.getId());
        if (optional.isPresent()){
            this.groupRepository.save(specGroup);
        }
    }

    /**
     * delete a specgroup by groupId
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteGroup(Long id) {
        this.groupRepository.deleteById(id);
    }

    /**
     * Save a new specParam
     * @param specParam
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveParam(SpecParam specParam) {
        specParam.setId(null);
        this.paramRepository.save(specParam);
    }

    /**
     * update a new specgroup
     * @param specParam
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void updateParam(SpecParam specParam) {
        Optional<SpecParam> optional = this.paramRepository.findById(specParam.getId());
        if (optional.isPresent()){
            this.paramRepository.save(specParam);
        }
    }

    /**
     * delete a specparam by paramId
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteParam(Long id) {
        this.paramRepository.deleteById(id);
    }

    /**
     * Buscar specGroup con SpecParam con cid
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupsWithParam(Long cid) {
        List<SpecGroup> groups = this.queryGroupsByCid(cid);
        groups.forEach(specGroup -> {
            List<SpecParam> params = this.queryParams(specGroup.getId(), null, null, null);
            specGroup.setParams(params);
        });
        return groups;
    }
}
