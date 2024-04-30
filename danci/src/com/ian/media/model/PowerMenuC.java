package com.ian.media.model;

public class PowerMenuC {
    private String id1;

    private String menuName1;
    
    private String parentId1;

    private String url;

    private Integer state;

    private Integer code;

    private String updateTime;

    private String updateUserId;

    private String updateUserName;

    private String column01;

    private String column02;

    public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getMenuName1() {
		return menuName1;
	}

	public void setMenuName1(String menuName1) {
		this.menuName1 = menuName1;
	}

	public String getParentId1() {
		return parentId1;
	}

	public void setParentId1(String parentId1) {
		this.parentId1 = parentId1;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
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
}