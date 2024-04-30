package com.ian.media.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ian.media.dao.MobileUserMapper;

import com.ian.media.model.MobileUser;

/**
 * 有饭有家--用户注册
 * @author Administrator
 *
 */
@Controller("mobileUser")
@Scope("prototype")
@RequestMapping("mobileUser.action")
public class MoblieUserController extends BaseController<MobileUser> {
	public MobileUserMapper mobileUserDao;

	@Autowired
    public void setAboutDao(MobileUserMapper mobileUserDao) {
        this.mobileUserDao = mobileUserDao;
    }

    @PostConstruct
    public void setBaseDao(){
        super.setBaseDao(mobileUserDao);
    }
	/**
	 * 新增功能
	 * @param session
	 * @param request
	 * @param params
	 * @return
	 */
    @RequestMapping(params = "add")
     @ResponseBody
	public Map<Object, Object> add(HttpSession session,HttpServletRequest request, MobileUser params){
    	Map<Object, Object> map = new HashMap<Object, Object>();
    	try {
    		mobileUserDao.insertSelective(params);
    		map.put("msg", "成功");
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "失败");
		}
    	return map;
    }
    
    /**
     * 修改
     * @param session
     * @param request
     * @param params
     * @return
     */
    @RequestMapping(params = "update")
      @ResponseBody
	public Map<Object, Object> update(HttpSession session,HttpServletRequest request,MobileUser params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			mobileUserDao.updateByPrimaryKeySelective(params);
			map.put("msg", "修改成功");
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		//return map;
		return map;
	}
 // 查询方法
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "listData")
	@ResponseBody
	public Map<Object, Object> listData(HttpServletRequest request){
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
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		// 如果没有page和rows则不传到SQL语句中
		if (page != null && rows != null) {
			queryMap.put("pageIndex",(Integer.parseInt(page)));
			queryMap.put("rows", (Integer.parseInt(rows)));
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<MobileUser> list = mobileUserDao.get(queryMap);
			map.put("rows", list);
			map.put("total", mobileUserDao.getCount(queryMap));
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
	// 根据id批量删除
	@RequestMapping(params = "delMoreEvent")
	@ResponseBody
	public Map<Object, Object> deleteMore(String id,HttpServletRequest request){
		Map<Object, Object> map = new HashMap<Object, Object>();
        try {
        	List<String> listId = new ArrayList<String>();
            String[] arrId = id.split(",");
            for(int i = 0 ; i < arrId.length ; i ++ ){
            	listId.add(arrId[i]);
            	Map<Object, Object> queryMap = new HashMap<Object, Object>();
            	queryMap.put("id", arrId[i]);
            	/*List<MobileUser> list = mobileUserDao.get(queryMap);*/
            	
            }
            if( listId.size() > 0 ){
            	mobileUserDao.deleteMoreByPK(listId);
            	map.put("msg", "删除成功");
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

}
