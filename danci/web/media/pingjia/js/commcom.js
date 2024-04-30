
/**
 * 获取当前时间
 */
function getNowDateTime(){
	var d = new Date();    
	return d.format("yyyy-MM-dd hh:mm:ss");
}

/**
 * 获取当前日期
 */
function getNowDate(){
	var d = new Date();    
	return d.format("yyyy-MM-dd");
}

/**
 * 获取选中数据
 */
function getcheckdata(){ 
	var params="";
	    $("[type='checkbox']:checked").each(function(){
         var id=$(this).attr("id");
         params+=id+",";
	
	}) 
	params=params.substring(0,params.length-1);
	return params;
}
function getUUID(){
	return Math.uuid();
}


/**
 * 获取选中数据json
 */
function getcheckdataJson(roleId){
	var params="[";
	$("input[name='checkbox']").each(function(){
		var check=$(this).attr("checked");
		var id=$(this).attr("id");
		if(check == "checked"){
			params+="{\"id\":\""+getUUID()+"\",\"roleId\":\""+roleId+"\",\"menuId\":\""+id+"\"},";
		}
	})
	params=params.substring(0,params.length-1);
	params+="]";
	return params;
}



/**
 * 重新查询页面清单数据后，全选复选框需要取消选中状态
 */
function cancelQueryCheckbox(){
	$("input[name='queryCheckbox']").removeAttr("checked");
    $("input[name='queryCheckbox']").parent().removeClass("checked");
}


$(function(){
	/**
	 * 全选事件
	 */
	$("#queryCheckbox").on('click',function(){
	    var ch=document.getElementsByName("checkbox");
if(document.getElementsByName("queryCheckbox")[0].checked==true)
{
for(var i=0;i<ch.length;i++)
{
ch[i].checked=true;
}
}else{
for(var i=0;i<ch.length;i++)
{
ch[i].checked=false;
}
}  
	    
	    
	})
})

//男女显示
function showSex(data){
	var sex;
	if(data == "1"){
		sex="男";
	}else{
		sex="女";
	}
	return sex;
}

//根据日期计算年龄
function showAge(str){
	if(str == ""){
	 return "未完善"; 
	}else{
		var age;
	 var r=str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);     
     if(r==null)return false;     
     var d=new Date(r[1], r[3]-1, r[4]);     
     if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]){   
           var Y = new Date().getFullYear();   
           age=(Y-r[1]);
           return age+"岁";
     }  
	}
	   
}