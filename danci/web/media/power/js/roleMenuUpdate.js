$(function(){
	
	var roleId=GetQueryString("roleId");
	var roleName=window.localStorage.getItem("roleName");
	$("#roleName").val(roleName);
	var sel=$.Params.create();
	
	sel.ajax(getIP()+"powerMenu.action?getAllMenu",function(data){
		var html="";
		$.each(data.rows,function(i,val){
			html+="<dl><dt><input id="+val.id+" name='checkbox' type='checkbox'>"+val.menuName+"</dt><dd>";
			$.each(val.subMenuList,function(i,subMenu) {
                html += "<input id="+subMenu.id1+" name='checkbox' type='checkbox'> "+subMenu.menuName1 ;
            });
			html+="</dd></dl>"
		})
		$("#allmenu").html(html);
		var sel1=$.Params.create();
		sel1.addParams("roleId",roleId); 
		sel1.ajax(getIP()+"powerMenu.action?getUserMenuById",function(data){
        	$.each(data,function(i,val){
                $("input[id='"+val.id+"']").attr("checked",'checked');
                $("input[id='"+val.id+"']").parent().addClass("checked");
                $.each(val.subMenuList,function(i,subMenu) {
                	$("input[id='"+subMenu.id1+"']").attr("checked",'checked');
                    $("input[id='"+subMenu.id1+"']").parent().addClass("checked");
                });
            });
        });
		App.init();
	})
	
	/**
	 * 修改角色
	 */
	$("#addSubmit").on("click",function(){
		var roleName=$("#roleName").val();
		var params=getcheckdataJson(roleId);
		var add=$.Params.create();
		add.addParams("addlist",params); 
		add.addParams("id",roleId); 
		add.addParams("roleName",roleName); 
		add.ajax(getIP()+"powerRole.action?update",function(data){
			alert(data.msg);
			window.location.href=getIP()+"media/power/roleList.jsp";
		})
	})
})