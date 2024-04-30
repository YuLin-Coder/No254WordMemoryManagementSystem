$(function(){
	var mid=GetQueryString("mid");
	//alert(mid);
	getData();
	function getData(){
		var sel=$.Params.create();
		sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	sel.addParams("parentId",mid);
	   	getPageData(sel,getIP()+"powerMenu.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.menuName+"</td>" +
								"<td>"+val.url+"</td>"+
								"<td>"+val.code+"</td>"+
								"<td onclick='update(\""+val.id+"\",\""+val.menuName+"\",\""+val.url+"\",\""+val.code+"\")'>修改</td></tr>";
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
   		$("#url").val("");
   		$("#pkid").val("");
   		$("#responsive").modal('toggle');
   	})
   	
   	/**
   	 * 添加/修改菜单
   	 */
   	$("#pmenuAddButton").on("click",function(){
   		var menuName=$("#pmenuName").val();
   		var code=$("#code").val();
   		var url=$("#url").val();
   		if(menuName == ""){
   			alert("菜单名不能为空！");
   			return false;
   		}
   		var id=$("#pkid").val();
   		var edit=$.Params.create();
   		edit.addParams("menuName",menuName);
   		edit.addParams("code",code);
   		edit.addParams("url",url);
   		edit.addParams("parentId",mid);
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
function update(mid,menuname,url,code){
	$("#pmenuName").val(menuname);
	$("#code").val(code);
	$("#pkid").val(mid);
	$("#url").val(url);
	$("#responsive").modal('toggle');
}

