<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
 <html lang="en">
<head>

<title></title>
	<meta charset="utf-8" />
 <script type="text/javascript" src="<%=basePath%>media/shangpin/js/jquery-1.10.2.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>media/shangpin/js/getIp.js"></script>
 <script type="text/javascript" src="<%=basePath%>media/shangpin/js/ian.js"></script>
  <script type="text/javascript" src="<%=basePath%>media/shangpin/js/page.js"></script>
  <script type="text/javascript" src="<%=basePath%>media/shangpin/js/pageMedia.js"> </script>
<script type="text/javascript" src="<%=basePath%>media/shangpin/js/commcom.js"></script>
 
<script type="text/javascript" src="<%=basePath%>media/shangpin/js/shangpinList.js"></script>
<link rel="stylesheet" href="<%=basePath%>media/shangpin/css/bootstrap.min.css" type="text/css"></link>
 
  <link rel="stylesheet" href="<%=basePath%>media/lay/css/font.css" type="text/css"></link>
 <link rel="stylesheet" href="<%=basePath%>media/lay/css/xadmin.css" type="text/css"></link>
 <link rel="stylesheet" href="<%=basePath%>media/lay/css/swiper.min.css" type="text/css"></link>
  <link rel="stylesheet" href="<%=basePath%>media/lay/lib/layui/css/layui.css" type="text/css"></link>
 

<style type="text/css">
select {
    width: 100px;
    background-color: rgb(255, 255, 255);
    border-width: 1px;
    border-style: solid;
    border-color: rgb(204, 204, 204);
    border-image: initial;
}
body{
	width: 100%;
	background-color: #54364a;
    background-image: url(<%=basePath%>media/lay/images/a.jpg);
    background-repeat: no-repeat;
    background-size: cover;
    color: #ffffff;
}
</style>
 

</head>


<style type="text/css">
select {
    width: 100px;
    background-color: rgb(255, 255, 255);
    border-width: 1px;
    border-style: solid;
    border-color: rgb(204, 204, 204);
    border-image: initial;
}

.btn-group
{
margin-left:50px;
font-size:12px;
}


</style>
<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section">&nbsp;&nbsp;&nbsp;<font style="font-weight:bold" >管理</font></h3>
	<div class="row-fluid">
		
		 <form class="layui-form xbs" action="">
                <div class="layui-form-pane" style="text-align: center;">
                  <div class="layui-form-item" style="display: inline-block;">
                    <label class="layui-form-label xbs768">名</label> 
                    <div class="layui-input-inline xbs768">
                      <input class="layui-input"  id="a1" placeholder="请输入名" id="LAY_demorange_e">
                    </div> 
                    <div class="layui-input-inline" style="width:80px">
                          <div  id="searchList" onclick="serarch()"  class="layui-btn">
			     	<i class="layui-icon"></i>
			  </div>
					</div>
                  </div>
                </div> 
            </form>
		 
		   <xblock style="padding-left:20px;"><button class="layui-btn" id="addbutton"><i class="layui-icon">&#xe608;</i>添加</button>&nbsp;<button class="layui-btn" id="delbutton"><i class="layui-icon">&#xe608;</i>删除</button></xblock>
		
		

</div>
	 <div class="portlet-body list">
	 
	  <div style="margin-left:20px; color:black;">  </div>
	 
		<table class="layui-table" id="listtable">
	           <thead>
	               <tr>
	                   <th style="width:5%;"><input type="checkbox"  id="queryCheckbox"  name="queryCheckbox" value=""></th>
	                        <th style="width:10%;">id</th>
	                    <th style="width:10%;">名称</th>
	                    <th style="width:10%;">类型</th> 
	                 
	                    <th style="width:10%;">图片</th> 

	                   <th style="width:10%;">操作</th>
	               </tr>
	           </thead>
				<tbody id="qkList">
				<tr>
					<td colspan="10">暂无数据</td>
				</tr>
				</tbody>
	
	       </table>
	       
	      <div class="pagination" id="pagination" >
	                    <div class="float_right padding5 paging" >
	                        <div id="divFood" class="float_left padding_top4">
	                        <span float="left">每页<font id="rowscount">0</font>条/共<font id="allcount">0</font>条 第<font id="nowpage">0</font>页/共<font id="allpage">0</font>页</span>
	                        <span onclick="firstPage()" float="left" class="pagefl"><a>首页</a></span>
	                        <span onclick="prePage()" float="left" class="pagefl"><a>上一页</a></span>
	                        <span onclick="nextPage()" float="left" class="pagefl"><a>下一页</a></span>
	                        <span onclick="lastPage()" float="left" class="pagefl"><a>末页</a></span>
	                       <!--  <span display="" float="left" class="pagefl" style="margin-right:10px;">跳转到第 <input style="width:20px;height:15px;margin-top:6px;" type="text" /> 页</span> -->
	                       <span display="" float="left" class="pagefl">跳转到第 
	                       		<select onchange="jumpPage(this)">
	                       			<option selected="selected" value="0">1</option>
	                       		</select> 页</span>
	                        </div>
	                   </div>
	           </div>
		</div>
		</div>
	</div>
</div>

</body>
 
</html>