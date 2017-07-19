package com.tc.ta.util.framework;


public class BaseSearchCondition {
    private String singlePage = "1";
    private Integer pageSize;
    private Integer curPage;
    private Integer firstRecord;

    private String fillPojo = "0";

    public Integer getPageSize() {
        if ("1".equals(singlePage)) {
            pageSize = Integer.MAX_VALUE;
        }else if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurPage() {
        if ("1".equals(singlePage) || curPage == null || curPage < 1) {
            curPage = 1;
        }
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getFirstRecord() {
        if ("1".equals(singlePage)) {
            firstRecord = 0;
        } else {
            firstRecord = (getCurPage() - 1) * getPageSize();
        }
        return firstRecord;
    }

    public void setFirstRecord(Integer firstRecord) {
        this.firstRecord = firstRecord;
    }


    public String getSinglePage() {
        return singlePage;
    }

    public void setSinglePage(String singlePage) {
        this.singlePage = singlePage;
    }

    public void setSinglePage(Boolean bolSinglePage) {
        if (bolSinglePage) {
            this.singlePage = "1";
        } else {
            this.singlePage = "0";
        }
    }


    public String getFillPojo() {
        return fillPojo;
    }

    public void setFillPojo(String fillPojo) {
        this.fillPojo = fillPojo;
    }

    public Boolean getFillPojoBol() {
        return "1".equalsIgnoreCase(fillPojo);
    }

    public void setFillPojoBol(Boolean isFillPojo) {
        if (isFillPojo) {
            this.fillPojo = "1";
        } else {
            this.fillPojo = "0";
        }
    }


}
