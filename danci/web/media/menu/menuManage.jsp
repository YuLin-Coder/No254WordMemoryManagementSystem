<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
 	<jsp:include page="../media.jsp"></jsp:include>
 	<link rel="stylesheet" href="media/menu/css/demo.css" type="text/css">
 	<link rel="stylesheet" href="media/menu/css/zTreeStyle/zTreeStyle.css" type="text/css">
 	<%--<script type="text/javascript" src="media/menu/js/jquery-1.4.4.min.js"></script>
 	--%><script type="text/javascript" src="media/menu/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="media/menu/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="media/menu/js/jquery.ztree.exedit-3.5.js"></script>
 	<script type="text/javascript" src="media/media/datepicker/WdatePicker.js"></script>
 	<script src="media/js/pageMedia.js"></script>
 	<script type="text/javascript" src="media/menu/js/menuManage.js"></script>
 		<link rel="stylesheet"   href="media/js/ui_loading/ui.loading.css" type="text/css"    media="screen" charset="utf-8" />  
    <script type="text/javascript" src="media/js/ui_loading/ui.loading.js"></script> 
 		<script type="text/javascript" src="media/menu/js/ajaxfileupload.js"></script>
 		<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
 </head>

<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section">菜单管理</h3>
	<div class="btn-group listsearch horizontal">
	<%--<button class="btn blue form_button shortmin" id="save">保存</button>
	--%></div>
 <div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right" >
	<table id="listtable"  style="margin-top: 100px">
	<tr >
	<td nowrap>菜单链接：</td><input type="hidden"   id="treeid" />
	<td><input type="text"  name="treeUrl" id="treeUrl" style="width:300px"/></td>
	</tr>
	<td nowrap>菜单图标：</td>
	<td nowrap><input type="file"  name="uploadFile" id="fileName" style="width:300px"/><image src="" id="img"  /></td>
	</tr>
	</table>
   </div>
</div>

<div class="form-actions">
		<button type="button" style="margin-left: 400px" id="save" class="btn blue form_button">保存</button>
	</div>
</div>

</body>
<script type="text/javascript">
 App.init();
</script>
</html>