<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
 	<jsp:include page="../medianl.jsp"></jsp:include>
 	<script type="text/javascript" src="media/js/pageMedia.js"></script>
 	<script type="text/javascript" src="media/ht_users/js/usersreg.js"></script>
 </head>

<body class="page-header-fixed" >
 
<div id="responsive" class="modal fade in" tabindex="-1" data-width="760">
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