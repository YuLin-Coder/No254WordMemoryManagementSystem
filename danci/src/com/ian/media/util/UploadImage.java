package com.ian.media.util;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadImage {

	
	@SuppressWarnings("unchecked")
	public  static String uploadImage(MultipartFile file,String realpath,String imageFileName)
	{
		File savefile = new File(new File(realpath), imageFileName);
	      if (!savefile.getParentFile().exists()){
	    	  savefile.getParentFile().mkdirs();
	      }
	      try {
	     	 //上传
	    	  	file.transferTo(savefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	public  static String uploadImage(String directImageUrl,String realpath,String imageFileName)
	{
		
		File uploadImage=new File(directImageUrl);
		File savefile = new File(new File(realpath), imageFileName);
	      if (!savefile.getParentFile().exists()){
	    	  savefile.getParentFile().mkdirs();
	      }
	      try {
	     	 //上传
				FileUtils.copyFile(uploadImage, savefile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}


    /**
     * 删除单个文件
     * @param   path    被删除的图片的途径
     * @param  imageName  图片名称
     * @return 单个文件删除成功返回true,否则返回false
     */
    public static boolean deleteFile(String path,String imageName){
        System.out.println(path+imageName);
        File file = new File(path+imageName);
        if(file.exists()){
            return file.delete();
        }else{
            return true;
        }
    }
}
