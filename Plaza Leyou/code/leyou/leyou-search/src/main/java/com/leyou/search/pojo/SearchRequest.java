package com.leyou.search.pojo;

import lombok.Data;

import java.util.Map;

public class SearchRequest {

    private String key;// search condition: url?key=xxx

    private Integer page;// currentPage

    private String sortBy; // para ordenar

    private Boolean descending;

    private Map<String, Object> filter;

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

    private static final Integer DEFAULT_SIZE = 20;// parámetro fijo
    private static final Integer DEFAULT_PAGE = 1;// default page

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if(page == null){
            return DEFAULT_PAGE;
        }
        // page must > 0
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return DEFAULT_SIZE;
    }

}
