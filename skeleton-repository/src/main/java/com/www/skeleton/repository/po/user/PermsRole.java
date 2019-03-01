package com.www.skeleton.repository.po.user;

import java.util.Date;

public class PermsRole {

    private Long id;

    private Long pid;

    private String name;

    private String description;

    private Date updateTime;

    private Date createTime;




    public Long getId () {
        return id;
    }

    public void setId (Long id) {
    this.id = id;
}

    public Long getPid () {
        return pid;
    }

    public void setPid (Long pid) {
    this.pid = pid;
}

    public String getName () {
        return name;
    }

    public void setName (String name) {
    this.name = name;
}

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
    this.description = description;
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