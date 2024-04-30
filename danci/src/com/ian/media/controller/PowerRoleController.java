package com.ian.media.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



import com.ian.media.dao.PowerMenuMapper;
import com.ian.media.dao.PowerRoleMapper;
import com.ian.media.dao.PowerRoleMenuMapper;
import com.ian.media.dao.PowerRoleUserMapper;
import com.ian.media.dao.PowerUserMapper;
import com.ian.media.dao.LogLoginMapper;
import com.ian.media.model.PowerMenu;
import com.ian.media.model.PowerRole;
import com.ian.media.model.PowerRoleMenu;
import com.ian.media.model.PowerRoleUser;
import com.ian.media.model.PowerUser;
import com.ian.media.model.LogLogin;
import com.ian.media.util.MD5;
import com.ian.media.util.IDGenerator;
import com.ian.media.util.Time;

/**
 * 角色管理功能
 * 
 * @author John
 * 
 */
@Controller("powerRole")
@Scope("prototype")
@RequestMapping("powerRole.action")
public class PowerRoleController extends BaseController<PowerRole> {
	public PowerUserMapper powerUserDao;
	public LogLoginMapper logLoginDao;
	public PowerRoleMapper powerRoleDao;
	public PowerRoleUserMapper powerRoleUserDao;
	public PowerMenuMapper powerMenuDao;
	public PowerRoleMenuMapper powerRoleMenuDao;

	@Autowired
	public void setPowerRoleDao(PowerRoleMapper powerRoleMapper) {
		this.powerRoleDao = powerRoleMapper;
	}
	
	@Autowired
	public void setPowerUserDao(PowerUserMapper powerUserMapper) {
		this.powerUserDao = powerUserMapper;
	}

	@Autowired
	public void setPowerRoleUserDao(PowerRoleUserMapper powerRoleUserMapper) {
		this.powerRoleUserDao = powerRoleUserMapper;
	}

	@Autowired
	public void setPowerMenuDao(PowerMenuMapper powerMenuMapper) {
		this.powerMenuDao = powerMenuMapper;
	}

	@Autowired
	public void setPowerRoleMenuDao(PowerRoleMenuMapper powerRoleMenuMapper) {
		this.powerRoleMenuDao = powerRoleMenuMapper;
	}

	@PostConstruct
	public void setBaseDao() {
		super.setBaseDao(powerRoleDao);
	}
	
	@RequestMapping(params = "add")
	@ResponseBody
	public 	Map<Object, Object> add(HttpSession session,HttpServletRequest request,PowerRole params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println(params.getRoleName()+"========================");
		try {
			params.setUpdateTime(Time.nowDateToString());
			powerRoleDao.insertSelective(params);
			String addlist=request.getParameter("addlist");
			JSONArray jsonArray = JSONArray.fromObject(addlist);
    		List<Map<String,Object>> mapListJson =(List)jsonArray;
    		dao.insertMore(mapListJson);
			map.put("msg", "添加成功");
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
	public Map update(HttpSession session,HttpServletRequest request,PowerRole params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			params.setUpdateTime(Time.nowDateToString());
			powerRoleDao.updateByPrimaryKeySelective(params);
			
			powerRoleMenuDao.deleteByRoleId(params.getId());
			
			String addlist=request.getParameter("addlist");
			JSONArray jsonArray = JSONArray.fromObject(addlist);
    		List<Map<String,Object>> mapListJson =(List)jsonArray;
    		dao.insertMore(mapListJson);
			map.put("msg", "修改成功");
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		return map;
	}
	
	
	
	
	/*****************************************************************************************************/

	/**
	 * 角色新增
	 * 
	 * @param paramT
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addRole")
	@ResponseBody
	public Map addRole(PowerRole paramT, HttpSession session,
			HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		String[] roleId = request.getParameterValues("roleId");// 菜单权限
		try {
			PowerUser u = (PowerUser) session.getAttribute("users");
			paramT.setUpdateUserId(u.getId());
			paramT.setUpdateUserName(u.getUserName());
			paramT.setUpdateTime(Time.nowDateToString());
			paramT.setState(1);
			if (paramT.getId() != null && !"".equals(paramT.getId())) {
				powerRoleDao.updateByPrimaryKeySelective(paramT);
			} else {
				paramT.setId(IDGenerator.getID());
				powerRoleDao.insertSelective(paramT);
			}
			// 如果选择了菜单，就插入到角色-菜单表
			powerRoleMenuDao.deleteByRoleId(paramT.getId());
			for (int p = 0; p < roleId.length; p++) {
				PowerRoleMenu menu = new PowerRoleMenu();// 新建一个角色-菜单实体
				menu.setId(IDGenerator.getID());
				menu.setMenuId(roleId[p]);
				menu.setRoleId(paramT.getId());// 角色id
				powerRoleMenuDao.insertSelective(menu);// 插入到数据库中
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

	/**
	 * 获取角色用户关联数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getUserAndRole")
	@ResponseBody
	public Map<Object, Object> getUserAndRole(HttpServletRequest request) {
		Map<Object, Object> queryMap = new HashMap<Object, Object>();
		List<PowerRoleUser> list = powerRoleUserDao.selectByUsreId(request
				.getParameter("userId"));
		queryMap.put("rows", list);
		return queryMap;
	}

	/**
	 * 角色管理页面查询菜单功能
	 * 
	 * @return
	 */
	@RequestMapping(params = "getMenu")
	@ResponseBody
	public Map<Object, Object> listMenuParent(HttpServletRequest request, HttpSession session)
	{
		PowerUser u = (PowerUser) session.getAttribute("users");
		String  dePartment=u.getDepartment();//部门
		Map<Object, Object> Rolemap = new HashMap<Object, Object>();
		Rolemap.put("user_id", u.getId());
		List<PowerRoleUser> listRoles=powerRoleUserDao.get(Rolemap);
		//如果是管理员，就可以看到全部信息
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<Object, Object> map2 = new HashMap<Object, Object>();
		String roleId = request.getParameter("roleId");
		if(listRoles.size()>0)
		{
			roleId=listRoles.get(0).getRoleId();
		}
		
		List<PowerMenu> listChild = new ArrayList<PowerMenu>();
		List<PowerMenu> listChild2 = new ArrayList<PowerMenu>();
		List<PowerMenu> listParent = new ArrayList<PowerMenu>();
		if (!"1".equals(roleId) && !"".equals(roleId)) {
			map.put("roleId", roleId);
			List<PowerRoleMenu> list = powerRoleMenuDao.get(map);// 查询菜单角色管理表
			for (int i = 0; i < list.size(); i++) {
				
				PowerMenu parentMenu = new PowerMenu();
				map2.clear();
				map2.put("roleId", roleId);
				map2.put("parentId", "0");
				map2.put("id", list.get(i).getMenuId());
				if (powerMenuDao.listMenu(map2).size() > 0) {
					parentMenu = powerMenuDao.listMenu(map2).get(0);// 查询菜单信息
					listParent.add(parentMenu);
				}

				PowerMenu chilMenu = new PowerMenu();
				map2.clear();
				map2.put("parentIdN", "0");
				map2.put("id", list.get(i).getMenuId());
				if (powerMenuDao.listMenu(map2).size() > 0) {
					chilMenu = powerMenuDao.listMenu(map2).get(0);
					listChild.add(chilMenu);
				}
			}
			if(dePartment.equals("管理员"))
			{
				map.put("parentId", "0");
				listParent = powerMenuDao.listMenu(map);
				map.clear();
				map.put("parentIdN", "0");
				listChild = powerMenuDao.listMenu(map);
				
			} 
			if (listParent.size() > 0) {
				map.put("parent", listParent);
			}
			if (listChild.size() > 0) {
				map.put("child", listChild);
			}
			return map;
		}
		map.put("parentId", "0");
		listParent = powerMenuDao.listMenu(map);
		map.clear();
		map.put("parentIdN", "0");
		listChild = powerMenuDao.listMenu(map);
		map.put("parent", listParent);
		map.put("child", listChild);
		return map;
	}

	/**
	 * 根据角色id查询菜单
	 * 
	 * @return
	 */
	@RequestMapping(params = "listMenu")
	@ResponseBody
	public Map<Object, Object> listMenuByRoleId(HttpServletRequest request) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		String roleId = request.getParameter("roleId");
		map.put("roleId", roleId);
		List<PowerRoleMenu> list = powerRoleMenuDao.get(map);
		resultMap.put("list", list);
		return resultMap;
	}

	/**
	 * 删除
	 */
	@RequestMapping(params = "deleteRole")
	@ResponseBody
	public Map<Object, Object> delete(String id) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<String> listId = new ArrayList<String>();
		String[] arrId = id.split(",");
		for (int i = 0; i < arrId.length; i++) {
			Map<Object, Object> mapRoleUser = new HashMap<Object, Object>();
			mapRoleUser.put("roleId", arrId[i]);
			List<PowerUser> listUser=powerUserDao.get(mapRoleUser);
			if(listUser.size()>0){
				map.put("msg", " 该角色下存在用户，不能删除！");
				map.put("success", true);
				return map;
			}else{
				listId.add(arrId[i]);
				powerRoleMenuDao.deleteByRoleId(arrId[i]);
//				powerRoleUserDao.deleteByPrimaryUserId(arrId[i]);
			}

		}
		powerRoleDao.deleteMoreByPK(listId);
		map.put("msg", "删除成功");
		map.put("success", true);
		return map;
	}
}
