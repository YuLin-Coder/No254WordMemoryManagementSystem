$(function(){
	
	getData();
	function getData(){
		var sel=$.Params.create();
		sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	getPageData(sel,getIP()+"powerUser.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					
					var zhuangtai='';
					$.each(data.rows,function(i,val){
						
						if(val.state==0)
							{
							zhuangtai='待审核';
							
							}
						else
							{ 
							zhuangtai='审核通过';
							}
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.loginName+"</td>" +
								"<td>"+val.password+"</td>" +
								"<td>"+val.userName+"</td>" +
								"<td>"+val.roleName+"</td>" +
								
									"<td>"+zhuangtai+"</td>" +
								"<td onclick='update(\""+val.id+"\",\""+val.loginName+"\",\""+val.userName+"\",\""+val.roleId+"\",\""+val.state+"\")'>修改</td></tr>";
					})
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
	 * 获取角色
	 */
	var listrole=$.Params.create();
	listrole.ajax(getIP()+"powerRole.action?list",function (data){
		if(data.success){
			var html="<option>请选择角色</option>";
			$.each(data.rows,function(i,val){
				html+="<option value='"+val.id+"'>"+val.roleName+"</option>";
			})
			$("#roleId").html(html);
		}
	});
	
	
   	
   	/**
   	 * 添加按钮
   	 */
   	$("#addbutton").on("click",function(){
   		$("#roleId").val("");
   		$("#pkid").val("");
   		$("#loginName").val("");
   		$("#userName").val("");
   		$("#responsive").modal('toggle');
   	})
   	
   	/**
   	 * 添加/修改
   	 */
   	$("#pmenuAddButton").on("click",function(){
   		var loginName=$("#loginName").val();
   		var userName=$("#userName").val();
   		var roleId=$("#roleId").val();
   		
   		var id=$("#pkid").val();
   		var edit=$.Params.create();
   		
   		edit.addParams("state",$('#state').val());
   		edit.addParams("loginName",loginName);
   		edit.addParams("userName",userName);
   		edit.addParams("roleId",roleId);
   		var url="";
   		if(id == ""){
   			url=getIP()+"powerUser.action?add";
   		}else{
   			edit.addParams("id",id);
   			url=getIP()+"powerUser.action?update";
   		}
   		edit.ajax(url,function (data){
			if(data.success){
				getData();
				$("#responsive").modal('hide');
			}
		});
   	})

   	/**
     * 删除
     */
    $("#delbutton").on("click",function(){
    	var params=getcheckdata();
    	if(params == ""){
    		alert("请选择记录");
    		return false;
    	}
    	if(confirm("确定要删除吗？")){
    		var del=$.Params.create();
        	del.addParams("id",params);
        	del.ajax(getIP()+"powerUser.action?delMore",function(data){
        		alert(data.msg);
        		if(data.success){
        			getData();
        		}
        	})
    	}
    })
   	
})

/***修改***/
function update(mid,loginName,userName,roleId,state){
	$("#pkid").val(mid);
	$("#loginName").val(loginName);
	$("#userName").val(userName);
	$("#roleId").val(roleId);
	$("#responsive").modal('toggle');
	$("#state").val(state);
	
}

/****查看子菜单****/
function cmenu(mid){
	window.location.href=getIP()+"media/power/cmenuList.jsp?mid="+mid;
}

