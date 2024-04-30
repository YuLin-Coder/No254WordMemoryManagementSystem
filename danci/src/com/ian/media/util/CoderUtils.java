package com.ian.media.util;

import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.PrintWriter;  
  
/** 
 * @date 2009-3-11 
 * @author Xing,Xiudong 
 * @Email:xingxiuodng[at]gmail.com 
 * @index:http://blog.csdn.net/xxd851116 
 */  
public class CoderUtils {  
  
    public static char ascii2Char(int ASCII) {  
        return (char) ASCII;  
    }  
  
    public static int char2ASCII(char c) {  
        return (int) c;  
    }  
  
    public static String ascii2String(int[] ASCIIs) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < ASCIIs.length; i++) {  
            sb.append((char) ascii2Char(ASCIIs[i]));  
        }  
        return sb.toString();  
    }  
  
    public static String ascii2String(String ASCIIs) {  
        String[] ASCIIss = ASCIIs.split(",");  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < ASCIIss.length; i++) {  
            sb.append((char) ascii2Char(Integer.parseInt(ASCIIss[i])));  
        }  
        return sb.toString();  
    }  
  
    public static int[] string2ASCII(String s) {// �ַ�ת��ΪASCII��  
        if (s == null || "".equals(s)) {  
            return null;  
        }  
  
        char[] chars = s.toCharArray();  
        int[] asciiArray = new int[chars.length];  
  
        for (int i = 0; i < chars.length; i++) {  
            asciiArray[i] = char2ASCII(chars[i]);  
        }  
        return asciiArray;  
    }  
  
    public static String getIntArrayString(int[] intArray) {  
        return getIntArrayString(intArray, ",");  
    }  
  
    public static String getIntArrayString(int[] intArray, String delimiter) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < intArray.length; i++) {  
            sb.append(intArray[i]).append(delimiter);  
        }  
        return sb.toString();  
    }  
  
    public static String getASCII(int begin, int end) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = begin; i < end; i++) {  
            sb.append(i).append(":").append((char) i).append("/t");  
            // sb.append((char) i).append("/t");  
            if (i % 10 == 0) {  
                sb.append("/n");  
            }  
        }  
        return sb.toString();  
    }  
  
    public static String getCHASCII(int begin, int end) {  
        return getASCII(19968, 40869);  
    }  
  
    public static void showASCII(int begin, int end) {  
        for (int i = begin; i < end; i++) {  
            // System.out.print(i + ":" + (char) i + "/t");  
            System.out.print((char) i + "/t");  
            if (i % 10 == 0) {  
                System.out.println();  
            }  
        }  
    }  
  
    public static void showCHASCII() {  
        showASCII(19968, 40869);  
    }  
  
    public static void showIntArray(int[] intArray) {  
        showIntArray(intArray, ",");  
    }  
  
    public static String showIntArray(int[] intArray, String delimiter) { 
    	String result="";
        for (int i = 0; i < intArray.length; i++) {  
            //System.out.print(intArray[i] + delimiter);  
            result+=intArray[i] + delimiter;
        } 
        return result;
    } 
  
    public static void createFile(String filePathAndName, String fileContent)  
            throws IOException {  
  
        String filePath = filePathAndName;  
        filePath = filePath.toString();  
        File myFilePath = new File(filePath);  
        if (!myFilePath.exists()) {  
            myFilePath.createNewFile();  
        }  
        FileWriter resultFile = new FileWriter(myFilePath);  
        PrintWriter myFile = new PrintWriter(resultFile);  
        String strContent = fileContent;  
        myFile.println(strContent);  
        myFile.close();  
        resultFile.close();  
    }  
  
    public static void main(String[] args) throws IOException {  
//    	String s = "好好学习！天天向上！————笑的自然 2009年3月11日";  
    	String s="-94";
        //String  str1=s.replaceAll(" ", ",");
        //System.out.println(ascii2String(str1));
    	//showIntArray(string2ASCII(s), " ");  
        System.out.println();  
        System.out.println(ascii2String(string2ASCII(s)));  
        System.out.println();
        createFile("c://console_ch.txt", getCHASCII(0, 50000));  
    }  
  
}  