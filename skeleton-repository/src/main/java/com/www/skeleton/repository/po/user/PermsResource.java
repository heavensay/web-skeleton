package com.www.skeleton.repository.po.user;

import java.util.Date;

public class PermsResource {

    private Long id;

    private Long pid;

    private String code;

    private String name;

    private String uri;

    private String iconUrl;

    private String type;

    private Integer sortby;

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

    public String getCode () {
        return code;
    }

    public void setCode (String code) {
    this.code = code;
}

    public String getName () {
        return name;
    }

    public void setName (String name) {
    this.name = name;
}

    public String getUri () {
        return uri;
    }

    public void setUri (String uri) {
    this.uri = uri;
}

    public String getIconUrl () {
        return iconUrl;
    }

    public void setIconUrl (String iconUrl) {
    this.iconUrl = iconUrl;
}

    public String getType () {
        return type;
    }

    public void setType (String type) {
    this.type = type;
}

    public Integer getSortby () {
        return sortby;
    }

    public void setSortby (Integer sortby) {
    this.sortby = sortby;
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