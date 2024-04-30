package com.ian.media.controller;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ian.media.dao.PowerMenuMapper;
import com.ian.media.dao.PowerRoleMapper;
import com.ian.media.dao.PowerRoleMenuMapper;
import com.ian.media.dao.PowerRoleUserMapper;
import com.ian.media.dao.PowerUserMapper;
import com.ian.media.dao.LogLoginMapper;
import com.ian.media.model.PowerMenu;
import com.ian.media.model.PowerRoleMenu;
import com.ian.media.model.PowerUser;
import com.ian.media.util.IDGenerator;
import com.ian.media.util.StringUtil;
import com.ian.media.util.Time;
import com.ian.media.util.ToolUtil;
import com.ian.media.util.UploadImage;

/**
 * 角色管理功能
 * @author John
 *
 */
@Controller("powerMenu")
@Scope("prototype")
@RequestMapping("powerMenu.action")
public class PowerMenuController extends BaseController<PowerMenu> {
	 public PowerUserMapper powerUserDao;
	 public LogLoginMapper logLoginDao; 
	 public PowerRoleMapper powerRoleDao;
	 public PowerRoleUserMapper powerRoleUserDao;
	 public PowerMenuMapper powerMenuDao;
	 public PowerRoleMenuMapper powerRoleMenuDao;
	 List<PowerMenu>  menuList=new ArrayList<PowerMenu>();
	@Autowired
    public void setPowerRoleDao(PowerRoleMapper powerRoleMapper) {
        this.powerRoleDao = powerRoleMapper;
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
	public void setBaseDao(){
		super.setBaseDao(powerMenuDao);
	}
	
	
	 @RequestMapping(params = "getUserMenu")
	    @ResponseBody
	    public List getUserMenu(HttpServletRequest request,HttpSession session) {
	        Map<Object, Object> map = new HashMap<Object, Object>();
	        List resultList = new ArrayList();
	        try {
	        	//  String aboutusId = request.getParameter("aboutusId");
	            Map queryMap = new HashMap();
	            PowerUser u = (PowerUser) session.getAttribute("users");
	            queryMap.put("roleId",u.getRoleId());
	            List<PowerRoleMenu> list = powerRoleMenuDao.get(queryMap);
	            System.out.println(list.size()+"------------------");
	            List ids = new ArrayList();
	            for (int i = 0 ;i<list.size();i++){
	            	PowerRoleMenu menuaboutus = list.get(i);
	                ids.add(menuaboutus.getMenuId());
	            }
	            queryMap.put("ids",ids);
	            resultList = powerMenuDao.getUserMenu(queryMap);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return resultList;
	        }
	        return resultList;
	    }
	
	 
	 @RequestMapping(params = "getUserMenuById")
	    @ResponseBody
	    public List getUserMenuById(HttpServletRequest request,HttpSession session) {
	        Map<Object, Object> map = new HashMap<Object, Object>();
	        List resultList = new ArrayList();
	        try {
	        	String roleId = request.getParameter("roleId");
	            Map queryMap = new HashMap();
	            queryMap.put("roleId",roleId);
	            List<PowerRoleMenu> list = powerRoleMenuDao.get(queryMap);
	            System.out.println(list.size()+"------------------");
	            List ids = new ArrayList();
	            for (int i = 0 ;i<list.size();i++){
	            	PowerRoleMenu menuaboutus = list.get(i);
	                ids.add(menuaboutus.getMenuId());
	            }
	            queryMap.put("ids",ids);
	            resultList = powerMenuDao.getUserMenu(queryMap);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return resultList;
	        }
	        return resultList;
	    }
	 
	
	@RequestMapping(params = "getAllMenu")
	@ResponseBody
	public Map getAllMenu(HttpSession session,HttpServletRequest request,PowerMenu params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<PowerMenu> list=powerMenuDao.getAllMenu(map);
			map.put("rows", list);
			map.put("msg", "查询成功");
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "查询失败");
		}
		return map;
	}
	
	
	
	@RequestMapping(params = "add")
	@ResponseBody
	public Map add(HttpSession session,HttpServletRequest request,PowerMenu params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			params.setId(IDGenerator.getID());
			params.setUpdateTime(Time.nowDateToString());
			powerMenuDao.insertSelective(params);
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
	public Map update(HttpSession session,HttpServletRequest request,PowerMenu params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			powerMenuDao.updateByPrimaryKeySelective(params);
			map.put("msg", "修改成功");
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		return map;
	}
	
	
	
	
	
	/***************************************************************************************/
	
	/**
	 * 保存菜单
	 * @param paramT
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public Map addMenu(HttpSession session,HttpServletRequest request)
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		String nodes=request.getParameter("nodes");
		String  treeNodeName=request.getParameter("treeNodeName");//本节点name
		String  treeNodeId=request.getParameter("id");//本节点id
		String  parentId=request.getParameter("parentId");//父节点的id
		PowerUser u=(PowerUser)session.getAttribute("users");
		PowerMenu menu=new PowerMenu();
		menu.setMenuName(treeNodeName);
		menu.setParentId(parentId);
		if(StringUtil.isNotNull(treeNodeId))
		{
			menu.setId(treeNodeId);	
		}else
		{
			menu.setId(IDGenerator.getID());
		}
		menu.setUpdateTime(Time.nowDateToString());
		menu.setUpdateUserName(u.getUserName());
		List<PowerMenu> list=new ArrayList<PowerMenu>();
		list.add(menu);
		try {
			if(StringUtil.isNotNull(treeNodeId))
			{
				powerMenuDao.deleteByPrimaryKey(menu.getId());		
			}
				
			for(int g=0;g<list.size();g++)
			{
				powerMenuDao.insertSelective(list.get(g));
			}	
			map.put("msg", "保存成功");
			map.put("success", true);	
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "保存失败");
		}
		return map;
	}
	/**
	 *  删除节点操作
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deleteTree")
	@ResponseBody
	public Map deleteTree(HttpSession session,HttpServletRequest request)
	{
		Map<Object, Object> map=new HashMap<Object, Object>();
		Map<Object, Object> mapId=new HashMap<Object, Object>();
		String treeNode=request.getParameter("id");// 删除节点的id
		try {
			if(StringUtil.isNotNull(treeNode))
			{
				powerMenuDao.deleteByPrimaryKey(treeNode);	//删除本节点
				mapId.put("parentId", treeNode);
			    List<PowerMenu> list=powerMenuDao.listMenu(mapId);//查询本节点是否有子节点
				for(int i=0;i<list.size();i++)
				{
					powerMenuDao.deleteByPrimaryKey(list.get(i).getId());	
				}
				map.put("success",true);
				map.put("msg", "删除成功");
			}	
		} catch (Exception e) {
			map.put("success",false);
			map.put("msg", "删除失败");
		}
		
		return map;
	}
	/**
	 * 根据菜单id查询菜单
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "findByTreeId")
	@ResponseBody
	public Map findByTreeId(HttpSession session,HttpServletRequest request)
	{
		String treeId=request.getParameter("treeId");
		Map<Object, Object> map=new HashMap<Object, Object>();
		map.put("id",treeId );
		List<PowerMenu> list=powerMenuDao.get(map);
		map.clear();
		map.put("list", list);
	   return map;

	}
	/**
	 * 维护菜单的链接
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateByTreeId")
	@ResponseBody
	public Map updateByTreeId(HttpSession session,HttpServletRequest request)
	{

		String treeId=request.getParameter("treeId");
		String url=request.getParameter("url");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile uploadFile = (MultipartFile) multipartRequest
				.getFile("uploadFile");
		 //定义图片名字
		String imgName = "";
		imgName = ToolUtil.getCurSystemDate("yyyyMMddHHmmss")+".png";
		String path2 = request.getSession().getServletContext().getRealPath("data/icon/");
		UploadImage.uploadImage(uploadFile, path2, imgName);
		
		Map<Object, Object> map=new HashMap<Object, Object>();
		 PowerMenu menu=new PowerMenu();
		 map.put("id", treeId);
	    List<PowerMenu> list= powerMenuDao.get(map);
	    if(list.size()>0)
	    {
	    	menu=list.get(0);
	    	menu.setUrl(url);
	    	menu.setIcon("data/icon/"+imgName);
	    	powerMenuDao.updateByPrimaryKeySelective(menu);
	    }
		map.put("msg", "链接添加成功");
		return map;
	}
	
	
	
}
