package test;

import com.leyou.LeyouItemApplication;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.dao.SpecGroupRepository;
import com.leyou.item.dao.SpuRepository;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = LeyouItemApplication.class)
@RunWith(SpringRunner.class)
public class SpringJpaTest {

    @Autowired
    private SpecGroupRepository repository;

    @Autowired
    private SpuRepository spuRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate(){
        SpecGroup specGroup = new SpecGroup();
        specGroup.setId(36l);
        specGroup.setCid(3l);
        specGroup.setName("test2");
        this.repository.save(specGroup);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave(){
        Sku sku = new Sku();
        List<Sku> skus = new ArrayList<>();
        SpuDetail spuDetail = new SpuDetail();
        skus.add(sku);
        SpuBo spuBo = new SpuBo();
        spuBo.setId(null);
        spuBo.setCname("test-test-test");
        spuBo.setBname("test");
        spuBo.setCid1(97l);
        spuBo.setCid2(98l);
        spuBo.setCid3(99l);
        spuBo.setBrandId(555l);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(new Date());
        spuBo.setSubTitle("test");
        spuBo.setTitle("test");
        spuBo.setValid(true);
        spuBo.setSaleable(true);
        spuBo.setSkus(skus);
        spuBo.setSpuDetail(spuDetail);

        Spu spu = new Spu();
        BeanUtils.copyProperties(spuBo, spu);
        spu.setId(null);
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spuBo.getCreateTime());
        System.out.println(spu);
        this.spuRepository.save(spu);
    }
}
