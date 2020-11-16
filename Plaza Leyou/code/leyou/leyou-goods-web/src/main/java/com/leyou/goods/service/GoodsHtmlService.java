package com.leyou.goods.service;

/**
 * static web page
 */
public interface GoodsHtmlService {

    public void createHtml(Long spuId);

    public void asyncExcute(Long spuId);

    public void deleteHtml(Long spuId);
}
