package com.www.skeleton.repository.po.user;

import java.util.Date;

public class PermsRoleResource {

    private Integer id;

    private Integer roleId;

    private Integer resourceId;

    private Date updateTime;

    private Date createTime;




    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
    this.id = id;
}

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
    this.roleId = roleId;
}

    public Integer getResourceId () {
        return resourceId;
    }

    public void setResourceId (Integer resourceId) {
    this.resourceId = resourceId;
}

    public Date getUpdateTime () {
        return updateTime;
    }

    public void setUpdateTime (Date updateTime) {
    this.updateTime = updateTime;
}

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
    this.createTime = createTime;
}


}