package com.ian.media.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.util.Arrays;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
/** 
 * 请求校验工具类 
 *  
 * @author liufeng 
 * @date 2013-05-18 
 */  
public class SignUtil {  
    // 与接口配置信息中的Token要一致  
    private static String token = "";
  
    /** 
     * 验证签名 
     *  
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] arr = new String[] { token, timestamp, nonce };  
        // 将token、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  
  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密  
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
  
        content = null;  
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
    
    public static String checkSignatureUrl(String ticket, String timestamp, String nonce,String url) throws Exception {  
    	String signature="";
    	String[] arr=new String[] {"noncestr="+nonce,"timestamp="+timestamp,"jsapi_ticket="+ticket,"url="+url};
    	Arrays.sort(arr);
    	StringBuilder content=new StringBuilder();
    	for(int i=0;i<arr.length;i++){
    		if(i !=0){
    			content.append("&");
    		}
    		content.append(arr[i]);
    	}
    	String string1=content.toString();
    	System.out.println("content============="+string1);
    	try{
    		MessageDigest crypt=MessageDigest.getInstance("SHA-1");
    		crypt.reset();
    		crypt.update(string1.getBytes("UTF-8"));
    		signature=byteToStr(crypt.digest());
    	}catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
    	
    	
    	
    	/*
    	MessageDigest md = null;  
    	String content="jsapi_ticket="+ticket+"&noncestr="+nonce+"&timestamp="+timestamp+"&url="+url;
    	System.out.println("content=="+content);
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密 
            byte[] digest = content.getBytes("UTF-8");
            md.update(digest);
            digest = md.digest(content.getBytes());  
            
            signature = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  */
        return signature;  
    }  
    
    
    /**
     * 网页服务签名加密
     */
    
    
  
    /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
    
    
    public static boolean check(HttpServletRequest request, HttpServletResponse response,String tempToken)
    {
        token = tempToken;
    	// 微信加密签名
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
        
        return SignUtil.checkSignature(signature, timestamp, nonce); 
        
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
    }
}
