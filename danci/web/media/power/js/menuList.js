$(function(){
	
	getData();
	function getData(){
		var sel=$.Params.create();
		sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	sel.addParams("parentId","0");
	   	getPageData(sel,getIP()+"powerMenu.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.menuName+"</td>" +
								"<td onclick='cmenu(\""+val.id+"\")'>查看子类</td>"+
								"<td>"+val.code+"</td>"+
								"<td onclick='update(\""+val.id+"\",\""+val.menuName+"\",\""+val.code+"\")'>修改</td></tr>";
					})
				}else{
					html="<tr><td colspan='8'>暂无数据</td></tr>";
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
   		$("#pmenuName").val("");
   		$("#code").val("");
   		$("#pkid").val("");
   		$("#responsive").modal('toggle');
   	})
   	
   	/**
   	 * 添加/修改菜单
   	 */
   	$("#pmenuAddButton").on("click",function(){
   		var menuName=$("#pmenuName").val();
   		var code=$("#code").val();
   		if(menuName == ""){
   			alert("菜单名不能为空！");
   			return false;
   		}
   		var id=$("#pkid").val();
   		var edit=$.Params.create();
   		edit.addParams("menuName",menuName);
   		edit.addParams("code",code);
   		var url="";
   		if(id == ""){
   			url=getIP()+"powerMenu.action?add";
   		}else{
   			edit.addParams("id",id);
   			url=getIP()+"powerMenu.action?update";
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
        	del.ajax(getIP()+"powerMenu.action?delMore",function(data){
        		alert(data.msg);
        		if(data.success){
        			getData();
        		}
        	})
    	}
    })
   	
})

/***修改***/
function update(mid,menuname,code){
	$("#pmenuName").val(menuname);
	$("#code").val(code);
	$("#pkid").val(mid);
	$("#responsive").modal('toggle');
}

/****查看子菜单****/
function cmenu(mid){
	window.location.href=getIP()+"media/power/cmenuList.jsp?mid="+mid;
}

