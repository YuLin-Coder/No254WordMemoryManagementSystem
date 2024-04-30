package com.ian.media.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ian.media.model.FileClass;


public class Backup{
	 static int countFiles = 0;// 声明统计文件个数的变量
	    static int countFolders = 0;// 声明统计文件夹的变量
	 
	    public static File[] searchFile(File folder, final String keyWord) {// 递归查找包含关键字的文件
	 
	        File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
	            @Override
	            public boolean accept(File pathname) {// 实现FileFilter类的accept方法
	                if (pathname.isFile())// 如果是文件
	                    countFiles++;
	                else
	                    // 如果是目录
	                    countFolders++;
	                if (pathname.isDirectory()
	                        || (pathname.isFile() && pathname.getName().toLowerCase().contains(keyWord.toLowerCase())))// 目录或文件包含关键字
	                    return true;
	                return false;
	            }
	        });
	 
	        List<File> result = new ArrayList<File>();// 声明一个集合
	        for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
	            if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
	                result.add(subFolders[i]);
	            } else {/*// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
	                File[] foldResult = searchFile(subFolders[i], keyWord);
	                for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
	                    result.add(foldResult[j]);// 文件保存到集合中
	                }
	            */}
	        }
	 
	        File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
	        result.toArray(files);// 集合数组化
	        return files;
	    }

	    public static Map<Object, Object> listBackupSql(){// java程序的主入口处
	    	Map<Object, Object> map = new HashMap<Object, Object>();
	    	List<FileClass> list = new ArrayList<FileClass>();
	        File folder = new File("D:/apache-tomcat-6.0.45/webapps/schools/upload/");// 默认目录
	        String keyword = ".sql";
	        if (!folder.exists()) {// 如果文件夹不存在
	            System.out.println("目录不存在：" + folder.getAbsolutePath());
	            return null;
	        }
	        File[] result = searchFile(folder, keyword);// 调用方法获得文件数组
	        System.out.println("在 " + folder + " 以及所有子文件时查找对象" + keyword);
	        System.out.println("查找了" + countFiles + " 个文件，" + countFolders + " 个文件夹，共找到 " + result.length + " 个符合条件的文件：");
	        for (int i = 0; i < result.length; i++) {// 循环显示文件
	            File file = result[i];
	            FileClass fiels = new FileClass();
	            fiels.setName(file.getName());
	            fiels.setPath(file.getPath());
	            list.add(fiels);
	           // System.out.println(file.getAbsolutePath() + " ");// 显示文件绝对路径
	        }
	        map.put("list", list);
	    	map.put("success",true);
			map.put("mesage", "查询成功");
	        map.put("result", result.length);
			return map;
	    }
	
	public static boolean setBackupSql(){
		  String user = "root"; // 数据库帐号
		  String password = "mysql"; // 登陆密码
		  String database = "schools"; // 需要备份的数据库名
		  String filepath = "D:/apache-tomcat-6.0.45/webapps/schools/upload/"; // 备份的路径地址
		 /* String realPath = request.getSession().getServletContext().getRealPath("/upload");
		 */ String data =ToolUtil.getCurSystemDate("yyyyMMddHHmmss");
		  String originFileName = filepath+data+".sql";
		  String   stmt1   =   "D:\\Program Files (x86)\\Ampps\\mysql\\bin\\mysqldump   "   +   database   +   "   -u   "   +   user   +   "   -p" 
		    +   password   +   "   --result-file="   +  originFileName; 
		  /*
		   * String mysql="mysqldump test -u root -proot
		   * --result-file=d:\\test.sql";
		   */
		  try {
		   Runtime.getRuntime().exec(stmt1);
		   System.out.println("数据已导出到文件" + filepath + "中");
		   System.out.println("数据已导出到文件" + originFileName + "中");
		  }
		  catch (IOException e) {
		   e.printStackTrace();
		  }
		 
		return true;
	}
	  public static boolean deleteFile(String sPath) {
			Boolean flag = false;
			File file = new File(sPath);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
				flag = true;
			}
			return flag;
		}
}
