package com.ian.media.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.json.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.ian.media.util.ToolUtil;
import com.ian.media.util.UploadImage;


@Controller("utilAction")
@Scope("prototype")
@RequestMapping("/util")
public class UtilController {
  private String IP ="http://localhost:8080/danci/upload/";

     /**
      * @title 主要用户素材管理的图片上传
      * @param request
      * @return Map 返回图片的URL
      */
  @RequestMapping("/uploadFile")
  @ResponseBody
  public String uploadFile(HttpServletRequest request) {
      Map map = new HashMap();

      try {
          //转化一下request
          MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
          //取得file
          MultipartFile file = (MultipartFile) multipartRequest.getFile("file_upload");


          Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();


          // 定义图片路径
          String path = request.getSession().getServletContext().getRealPath(
                  File.separator+"data"+File.separator+"material"+File.separator)+File.separator;

          ToolUtil.createDir(path);

          for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
              MultipartFile mf = entity.getValue();
              String imgName = ToolUtil.getUploadImageNamejpg("material");

              //上传图片
              File uploadFile = new File(path+imgName);


              FileCopyUtils.copy(mf.getBytes(), uploadFile);

              map.put("imageUrl", imgName);
          }

      } catch (Exception e) {
          e.printStackTrace();
          return JSONObject.toJSONString("map");
      }
      return JSONObject.toJSONString(map) ;
  }





  /**
   * @title 主要用户素材管理的图片上传
   * @param request
   * @return Map 返回图片的URL
   */
  @RequestMapping("/uploadFileByBase64")
  @ResponseBody
  public Map uploadFileByBase64(HttpServletRequest request) {
      Map map = new HashMap();
      try {

          String data =request.getParameter("data");
          if(ToolUtil.unless(data)){
              data = data.substring(22,data.length()-1);
              // 定义图片路径
              String path = request.getSession().getServletContext().getRealPath(
                      "data/material/");
              String imgName = ToolUtil.getUploadImageNamejpg("material");
              ToolUtil.createFile(path+imgName);
              ToolUtil.generateImage(data,path+imgName);
              map.put("url","data/material/"+imgName);
              map.put("success",true);
          }else{
              map.put("success",false);
          }

      } catch (Exception e) {
          e.printStackTrace();
          map.put("success",false);
          return map;
      }

      return map;
  }


     /**
      * @title 主要ueditor富文本编辑器的图片上传
      * @param request
      * @param action  前台传值 action=config action = uploadImage
      * @return Map 返回图片的URL
      */
     @RequestMapping("/ueditor")
     @ResponseBody
     public String ueditor(HttpServletRequest request,HttpServletResponse response,String action) {
         Map<Object, Object> map = new HashMap<Object, Object>();
         IP="http://localhost:8080/danci/upload/ueditor/";
         //ue
         if("config".equals(action)){
             try {
                 String encoding="UTF-8";
                 String filePath = request.getSession().getServletContext().getRealPath("/media/ueditor/jsp/config.json");
                 File file=new File(filePath);
                 if(file.isFile() && file.exists()){ //判断文件是否存在
                     InputStreamReader read = new InputStreamReader(
                             new FileInputStream(file),encoding);//考虑到编码格式
                     BufferedReader bufferedReader = new BufferedReader(read);
                     String lineTxt = null;
                     StringBuffer buffer = new StringBuffer();
                     while((lineTxt = bufferedReader.readLine()) != null){
                         buffer.append(lineTxt);
                     }
                     read.close();
                     return buffer.toString();
                 }else{
                     System.out.println("找不到指定的文件");
                 }
             } catch (Exception e) {
                 System.out.println("读取文件内容出错");
                 e.printStackTrace();
             }
         }else if("uploadimage".equals(action)){
        	
             try{
                 //转化一下request
                 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                 //取得file
                 MultipartFile file = (MultipartFile) multipartRequest.getFile("file_upload");


                 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();


                 // 定义图片路径
                 String path = request.getSession().getServletContext().getRealPath(
                         File.separator+"upload"+File.separator+"ueditor"+File.separator)+File.separator;



                 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                     MultipartFile mf = entity.getValue();
                     String imgName = ToolUtil.getUploadImageNamejpg("ue");

                     String imgDate = ToolUtil.getCurSystemDate("yyyyMMddhhmmss")+getSixNumber();

                     String imageName = "ue"+"_"+imgDate+".jpg";

                     ToolUtil.createFile(path+imgName);
                     //上传图片
                     File uploadFile = new File(path+imgName);


                     FileCopyUtils.copy(mf.getBytes(), uploadFile);

                     map.put("state","SUCCESS");
                     map.put("url",IP+imgName);
                     map.put("original",IP+imgName);
                     map.put("name",imgName);

             }
             return JSONObject.toJSONString(map);


             }catch (Exception e){
                 e.printStackTrace();
             }
         }else if("listimage".equals(action)){
             String path = request.getSession().getServletContext().getRealPath(
                     File.separator+"upload"+File.separator+"ueditor"+File.separator)+File.separator;
             ToolUtil.createDir(path);
             File fileDir = new File(path);

             String[] files= fileDir.list();


             String start = request.getParameter("start");
             String size = request.getParameter("size");
             map.put("state","SUCCESS");
             map.put("start",0);
             map.put("total",files.length);
             List list = new ArrayList();
             for(int i= 0 ;i<files.length;i++){
                 Map map1 = new HashMap();
                 map1.put("url",files[i]);
                 list.add(map1);
             }

             map.put("list",list);
             return JSONObject.toJSONString(map);
         }else if("uploadFile".equals(action))
        	 {
        	    //转化一下request
             MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
             //取得file
             MultipartFile file = (MultipartFile) multipartRequest.getFile("upfile");


             Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

             // 定义图片路径
             String path = request.getSession().getServletContext().getRealPath(
                     File.separator+"upload"+File.separator+"ueditor"+File.separator)+File.separator;
             for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                 MultipartFile mf = entity.getValue();
                 String imgName = ToolUtil.getUploadImageNamejpg("ue");

                 String imgDate = ToolUtil.getCurSystemDate("yyyyMMddhhmmss")+getSixNumber();

                 String imageName =imgDate+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
//                 "material"+"_"+imgDate+".jpg";

                 ToolUtil.createFile(path+imageName);
                 //上传图片
                 File uploadFile = new File(path+imageName);


                 try {
					FileCopyUtils.copy(mf.getBytes(), uploadFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                 map.put("state","SUCCESS");
                 map.put("url",IP+imageName);
                 map.put("original",IP+imageName);
                 map.put("name",imageName);
                 return JSONObject.toJSONString(map);
             }
        	 }else{
             map.put("state","请求地址出错");
             return JSONObject.toJSONString(map);
         }
         return "";
     }
     
     /**
      * @author david
      * @title 主要用户图文消息的删除图片
      * @param request
      * @param response
      * @return 返回消息
      */
     @RequestMapping("/deleteFile")
     @ResponseBody
     public Map deleteFile(HttpServletRequest request,HttpServletResponse response){
         Map map = new HashMap();

         String url = request.getParameter("imageUrl");
         if(url == null){
             map.put("success",false);
             map.put("msg","图片地址错误");
             return map;
         }
         // 定义图片路径
         String path = request.getSession().getServletContext().getRealPath(
                 "data/material/")+"\\";

         if(UploadImage.deleteFile(path,url)){
             map.put("success",true);
             map.put("msg","图片删除成功");
             return map;
         }else{
             map.put("success",false);
             map.put("msg","图片删除失败");
             return map;
         }
     }

     public static int getSixNumber(){
         int[] array = {0,1,2,3,4,5,6,7,8,9};
         Random rand = new Random();
         for (int i = 10; i > 1; i--) {
             int index = rand.nextInt(i);
             int tmp = array[index];
             array[index] = array[i - 1];
             array[i - 1] = tmp;
         }
         int result = 0;
         for(int i = 0; i < 6; i++)
             result = result * 10 + array[i];
         return result;
     }
     
     //推送注册设备
     public static String registerDevice(String deviceCode,String deviceType){
    	 String requestUrl="http://test.s4s.com.cn/ianpush/resources/register/regDevice";
			String appId="";//个推生成的应用id
			if(deviceType.equals("1")){//ios
				appId="LSXvXALg206oIZJIIYNYt8";
			}else{
				appId="OyqTQ5dSMG8pDFRX5AZ3Q5";
			}
			
			String deType=deviceType;//设备类型1是ios 2 是android
			String osName="";
			String phoneNum="";
			String jsonMsg="{\"appId\": \""+appId+"\",\"deviceCode\": \""+deviceCode+"\",\"deviceType\": \""+deType+"\",\"osName\": \"\",\"phoneNum\":\"\"}";
			//JSONObject jsonObject = WxPublicMethodUtil.httpRequest(requestUrl, "POST",jsonMsg,"123");
			UtilController.post(requestUrl, jsonMsg);
			return "";
     }
     
     //推送消息
     public static String post(String strURL, String params) {  
	        System.out.println(strURL);  
	        System.out.println(params);  
	        try {  
	            URL url = new URL(strURL);// 创建连接  
	            HttpURLConnection connection = (HttpURLConnection) url  
	                    .openConnection();  
	            connection.setDoOutput(true);  
	            connection.setDoInput(true);  
	            connection.setUseCaches(false);  
	            connection.setInstanceFollowRedirects(true);  
	            connection.setRequestMethod("POST"); // 设置请求方式  
	            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
	            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
	            connection.connect();  
	            OutputStreamWriter out = new OutputStreamWriter(  
	                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
	            out.append(params);  
	            out.flush();  
	            out.close();  
	            // 读取响应  
	            int length = (int) connection.getContentLength();// 获取长度  
	            InputStream is = connection.getInputStream();  
	            if (length != -1) {  
	                byte[] data = new byte[length];  
	                byte[] temp = new byte[512];  
	                int readLen = 0;  
	                int destPos = 0;  
	                while ((readLen = is.read(temp)) > 0) {  
	                    System.arraycopy(temp, 0, data, destPos, readLen);  
	                    destPos += readLen;  
	                }  
	                String result = new String(data, "UTF-8"); // utf-8编码  
	                System.out.println(result);  
	                return result;  
	            }  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return "error"; // 自定义错误信息  
	    }  
	
	
}
