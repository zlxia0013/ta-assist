package com.tc.ta.core.privilege.pojo;

import java.util.Date;

import com.tc.ta.util.Consts;
import com.tc.ta.util.framework.BaseSearchCondition;

public class Privilege extends BaseSearchCondition {
    private Integer id;

    private String code;

    private String value;

    private Integer noLimitAccess;

    private Integer needLogin;

    private String comments;

    // %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_DECLARE%%

    public Privilege() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNoLimitAccess() {
        return noLimitAccess;
    }

    public void setNoLimitAccess(Integer noLimitAccess) {
        this.noLimitAccess = noLimitAccess;
    }

    public Integer getNeedLogin() {
        return needLogin;
    }

    public boolean getNeedLoginBol() {
        return needLogin != null && Consts.YES_INT == needLogin;
    }

    public void setNeedLogin(Integer needLogin) {
        this.needLogin = needLogin;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_GET_SET%%
}
