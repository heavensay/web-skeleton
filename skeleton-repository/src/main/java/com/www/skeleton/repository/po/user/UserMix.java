package com.www.skeleton.repository.po.user;

import java.util.Date;
import java.util.List;

/**
 * @author lijianyu
 * @date 2019/2/18 15:20
 */
public class UserMix {

    private Long id;

    private String userName;

    private String password;

    private Date updateTime;

    private Date createTime;

    private List<PermsRoleMix> permsRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<PermsRoleMix> getPermsRoles() {
        return permsRoles;
    }

    public void setPermsRoles(List<PermsRoleMix> permsRoles) {
        this.permsRoles = permsRoles;
    }
}
