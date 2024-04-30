package com.ian.media.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ian.media.dao.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONArray;
public class BaseController<T>{
	protected BaseDao dao = null; 

	public void setBaseDao(BaseDao baseDao){
		this.dao = baseDao;
	}
	 
    // 查询方法
	@RequestMapping(params = "list")
	@ResponseBody
	public Map<Object, Object> get(HttpServletRequest request){
		Map<Object, Object> queryMap = new HashMap<Object, Object>();
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			String paramValue = request.getParameter(paramName);
			if (paramValue.equals("")) {

			} else {
				//形成键值对应的map
				queryMap.put(paramName, paramValue);
				
			}
		}
		  
		
		//System.out.println(JSON.toJSON(queryMap));
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		// 如果没有page和rows则不传到SQL语句中
		if (page != null && rows != null) {
			queryMap.put("pageIndex",(Integer.parseInt(page)));
			queryMap.put("rows", (Integer.parseInt(rows)));
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<T> list = dao.get(queryMap);
			map.put("rows", list);
			map.put("total", dao.getCount(queryMap));
			map.put("success",true);
			map.put("page", page);
			map.put("msg","查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("success",false);
			map.put("msg","查询失败");
			return map;
		}
		return map;
	}
	
	
	
	
	// 根据多个id查询记录
	@RequestMapping(params = "getMore")
	@ResponseBody
	public Map getMore(HttpServletRequest request)
	{
		Map<Object, Object> queryMap = new HashMap<Object, Object>();
		Enumeration en = request.getParameterNames();
		String id=request.getParameter("idlist");
		List<String> listId = new ArrayList<String>();
        String[] arrId = id.split(",");
        for(int i = 0 ; i < arrId.length ; i ++ ){
        	listId.add(arrId[i]);
        }
        queryMap.put("listId", listId);
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			String paramValue = request.getParameter(paramName);
			if (paramValue.equals("")) {

			} else {
				//形成键值对应的map
				queryMap.put(paramName, paramValue);
			}
		}
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		// 如果没有page和rows则不传到SQL语句中
		if (page != null && rows != null) {
			queryMap.put("index",
					((Integer.parseInt(page)) - 1) * (Integer.parseInt(rows)));
			queryMap.put("rows", (Integer.parseInt(rows)));
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<T> list = dao.get(queryMap);
			map.put("rows", list);
			map.put("total", dao.getCount(queryMap));
			map.put("msg","查询成功");
			map.put("success",true);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg","查询失败");
			map.put("success",false);
			return map;
		}
		return map;
	}
	
	// 根据id删除记录
	@RequestMapping(params = "del")
	@ResponseBody
	public Map<Object, Object> delete(String id){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			dao.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "删除失败");
			map.put("success", false);
			return map;
		}
		map.put("msg", "删除成功");
		map.put("success", true);
		return map;
	}
	
	
	// 根据id批量删除
	@RequestMapping(params = "delMore")
	@ResponseBody
	public Map<Object, Object> deleteMore(String id){
		Map<Object, Object> map = new HashMap<Object, Object>();
        try {
        	List<String> listId = new ArrayList<String>();
            String[] arrId = id.split(",");
            for(int i = 0 ; i < arrId.length ; i ++ ){
            	listId.add(arrId[i]);
            }
            if( listId.size() > 0 ){
            	System.out.println("listId:::::::::::::::::::::::::."+listId);
            	dao.deleteMoreByPK(listId);
            	map.put("msg", "操作成功");
                map.put("success", true);
            }else{
            	 map.put("msg", "缺少删除条件");
                 map.put("success", false);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "删除失败");
            map.put("success", false);
            return map;
            
        }
	}
	
	// 根据id批量删除
	@RequestMapping(params = "delMoreh")
	@ResponseBody
	public Map<Object, Object> deleteMoreh(String id){
		Map<Object, Object> map = new HashMap<Object, Object>();
        try {
        	List<String> listId = new ArrayList<String>();
            String[] arrId = id.split(",");
            for(int i = 0 ; i < arrId.length ; i ++ ){
            	listId.add(arrId[i]);
            }
            if( listId.size() > 0 ){
            	dao.deleteMoreByPKh(listId);
            	map.put("msg", "操作成功");
                map.put("success", true);
            }else{
//            	 map.put("msg", "缺少删除条件");
                 map.put("success", false);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "删除失败");
            map.put("success", false);
            return map;
            
        }
	}
	// 批量添加
	@RequestMapping(params = "addMore")
	@ResponseBody
	public Map<Object, Object> addMore(String params){
		Map<Object, Object> map = new HashMap<Object, Object>();
        try {
        	JSONArray jsonArray = JSONArray.fromObject(params);
    		List<Map<String,Object>> mapListJson =(List)jsonArray;
            if(mapListJson.size() > 0 ){
            	dao.insertMore(mapListJson);
            	map.put("msg", "保存成功");
                map.put("success", true);
            }else{
            	 map.put("msg", "保存失败");
                 map.put("success", false);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "保存失败");
            map.put("success", false);
            return map;
            
        }
	}
	
	//批量修改多个参数
	@RequestMapping(params = "updateMore")
	@ResponseBody
	public Map<Object, Object> updateMore(String params){
		Map<Object, Object> map = new HashMap<Object, Object>();
        try {
        	JSONArray jsonArray = JSONArray.fromObject(params);
    		List<Map<String,Object>> mapListJson =(List)jsonArray;
            if(mapListJson.size() > 0 ){
            	dao.updateMore(mapListJson);
            	map.put("msg", "修改成功");
                map.put("success", true);
            }else{
            	 map.put("msg", "修改失败");
                 map.put("success", false);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "修改失败");
            map.put("success", false);
            return map;
            
        }
	}

	// 根据id单个修改
	@RequestMapping(params = "updMoreByPkId")
	@ResponseBody
	public Map<Object, Object> updMoreByPkId(T paramT){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			dao.updateByPrimaryKeySelective(paramT);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
			map.put("success", false);
			return map;
		}
		map.put("msg", "修改成功");
		map.put("success", true);
		return map;
	}
	
	// 根据id批量修改
	@RequestMapping(params = "updMore")
	@ResponseBody
	public Map<Object, Object> updateMore(String id,T paramT){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Map<String, Object> param=new HashMap<String, Object>();
			List<String> listId = new ArrayList<String>();
            String[] arrId = id.split(",");
            for(int i = 0 ; i < arrId.length ; i ++ ){
            	listId.add(arrId[i]);
            }
            if( listId.size() > 0 ){
               param.put("obj", paramT);             
 	           param.put("listId", listId); 
 	           dao.updateMoreByPK(param);
            }
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
			map.put("success", false);
			return map;
		}
		map.put("msg", "修改成功");
        map.put("success", true);
		return map;
	}


	

	/**
	 * 将前台的时间字符串转化为Date类型
	 * */
	@InitBinder
	public void InitBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// 不要删除下行注释!!! 将来"yyyy-MM-dd"将配置到properties文件中
		// SimpleDateFormat dateFormat = new
		// SimpleDateFormat(getText("date.format", request.getLocale()));
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		// dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	
}
