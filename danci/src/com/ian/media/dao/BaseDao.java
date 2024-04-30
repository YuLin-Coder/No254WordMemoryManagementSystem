package com.ian.media.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;



public interface BaseDao <T, PK extends Serializable>{
	 
	/**
	 * 条件查询
	 * @param map
	 * @return
	 */
	public List<T> get(Map<Object, Object> map);
	
	/**
	 * 查询结果条数
	 * @param map
	 * @return
	 */
	public int getCount(Map<Object,Object> map);
	
	/**
	 * 根据主键单条删除
	 * @param id
	 */
	public void deleteByPrimaryKey(PK id);
	
	/**
	 * 根据主键批量删除
	 * @param id
	 */
	public void deleteMoreByPK(List<String> id); 
	/**
	 * 根据主键批量删除
	 * @param id
	 */
	public void deleteMoreByPKh(List<String> id); 
	
	/**
	 * 根据主键单条修改
	 * @param paramT
	 */
	public void updateByPrimaryKeySelective(T paramT);
	
	/**
	 * 根据主键批量修改
	 * @param map
	 */
	public void updateMoreByPK(Map<String, Object> map);
	
	/**
	 * 根据条件批量修改不同参数
	 */
	public void updateMore(List<String> params);
	
	/**
	 * 批量添加
	 */
	public void insertMore(List<String> params);
	/**
	 * 插入
	 * @return
	 */
	public int insertSelective(T paramT);

	
}