var sel=$.Params.create();
$(function(){
    getData();
    
    	/**
   	 * 添加按钮
   	 */
   	$("#addbutton").on("click",function(){
   		window.location.href=getIP()+"media/locatResource/locatEdit.jsp";
   	})
    /**
     * 查询
     */
    $("#searchList").on("click",function(){
    	var name=$("#name").val();
    	if(name != ""){
    		sel.addParams("name",name);
    	}else{
    		sel.addParams("name",null);
    	}
    	getData();
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
        	del.ajax(getIP()+"localResource.action?delMore",function(data){
        		alert(data.msg);
        		if(data.success){
        			$("#queryCheckbox").removeAttr("checked");
        			$("#queryCheckbox").parent().removeClass("checked");
        			getData();
        		}
        	})
    	}
    })
    //获取活动数据
    
    function getData(){
	   	sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	getPageData(sel,getIP()+"localResource.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.name+"</td>" +
								"<td><img style='width:80px;height:80px;' src='"+getIP()+"/data/material/"+val.imageUrl+"'></td>" +
								"<td>"+val.address+"</td>" +
								"<td>"+val.tel+"</td>" +
								"<td>"+
								//"<span onclick='getDetail(\""+val.id+"\")' class='checkDetailsBtn'>查看详情</span>"+
								"<a onclick='update(\""+val.id+"\")' class='btn btn-info'>修改</a></td>"+
								"</tr>";
					})
				}else{
					html="<tr><td colspan='10'>暂无数据</td></tr>";
				}
				$("table tbody").html(html);
				App.init();
				$("#loading").hide();
	   		}else{
	   			alert(data.msg);
	   		}
	   		
	   	});
	}
   
})
//
function getDetail(id){
	window.location.href=getIP()+"media/member/memberDetails.jsp?eid="+id;
}
//修改
function update(id){
	window.location.href=getIP()+"media/locatResource/locatModify.jsp?eid="+id;
}

