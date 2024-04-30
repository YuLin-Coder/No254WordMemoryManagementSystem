var rows=15;


var myselfPage;
var myselfPath;
var myselfSucc;

var pageEnd;
var pageIndex;

/***给页面分页赋值****/
function showpage(total,pages){
	var nowpage=Number(pages)/rows;
	var allpage=Math.ceil(Number(total)/rows);
	pageEnd=allpage-1;
	pageIndex=nowpage;
	$("#allcount").html(total);
	$("#rowscount").html(rows);
	$("#allpage").html(allpage);
	$("#nowpage").html(nowpage+1);
	var option="";
	for(var i=0;i<allpage;i++){
		var val=i+1;
		option+="<option value='"+i+"'>"+val+"</option>"
	}
	$(".pagefl select").html(option);
	$(".pagefl select").val(nowpage);
}

	/**
 * 获取数据
 */
function getPageData(param,path,succ){
	myselfPath=path;
	myselfPage=param;
	myselfSucc=succ;
   	param.ajax(path,function(data){
		showpage(data.total,data.page);
		succ(data);
		//记住当前所选的页面，目的是从其他页面再返回清单页时，保持之前所选的页面
		window.localStorage.setItem("page",data.page);
	});
}

/**
 * 首页
 * 
 */
function firstPage(){
	turnPage("0");
}

/**
 * 最后一页
 */
function lastPage(){
	turnPage(getpageEnd());
}

/**
 * 上一页
 */
function prePage(){
	var index=getPathIndex()-1;
	if(index<0){
		alert("已经是首页了");
	}else{
		turnPage(index);
	}
}

/**
 * 下一页
 */
function nextPage(){
	var index=getPathIndex()+1;
	if(index>getpageEnd()){
		alert("已经是最后一页了");
	}else{
		turnPage(index);
	}
}

/**
 * 跳转
 */
function jumpPage(obj){
	var val=$(obj).val();
	turnPage(val);
}

function jumpRows(obj){
    var rowscount=$("#rowscount").html();
	var val=$(obj).val();
	alert(rows);
	if(val!="" || val!='undefined')
	{
	rows = val;
	myselfPage.json["rows"]=rows;
	}
	
   var index=getPathIndex()+1;
	myselfPage.json["page"]=index*rows;
	
	getPageData(myselfPage,myselfPath,myselfSucc);
}
/**
 * 修改page值，显示当前页数据
 * @param index
 * @return
 */
function turnPage(index){
	myselfPage.json["page"]=index*rows;
	getPageData(myselfPage,myselfPath,myselfSucc);
}




function getPathIndex(){
	return pageIndex;
}

/**
 * 最后一页
 * @return
 */
function getpageEnd(){
	return pageEnd;
}
