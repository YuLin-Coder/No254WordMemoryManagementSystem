package com.ian.media.controller;


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




import com.ian.media.dao.LogLoginMapper;
import com.ian.media.model.LogLogin;
/**
 * 登录功能
 * @author Administrator
 *
 */

@Controller("logLogin")
@Scope("prototype")
@RequestMapping("logLogin.action")
public class LogLoginController extends BaseController<LogLogin> {
	 public LogLoginMapper logLoginDao; 

	@Autowired
    public void setLogLoginMapperDao(LogLoginMapper logLoginDao) {
        this.logLoginDao = logLoginDao;
    }
	
	@PostConstruct
	public void setBaseDao(){
		super.setBaseDao(logLoginDao);
	}
	
	@RequestMapping(params="listLogLogin")
    @ResponseBody
    public Map<String, Object> listLogLogin(LogLogin logLogin, HttpServletRequest request,HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        String datefrom=request.getParameter("datefrom");
        String dateto=request.getParameter("dateto");
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
    	try {
    		Map<Object, Object> queryMap = new HashMap<Object, Object>();
    		if(logLogin.getUserName()!=null && !logLogin.getUserName().equals("")){
    			queryMap.put("userName",logLogin.getUserName());
    		}
            queryMap.put("type",logLogin.getType());
            queryMap.put("dateto", dateto);
            queryMap.put("datefrom", datefrom);
            map.put("total", logLoginDao.getCount(queryMap));
            queryMap.put("pageIndex",Integer.parseInt(page));
            queryMap.put("rows",Integer.parseInt(rows));
            List<LogLogin> list = logLoginDao.listLogLogin(queryMap);
            map.put("page", page);
            map.put("success", true);
            map.put("rows", list);
	        
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "服务器异常");
			e.printStackTrace();
		}
        return map;
    }

}
