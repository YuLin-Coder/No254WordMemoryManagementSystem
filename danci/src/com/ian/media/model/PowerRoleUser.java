package com.ian.media.model;

public class PowerRoleUser {
    private String id;

    private String powId;

    private String userId;

    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPowId() {
        return powId;
    }

    public void setPowId(String powId) {
        this.powId = powId == null ? null : powId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}