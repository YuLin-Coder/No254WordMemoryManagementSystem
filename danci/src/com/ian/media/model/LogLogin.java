package com.ian.media.model;

public class LogLogin {
    private String id;

    private Integer type;

    private String loginName;

    private String userName;

    private String userId;

    private String loginTimer;

    private String column01;

    private String column02;

    private String column03;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginTimer() {
        return loginTimer;
    }

    public void setLoginTimer(String loginTimer) {
        this.loginTimer = loginTimer == null ? null : loginTimer.trim();
    }

    public String getColumn01() {
        return column01;
    }

    public void setColumn01(String column01) {
        this.column01 = column01 == null ? null : column01.trim();
    }

    public String getColumn02() {
        return column02;
    }

    public void setColumn02(String column02) {
        this.column02 = column02 == null ? null : column02.trim();
    }

    public String getColumn03() {
        return column03;
    }

    public void setColumn03(String column03) {
        this.column03 = column03 == null ? null : column03.trim();
    }
}