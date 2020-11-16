package com.leyou.search.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyou.item.pojo.*;
import com.leyou.search.client.BrandClient;
import com.leyou.search.client.CategoryClient;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.client.SpecificationClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.pojo.SearchResult;
import com.leyou.search.repository.GoodsRepository;
import com.leyou.search.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private GoodsRepository goodsRepository;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Spu -> Goods
     * @param spu
     * @return
     */
    @Override
    public Goods buildGoods(Spu spu) throws Exception {
        Goods goods = new Goods();

        //obtener categoryName con categoryId
        List<String> names = this.categoryClient.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));

        //obtener brand con brandId
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());

        //buscar todos los skus con spuId y continuadamente los precios
        List<Sku> skus = this.goodsClient.querySkusBySpuId(spu.getId());

        //crear un ArrayList para coleccionar los precios de sku
        List<Long> prices =  new ArrayList<>();

        /*sku -> sku_id, image, price, title
        crear una List<Map<key, value>> para coleccionar los datos:
        key = sku_id/title/price/image
        value =Object
        */
        List<Map<String, Object>> skuMapList = new ArrayList<>();
        skus.forEach(sku -> {
            //price
            prices.add(sku.getPrice());
            Map<String, Object> map = new HashMap<>();
            map.put("id", sku.getId());
            map.put("title", sku.getTitle());
            map.put("price", sku.getPrice());
            /*image -> List<Image> != null -> split list para obtener el primer elemento
            * los elementos de images se separan con “,”*/
            map.put("image", StringUtils.isBlank(sku.getImages())? "": StringUtils.split(sku.getImages(),",")[0]);

            skuMapList.add(map);
        });

        //specParam -> guardarlos en Map<specName, specValue>
        List<SpecParam> params = this.specificationClient.queryParams(null, spu.getCid3(), null, true);
        //obtener spuDetail con spuId
        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spu.getId());
        //generic_spec({json}) -> Map<specParam_id, specParam_value>
        Map<String, Object> genericSpecMap = MAPPER.readValue(spuDetail.getGenericSpec(), new TypeReference<Map<String, Object>>() {});
        //special_spec({json}) -> Map<specParam_id, List<specValue>>
        Map<String, List<Object>> specialSpecMap = MAPPER.readValue(spuDetail.getSpecialSpec(), new TypeReference<Map<String, List<Object>>>(){});
        Map<String, Object> specs = new HashMap<>();
        params.forEach(specParam -> {
            if (specParam.getGeneric()){
                //SpuDetail(generic_spec)
                String value = genericSpecMap.get(specParam.getId().toString()).toString();
                //value == numeric? if not, value == segment
                if (specParam.getNumeric()){
                    value = this.chooseSegment(value, specParam);
                }
                specs.put(specParam.getName(), value);
            }else {
                //SpuDetail(special_spec)
                List<Object> list = new ArrayList<>();
                List<Object> value = specialSpecMap.get(specParam.getId().toString());
                specs.put(specParam.getName(), value);
            }
        });

        BeanUtils.copyProperties(spu, goods);

        //build search conditions(categoryName, brandName and title, separated by a space)
        goods.setAll(spu.getTitle()+" "+ StringUtils.join(names, " ") +" "+brand.getName());

        //get all sku price under one spu (any price corresponds with the search condition, it should be found到)
        goods.setPrice(prices);

        //get all skus, -> json
        goods.setSkus(MAPPER.writeValueAsString(skuMapList));

        //get all specParams which searching is true (Map<specName, specValue>)
        goods.setSpecs(specs);

        return goods;
    }


    @Override
    public SearchResult search(SearchRequest searchRequest) {
        // searchRequest is null?
        if (StringUtils.isBlank(searchRequest.getKey())){
            return null;
        }

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //add search conditions
        //set a boolQuery in which there are basic query and filter query
        BoolQueryBuilder searchQuery = buildBoolQueryBuilder(searchRequest);
        queryBuilder.withQuery(searchQuery);

        //pagination: page starts from 0
        queryBuilder.withPageable(PageRequest.of(searchRequest.getPage() -1, searchRequest.getSize()));

        //sort
        String sortBy = searchRequest.getSortBy();
        Boolean desc = searchRequest.getDescending();
        if (StringUtils.isNotBlank(sortBy)){
            queryBuilder.withSort(SortBuilders.fieldSort(sortBy).order(desc ? SortOrder.ASC : SortOrder.DESC));
        }

        //add sourceFilter which contains only 3 fields: id, skus, subTitle
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "subTitle", "skus"}, null));

        //add aggregations of category and brand
        String categoryAggName = "categories";
        String brandAggName = "brands";
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("cid3"));
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggName).field("brandId"));

        //execute search of aggregation of brand and category
        AggregatedPage<Goods> goodsPage = (AggregatedPage<Goods>) this.goodsRepository.search(queryBuilder.build());

        //analyze goodPage
        List<Map<String, Object>> categories = getCategoryAggResult(goodsPage.getAggregation(categoryAggName));
        List<Brand> brands = getBrandAggResult(goodsPage.getAggregation(brandAggName));

        // context of spec params' aggregation: when the user select item's category o brand

        //there is only one category -> spec params' aggregation
        List<Map<String, Object>> specs = null;
        if (!CollectionUtils.isEmpty(categories) && categories.size() == 1){
            //set a function to proceed the spec's aggregation. Parameters: cid+searchQuery
           // Long cid = (Long)categories.get(0).get("id");
            specs = getParamAggResult((Long)categories.get(0).get("id"), searchQuery);
        }

        SearchResult result = new SearchResult(goodsPage.getTotalElements(), goodsPage.getTotalPages(), goodsPage.getContent(),categories, brands, specs);

        return result;
    }

    /**
     * Guardar artículo nuevo o las modificaciones en ElasticSearch
     * @param spuId
     * @throws Exception
     */
    @Override
    public void save(Long spuId) throws Exception {
        Spu spu = this.goodsClient.querySpuBySpuId(spuId);
        Goods goods = this.buildGoods(spu);
        this.goodsRepository.save(goods);
    }

    /**
     * Eliminar el artículo en ElasticSearch
     * @param spuId
     * @throws Exception
     */
    @Override
    public void delete(Long spuId) {
        this.goodsRepository.deleteById(spuId);
    }

    /**
     * Establecer bool search
     * @param searchRequest
     * @return
     */
    private BoolQueryBuilder buildBoolQueryBuilder(SearchRequest searchRequest) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //add basic query to boolQueryBuilder
        boolQueryBuilder.must(QueryBuilders.matchQuery("all", searchRequest.getKey()).operator(Operator.AND));

        //add filter query to boolQueryBuilder
        //get filter selected by user
        Map<String, Object> filter = searchRequest.getFilter();
        filter.entrySet().forEach(entry -> {
            String key = entry.getKey();
            if (StringUtils.equals("Marca", key)){
                key = "brandId";
            }else if (StringUtils.equals("Categoría", key)){
                key = "cid3";
            }else {
                key = "specs." +key+ ".keyword";
            }

            boolQueryBuilder.filter(QueryBuilders.termQuery(key, entry.getValue()));
        });
        return boolQueryBuilder;
    }

    /**
     * Agregación de los specParams en base de condiciones de búsqueda
     * @param cid
     * @param searchQuery
     * @return
     */
    private List<Map<String, Object>> getParamAggResult(Long cid, QueryBuilder searchQuery) {
        //build a NativeSearchQueryBuilder
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //add the parameter searchQuery(search conditions)
        queryBuilder.withQuery(searchQuery);
        //cid3 -> get specParams which searching is true
        List<SpecParam> params = this.specificationClient.queryParams(null, cid, null, true);
        //add spec params' aggregation
        params.forEach(specParam -> {
            queryBuilder.addAggregation(AggregationBuilders.terms(specParam.getName()).field("specs."+specParam.getName()+".keyword"));
        });
        //add sourceFilter(null)
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));
        //execute aggregation search
        AggregatedPage<Goods> goodsPage = (AggregatedPage<Goods>)this.goodsRepository.search(queryBuilder.build());

        List<Map<String, Object>> specs = new ArrayList<>();

        //analyze goodPage -> key=specParam name,value=specParam value in aggregations
        Map<String, Aggregation> aggregationMap = goodsPage.getAggregations().asMap();
        for (Map.Entry<String, Aggregation> entry : aggregationMap.entrySet()) {
            //create a hashmap {k:specParam name, option:[specParam values in aggregations]}
            Map<String, Object> map = new HashMap<>();
            map.put("k", entry.getKey());
            //create an ArrayList to collect key of each bucket
            List<String> options = new ArrayList<>();
            StringTerms terms = (StringTerms)entry.getValue();
            //get buckets of aggregations
            terms.getBuckets().forEach(bucket -> options.add(bucket.getKeyAsString()));
            map.put("options", options);
            specs.add(map);
        }
        return specs;
    }

    /**
     * Get the Aggregation Result of brands
     * @param aggregation
     * @return
     */
    private List<Brand> getBrandAggResult(Aggregation aggregation) {
        LongTerms terms = (LongTerms)aggregation;
        //get buckets of aggregations. brandId in bucket(Long) -> Brand
        return terms.getBuckets().stream().map(bucket -> {
            return this.brandClient.queryBrandById(bucket.getKeyAsNumber().longValue());
        }).collect(Collectors.toList());
    }

    /**
     * Get the Aggregation Result of categories
     * @param aggregation
     * @return
     */
    private List<Map<String, Object>> getCategoryAggResult(Aggregation aggregation) {
        LongTerms terms = (LongTerms)aggregation;
        //get buckets of aggregations. cid in bucket(Long) -> Map<String, Object> -> List
        return terms.getBuckets().stream().map(bucket -> {
            //create a hashmap
            Map<String, Object> map = new HashMap<>();
            //get cid of bucket
            long cid = bucket.getKeyAsNumber().longValue();
            //cid3 -> categoryName
            List<String> names = this.categoryClient.queryNamesByIds(Arrays.asList(cid));
            map.put("id", cid);
            map.put("name", names.get(0));
            return map;
        }).collect(Collectors.toList());
    }

    private String chooseSegment(String value, SpecParam p) {

        double val = NumberUtils.toDouble(value);
        String result = "Otros";

        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if(segs.length == 2){
                end = NumberUtils.toDouble(segs[1]);
            }
            if(val >= begin && val < end){
                if(segs.length == 1){
                    result = "Más de "+ segs[0] + p.getUnit();
                }else if(begin == 0){
                    result = "Hasta "+ segs[1] + p.getUnit();
                }else{
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }
}
