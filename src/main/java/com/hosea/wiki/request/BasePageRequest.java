package com.hosea.wiki.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class BasePageRequest {
    @NotNull(message = "当前页码不能为空")
    protected Integer currentPage;
    @NotNull(message = "页码大小不能为空")
    @Max(value = 1000, message = "请输入合法大小")
    protected Integer sizeOfPage;


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSizeOfPage() {
        return sizeOfPage;
    }

    public void setSizeOfPage(Integer sizeOfPage) {
        this.sizeOfPage = sizeOfPage;
    }
}
