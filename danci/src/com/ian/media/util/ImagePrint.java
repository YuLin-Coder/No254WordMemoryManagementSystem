package com.ian.media.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;
import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ImagePrint {
	private static final Integer WIDTH=10;
	private static final Integer HEIGHT=10;
	private  void print(File file) throws WriterException, IOException, PrintException {
		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
		PrintService defaultService = PrintServiceLookup
				.lookupDefaultPrintService();
		DocPrintJob job = defaultService.createPrintJob(); // 创建打印作业
		Object fis = new FileInputStream(file); // 构造待打印的文件流
		DocAttributeSet das = new HashDocAttributeSet();
		Doc doc = new SimpleDoc(fis, flavor, das);
		job.print(doc, pras);
	}
	/**二维码打印*/
	public Boolean printQRCode(String code,String filePath) {
		try {
			String format = "png";
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(code,
					BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			 File outputFile = new File(filePath);
			 MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			//MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			print(outputFile);
			return true;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PrintException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**二维码打印*/
	@Test
	public Boolean printQRCode() {
		try {
			String format = "png";
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode("",
					BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
			 File outputFile = new File("d:/new.gif");
     	 MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			//MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			print(outputFile);
			return true;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PrintException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**一维码打印*/
	public Boolean printDimensionalCode(String code,String filePath) {
	      try {
	    	  
    	    JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
			BufferedImage localBufferedImage = localJBarcode.createBarcode(code);
			localJBarcode.setEncoder(Code39Encoder.getInstance());
			localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
			localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
			localJBarcode.setShowCheckDigit(false);
		    FileOutputStream localFileOutputStream = new FileOutputStream(filePath);
		    ImageUtil.encodeAndWrite(localBufferedImage, "png", localFileOutputStream, WIDTH, HEIGHT);
		    localFileOutputStream.close();
		    print(new File(filePath));
		    return true;
		} catch (InvalidAtributeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (PrintException e) {
			e.printStackTrace();
		}
	      return false;
	}
}
