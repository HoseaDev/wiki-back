package com.hosea.wiki.resp;

import java.util.List;

public class PageResponse <T>{

    private Long total;
    private List<T> list;

    public PageResponse(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
