package com.leyou.item.service.impl;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.dao.*;
import com.leyou.item.pojo.*;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Incluye Spu, SpuDetails, Sku
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuRepository spuRepository;

    @Autowired
    private SpuDetailRepository spuDetailRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * query Spus with Pageable by conditions
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, String sortBy, Boolean desc, Integer page, Integer rows) {
        //preparar condiciones de búsqueda
        Specification<Spu> spec = ((root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            //key is null ?
            if (StringUtils.isNotBlank(key)){
                Predicate pTitle = criteriaBuilder.like(root.get("title").as(String.class), key+"%");
                predicate = criteriaBuilder.and(predicate, pTitle);
            }

            if (saleable != null){
                //añadir la condición del filtro ->saleable
                Predicate pSaleable = criteriaBuilder.equal(root.get("saleable"), saleable);
                predicate = criteriaBuilder.and(predicate, pSaleable);
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

        //ejecuta la búsqueda y obtener la colección de spu
        //List<Spu> spus = this.spuRepository.findAll(spec); //return value debe incluir las condiciones de búsqueda y los datos de pagination
        Page<Spu> spuPage = this.spuRepository.findAll(spec, pageable);
        List<Spu> spus = spuPage.getContent();

        //Spu -> SpuBo
        List<SpuBo> spuBos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            Optional<Brand> optional = this.brandRepository.findById(spu.getBrandId());
            if (optional.isPresent()){
                Brand brand = optional.get();
                spuBo.setBname(brand.getName());
            }
            List<String> names = this.categoryService.queryNamesByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "-"));
            return spuBo;
        }).collect(Collectors.toList());

        //return PageResult<SpuBo>
        return new PageResult<SpuBo>(spuPage.getTotalElements(), spuPage.getTotalPages(), spuBos);
    }

    /**
     * save good
     * @param spuBo
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveGoods(SpuBo spuBo) {
        //el parámetro spuBo no contiene id
        //añadir spu primero
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuBo, spu);
        spu.setId(null);
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        this.spuRepository.save(spu);

        //añade spuDetail
        spuBo.setId(spu.getId());
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        this.spuDetailRepository.save(spuDetail);

        //añade sku y stock
        this.saveSkusAndStock(spuBo);

        this.sendMsg("insert", spuBo.getId());
    }

    /**
     * Método extractor de enviar mensajes a service-consumer mediante Spring AmqpTemplate
     * para aumentar, modificar y eliminar los artículos
     * @param type
     * @param spuId
     */
    private void sendMsg(String type, Long spuId) {
        try {
            this.amqpTemplate.convertAndSend("item."+type, spuId);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método extractor para aumentar los skus y el stock
     * @param spuBo
     */
    private void saveSkusAndStock(SpuBo spuBo) {
        //añade sku
        spuBo.getSkus().forEach(sku -> {
            sku.setId(null);
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            if (sku.getEnable() == null) {
                sku.setEnable(false);
            }
            this.skuRepository.save(sku);

            //añade stock
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setSeckillStock(null);
            stock.setSeckillTotal(null);
            stock.setStock(sku.getStock());
            this.stockRepository.save(stock);
        });
    }

    /**
     * query SpuDetail by SpuId
     * @param spuId
     * @return
     */
    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        Optional<SpuDetail> optional = this.spuDetailRepository.findById(spuId);
        if (optional.isPresent()){
            SpuDetail spuDetail = optional.get();
            return spuDetail;
        }
        return null;
    }

    /**
     * query skus by spuId
     * @param spuId
     * @return
     */
    @Override
    public List<Sku> querySkusBySpuId(Long spuId) {
        List<Sku> skus = this.skuRepository.findBySpuId(spuId);
        skus.forEach(sku -> {
            Optional<Stock> optional = this.stockRepository.findById(sku.getId());
            if (optional.isPresent()){
                Stock stock = optional.get();
                sku.setStock(stock.getStock());
            }
        });
        return skus;
    }

    /**
     * update good
     * @param spuBo
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateGoods(SpuBo spuBo) {
        //En total hay que actualizar 4 tables: spu, spuDetail, sku, stock
        List<Sku> skus = this.skuRepository.findBySpuId(spuBo.getId());

        //eliminar stock
        skus.forEach(sku -> {
            this.stockRepository.deleteById(sku.getId());
            //eliminar sku
            this.skuRepository.delete(sku);
        });

        //con spu_id añadir sku y stock
        this.saveSkusAndStock(spuBo);

        //actualizar spu y spu_detail
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuBo, spu);
        spu.setLastUpdateTime(new Date());
        this.spuRepository.save(spu);

        //añade spuDetail
        this.spuDetailRepository.save(spuBo.getSpuDetail());

        this.sendMsg("update", spuBo.getId());
    }

    /**
     * Eliminación lógica de producto según spuId(No se trata de eliminación física)
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteGood(long id) {
        //update tb_sku: enable + lastUpdateTime
        List<Sku> skus = this.skuRepository.findBySpuId(id);
        skus.forEach(sku -> {
            sku.setEnable(false);
            sku.setLastUpdateTime(new Date());
            this.skuRepository.save(sku);
        });

        //update tb_spu: valid + lastUpdateTime
        Optional<Spu> optional = this.spuRepository.findById(id);
        if (optional.isPresent()){
            Spu spu = optional.get();
            spu.setValid(false);
            spu.setSaleable(false);
            spu.setLastUpdateTime(new Date());
            this.spuRepository.save(spu);
        }

        this.sendMsg("delete", id);
    }

    /**
     * Dar de baja el producto con el spuId
     * @param spuId
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void changeSaleableStatus(Long spuId) {
        //get spu and sku
        Optional<Spu> optional = this.spuRepository.findById(spuId);
        if (optional.isPresent()){
            Spu spu = optional.get();
            List<Sku> skus = this.skuRepository.findBySpuId(spuId);

            //if spu's saleable == true, saleable->false, sku's enable->false, and update both lastUpdateTime
            if (spu.getSaleable()){
                spu.setSaleable(false);
                spu.setLastUpdateTime(new Date());
                this.spuRepository.save(spu);

                skus.forEach(sku -> {
                    sku.setEnable(false);
                    sku.setLastUpdateTime(new Date());
                    this.skuRepository.save(sku);
                });
                this.sendMsg("delete", spuId);
            }else{
                spu.setSaleable(true);
                spu.setLastUpdateTime(new Date());
                this.spuRepository.save(spu);

                skus.forEach(sku -> {
                    sku.setEnable(true);
                    sku.setLastUpdateTime(new Date());
                    this.skuRepository.save(sku);
                });
                this.sendMsg("insert", spuId);
            }
        }

    }

    /**
     * Buscar Spu con SpuId
     * @param spuId
     * @return
     */
    @Override
    public Spu querySpuBySpuId(Long spuId) {
        Optional<Spu> optional = this.spuRepository.findById(spuId);
        if (optional.isPresent()){
            Spu spu = optional.get();
            return spu;
        }
        return null;
    }

    @Override
    public Sku querySkuBySkuId(Long skuId) {
        Optional<Sku> optional = this.skuRepository.findById(skuId);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

}
