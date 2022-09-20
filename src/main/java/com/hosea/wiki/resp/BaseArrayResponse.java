package com.hosea.wiki.resp;

import java.util.List;

public class BaseArrayResponse<T> {

    private List<T> list;


    public BaseArrayResponse(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
