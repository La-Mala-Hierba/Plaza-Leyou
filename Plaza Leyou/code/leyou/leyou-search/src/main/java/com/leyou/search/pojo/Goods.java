package com.leyou.search.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ToString
@Document(indexName = "goods", type = "docs", shards = 1, replicas = 0)
public class Goods {
    @Id
    private Long id; // spuId
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all; // Search conditions: incluye los títulos, las categorías y las marcas
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;// gancho mercial
    private Long brandId;// brand id
    private Long cid1;// category id 1
    private Long cid2;// category id 2
    private Long cid3;// category id 3
    private Date createTime;// spu createTime
    private List<Long> price;// sku price
    @Field(type = FieldType.Keyword, index = false)
    private String skus;// json de List<sku> para facilitar la búsqueda, ES no dispone de eficacia con las Collections
    private Map<String, Object> specs;// sepcial specfication para búsqueda, key = spec name, value = spec value
}