$(function(){
	
$("#return").on("click",function(){
	history.go(-1);
	});

 
var listrole=$.Params.create();
listrole.ajax(getIP()+"caipinleixing.action?list",function (data){
	if(data.success){
		var html="<option>请选择类型</option>";
		$.each(data.rows,function(i,val){
			html+="<option value='"+val.id+"'>"+val.a1+"</option>";
		})
		$("#a2").html(html);
	}
});

 
})

  

 function  addsstu(){
	 
 

	
	
 	var edit=$.Params.create();
 
  	var url=getIP()+"saomayongche.action?add";
  
 	edit.addParams("a1",$('#a1').val());
	edit.addParams("a2",$('#a2').val());
	edit.addParams("a3",$('#a3').val());
	edit.addParams("a4",$('#a4').val());
   
   		edit.ajax(url,function (data){
			if(data.success){
			alert("添加成功");
			window.location.href=getIP()+"media/saomayongche/saomayongcheList.jsp";
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


   	


