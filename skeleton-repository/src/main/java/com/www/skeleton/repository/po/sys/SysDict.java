package com.www.skeleton.repository.po.sys;

import lombok.Data;

import java.util.Date;

/**
 * @author ljy
 * @date 2019/7/4 10:35
 */
public class SysDict {

    private Long id;
    private Long pid;
    private String category;
    private String value;
    private String valueLabel;
    private Integer sortby;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueLabel() {
        return valueLabel;
    }

    public void setValueLabel(String valueLabel) {
        this.valueLabel = valueLabel;
    }

    public Integer getSortby() {
        return sortby;
    }

    public void setSortby(Integer sortby) {
        this.sortby = sortby;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
