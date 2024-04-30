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

import com.ian.media.dao.pingjiaMapper;
import com.ian.media.model.pingjia;
import com.ian.media.model.dingdan;

@Controller("pingjia")
@Scope("prototype")
@RequestMapping("pingjia.action")
public class pingjiaController  extends BaseController<pingjia>{
	
	public pingjiaMapper pingjiaMapper; 
	@Autowired
	  public void setPlansMapperDao(pingjiaMapper pingjiaMapper) {
        this.pingjiaMapper = pingjiaMapper;
    }
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(pingjiaMapper);
	}
	@RequestMapping(params = "add")
	@ResponseBody
	public Map<Object, Object> add(HttpSession session,HttpServletRequest request,pingjia params){
		
		System.out.println("你好1111111111111");
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		System.out.print("你好"+params);
		pingjia ss=new pingjia();
		 
		String a1=request.getParameter("a1");
		String a2=request.getParameter("a2");
		String a3=request.getParameter("a3");
		String a4=request.getParameter("a4");
 
		ss.setA1(a1);
		ss.setA2(a2);
		ss.setA3(a3);
		ss.setA4(a4);
		 
		try {
			pingjiaMapper.insertSelective(ss);
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
	public Map<Object, Object> update(HttpSession session,HttpServletRequest request,pingjia params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			pingjiaMapper.updateByPrimaryKeySelective(params);
				map.put("success", true);
				map.put("msg", "修改 成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		return map;
	}
	@RequestMapping(params = "yuding")
	@ResponseBody
	public Map<Object, Object> yuding(HttpSession session,HttpServletRequest request,pingjia params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			pingjiaMapper.updateByPrimaryKeySelective(params);
			
			//写入订单信息
			
			dingdan _dingdan=new dingdan();
			
			_dingdan.setA1(params.getA1());//房间编号
			_dingdan.setA2(params.getA5()); //扫码人姓名
			_dingdan.setA3(params.getA6());//扫码人联系方式
			_dingdan.setA4(params.getA3());//房间价格
			_dingdan.setA8(String.valueOf(params.getId()));//房间id
			 
			_dingdan.setA9(params.getA7());//当前登录用户id
			
			System.out.println("iddddddddddddddd::::::::::::."+params.getId()+"   a9::::::::::::"+params.getA7());
			 
		    pingjiaMapper.insertdingdan(_dingdan);
			 
			
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
