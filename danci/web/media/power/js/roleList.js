$(function(){
	
	getData();
	function getData(){
		var sel=$.Params.create();
		sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	getPageData(sel,getIP()+"powerRole.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.roleName+"</td>" +
								"<td>"+val.updateTime+"</td>"+
								"<td style='cursor:pointer' onclick='update(\""+val.id+"\",\""+val.roleName+"\")'>修改</td></tr>";
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
   		window.location.href=getIP()+"media/power/roleMenuEdit.jsp";
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
        	del.ajax(getIP()+"powerRole.action?deleteRole",function(data){
        		alert(data.msg);
        		if(data.success){
        			getData();
        		}
        	})
    	}
    })
   	
})

/***修改***/
function update(roleId,roleName){
	window.localStorage.setItem("roleName",roleName);
	window.location.href=getIP()+"media/power/roleMenuUpdate.jsp?roleId="+roleId;
}
