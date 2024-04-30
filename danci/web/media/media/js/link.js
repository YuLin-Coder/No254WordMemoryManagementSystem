$(function(){
	
	getData();
	function getData(){
		var sel=$.Params.create();
		sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	getPageData(sel,getIP()+"link.action?list",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						html+="<tr>" +
								"<td><input id='"+val.linkid+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.linkname+"</td>" +
								"<td>"+val.linkurl+"</td>" +
								"<td>"+val.name+"</td>" +
								"<td onclick='update(\""+val.linkid+"\",\""+val.imgurl+"\",\""+val.linkname+"\",\""+val.classid+"\",\""+val.linkurl+"\")'>修改</td></tr>";
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
	 * 获取类别
	 */
	var listrole=$.Params.create();
	listrole.ajax(getIP()+"linkClass.action?list",function (data){
		if(data.success){
			var html="";
			$.each(data.rows,function(i,val){
				html+="<option value='"+val.classid+"'>"+val.name+"</option>";
			})
			$("#classid").html(html);
		}
	});
	
	
   	
   	/**
   	 * 添加按钮
   	 */
   	$("#addbutton").on("click",function(){
   		$("#linkid").val("");
   		$("#linkname").val("");
   		$("#linkurl").val("");
   		$("#imgurl").val("");
   		$("#responsive").modal('toggle');
   	})
   	
   	/**
   	 * 添加/修改
   	 */
   	$("#pmenuAddButton").on("click",function(){
   		var linkname=$("#linkname").val();
   		var imgurl=$("#imgurl").val();
   		var classid=$("#classid").val();
   		var linkurl=$("#linkurl").val();
   		var id=$("#linkid").val();
   		var edit=$.Params.create();
   		edit.addParams("linkname",linkname);
   		edit.addParams("imgurl",imgurl);
   		edit.addParams("linkurl",linkurl);
   		edit.addParams("classid",classid);
   		if(linkname == ""){
   			alert("链接名称不能为空");
   			return;
   		}
   		if(classid == ""){
   			alert("链接栏目不能为空");
   			return;
   		}
   		var url="";
   		if(id == ""){
   			url=getIP()+"link.action?add";
   		}else{
   			edit.addParams("linkid",id);
   			url=getIP()+"link.action?update";
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
    	alert(params);
    	if(confirm("确定要删除吗？")){
    		var del=$.Params.create();
        	del.addParams("id",params);
        	del.ajax(getIP()+"link.action?delMore",function(data){
        		alert(data.msg);
        		if(data.success){
        			getData();
        		}
        	})
    	}
    })
   	
})
 
	

/***修改***/
function update(linkid,imgurl,linkname,classid,linkurl){
	$("#linkid").val(linkid);
	$("#classid").val(classid);
	$("#linkname").val(linkname);
	$("#imgurl").val(imgurl);
	$("#linkurl").val(linkurl);
	$("#responsive").modal('toggle');
}
/****查看子菜单****/
function cmenu(mid){
	window.location.href=getIP()+"media/power/cmenuList.jsp?mid="+mid;
}
