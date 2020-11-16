package com.leyou.search.service;

import com.leyou.item.pojo.Spu;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;
import com.leyou.search.pojo.SearchResult;

public interface SearchService {

    public Goods buildGoods(Spu spu) throws Exception;

    public SearchResult search(SearchRequest searchRequest);

    public void save(Long spuId) throws Exception;

    public void delete(Long spuId);
}
