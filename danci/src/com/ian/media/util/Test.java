package com.ian.media.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class Test {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*String text = "http://www.ablanxue.com";
		int width = 300;
		int height = 300;
		// 二维码的图片格式
		String format = "gif";
		Hashtable hints = new Hashtable();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
				BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		File outputFile = new File("d:" + File.separator + "new.gif");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
		PrintService defaultService = PrintServiceLookup
				.lookupDefaultPrintService();
		DocPrintJob job = defaultService.createPrintJob(); // 创建打印作业
		Object fis = new FileInputStream(outputFile); // 构造待打印的文件流
		DocAttributeSet das = new HashDocAttributeSet();
		Doc doc = new SimpleDoc(fis, flavor, das);
		job.print(doc, pras);*/
		
		
	      
	    String result="";
	    System.out.println("请输入字符串");
	    
	    BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
	    String str=strin.readLine().toString();
	    System.out.println("请输入替换字符串");
	    BufferedReader strina=new BufferedReader(new InputStreamReader(System.in));
	    String stra=strina.readLine().toString();
	    result=str.replace(" ", stra);
		System.out.println(result);
		
		
		
		
	}
}