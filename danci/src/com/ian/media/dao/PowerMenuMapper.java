package com.ian.media.dao;

import java.util.List;
import java.util.Map;

import com.ian.media.model.PowerMenu;

public interface PowerMenuMapper  extends BaseDao<PowerMenu, String>{
    //查询菜单数据
	List<PowerMenu> listMenu(Map map);
	
	/**
	 * 获取菜单
	 */
	public List<PowerMenu> getAllMenu(Map map);
	
	/**
	 * 获取用户菜单
	 */
	public List<PowerMenu> getUserMenu(Map map);
	
}