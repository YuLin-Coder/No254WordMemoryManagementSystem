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

import com.ian.media.dao.leixingMapper;
import com.ian.media.model.leixing;

@Controller("leixing")
@Scope("prototype")
@RequestMapping("leixing.action")
public class leixingController  extends BaseController<leixing>{
	
	public leixingMapper leixingMapper; 
	@Autowired
	  public void setPlansMapperDao(leixingMapper leixingMapper) {
        this.leixingMapper = leixingMapper;
    }
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(leixingMapper);
	}
	@RequestMapping(params = "add")
	@ResponseBody
	public Map<Object, Object> add(HttpSession session,HttpServletRequest request,leixing params){
		
		System.out.println("你好1111111111111");
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		System.out.print("你好"+params);
		leixing ss=new leixing();
		 
		String a1=request.getParameter("a1");
		String a2=request.getParameter("a2");
 
		ss.setA1(a1);
		ss.setA2(a2);
		 
		try {
			leixingMapper.insertSelective(ss);
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
	public Map<Object, Object> update(HttpSession session,HttpServletRequest request,leixing params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			leixingMapper.updateByPrimaryKeySelective(params);
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
