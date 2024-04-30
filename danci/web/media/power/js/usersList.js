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
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.loginName+"</td>" +
								"<td>"+val.userName+"</td>" +
								"<td>"+val.roleName+"</td>" +
								"<td>"+val.tel+"</td>" +
								"<td>"+val.email+"</td>"+
								"<td onclick='update(\""+val.id+"\",\""+val.loginName+"\",\""+val.userName+"\",\""+val.tel+"\",\""+val.email+"\",\""+val.roleId+"\")'>修改</td></tr>";
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
   		$("#loginName").val("");
   		$("#userName").val("");
   		$("#tel").val("");
   		$("#email").val("");
   		$("#responsive").modal('toggle');
   	})
   	
   	/**
   	 * 添加/修改
   	 */
   	$("#pmenuAddButton").on("click",function(){
   		var loginName=$("#loginName").val();
   		var userName=$("#userName").val();
   		var email=$("#email").val();
   		var tel=$("#tel").val();
   		var roleId=$("#roleId").val();
   		
   		var id=$("#pkid").val();
   		var edit=$.Params.create();
   		edit.addParams("loginName",loginName);
   		edit.addParams("userName",userName);
   		edit.addParams("tel",tel);
   		edit.addParams("roleId",roleId);
   		edit.addParams("email",email);
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
function update(mid,loginName,userName,tel,email,roleId){
	$("#loginName").val(loginName);
	$("#userName").val(userName);
	$("#tel").val(tel);
	$("#email").val(email);
	$("#pkid").val(mid);
	$("#roleId").val(roleId);
	$("#responsive").modal('toggle');
}

/****查看子菜单****/
function cmenu(mid){
	window.location.href=getIP()+"media/power/cmenuList.jsp?mid="+mid;
}

