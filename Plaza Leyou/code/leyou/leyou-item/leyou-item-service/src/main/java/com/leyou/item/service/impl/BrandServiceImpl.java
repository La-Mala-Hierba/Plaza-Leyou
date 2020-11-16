package com.leyou.item.service.impl;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.dao.BrandRepository;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;


    /**
     * Buscar las marcas con los parámetro pageable y sort
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Specification<Brand> spec = ((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            // key puede ser "name", también puede ser "letter"(la primera letra)
            if (StringUtils.isNotBlank(key)) {
                Predicate pName = criteriaBuilder.like(root.get("name").as(String.class), key + "%");
                Predicate pLetter = criteriaBuilder.equal(root.get("letter"), key);
                return criteriaBuilder.and(predicate, criteriaBuilder.or(pName, pLetter));
            }
            return predicate;
        });
        //pagination y sort
        Pageable pageable = null;
        if (StringUtils.isNotBlank(sortBy)) {
            Sort.Direction direction = desc ? Direction.DESC : Direction.ASC;
            Sort sort = Sort.by(direction, sortBy);
            pageable = PageRequest.of(page - 1, rows, sort);
        } else {
            pageable = PageRequest.of(page - 1, rows);
        }
        //resultado de clase Page
        Page<Brand> brandPage = this.brandRepository.findAll(spec, pageable);
        //Encapsular PageResult
        return new PageResult<>(brandPage.getTotalElements(), brandPage.getTotalPages(), brandPage.getContent());
    }

    /**
     * Add a new brand
     * @param brand
     * @param cids
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void save(Brand brand, List<Long> cids) {
        // Add firstly the table tb_brand
        this.brandRepository.save(brand);

        // Then add the table tb_category_brand
        cids.forEach(cid -> this.brandRepository.saveCategoryAndBrand(cid, brand.getId()));
    }

    /**
     * edit one brand
     * @param brand
     * @param cids
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void edit(Brand brand, List<Long> cids) {

        Optional<Brand> optional = this.brandRepository.findById(brand.getId());
        if (optional.isPresent()){
            System.out.println(brand);
            this.brandRepository.save(brand);

            //delete tb_category_brand
            this.brandRepository.deleteByBrandIdInCategoryAndBrand(brand.getId());
            //save tb_category_brand
            cids.forEach(cid -> this.brandRepository.saveCategoryAndBrand(cid, brand.getId()));
        }


    }

    /**
     * eliminar una marca o varias
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteBrand(long id) {
        Optional<Brand> optional = this.brandRepository.findById(id);
        if (optional.isPresent()){
            //delete tb_brand
            this.brandRepository.deleteById(id);
            //save tb_brand
            this.brandRepository.deleteByBrandIdInCategoryAndBrand(id);
        }
    }

    /**
     * query Brands by Cid
     * @param cid
     * @return
     */
    @Override
    public List<Brand> queryBrandsByCid(Long cid) {
        return this.brandRepository.findAllByCid(cid);
    }

    /**
     * Buscar marca con brandId
     * @param id
     * @return
     */
    @Override
    public Brand queryBrandById(Long id) {
        Optional<Brand> optional = this.brandRepository.findById(id);
        if (optional.isPresent()){
            Brand brand = optional.get();
            return brand;
        }
        return null;
    }

}
