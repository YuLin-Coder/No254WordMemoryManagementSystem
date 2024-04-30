<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
 	<jsp:include page="../media.jsp"></jsp:include>
 	<script type="text/javascript" src="media/js/pageMedia.js"></script>
 	<script type="text/javascript" src="media/ht_users/js/usersList.js"></script>
 	
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

<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section">用户管理</h3>
	<div class="row-fluid">
		<div class="span12">
	     <div class="btn-group pull-right listsearch">
	    	 <button id="addbutton" class="btn blue form_button short">添加</button>
	        <button id="delbutton" class="btn blue form_button short">删除</button>
		</div>
	 <div class="portlet-body list">
		<table class="layui-table" id="listtable">
	           <thead>
	               <tr>
	                   <th style="width:10px;"><input type="checkbox"  id="queryCheckbox"  name="queryCheckbox" value=""></th>
	                   <th style="width:10px;">用户名</th>
	                   <th style="width:40px;">密码</th>
	                   <th style="width:10px;">用户姓名</th>
	                   <th style="width:15px;">管理级别</th>
	                     <th style="width:15px;">用户状态</th>
	                   <th style="width:15px;">操作</th>
	               </tr>
	           </thead>
				<tbody id="qkList">
				<tr>
					<td colspan="10">暂无数据</td>
				</tr>
				</tbody>
	
	       </table>
	      <div class="pagination" id="pagination">
	                    <div class="float_right padding5 paging">
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
	                    <div class="clear"></div>
	           </div>
		</div>
		</div>
	</div>
</div>

<div id="responsive" class="modal hide fade" tabindex="-1" data-width="760">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h3>用户管理</h3>
	</div>
	<div class="modal-body">
		<div class="row-fluid">
			<div class="span12">
				<input id="pkid" type="hidden" class="span12 m-wrap">
				<h4>登录名：</h4>
				<p><input id="loginName" type="text" class="span12 m-wrap"></p>
				<h4>姓名：</h4>
				<p><input id="userName" type="text" class="span12 m-wrap"></p>
				<h4>审核状态：</h4>
				<p>
					<select id="state">
						<option value="0">待审核</option>
						<option value="1">审核通过</option>
					</select>
				</p>
				
				<h4>角色：</h4>
				<p>
					<select id="roleId">
						<option>请选择管理级别</option>
					</select>
				</p>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn">取消</button>
		<button id="pmenuAddButton" type="button" class="btn blue">保存</button>

	</div>

</div>


</body>
<script type="text/javascript">
 App.init();
</script>
</html>