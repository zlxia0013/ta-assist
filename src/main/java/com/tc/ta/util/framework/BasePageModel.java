package com.tc.ta.util.framework;

import java.util.ArrayList;
import java.util.List;

public class BasePageModel {
    private Integer pageCount;
    private Integer curPage;
    private Integer totalRecordCount;
    private Integer pageSize;
    private List<?> objList = new ArrayList<Object>();

    public Integer getPageCount() {
        if (totalRecordCount % pageSize == 0) {
            pageCount = totalRecordCount / pageSize;
        } else {
            pageCount = totalRecordCount / pageSize + 1;
        }
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getObjList() {
        return objList;
    }

    public void setObjList(List<?> objList) {
        this.objList = objList;
    }
}
