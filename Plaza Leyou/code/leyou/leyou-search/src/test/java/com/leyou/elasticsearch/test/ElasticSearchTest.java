package com.leyou.elasticsearch.test;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.repository.GoodsRepository;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Importar los datos de artículos desde leyou-item-service hasta leyou-search
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;

    @Test
    public void createIndex(){
        // create un index y mapping
        this.elasticsearchTemplate.createIndex(Goods.class);
        this.elasticsearchTemplate.putMapping(Goods.class);

        //import los datos
        Integer page = 1;
        Integer rows = 100; // import 100 datos cada vez

        //循环导入数据
        do{
            //分页查询spu, 获取分页结果集
            PageResult<SpuBo> result = this.goodsClient.querySpuByPage(null, true, null, null, page, rows);
            //获取当前页的数据
            List<SpuBo> items = result.getItems();
            //将List<SpuBo>构建成List<Goods>
            List<Goods> goodsList = items.stream().map(spuBo -> {
                try {
                    return this.searchService.buildGoods(spuBo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

            //执行新增数据的方法
            this.goodsRepository.saveAll(goodsList);

            rows = items.size();
            page ++;
        }while (rows == 100);
        //最后一页条数的两种情况: 1.真实条数<rows100条，循环结束；2. 真实条数=rows100条，再循环一次
    }
}
