 
 

 function  pingjiaadd(){
	 
 
 	var edit=$.Params.create();
 
  	var url=getIP()+"pingjia.action?add";
  
  	edit.addParams("a1",$('#a1').val());//当前登录用户名
  	edit.addParams("a2",$('#a2').val());//当前登录用户名
  	
	edit.addParams("a4","'"+usersId+"'");//当前登录用户id
	edit.addParams("a3",$('#a3').val());//内容
	
   
   		edit.ajax(url,function (data){
			if(data.success){
			alert("添加成功");
			window.location.href=getIP()+"media/pingjia/pingjiaList.jsp";
			}
		else{
				
				alert('添加失败');
	}
	})
		 
  	 
	}


   	


