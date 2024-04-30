package com.ian.media.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ian.media.dao.LinkMapper;
import com.ian.media.model.Link;
import com.ian.media.util.ToolUtil;

/**
 * 连接管理
 * @author John
 *
 */
@Controller("link")
@Scope("prototype")
@RequestMapping("link.action")
public class LinkController extends BaseController<Link>{
	public LinkMapper linkDao = null;
	@Autowired
	public void setLink(LinkMapper linkDaos) {
		this.linkDao = linkDaos;
	}
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(linkDao);
	}
	@RequestMapping(params = "add")
	@ResponseBody
	public Map<Object, Object> add(HttpSession session,HttpServletRequest request,Link params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			linkDao.insertSelective(params);
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
	public Map<Object, Object> update(HttpSession session,HttpServletRequest request,Link params){
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
				linkDao.updateByPrimaryKeySelective(params);
				map.put("success", true);
				map.put("msg", "修改 成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);	
			map.put("msg", "修改失败");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "uploadPhoto", method = { RequestMethod.POST,RequestMethod.GET })
	 public Map<Object, Object> uploadPhoto(@RequestParam(value = "photoFile") MultipartFile apkFile,
		        HttpServletRequest request, HttpServletResponse respons,HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (apkFile != null) {
		        //获取保存的路径，
		      //  String realPath = request.getSession().getServletContext().getRealPath("/imgUpload");
		        String realPath = request.getSession().getServletContext().getRealPath("/upload/image/img");
		        if (apkFile.isEmpty()) {
		            // 
		        	map.put("mesage", "未选择文件");
		        } else{
		            // 文件原名称
		        	String data =ToolUtil.getCurSystemDate("yyyyMMddHHmmss");
		            String originFileName = data+".jpg";
		            try {
		                //这里使用Apache的FileUtils方法来进行保存
		                FileUtils.copyInputStreamToFile(apkFile.getInputStream(),
		                        new File(realPath, originFileName));
		                String path ="upload/image/img/"+originFileName;
		                map.put("mesage", "上传成功");
		                map.put("path", path);
		            } catch (IOException e) {
		            	map.put("mesage","上传失败");
		                e.printStackTrace();
		            }
		        }
		 
		    }
		    return map;
		}

}
