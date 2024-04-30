package com.ian.media.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileUtil {
	 private static String path = "D:/"; 
	 private static String filenameTemp; 
	/**
	 * 根据绝对路径获取文件名称
	 * **/
	public String getFileName(String pathAndName){
		String tempPath = pathAndName.trim();  
		String fileName = tempPath.substring(tempPath.lastIndexOf("/")+1);  
		return fileName;
	}
	
	/**
	 * 根据绝对路径获取除文件名意外的路径
	 * */
	public String getPath(String pathAndName){
		String path = new String();
		if(StringUtil.isNotNull(pathAndName)){
			String tempPath = pathAndName.trim();  
			path = tempPath.substring(0,tempPath.lastIndexOf("\\")+1); 
			/*
			if("".equals(path)){
				path = "\\";
			}else{
				path = path.replaceAll("/", "//");
			}
			*/
			
		}

		return path;
	}
	/**
	 * 根据路径创建文件夹
	 * **/
	public void createDirectory(String path) {
		StringTokenizer st=new   StringTokenizer(path,"\\");  
		String tempPath =st.nextToken()+"\\";  
		while(st.hasMoreTokens())  
		{  
			tempPath = tempPath + st.nextToken()+"\\";  
			File inbox = new File(tempPath);  
			if(!inbox.exists())  
				inbox.mkdir();  
		}
	}

	/**
	 * 删除文件夹及文件夹中的所有文件
	 * @param filePath 要删除的文件夹绝对路径 如：f:\\logs,将删除logs文件夹 及logs文件夹中的所有文件和子文件夹
	 */
	public void deleteFolder(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			return;
		}
		if(file.isFile()) {
			this.deleteFile(file.getAbsolutePath());
		} else {
			File[] fileList = file.listFiles();
			for(File f : fileList) {
				String path = f.getAbsolutePath();
				if(f.isFile()) {
					this.deleteFile(path);
				} else if(f.isDirectory()) {
					deleteFolder(path);
				}
			}
		}
		this.deleteFile(filePath);
	}
	
	/**  
	 * 删除单个文件  
	 * @param   fileName    被删除文件的文件名  
	 * @return 单个文件删除成功返回true,否则返回false  
	 */  
	public boolean deleteFile(String fileName){   
		File file = new File(fileName);   
		if(file.exists()){   
			return file.delete();   
		}else{   
			return true;   
		}   
	} 

	/**
	 * 复制文件
	 * @param basicUrl 需要备份的文件根目录  如：f://ftpserverfiles 如果是磁盘则写：f:
	 * @param destUrl  备份文件的根目录 如：f://abs//sdf
	 */
	public void coryFiles(String basicUrl, String destUrl) throws Exception {
		File file = new File(basicUrl);
		this.copyFiles(file, basicUrl, destUrl);
	}

	/**
	 * 
	 * @param file  根文件
	 * @param basicPath 需要备份文件目录
	 * @param destPath 备份文件目录
	 */
	public void copyFiles(File file, String basicPath, String destPath) throws Exception {

		File[] fileList = file.listFiles();
		for(File f : fileList) {
			String absolutePath = f.getAbsolutePath();  //当前文件全路径
//			String filePath = destPath+absolutePath.replace("\\", "//").substring(basicPath.length()); //得到备份文件全路径
			String filePath = destPath+absolutePath.substring(basicPath.length()); //得到备份文件全路径
			if(f.isHidden()) {
				continue;
			} else if(f.isDirectory()) {
				this.createFile(filePath);
				File newFile = new File(absolutePath);
				this.copyFiles(newFile, absolutePath, filePath);
			} else if(f.isFile()) {
				this.writeFile(absolutePath, filePath);
			}
		}
	}

	/**
	 * 复制文件
	 * @param basicUrl 需要备份的文件根目录  如：f://ftpserverfiles 如果是磁盘则写：f:
	 * @param destUrl  备份文件的根目录 如：f://abs//sdf
	 * @throws Exception 
	 */
	public void copyAndbackupFile(String basicUrl, String destUrl) throws Exception {
		File file = new File(basicUrl);
		this.copyAndbackupFile(file, basicUrl, destUrl);
	}

	/**
	 * 
	 * @param file  根文件
	 * @param basicPath 需要备份文件目录
	 * @param destPath 备份文件目录
	 * @throws Exception 
	 */
	public void copyAndbackupFile(File file, String basicPath, String destPath) throws Exception {

		File[] fileList = file.listFiles();
		for(File f : fileList) {
			String absolutePath = f.getAbsolutePath();  //当前文件全路径
			String filePath = destPath+absolutePath.substring(basicPath.length()); //得到备份文件全路径
			if(f.isHidden()) {
				continue;
			} else if(f.isDirectory()) {
				this.createFile(filePath);
				File newFile = new File(absolutePath);
				this.copyAndbackupFile(newFile, absolutePath, filePath);
			} else if(f.isFile()) {
				String bakFile = destPath+"\\"+f.getName();
				if(new File(bakFile).exists()){
					this.writeFile(bakFile, bakFile+".hondabak");
				}
				this.writeFile(absolutePath, filePath);
			}
		}
	}

	/**
	 * 复制文件
	 * @param basicPath 文件源路径： e://basic//copy.txt
	 * @param filePath 文件存放全路径： d://dest//copy.txt
	 * @throws Exception 
	 */
	public void writeFile(String basicPath, String filePath) throws Exception {
//		File basicfile = new File(basicPath);
		FileInputStream in = null;
		FileOutputStream out = null;
		this.createFile(filePath.substring(0, filePath.lastIndexOf("\\")));
		try {
			in = new FileInputStream(basicPath);
			out = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
//			throw new Exception("文件找不到！！！");
		}
		
		byte[] bt = new byte[1024*1024*10];
		int len = 0;
		try {
			while((len = in.read(bt)) > 0) {
				out.write(bt, 0, len);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
//			throw new Exception("文件复制失败！！！");
		} finally{
			try {
                if(null!=in)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
//				throw new Exception("关闭输入流失败");
			}
			try {
                if(null!=out)
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
//				throw new Exception("关闭输出流失败！！！");
			}
		}

	}

	/**
	 * 创建文件夹
	 * @param url
	 */
	public void createFile(String url) {
		File file = new File(url);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	/**
	 * 将文本内容写入相关文件
	 * @throws IOException 
	 * **/
	public void writeStringToFile(String content, String path) throws IOException{
		//createDirectory(this.getPath(path));
		File file = new File(path);
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content);
		writer.close();
	}
	/**
	 * 删除path文件夹下面以fileSuffix为后缀的文件
	 * **/
	public void deleteDiretoryFileBySuffix(String path){
		File file = new File(path);
		File[] files = file.listFiles();
		int bakIndex = 0;
		for(File f:files){
			if(f.isDirectory()){
				deleteDiretoryFileBySuffix(f.getAbsolutePath());
			}else if(f.isFile()){
				bakIndex = f.getName().lastIndexOf(".hondabak");
				if(bakIndex>0){
					f.delete();
				}
			}
		}
	}
	/**
	 * 恢复文件
	 * @throws Exception 
	 * **/
	public void recoverFile(String path){
		File file = new File(path);
		File[] files = file.listFiles();
		int bakIndex = 0;
		for(File f:files){
			if(f.isDirectory()){
				recoverFile(f.getAbsolutePath());
			}else if(f.isFile()){
				bakIndex = f.getName().lastIndexOf(".hondabak");
				if(bakIndex>0){
					try {
						writeFile(path+"\\"+f.getName(), path+"\\"+f.getName().substring(0,bakIndex));
					} catch (Exception e) {
						e.printStackTrace();
					}
					f.delete();
				}
			}
		}
	}
	
	public void createTxt(String value) throws IOException
	{
		
		filenameTemp = path + "errorLog" + ".txt"; 
   	 File filename = new File(filenameTemp); 
   	 if (!filename.exists()) { 
   	
			filename.createNewFile();
			writeStringToFile(value,filenameTemp);
	  }else {
		  writeStringToFile(value,filenameTemp);
	}
 	
	}
}
