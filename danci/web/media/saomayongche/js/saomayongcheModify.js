$(function(){	
	$("#return").on("click",function(){
	history.go(-1); 
	});
 
	
	var listrole=$.Params.create();
	listrole.ajax(getIP()+"leixing.action?list",function (data){
		if(data.success){
			var html="<option>请选择类型</option>";
			$.each(data.rows,function(i,val){
				html+="<option value='"+val.id+"'>"+val.a1+"</option>";
			})
			$("#a2").html(html);
		}
	});

	
	
var eid=GetQueryString("eid"); 

  
	var p=$.Params.create();
	p.addParams("id",eid); 
    p.ajax(getIP()+"shangpin.action?list",function(data){
		var html="";
		var val=data.rows[0]; 
	 

		$("#a1").val(val.a1); 
		$("#a2").val(val.a2); 
		$("#a3").val(val.a3);
	 
	
		$("#id").val(eid);
	})
})

//房间扫码
function  adds(){
	 
	 
	
	var a1 = $("#a1").val();
	var a2 = $("#a2").val();
  	var a3 = $("#a3").val();
  	var a4 ='';
  	var a5 = $("#a5").val();
  	var a6 = $("#a6").val();
	var a7 = usersId;//当前登录用户id
	
  	var id = $("#id").val();
 	var edit=$.Params.create();
 	var url=getIP()+"shangpin.action?yuding"; //房间预定，将房间状态改为已扫码 
	    edit.addParams("a1",a1);//房间编号
	    edit.addParams("a3",a3);//价格
   		edit.addParams("a4",a4);//房间状态
   		edit.addParams("a5",a5);//
   		edit.addParams("a6",a6);//扫码时间
   		edit.addParams("a7",a7);//当前登录用户id
 
   		edit.addParams("id",id);
   		edit.ajax(url,function (data){
			if(data.success){
			alert("保存成功");
			window.location.href=getIP()+"media/saomayongche/saomayongcheList.jsp";
			}
		})
		
	}



   	function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}