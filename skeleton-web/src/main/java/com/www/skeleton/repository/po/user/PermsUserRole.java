package com.www.skeleton.repository.po.user;


public class PermsUserRole {

    private Long id;

    private Long userId;

    private Long permsRoleId;




    public Long getId () {
        return id;
    }

    public void setId (Long id) {
    this.id = id;
}

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
    this.userId = userId;
}

    public Long getPermsRoleId () {
        return permsRoleId;
    }

    public void setPermsRoleId (Long permsRoleId) {
    this.permsRoleId = permsRoleId;
}


}