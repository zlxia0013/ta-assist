package com.tc.ta.core.user.pojo;


import com.tc.ta.util.framework.BaseSearchCondition;

import java.util.Date;

public class User extends BaseSearchCondition{
    private Integer id;

    private Integer verNbr;

    private String name;

    private String pwd;

    private String phone;

    private String fullName;

    private Integer stateId;

    private Integer addUserId;

    private Date addTime;

    private String remark;

    // %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_DECLARE%%

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVerNbr() {
        return verNbr;
    }

    public void setVerNbr(Integer verNbr) {
        this.verNbr = verNbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    // %%ONLY_FOR_INS_FLD%FI_JAVA_ENTITY_GET_SET%%
}
