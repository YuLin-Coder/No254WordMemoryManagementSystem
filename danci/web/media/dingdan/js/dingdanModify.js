$(function(){	
	$("#return").on("click",function(){
	history.go(-1);
	});

	
	 
	
	
	
var eid=GetQueryString("eid"); 

 
	var p=$.Params.create();
	p.addParams("id",eid); 
    p.ajax(getIP()+"dingdan.action?list",function(data){
		var html="";
		var val=data.rows[0]; 
	 

		$("#a1").val(val.a1);
		$("#a8").val(val.a8);
 
		$("#a6").val(val.a4);

		$("#id").val(eid);
	})
})
function  adds(){
	
	
 
	var a1 = $("#a1").val();
	var a6 = $("#a6").val();
	var a7 = "空车";
	var a8=$("#a8").val();//房间id
	
	var a10=a10s;
	var a11 = $("#a11").val();
	
	
	if(a6=="")
		{
		
		alert('请输入金额');
		return;
		}
	

	var eid=GetQueryString("eid"); 

	var id =eid;

 	var edit=$.Params.create();
 	var url=getIP()+"dingdan.action?update";
   		edit.addParams("a1",a1); 
   		edit.addParams("a6",a6); 
   		edit.addParams("a7",a7); 
   		edit.addParams("a8",a8); 
   		edit.addParams("a10",a10); 
   		edit.addParams("a11",a11); 
 
   		edit.addParams("id",id);
   		edit.ajax(url,function (data){
			if(data.success){
			alert("保存成功");
			window.location.href=getIP()+"media/dingdan/dingdanList.jsp";
			}
		})
	}
   	function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}