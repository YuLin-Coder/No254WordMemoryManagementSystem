package com.ian.media.util;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

//import sun.misc.BASE64Decoder;

public class ToolUtil {
	private static final Log log = LogFactory.getLog(ToolUtil.class);
	private static final String DEBUG = "1";
	private static final long DISPLAY_ORDER_OFFSET = 1238515200000L; // 2009-04-01
	private static final long DISPLAY_ORDER_TOP = 630720000000L; // 20 year

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


    public static boolean  unless(String str){
        if(str == null || "".equals(str)){
            return false;
        }else{
            return true;
        }
    }

	public static void sleepMilliSeconds(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	public static void sleepSeconds(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
		}
	}

	public static long getDisplayOrder() {
		return new Date().getTime() - DISPLAY_ORDER_OFFSET;
	}

	public static long getTopDisplayOrder(long displayOrder,
			String orderDirection, boolean topFlag) {
		if (orderDirection == null || orderDirection.trim().equals("asc")) {
			if (topFlag)
				return displayOrder - DISPLAY_ORDER_TOP;
			else
				return displayOrder + DISPLAY_ORDER_TOP;
		} else {
			if (topFlag)
				return displayOrder + DISPLAY_ORDER_TOP;
			else
				return displayOrder - DISPLAY_ORDER_TOP;
		}
	}

	public static boolean isDisplayOrderTop(long displayOrder,
			String orderDirection) {
		if (orderDirection == null || orderDirection.trim().equals("asc")) {
			if (displayOrder < 0)
				return true;
			else
				return false;
		} else {
			if (displayOrder > DISPLAY_ORDER_TOP)
				return true;
			else
				return false;
		}
	}

	public static boolean isIdInList(Integer id, List<Integer> idList) {
		if (idList == null || idList.size() == 0)
			return false;
		for (Integer tmpId : idList) {
			if (tmpId != null && tmpId.equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static void addId2List(Integer id, List<Integer> idList) {
		if (idList == null)
			idList = new ArrayList<Integer>();
		if (!ToolUtil.isIdInList(id, idList))
			idList.add(id);
	}

	public static boolean isIdInArray(Integer id, Integer[] ids) {
		if (ids == null || ids.length == 0)
			return false;
		for (Integer tmpId : ids) {
			if (tmpId != null && tmpId.equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isCharUpper(char ch) {
		if (ch >= 'A' && ch <= 'Z')
			return true;
		return false;
	}

	public static char char2Lower(char ch) {
		if (ch < 'A' && ch > 'Z')
			return ch;
		return (char) (ch + 32);
	}

	public static String concatPath(String path1, String path2) {
		if (path1 == null)
			return path2;
		if (path2 == null)
			return path1;
		path1 = path1.replace('\\', '/');
		path2 = path2.replace('\\', '/');
		while (path1.endsWith("/"))
			path1 = path1.substring(0, path1.length() - 1);
		while (path2.startsWith("/"))
			path2 = path2.substring(1);
		String path = "";
		if (path1.equals("/"))
			path = path1 + path2;
		else
			path = path1 + "/" + path2;
		path = path.replace('\\', '/');
		return path;
	}

	public static String path2Url(String path) {
		if (path == null)
			return null;
		return path.replace('\\', '/');
	}

	public static String getRandomNumber(int digitNum) {
		String value = "";
		double v = java.lang.Math.random();
		for (int i = 0; i < digitNum; i++)
			v *= 10;
		value = new Integer((int) v).toString();
		while (value.length() < digitNum) {
			value += "0";
		}
		value = value.substring(0, digitNum);
		while (value.length() < digitNum) {
			value = "0" + value;
		}

		return value;
	}

	// 位数不足时左对齐，右补空格


	public static String int2String(int value, int length) {
		String str = new Integer(value).toString();
		if (str.length() > length)
			return str.substring(0, length);
		while (str.length() < length) {
			str += " ";
		}
		return str;
	}

	// 位数不足时左对齐，右补空格


	public static String long2String(long value, int length) {
		String str = new Long(value).toString();
		if (str.length() > length)
			return str.substring(0, length);
		while (str.length() < length) {
			str += " ";
		}
		return str;
	}

	// 位数不足时左对齐，右补空格


	public static String string2String(String str, int length) {
		if (str.length() > length)
			return str.substring(0, length);
		while (str.length() < length) {
			str += " ";
		}
		return str;
	}

	public static int string2Int(String str, int begin, int length) {
		if (str == null || str.length() <= begin
				|| str.length() < begin + length)
			return 0;
		String newstr = str.substring(begin, begin + length);
		newstr = newstr.trim();
		if (newstr.equals(""))
			return 0;
		return Integer.parseInt(newstr);
	}

	public static long string2Long(String str, int begin, int length) {
		if (str == null || str.length() <= begin
				|| str.length() < begin + length)
			return 0;
		String newstr = str.substring(begin, begin + length);
		newstr = newstr.trim();
		if (newstr.equals(""))
			return 0;
		return Long.parseLong(newstr);
	}

	public static String string2String(String str, int begin, int length) {
		if (str == null || str.length() <= begin
				|| str.length() < begin + length)
			return "";
		String newstr = str.substring(begin, begin + length);
		newstr = newstr.trim();
		return newstr;
	}

	public static Integer parseInt(String s, Integer defaultValue) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Integer parseInt(Map<String, String> paras, String paraName) {
		if (paras == null || paraName == null || paraName.trim().equals(""))
			return null;
		String paraValue = paras.get(paraName);
		return parseInt(paraValue, null);
	}

	public static Integer parseInt(Map<String, String> paras, String paraName,
			Integer defaultValue) {
		Integer value = parseInt(paras, paraName);
		if (value == null)
			value = defaultValue;
		return value;
	}

	public static String parseString(Map<String, String> paras, String paraName) {
		if (paras == null || paraName == null || paraName.trim().equals(""))
			return null;
		return paras.get(paraName);
	}

	public static String parseString(Map<String, String> paras,
			String paraName, String defaultValue) {
		String value = parseString(paras, paraName);
		if (value == null)
			value = defaultValue;
		return value;
	}

	public static List<Integer> parseIntegerList(String[] str) {
		List<Integer> list = new ArrayList<Integer>();
		if (str == null || str.length == 0)
			return list;
		for (int i = 0; i < str.length; i++) {
			String id = str[i];
			if (id == null || id.trim().equals(""))
				continue;
			list.add(new Integer(id));
		}
		return list;
	}

	public static List<Integer> parseIntegerList(String str, String separator) {
		List<Integer> list = new ArrayList<Integer>();
		if (str == null)
			return list;
		StringTokenizer st = new StringTokenizer(str, separator);
		while (st.hasMoreTokens()) {
			list.add(new Integer(st.nextToken()));
		}
		return list;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0)
			return "";
		Vector<String> v = new Vector<String>();
		StringTokenizer st = new StringTokenizer(ip, ",");
		while (st.hasMoreTokens()) {
			v.add((String) st.nextToken());
		}
		if (v.size() == 0)
			return "";
		// for (int i = 0; i < v.size(); i++)
		// System.out.println("Client IP[" + i + "]=" + v.get(i));
		String validIp = (String) v.get(0);
		validIp = validIp.trim();
		return validIp;
	}

	public static boolean isStringInList(String str, List<String> strList) {
		if (str == null || strList == null || strList.size() == 0)
			return false;
		for (String tmpStr : strList) {
			if (tmpStr != null && tmpStr.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将字符串解析为Map
	 * 
	 * @param str
	 *            : String
	 * @param elementDelimiter
	 *            : 元素分隔符

	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param keyValueDelimiter
	 *            : 键/值分隔符
	 * @return: map
	 */
	public static Map<String, String> parseMap(String str,
			String elementDelimiter, String keyValueDelimiter) {
		Map<String, String> map = new HashMap<String, String>();
		if (str == null || (str = str.trim()).length() <= 0
				|| elementDelimiter == null || keyValueDelimiter == null)
			return map;
		String[] elements = Pattern.compile(elementDelimiter, Pattern.LITERAL)
				.split(str);
		if (elements == null || elements.length <= 0)
			return map;
		for (String e : elements) {
			if (e == null) {
				continue;
			}
			String keyValue[] = Pattern.compile(keyValueDelimiter,
					Pattern.LITERAL).split(e, 2);
			if (keyValue.length != 2)
				continue;
			String key = keyValue[0];
			String value = keyValue[1];
			if (key == null || (key = key.trim()).length() <= 0)
				continue;
			if (value == null || value.trim().equals(""))
				value = "";
			map.put(key, value);
		}
		return map;
	}

	public static String map2String(Map map, String elementDelimiter,
			String keyValueDelimiter) {
		String str = elementDelimiter;
		if (map == null || map.size() == 0)
			return str;
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			str += entry.getKey();
			str += keyValueDelimiter;
			str += entry.getValue();
			str += elementDelimiter;
		}
		return str;
	}

	public static List map2List(Map map) {
		List list = new ArrayList();
		if (map == null || map.size() == 0)
			return list;
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			list.add(entry.getValue());
		}
		return list;
	}

	public static List<Map<String, Object>> map2MapList(Map map) {
		List<Map<String, Object>> list = new ArrayList();
		if (map == null || map.size() == 0)
			return list;
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Map<String, Object> tMap = new HashMap<String, Object>();
			tMap.put("key", entry.getKey());
			tMap.put("value", entry.getValue());
			list.add(tMap);
		}
		return list;
	}

	public static String addPara(String para, String paraName, String paraValue) {
		if (para == null || para.trim().equals(""))
			para = "";
		Map<String, String> paraMap = ToolUtil.parseMap(para, "\\,", "\\=");
		if (paraName == null || paraName.trim().equals("") || paraValue == null)
			return para;
		paraMap.put(paraName, paraValue);
		return ToolUtil.map2String(paraMap, ",", "=");
	}

	public static String addPara(String para, String paraName, Integer paraValue) {
		if (para == null || para.trim().equals(""))
			para = "";
		if (paraName == null || paraName.trim().equals("") || paraValue == null)
			return para;
		Map<String, String> paraMap = ToolUtil.parseMap(para, ",", "=");
		paraMap.put(paraName, paraValue.toString());
		String newPara = ToolUtil.map2String(paraMap, ",", "=");
		return newPara;
	}

	public static void setCookieValue(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			Integer minutes, String path, String domain) {
		if (name == null || name.trim().equals(""))
			return;
		if (value == null)
			value = "";
		name = escape(name);
		value = escape(value);
		Cookie cookie = getCookie(request, name);
		if (cookie == null) {
			cookie = new Cookie(name, value);
		} else {
			cookie.setValue(value);
		}
		if (minutes == null)
			minutes = 600;
		else if (minutes > 0)
			minutes *= 60;
		cookie.setMaxAge(minutes);
		if (path == null || path.trim().equals(""))
			path = "/";
		cookie.setPath(path);
		if (domain == null || domain.trim().equals(""))
			domain = "";
		if (!domain.equals("")) {
			if (!domain.startsWith("."))
				domain = "." + domain;
			cookie.setDomain(domain);
		}
		response.addCookie(cookie);
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		if (name == null || name.trim().equals(""))
			return null;
		name = escape(name);
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie == null)
				continue;
			String tmpName = cookie.getName();
			if (tmpName == null)
				continue;
			if (tmpName.equals(name)) {
				return cookie;
			}
		}
		return null;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie == null)
			return null;
		return unescape(cookie.getValue());
	}

	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null)
			return;
		if (name == null || name.trim().equals(""))
			return;
		name = escape(name);
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if (c == null)
				continue;
			if (c.getName().equals(name)) {
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
	}

	public static String toDate(String dateStr) {
		if (dateStr == null || dateStr.equals(""))
			return "''";
		return "to_date('" + dateStr + "', 'yyyy-mm-dd hh24:mi:ss')";

	}

	public static String getCurSystemDate() {
		return date2String(new Date(), "yyyyMMddHHmmss");
	}
	
	public static Long getlongDate(String date) {
		Format f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Long start = null;
        try {
			Date d = (Date) f.parseObject(date);
			start=d.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return start;
	}

	public static String getCurSystemDate(String format) {
		return date2String(new Date(), format);
	}

	public static Date formatDate(Date date, String format) {
		if (date == null)
			return null;
		if (format == null || format.trim().equals(""))
			format = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (format.indexOf("yyyy") < 0)
			cal.clear(Calendar.YEAR);
		if (format.indexOf("MM") < 0)
			cal.clear(Calendar.MONTH);
		if (format.indexOf("dd") < 0)
			cal.clear(Calendar.DAY_OF_MONTH);
		if (format.indexOf("HH") < 0)
			cal.clear(Calendar.HOUR_OF_DAY);
		if (format.indexOf("mm") < 0)
			cal.clear(Calendar.MINUTE);
		if (format.indexOf("ss") < 0)
			cal.clear(Calendar.SECOND);
		if (format.indexOf("SS") < 0)
			cal.clear(Calendar.MILLISECOND);
		date = cal.getTime();
		return date;
	}

	public static Date string2Date(String str, String format) {
		if (str == null || str.trim().equals(""))
			return null;
		if (format == null || format.trim().equals(""))
			format = "yyyy-MM-dd HH:mm:ss";
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(str);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	public static String date2String(Date date, String format) {
		Date newDate = formatDate(date, format);
		if (newDate == null)
			return "";
		return new SimpleDateFormat(format).format(newDate);
	}

	public static Date getDateByDayMin(Date date) {
		if (date == null)
			return date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();
		return date;
	}

	public static Date getDateByDayMax(Date date) {
		if (date == null)
			return date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		date = cal.getTime();
		return date;
	}

	public static String escapeJsString(String s, char quoteChar) {
		if (s == null)
			return s;
		StringBuffer sb = new StringBuffer();
		sb.append(quoteChar);
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			String replace;
			switch (ch) {
			case '"':
				replace = "\\\"";
				break;
			case '\'':
				replace = "\\'";
				break;
			case '\n':
				replace = "\\n";
				break;
			case '\r':
				replace = "\\r";
				break;
			case '\\':
				replace = "\\\\";
				break;
			case '<': // ..."+"<"+"...
				replace = quoteChar + "+" + quoteChar + "<" + quoteChar + "+"
						+ quoteChar;
				break;
			case '>': // ..."+">"+"...
				replace = quoteChar + "+" + quoteChar + ">" + quoteChar + "+"
						+ quoteChar;
				break;
			default:
				sb.append(ch);
				continue;
			}
			sb.append(replace);
		}
		sb.append(quoteChar);
		return sb.toString();
	}

	public static String replaceAWithB(String str, String A, String B) {
		StringBuffer res = new StringBuffer();
		int p = 0;
		int q = 0;
		while ((q = str.indexOf(A, p)) > -1) {
			res.append(str.substring(p, q));
			if (B != null)
				res.append(B);
			p = q + A.length();
		}
		res.append(str.substring(p));
		return res.toString();
	}

	// 删除一个串，串在源串中是以separate分隔
	public static String deleteString(String str, String delStr, char separate) {
		String rtStr = str;
		if (rtStr.startsWith(delStr)) {
			if (rtStr.length() == delStr.length())
				rtStr = "";
			else if (rtStr.charAt(delStr.length()) == separate)
				rtStr = rtStr.substring(delStr.length() + 1);
		} else {
			delStr = (separate + delStr);
			int index = -1;
			while ((index = rtStr.indexOf(delStr)) >= 0) {
				if (rtStr.length() == index + delStr.length())
					rtStr = rtStr.substring(0, index);
				else if (rtStr.charAt(index + delStr.length()) == separate)
					rtStr = rtStr.substring(0, index)
							+ rtStr.substring(index + delStr.length());
				else
					rtStr = rtStr.substring(index + delStr.length());
			}
		}
		return rtStr;
	}

	/**
	 * 计算带中文的字符串的长度
	 * 
	 * @param str
	 *            ：源串

	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return：字串长度

	 */
	public static int strlen(String str) {
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 255)
				len += 2;
			else
				len++;
		}
		return len;
	}

	/**
	 * 从含中文的字符串里面取一个字串，如"www你好www"
	 * 
	 * @param str
	 *            ：源串

	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param start
	 *            ：此位置开始

	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param end
	 *            ：此位置之前
	 * @return：成功返回字串，失败返回空

	 */
	public static String substring(String str, int start, int end) {
		if (str == null)
			return null;
		if (str.equals("") || start > end)
			return "";
		int len = 0;
		int start1 = 0;
		int start2 = 0;
		for (int i = 0; i < str.length(); i++) {
			if (len == start) {
				start1 = start;
				start2 = i;
				break;
			} else if (len > start) {
				start1 = start - 1;
				start2 = i - 1;
				break;
			}
			if (str.charAt(i) > 255)
				len += 2;
			else
				len++;
		}

		StringBuffer newStr = new StringBuffer();
		len = start1;
		for (int i = start2; i < str.length(); i++) {
			if (str.charAt(i) > 255) {
				len += 2;
			} else
				len++;
			if (len <= end)
				newStr.append(str.charAt(i));
		}
		return newStr.toString();
	}

	/**
	 * 从串右面开始截取N位，位数不足左补0
	 * 
	 * @param str
	 *            ：源串

	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param count
	 *            ：截取的位数
	 * @return：截取的结果串

	 */
	public static String rFetch(String str, int count) {
		String tmp = str;
		while (tmp.length() < count)
			tmp = "0" + tmp;
		tmp = tmp.substring(tmp.length() - count);
		return tmp;
	}

	public static String getLastTwoOfString(String str) {
		if (str == null || str.equals(""))
			return "00";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				return "00";
		}
		if (str.length() < 2)
			return "0" + str;
		return str.substring(str.length() - 2);
	}

	public static String escape(String src) {
		// int i;
		// char j;
		// StringBuffer tmp = new StringBuffer();
		// tmp.ensureCapacity(src.length() * 6);
		//
		// for (i = 0; i < src.length(); i++) {
		// j = src.charAt(i);
		// if (Character.isDigit(j) || Character.isLowerCase(j)
		// || Character.isUpperCase(j))
		// tmp.append(j);
		// else if (j < 256) {
		// tmp.append("%");
		// if (j < 16)
		// tmp.append("0");
		// tmp.append(Integer.toString(j, 16));
		// } else {
		// tmp.append("%u");
		// tmp.append(Integer.toString(j, 16));
		// }
		// }
		// return tmp.toString();
		try {
			return URLEncoder.encode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static String unescape(String src) {
		// StringBuffer tmp = new StringBuffer();
		// tmp.ensureCapacity(src.length());
		// int lastPos = 0, pos = 0;
		// char ch;
		// while (lastPos < src.length()) {
		// pos = src.indexOf("%", lastPos);
		// if (pos == lastPos) {
		// if (src.charAt(pos + 1) == 'u') {
		// ch = (char) Integer.parseInt(
		// src.substring(pos + 2, pos + 6), 16);
		// tmp.append(ch);
		// lastPos = pos + 6;
		// } else {
		// ch = (char) Integer.parseInt(
		// src.substring(pos + 1, pos + 3), 16);
		// tmp.append(ch);
		// lastPos = pos + 3;
		// }
		// } else {
		// if (pos == -1) {
		// tmp.append(src.substring(lastPos));
		// lastPos = src.length();
		// } else {
		// tmp.append(src.substring(lastPos, pos));
		// lastPos = pos;
		// }
		// }
		// }
		// return tmp.toString();
		try {
			return URLDecoder.decode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static void main(String[] args) {
		// String para = "";
		// System.out.println("=====para=" + para);
		// para = Util.addPara(para, "column_id", 101);
		// para = Util.addPara(para, "content_id", 201);
		// String tmp = "~!@#$%^&*()_+|\\=-,./?><;'][{}\"";
		/*
		 * String tmp = "a中国人a"; System.out.println("testing escape : " + tmp);
		 * tmp = escape(tmp); System.out.println(tmp);
		 * System.out.println("testing unescape :" + tmp);
		 * System.out.println(unescape(tmp));
		 * System.out.println(substring("1我2我3我我456", 1, 2));
		 * System.out.println(substring("1我2我3我我456", 1, 3));
		 * System.out.println(substring("1我2我3我我456", 2, 3));
		 * System.out.println(substring("1我2我3我我456", 2, 4)); String var =
		 * "1我2我3我我4561我2我3我我4561我2我3我我4561我2我3我我456";
		 * System.out.println(substring(var, 18, strlen(var)));
		 * System.out.println(substring(var, 19, strlen(var)));
		 * System.out.println(substring(var, 20, strlen(var))); var =
		 * "我我4561我2我3我我456"; System.out.println(substring(var, 18,
		 * strlen(var)));
		 */
		// System.out.println("getRandomNumber()=" + getRandomNumber(4));
		// System.out.println("getCurSystemDate()=" + getCurSystemDate());
		// String str = "hello!！ 全角转换，ＤＡＯ ０５３２８８６３１６５３";
		// System.out.println(getCurrentMonday());
		// System.out.println(getCurrentWeekend());
	}

	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获取本周周一的日期


	public static Date getCurrentMonday() {
		// int weeks = 0;
		// int mondayPlus = getMondayPlus();
		// System.out.println(mondayPlus);
		// GregorianCalendar currentDate = new GregorianCalendar();
		// currentDate.add(GregorianCalendar.DATE, mondayPlus);
		// Date monday = currentDate.getTime();
		// System.out.println(monday);
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		// return preMonday;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_WEEK, 2);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	// 获取本周周日的日期


	public static Date getCurrentWeekend() {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, getMondayPlus() + 6);
		Date date = currentDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		date = cal.getTime();
		return date;
	}
	
	
	//获取上传图片的自定义名称png
	public static String getUploadImageName(String modelName){
		String imgDate = getCurSystemDate("yyyyMMddhhmmss");
		String imageName = modelName+"_"+imgDate+".png";
		return imageName;
	}
	
	//获取上传图片的自定义名称jpg

	public static String getUploadImageNamejpg(String modelName){
		String imgDate = getCurSystemDate("yyyyMMddhhmmss"+getSixNumber());
		String imageName = modelName+"_"+imgDate+".jpg";
		return imageName;
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

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }


    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    //base64字符串转化成图片
    public static boolean generateImage(String imgStr,String url)
    {  
    	/*
    	//对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = url;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }*/
    	return true;
    }
    
    public static String getValue(String fileNamePath, String key)throws IOException {  
        Properties props = new Properties();  
        InputStream in = null;  
        try {  
//            in = new FileInputStream(fileNamePath);  
            // 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中  
            in = ToolUtil.class.getResourceAsStream(fileNamePath);  
            props.load(in);  
            String value = props.getProperty(key);  
            // 有乱码时要进行重新编码  
            // new String(props.getProperty("name").getBytes("ISO-8859-1"), "GBK");  
            return value;  
  
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;  
  
        } finally {  
            if (null != in)  
                in.close();  
        }  
    }  
    
    
    public static String baidu(String url,Map<String, String> paramMap){
		   String apiUrl = url;
	       String appKey = "9s15sG9kyI1sOw2KOnqAoKFz";    //请替换为自己的 App Key 和 App secret 
	       StringBuilder stringBuilder = new StringBuilder();
	       
	       // 对参数名进行字典排序
	       String[] keyArray = paramMap.keySet().toArray(new String[0]);
	       Arrays.sort(keyArray);
	       // 拼接有序的参数名-值串
	       stringBuilder.append(appKey);
	       for (String key : keyArray)
	       {
	           stringBuilder.append(key).append(paramMap.get(key));
	       }
	       // 添加签名
	       stringBuilder = new StringBuilder(); 
	       stringBuilder.append("ak=").append(appKey);
	       for (Entry<String, String> entry : paramMap.entrySet())
	       {
	           stringBuilder.append('&').append(entry.getKey()).append('=').append(entry.getValue());
	       }
	       String queryString = stringBuilder.toString();

	       StringBuffer response = new StringBuffer();
	       HttpClientParams httpConnectionParams = new HttpClientParams();
	       httpConnectionParams.setConnectionManagerTimeout(1000);
	       HttpClient client = new HttpClient(httpConnectionParams);
	       HttpMethod method = new GetMethod(apiUrl);

	       BufferedReader reader = null;
	       try {
				
			
	       String encodeQuery = URIUtil.encodeQuery(queryString, "UTF-8"); // UTF-8 请求
	       method.setQueryString(encodeQuery);
	       client.executeMethod(method);
	       reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
	       String line = null;
	       while ((line = reader.readLine()) != null)
	       {
	           response.append(line).append(System.getProperty("line.separator"));
	       }
	       reader.close();
	       method.releaseConnection();
	       } catch (Exception e) {
				// TODO: handle exception
			}
	       System.out.println(response.toString()); 
	       return response.toString();
	   }
    
    public static String getaddress(String latitude1,String longitude1){
    	 BigDecimal b11 = new BigDecimal(latitude1);
	     BigDecimal b22 = new BigDecimal("0.008547");
	     BigDecimal latitude = b11.add(b22);
	     
	     BigDecimal a11 = new BigDecimal(longitude1);
	     BigDecimal a22 = new BigDecimal("0.012504");
	     BigDecimal longitude = a11.add(a22);
	     String location=latitude+","+longitude;
	     System.out.println("你的经纬度："+latitude+"----"+longitude);
    	Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("location", location);
        paramMap.put("pois", "1");
        paramMap.put("output", "json");
  		String suibian=ToolUtil.baidu("http://api.map.baidu.com/geocoder/v2/",paramMap);
  		System.out.println("======"+suibian);
  		JSONObject jsonObject = JSONObject.fromObject(suibian);
  		JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.getString("result"));
  		String address=jsonObject1.getString("formatted_address");
      	return address;
    }
    
    /**
     * 根据类型判断文件扩展名
     *
     * @param contentType 内容类型
     * @return String
     */
    public static String getFileExt(String contentType) {
        String fileExt="";
        if ("image/jpeg".equals(contentType)) {
            fileExt=".jpg";
        }else if ("audio/mpeg".equals(contentType)) {
            fileExt=".mp3";
        }else if ("audio/amr".equals(contentType)) {
            fileExt=".amr";
        }else if ("video/mp4".equals(contentType)) {
            fileExt=".mp4";
        }else if ("video/mpeg4".equals(contentType)) {
            fileExt=".mp4";
        }
        return fileExt;
    }
  
}
