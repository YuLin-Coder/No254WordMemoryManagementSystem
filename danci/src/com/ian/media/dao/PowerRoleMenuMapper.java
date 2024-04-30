package com.ian.media.dao;

import java.util.List;

import com.ian.media.model.PowerRoleMenu;

public interface PowerRoleMenuMapper extends BaseDao<PowerRoleMenu, String> {
	 int deleteByRoleId(String userId);
	 
	 public void insertMore(List<String> params);

}