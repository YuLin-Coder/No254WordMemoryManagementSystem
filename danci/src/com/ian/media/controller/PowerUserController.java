package com.ian.media.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

 
import com.ian.media.dao.PowerRoleUserMapper;
import com.ian.media.dao.PowerUserMapper;
import com.ian.media.dao.LogLoginMapper;
import com.ian.media.model.PowerMenu;
import com.ian.media.model.PowerRoleUser;
import com.ian.media.model.PowerUser;
import com.ian.media.model.LogLogin;
import com.ian.media.util.MD5;
import com.ian.media.util.IDGenerator;
import com.ian.media.util.Time;


@Controller("powerUser")
@Scope("prototype")
@RequestMapping("powerUser.action")
public class PowerUserController extends BaseController<PowerUser> {
	 public PowerUserMapper powerUserDao;
	 public LogLoginMapper logLoginDao; 
	 public PowerRoleUserMapper roleUserDao;

	@Autowired
    public void setPowerUserMapperDao(PowerUserMapper powerUserDao) {
        this.powerUserDao = powerUserDao;
    }
	@Autowired
    public void setLogLoginMapperDao(LogLoginMapper logLoginDao) {
        this.logLoginDao = logLoginDao;
    }
	@Autowired
    public void setRoleUserDao(PowerRoleUserMapper roleUserMapper) {
        this.roleUserDao = roleUserMapper;
    }
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(powerUserDao);
	}
	
	/**
	 * 登录
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(params="login")
    @ResponseBody
    public Map login(PowerUser user, HttpServletRequest request,HttpSession session){
        Map map = new HashMap();
        String loginName = user.getLoginName();
        String passWord = user.getPassword();
        System.out.print("loginName::::::::::::"+loginName);
    	try {
	        if (loginName.equals("") || loginName.equals(null) || passWord.equals("") || passWord.equals(null)){
	        	map.put("success", false);
	        }else{
	        	map.put("loginName", loginName);
	        	map.put("password", MD5.pass(passWord));
	        	System.out.print(powerUserDao.get(map));
	        	List<PowerUser> list=powerUserDao.get(map);
	        	 if (list.size() <= 0){
		                map.put("success", false);
		            }else {
		            	this.addlog(list.get(0),1,request);
		            	map.put("success", true);
		            	map.put("users", list.get(0));
		                session.setAttribute("users",list.get(0));
		                
		              
		            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return map;
    }
	/**
	 * 获取IP地址
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	 public  String getIpAddr(HttpServletRequest request) {
	        String ip = request.getHeader("X-Real-IP");
	        if (null != ip && !"".equals(ip.trim())
	                && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	        ip = request.getHeader("X-Forwarded-For");
	        if (null != ip && !"".equals(ip.trim())
	                && !"unknown".equalsIgnoreCase(ip)) {
	            // get first ip from proxy ip
	            int index = ip.indexOf(',');
	            if (index != -1) {
	                return ip.substring(0, index);
	            } else {
	                return ip;
	            }
	        }
	        return request.getRemoteAddr();
	    }
	
	/**
	 * 登录
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(params="mobilelogin")
    @ResponseBody
    public Map mobilelogin(PowerUser user, HttpServletRequest request,HttpSession session){
        Map map = new HashMap();
        String loginName = user.getLoginName();
        String passWord = user.getPassword();
        int mobilePowerState=user.getMobilePowerState();
    	try {
	        if (loginName.equals("") || loginName.equals(null) || passWord.equals("") || passWord.equals(null)){
	        	map.put("success", false);
	        }else{
	        	map.put("loginName", loginName);
	        	map.put("password", MD5.pass(passWord));
	        	map.put("mobilePowerState", mobilePowerState);
	        	List<PowerUser> list=powerUserDao.get(map);
	        	 if (list.size() <= 0){
		                map.put("success", false);
		            }else {
		            	this.addlog(list.get(0),2,request);
		            	map.put("user",list.get(0));
		            	map.put("success", true);
		            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return map;
    }
	
	
	public void addlog(PowerUser user,int type, HttpServletRequest request){
		
		LogLogin paramT=new LogLogin();
        paramT.setId(IDGenerator.getID());
        paramT.setLoginName(user.getLoginName());
        paramT.setLoginTimer(Time.nowDateToString());
        paramT.setType(type);
        paramT.setUserId(user.getId());
        paramT.setColumn03(this.getIpAddr(request));
        paramT.setUserName(user.getUserName());
        logLoginDao.insertSelective(paramT);
	}
	
	
	/**
	 * 退出
	 */
	@RequestMapping(params="logOut")
    @ResponseBody
    public Map logOut(HttpSession session){
		Map map = new HashMap();
        try {
            session.setAttribute("users",new PowerUser());
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }
	
	/**
	 * 修改密码
	 */
	@RequestMapping(params="updatePwd")
    @ResponseBody
    public Map updatePwd(HttpServletRequest request,String userId,String oldpwd,String newpwd){
		Map map = new HashMap();
        try {
        	map.put("id", userId);
        	map.put("password", MD5.pass(oldpwd));
        	List<PowerUser> list=powerUserDao.get(map);
        	if(list.size() == 0){
        		map.put("result", 0);
        	}else{
        		map.put("result", 1);
        		PowerUser powerUser=new PowerUser();
        		powerUser.setId(userId);
        		powerUser.setPassword(MD5.pass(newpwd));
        		powerUserDao.updateByPrimaryKeySelective(powerUser);
        	}
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }
	
	
	@RequestMapping(params = "add")
	@ResponseBody
	public Map add(HttpSession session,HttpServletRequest request,PowerUser params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Map<Object, Object> quemap = new HashMap<Object, Object>();
			quemap.put("loginName", params.getLoginName());
			List<PowerUser> list=powerUserDao.get(quemap);
			if(list.size()>0){
				map.put("msg", "登录名已存在！");
			}else{
				params.setId(IDGenerator.getID());
				params.setPassword(MD5.pass("111111"));
				params.setUpdateTime(Time.nowDateToString());
				powerUserDao.insertSelective(params);
				map.put("msg", "添加 成功");
			}
			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "添加失败");
		}
		return map;
	}

	@RequestMapping(params = "addtwo")
	@ResponseBody
	public Map addtwo(HttpSession session,HttpServletRequest request,PowerUser params){
		
		System.out.print("loginName:::"+params.getLoginName()+"  password:::::::::::::: "+request.getParameter("password"));
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Map<Object, Object> quemap = new HashMap<Object, Object>();
			quemap.put("loginName", params.getLoginName());
			List<PowerUser> list=powerUserDao.get(quemap);
			if(list.size()>0){
				map.put("msg", "登录名已存在！");
			}else{
				params.setId(IDGenerator.getID());
				params.setPassword(MD5.pass(request.getParameter("password")));
				params.setUpdateTime(Time.nowDateToString());
				
				params.setRoleId("081BBBF0-3372-4A15-8C96-BB8242836DCF");
				
				params.setUserName(params.getLoginName());
				
				powerUserDao.insertSelective(params);
				map.put("msg", "添加 成功");
			}
			
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
	public Map update(HttpSession session,HttpServletRequest request,PowerUser params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			Map<Object, Object> quemap = new HashMap<Object, Object>();
			quemap.put("loginName", params.getLoginName());
			List<PowerUser> list=powerUserDao.get(quemap);
			if(list.size()>0 && !list.get(0).getId().equals(params.getId())){
				map.put("success", false);
				map.put("msg", "登录名已存在！");
			}else{
				powerUserDao.updateByPrimaryKeySelective(params);
				map.put("success", true);
				map.put("msg", "修改 成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		return map;
	}
	
	
	
	
	/**
	 * 用户管理新增/修改
	 * @param paramT
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addUser")
	@ResponseBody
	public Map addUser(PowerUser paramT,HttpSession session,HttpServletRequest request)
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		String[] roleId=request.getParameterValues("roleId");
		try {
			PowerUser u=(PowerUser)session.getAttribute("users");
			paramT.setUpdateUserId(u.getId());
			paramT.setUpdateUserName(u.getUserName());
			paramT.setUpdateTime(Time.nowDateToString());
			paramT.setState(1);
			paramT.setMobilePowerState(1);
			paramT.setPassword(MD5.pass("111111"));
			if(paramT.getId()!=null && !"".equals(paramT.getId())){
				powerUserDao.updateByPrimaryKeySelective(paramT);
			}else {
				paramT.setId(IDGenerator.getID());
				powerUserDao.insertSelective(paramT);
				
			}
			//用户跟角色关联表 现根据userid删除然后再保存
			roleUserDao.deleteByPrimaryUserId(paramT.getId());
			for(int i=0;i<roleId.length;i++)
			{
				PowerRoleUser roleUser=new PowerRoleUser();
				roleUser.setId(IDGenerator.getID());
				roleUser.setRoleId(roleId[i]);//角色id
				roleUser.setPowId("0");
				roleUser.setUserId(paramT.getId());//用户id
				roleUserDao.insertSelective(roleUser);
			}
			map.put("msg", "保存成功");
			map.put("success", true);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "保存失败");
		}
		
		return map;
	}
	
	@RequestMapping(params = "deleteUser")
	@ResponseBody
	public Map<Object, Object> delete(String id)
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<String> listId = new ArrayList<String>();
        String[] arrId = id.split(",");
        for(int i = 0 ; i < arrId.length ; i ++ ){
        	listId.add(arrId[i]);
        	roleUserDao.deleteByPrimaryUserId(arrId[i]);
        }
        powerUserDao.deleteMoreByPK(listId);
		 map.put("msg", "删除成功");
         map.put("success", true);
         return map;
	}	

}
