var sel=$.Params.create();
$(function(){
	
	var newDate = getNowDate();
    $("#d5222").val(newDate);
    var zdateStart=new Date((+new Date())-30*24*3600*1000).format("yyyy-MM-dd");
    $("#d5221").val(zdateStart);
    
    getData();
    
    /**
     * 查询
     */
    $("#searchList").on("click",function(){
    	var userName=$("#userName").val();
    	if(userName != ""){
    		sel.addParams("userName",userName);
    	}else{
    		sel.addParams("userName",null);
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
        	del.ajax(getIP()+"logLogin.action?delMore",function(data){
        		alert(data.msg);
        		if(data.success){
        			$("#queryCheckbox").removeAttr("checked");
        			$("#queryCheckbox").parent().removeClass("checked");
        			getData();
        		}
        	})
    	}
    })
    
    function getData(){
		var datefrom=$("#d5221").val();
	    var dateto=$("#d5222").val();
	    var type=$("#type").val();
		sel.addParams("type",type);
		sel.addParams("datefrom",datefrom);
	   	sel.addParams("dateto",dateto);
	   	sel.addParams("page",0);
	   	sel.addParams("rows",rows);
	   	getPageData(sel,getIP()+"logLogin.action?listLogLogin",function(data){
	   		if(data.success){
	   			var len=data.total;
		   		var html="";
				if(len >0){
					$.each(data.rows,function(i,val){
						var type;
						if(val.type == 1){
							type="服务端";
						}else{
							type="移动端";
						}
						html+="<tr>" +
								"<td><input id='"+val.id+"' name='checkbox' type='checkbox'></td>" +
								"<td>"+val.userName+"</td>" +
								"<td>"+val.loginName+"</td>" +
								"<td>"+type+"</td>" +
								"<td>"+val.column03+"</td>" +
								"<td>"+val.loginTimer+"</td></tr>";
					})
				}else{
					html="<tr><td colspan='4'>暂无数据</td></tr>";
				}
				$("table tbody").html(html);
				App.init();
	   		}else{
	   			alert(data.msg);
	   		}
	   		
	   	});
	}
   

})


