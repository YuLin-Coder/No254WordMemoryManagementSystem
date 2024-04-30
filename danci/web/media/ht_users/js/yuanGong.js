var sel=$.Params.create();
$(function(){
	
       getData();
    /**
     * 查询
     */
    $("#searchList").on("click",function(){
    	var username=$("#username").val();
    	if(username != ""){
    		sel.addParams("username",username);
    	}else{
    		sel.addParams("username",null);
    	}
    	   	sel.addParams("schoolid",1);
    	getData();
    })
    
   
    $("#delbutton").on("click",function(){
    	var params=getcheckdata();
    	if(params == ""){
    		alert("请选择记录");
    		return false;
    	}
      	if(confirm("确定要删除吗？")){
    		var del=$.Params.create();
        	del.addParams("id",params);
        	
        	del.ajax(getIP()+"yuangong.action?delMore",function(data){
        		alert(data.msg);
        		if(data.success){
        			$("#queryCheckbox").removeAttr("checked");
        			$("#queryCheckbox").parent().removeClass("checked");
        			getData();
        		}
        	})
    	}
    })
    
    function getData(){
		sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	sel.addParams("schoolid",1);
	   	getPageData(sel,getIP()+"yuangong.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.userid+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.username+"</td>" +
								"<td>"+val.password+"</td>" +
								"<td>"+val.department+"</td>" +
								"<td onclick='update(\""+val.userid+"\",\""+val.username+"\",\""+val.password+"\",\""+val.department+"\")'>修改</td></tr>";	})
				}else{
					html="<tr><td colspan='7'>暂无数据</td></tr>";
				}
				$("table tbody").html(html);
				App.init();
	   		}else{
	   			alert(data.msg);
	   		}
	   		
	   	});
	}					
    
    
 
   
    	/**
   	 * 添加按钮
   	 */
   	$("#addbutton").on("click",function(){
   		$("#passwords").val("");
   		$("#usernames").val("");
   		$("#departments").val("");
   		$("#responsive").modal('toggle');
   	})
   	
      $("#uploads").on("click",function(){
    	  {
	    var f = $("#apkFile").val();  
	    if(f==null||f==""){  
	       alert("空文件不能上传");
	        return;  
	      }else{  
	       var extname = f.substring(f.lastIndexOf(".")+1,f.length);  
	       extname = extname.toLowerCase();//处理了大小写  
	       if(extname!= "xls"&&extname!= "xlsx"){  
				alert("格式错误");
		         return ;  
	        }  
	      }  
	     var file = document.getElementById("apkFile").files;    
	     var size = file[0].size;  
	     if(size>2097152){  
	          alert("错误提示:文件太大,大小最多支持2M!</span>");   
	          return ;  
	      }  
	      $.ajaxFileUpload({  
		        url : getIP()+"upload/uploadExel.action", //用于文件上传的服务器端请求地址  
		        secureuri : false, //一般设置为false  
		        fileElementId : 'apkFile', //文件上传空间的id属性  <input type="file" id="file" name="file" />  
		        type : 'post',  
		        async : false,
		        dataType : 'json', //返回值类型 一般设置为json 
		        enctype:'multipart/form-data', 
		        success : function(data, status) //服务器成功响应处理函数  
		        {  
		       		alert(data.info);
					getData();
		        },  
		        error : function(data, status, e)//服务器响应失败处理函数  
		        {  
		       		alert("上传失败");
		        }  
		    }); 
		}  
      });
	
	
 	
   
     	/**
   	 * 添加/修改
   	 */
   	$("#pmenuAddButton").on("click",function(){
   		var username=$("#usernames").val();
   		var id=$("#userId").val();
   		var password=$("#passwords").val();
   		var department=$("#departments").val();
   		var edit=$.Params.create();
   		edit.addParams("username",username);
   		edit.addParams("password",password);
   		edit.addParams("department",department);
   			edit.addParams("schoolid",1);
   		var url="";
   		if(username== ""  || password==""){
   			alert("用户名密码不能为空");
   			return;
   		}
   		if(id == ""){
   			url=getIP()+"yuangong.action?add";
   		}else{
   			edit.addParams("userid",id);
   			url=getIP()+"yuangong.action?update";
   		}
   		edit.ajax(url,function (data){
			if(data.success){
				getData();
				$("#responsive").modal('hide');
			}
		});
   	})


})

/***修改***/
   
   function update(userid,username,password,department){
	   $("#userId").val(userid);
	   $("#usernames").val(username);
	   $("#passwords").val(password);
	   $("#departments").val(department);
	   $("#responsive").modal('toggle');
   }
