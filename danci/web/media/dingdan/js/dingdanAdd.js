$(function(){
	
$("#return").on("click",function(){
	history.go(-1);
	});

 
})

  

 function  addsstu(){
	
  
 
 	var edit=$.Params.create();
 
  	var url=getIP()+"dingdan.action?add";
  
 	edit.addParams("a1",$('#a1').val());
   
   		edit.ajax(url,function (data){
			if(data.success){
			alert("添加成功");
			window.location.href=getIP()+"media/dingdan/dingdanList.jsp";
			}
		else{
				
				alert('添加失败');
	}
	})
		 
  	/*
		       $.ajax({
                type: "post",
               url: url,
              data: {
                  a1:a1,
                  a2:a2,
                  a3:a3,
                  a4:a4
               },
                dataType: "json",
                success: function (dataMs) {
                   
              	alert(111);

               }
           }
          );
 
		*/
	}


   	


