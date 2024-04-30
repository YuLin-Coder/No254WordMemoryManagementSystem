package com.ian.media.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;

public class WxService {
	private final static Log log = LogFactory.getLog(WxService.class);
    public final static String USER_AGENT_H = "User-Agent";
    public final static String REFERER_H = "Referer";
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
    public final static String UTF_8 = "UTF-8";
	public final static String LOGIN_URL = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	public final static String INDEX_URL = "https://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String SENDMSG_URL = "https://mp.weixin.qq.com/cgi-bin/singlesend";
	public final static String FANS_URL = "https://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
	public final static String LOGOUT_URL = "https://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
	public final static String CONTACT_MANAGE_PAGE = "https://mp.weixin.qq.com/cgi-bin/contactmanagepage";
	
	public String token;
	public String slave_user;
	public String slave_sid;
	public String sig;

	private Cookie[] cookies;
	private String cookiestr;

	private int loginErrCode;
	private String loginErrMsg;
	private int msgSendCode;
	private String msgSendMsg;
	private List fans;

	private String loginUser;
	private String loginPwd;
	public boolean isLogin = false;

	public Cookie[] getCookies() {
		return cookies;
	}

	public void setCookies(Cookie[] cookies) {
		this.cookies = cookies;
	}

	public String getCookiestr() {
		return cookiestr;
	}

	public void setCookiestr(String cookiestr) {
		this.cookiestr = cookiestr;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getLoginErrCode() {
		return loginErrCode;
	}

	public void setLoginErrCode(int loginErrCode) {
		this.loginErrCode = loginErrCode;
	}

	public String getLoginErrMsg() {
		return loginErrMsg;
	}

	public void setLoginErrMsg(String loginErrMsg) {
		this.loginErrMsg = loginErrMsg;
	}

	public int getMsgSendCode() {
		return msgSendCode;
	}

	public void setMsgSendCode(int msgSendCode) {
		this.msgSendCode = msgSendCode;
	}

	public String getMsgSendMsg() {
		return msgSendMsg;
	}

	public void setMsgSendMsg(String msgSendMsg) {
		this.msgSendMsg = msgSendMsg;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}



	/**
	 * @title 微信公众平台模拟登陆
	 * @param username 登陆用户民
	 * @param pwd 密码
	 */
	public Map login(String username, String pwd, HttpServletRequest request) {
        Map returnMap = new HashMap();
		try {

			HttpSession session = request.getSession();
			String url = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN&f=json&imgcode="
					+ ""
					+ "&pwd="
					+ DigestUtils.md5Hex(pwd.getBytes())
					+ "&username=" + username;

			Protocol myhttps = new Protocol("https",
					new MySSLProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);

			HttpClient client = new HttpClient();
			PostMethod getMethod = new PostMethod(url);
			getMethod.setRequestHeader("Referer", "https://mp.weixin.qq.com/");
			getMethod.setRequestHeader("Host", "mp.weixin.qq.com");
			client.executeMethod(getMethod);
			Cookie[] cookies = client.getState().getCookies();
			String returnStr = getMethod.getResponseBodyAsString();

			System.out.println(returnStr);

			Map reutrnMap = (Map)JSONObject.parse(returnStr);

			Map retMap = (Map) reutrnMap.get("base_resp");

			if (Integer.valueOf(retMap.get("ret").toString()) == 302
					|| Integer.valueOf(retMap.get("ret").toString()) == 0) {

				this.cookies = client.getState().getCookies();

				StringBuffer cookie = new StringBuffer();
				for (Cookie c : client.getState().getCookies()) {
					cookie.append(c.getName()).append("=").append(c.getValue())
							.append(";");
				}
				this.cookiestr = cookie.toString();
				this.isLogin = true;
				this.token = getToken((String) reutrnMap.get("redirect_url"));

                System.out.println(this.cookiestr);
                System.out.println(this.token);
                request.getSession().setAttribute("token", this.token);
                request.getSession().setAttribute("cookie", this.cookiestr);
                returnMap.put("success",true);
                returnMap.put("msg","登陆成功");
                return returnMap;
			}else{
                int errCode =Integer.valueOf(retMap.get("ret").toString());
                this.loginErrCode = errCode;
                switch (errCode) {

                    case -1:
                        this.loginErrMsg = "系统错误";
                        break;
                    case -2:
                        this.loginErrMsg = "帐号或密码错误";
                        break;
                    case -23:
                        this.loginErrMsg = "密码错误";
                        break;
                    case -4:
                        this.loginErrMsg = "不存在该帐户";
                        break;
                    case -5:
                        this.loginErrMsg = "访问受限";
                        break;
                    case -6:
                        this.loginErrMsg = "需要输入验证码";
                        break;
                    case -7:
                        this.loginErrMsg = "此帐号已绑定私人微信号，不可用于公众平台登录";
                        break;
                    case -8:
                        this.loginErrMsg = "邮箱已存在";
                        break;
                    case -32:
                        this.loginErrMsg = "验证码输入错误";
                        break;
                    case -200:
                        this.loginErrMsg = "因频繁提交虚假资料，该帐号被拒绝登录";
                        break;
                    case -94:
                        this.loginErrMsg = "请使用邮箱登陆";
                        break;
                    case 10:
                        this.loginErrMsg = "该公众会议号已经过期，无法再登录使用";
                        break;
                    case 65201:
                    case 65202:
                        this.loginErrMsg = "成功登陆，正在跳转...";
                        break;
                    case 0:
                        this.loginErrMsg = "成功登陆，正在跳转...";
                        break;
                    default:
                        this.loginErrMsg = "未知的返回";
                        break;
                }

                returnMap.put("success",false);
                returnMap.put("msg",this.loginErrMsg);
                return returnMap;
            }

		} catch (Exception e) {
			e.printStackTrace();
            returnMap.put("success",false);
            returnMap.put("msg","未知的返回");
            return returnMap;
		}
	}

	/**
	 * @title 解析token
	 * @param s
	 * @return
	 */
	private String getToken(String s) {
		try {
			if (StringUtils.isBlank(s))
				return null;
			String[] ss = StringUtils.split(s, "?");
			String[] params = null;
			if (ss.length == 2) {
				if (!StringUtils.isBlank(ss[1]))
					params = StringUtils.split(ss[1], "&");
			} else if (ss.length == 1) {
				if (!StringUtils.isBlank(ss[0]) && ss[0].indexOf("&") != -1)
					params = StringUtils.split(ss[0], "&");
			} else {
				return null;
			}
			for (String param : params) {
				if (StringUtils.isBlank(param))
					continue;
				String[] p = StringUtils.split(param, "=");
				if (null != p && p.length == 2
						&& StringUtils.equalsIgnoreCase(p[0], "token"))
					return p[1];

			}
		} catch (Exception e) {
			String info = "【解析Token失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			log.debug(info);
			log.info(info);
			return null;
		}
		return null;
	}


	
	public String getFans(HttpServletRequest request) {
		try {
			
			HttpSession session = request.getSession();
			String token = (String)session.getAttribute("token");
			String cookie = (String)session.getAttribute("cookie");
			
			String url = "https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=10&pageidx=0&type=0&token="+token+"&lang=zh_CN";

            HttpClient client = new HttpClient();
            client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

            GetMethod get = new GetMethod(url);
            get.setRequestHeader("Referer", url);
            get.setRequestHeader("Host", "mp.weixin.qq.com");
            client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            get.setRequestHeader("Cookie",cookie);

            System.out.println(token+cookie);

            int status = client.executeMethod(get);
            System.out.println(status);
            if (status == HttpStatus.SC_OK) {
                // return parseFansCount(get.getResponseBodyAsString());
//					return parseFans(get.getResponseBodyAsString());
                System.out.println(get.getResponseBodyAsString());
                return get.getResponseBodyAsString();
            }else{
                return "获取失败,错误码:"+String.valueOf(status);
            }

		} catch (Exception e) {
			String info = "【获取粉丝数失败】【可能登录过期】";
			System.err.println(info);
			log.debug(info);
			log.info(info);
            return "";
		}

	}



    public Map setURLAndToken(String callback_url,String callback_token,HttpServletRequest request){
        Map returnMap= new HashMap();
        try{
            HttpClient client = new HttpClient();
            HttpSession session = request.getSession();
            String token = (String)session.getAttribute("token");
            String cookie = (String)session.getAttribute("cookie");
            String url =  "https://mp.weixin.qq.com/advanced/callbackprofile";
            PostMethod post = new PostMethod(url);
            client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            post.setRequestHeader("Referer","https://mp.weixin.qq.com/");
            post.setRequestHeader("Host", "mp.weixin.qq.com");
            System.out.println(cookie);

            NameValuePair[] params = new NameValuePair[]{
                    new NameValuePair("t","ajax-response"),
                    new NameValuePair("token",token),
                    new NameValuePair("lang","zh_CN"),
                    new NameValuePair("url",callback_url),
                    new NameValuePair("callback_token",callback_token)
            };

            Protocol myhttps = new Protocol("https",
                    new MySSLProtocolSocketFactory(), 443);
            Protocol.registerProtocol("https", myhttps);
            post.setQueryString(params);
            post.setRequestHeader("cookie", cookie);

            int status;
            status = client.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                System.out.println(text);
                Map map = (Map)JSONObject.parse(text);
                String ret = (String) map.get("ret");
                if(Integer.parseInt(ret) == 0){
                    returnMap.put("success",true);
                    returnMap.put("msg","设置成功");
                }else{
                    returnMap.put("success",false);
                    returnMap.put("msg","参数错误");
                }
            }
            return returnMap;
        }catch (Exception e){
            e.printStackTrace();
            return returnMap;
        }

    }
	

	public static void main(String args[]) {
		// login("","","");
	}
}
