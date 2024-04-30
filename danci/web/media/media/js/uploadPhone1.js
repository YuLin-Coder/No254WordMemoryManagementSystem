	
	  function submitPhoto1(){
	    var f = $("#photoFile").val();  
	    if(f==null||f==""){  
	       alert("空文件不能上传");
	        return;  
	      }else{  
	       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
	       extname = extname.toLowerCase();//处理了大小写  
	       /*
	       if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){   
				alert("格式错误");
		         return ;  
	        }*/  
	      }  
	     var file = document.getElementById("photoFile").files;    
	     var size = file[0].size;  
	     if(size>20097152){  
	          alert("错误提示:文件太大，图片大小最多支持20M!</span>");   
	          return ;  
	      }  
	    ajaxFileUploadPhoto1();  
	}  
	
	function ajaxFileUploadPhoto1() {
		  $.ajaxFileUpload({  
		        url : getIP()+'link.action?uploadPhoto', //用于文件上传的服务器端请求地址  
		        secureuri : false, //一般设置为false  
		        fileElementId : 'photoFile', //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		        type : 'post',  
		        async : false,
		        dataType : 'json', //返回值类型 一般设置为json 
		        enctype:'multipart/form-data', 
		        success : function(data, status) //服务器成功响应处理函数  
		        {  
		       		alert(data.mesage);
		       		$("#imgurl1").val(data.path);
		       		 $('#thumbnailImage1').attr('src',getIP()+data.path);
		        },  
		        error : function(data, status, e)//服务器响应失败处理函数  
		        {  
		       		alert("失败");
		        }  
		    });  
	}
