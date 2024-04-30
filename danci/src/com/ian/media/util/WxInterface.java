package com.ian.media.util;

public class WxInterface {

	 //获取access_token
	 public final static String get_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	 
	 //获取用户基本信息
	 public final static String get_user_info = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	 
	 //客服接口——发送消息
	 public final static String kf_send_msg="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	 
	 //获取ticket
	 public final static String get_ticket="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	 
	 //上传图片
	 public static String upload_img_url="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	 
	 //上传图文消息
	 public static String upload_newImg_url="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	    
	 //分组发送
	 public static String send_newImg_url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	    
	 //根据关注着列表推送
	 public static String send_Customer_url="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	    
	 //文本推送
	 public static String testSend_Customer_url="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
}
