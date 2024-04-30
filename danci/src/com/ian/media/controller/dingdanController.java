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

import com.ian.media.dao.dingdanMapper;
import com.ian.media.model.dingdan;
import com.ian.media.model.shangpin;


@Controller("dingdan")
@Scope("prototype")
@RequestMapping("dingdan.action")
public class dingdanController  extends BaseController<dingdan>{
	
	public dingdanMapper dingdanMapper; 
	@Autowired
	  public void setPlansMapperDao(dingdanMapper dingdanMapper) {
        this.dingdanMapper = dingdanMapper;
    }
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(dingdanMapper);
	}
	@RequestMapping(params = "add")
	@ResponseBody
	public Map<Object, Object> add(HttpSession session,HttpServletRequest request,dingdan params){
		
		System.out.println("你好1111111111111");
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		System.out.print("你好"+params);
		dingdan ss=new dingdan();
		 
		String a1=request.getParameter("a1");
		String a2=request.getParameter("a2");
 
		ss.setA1(a1);
		ss.setA2(a2);
		 
		try {
			dingdanMapper.insertSelective(ss);
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
	public Map<Object, Object> update(HttpSession session,HttpServletRequest request,dingdan params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			
			
			//登记身份证号
			dingdanMapper.updateByPrimaryKeySelective(params);
  			//更改房间状态
			
			
			/*
			caipin _cheliang=new caipin();
			
			_cheliang.setId(Integer.parseInt(params.getA8()));//传入房间id
			_cheliang.setA1(params.getA1());//传入房间号
			_cheliang.setA4(params.getA7());//更改房间状态 
			
			System.out.println("a1::::::::::::."+params.getA1()+" params.getA7()"+params.getA7()+"    id::::"+params.getA8()+" a11::::::"+params.getA11());
			
			dingdanMapper.updatecheliang(_cheliang);
			*/
			
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
