package com.ian.media.util.excel;

public class StringUtil {

	/**
	 * 功能: 按照指定字节长度截取字符串并选择在后面是否加...
	 * 
	 * @param str
	 *            需要截取的字符串
	 * @param offset
	 *            需要截取的字节长度，不包括追加的…
	 * @param addEllipsis
	 *            是否添加省略号…  true 是  false 否
	 * @return String 截取后的字符串
	 */
	public static String getSubStr(String str,int offset,boolean addEllipsis) {
		if (str == null)
			return "";
		int realLength = getStrRealLength(str);
		if (realLength <= offset) {
			return str;
		}
		int length = 0;
		StringBuilder sb = new StringBuilder();
		char[] chars = str.toCharArray();
		int ii = 0;
		while (length < offset) {
			char ch = chars[ii++];
			if (ch <= 127) {
				length++;
			} else {
				length += 2;
			}
			sb.append(ch);
		}
		if(addEllipsis==true){
			sb.append("…");
		}
		return sb.toString();
	}

	/**
	 * 获取字符串的实际长度（按字节长度，如一个中文长度为2）
	 * 
	 * @param str
	 *            待返回长度的字符串
	 * @return 字符串的字节长度
	 */
	public static int getStrRealLength(String str) {

		if (str == null)
			return 0;

		int length = 0;

		char[] chars = str.toCharArray();

		for (char ch : chars) {

			if (ch <= 127) {
				length++;

			} else {
				length += 2;
			}
		}
		return length;
	}
	
	public static void main(String[] args) {
		System.out.println(getSubStr("abcde中国人efgh",9,true));
	}
}
