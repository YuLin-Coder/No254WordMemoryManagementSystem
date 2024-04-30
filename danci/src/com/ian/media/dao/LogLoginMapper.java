package com.ian.media.dao;

import java.util.List;
import java.util.Map;

import com.ian.media.model.LogLogin;

public interface LogLoginMapper extends BaseDao<LogLogin,String>{
	/**
	 * 获取日志
	 */
	public List<LogLogin> listLogLogin(Map map);
	
}