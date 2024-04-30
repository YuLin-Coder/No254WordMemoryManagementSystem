package com.ian.media.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ian.media.dao.LinkClassMapper;
import com.ian.media.model.LinkClass;
/**
 * 连接种类管理
 * @author John
 *
 */
@Controller("linkClass")
@Scope("prototype")
@RequestMapping("linkClass.action")
public class LinkClassController extends BaseController<LinkClass>{
	public LinkClassMapper linkClassDao = null;
	@Autowired
	public void setLinkClass(LinkClassMapper linkClassDao) {
		this.linkClassDao = linkClassDao;
	}
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(linkClassDao);
	}
	@RequestMapping(params = "add")
	@ResponseBody
	public Map<Object, Object> add(HttpSession session,HttpServletRequest request,LinkClass params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			linkClassDao.insertSelective(params);
				map.put("msg", "添加 成功");
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "添加失败");
		}
		return map;
	}
	@RequestMapping(params = "update")
	@ResponseBody
	public Map<Object, Object> update(HttpSession session,HttpServletRequest request,LinkClass params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
				linkClassDao.updateByPrimaryKeySelective(params);
				map.put("success", true);
				map.put("msg", "修改 成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		return map;
	}

}
