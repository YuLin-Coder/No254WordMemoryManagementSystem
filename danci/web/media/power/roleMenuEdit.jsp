<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
 	<jsp:include page="../media.jsp"></jsp:include>
 	<script type="text/javascript" src="media/js/uuid.js"></script>
 	<script type="text/javascript" src="media/js/pageMedia.js"></script>
 	<script type="text/javascript" src="media/power/js/roleMenuEdit.js"></script>
 </head>

<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section">添加角色</h3>
	<div class="row-fluid">
			<div class="span12">
				<input id="pkid" type="hidden" class="span12 m-wrap">
				<h3 class="form-section">角色名称：<input id="roleName" type="text" class="span6 m-wrap"></h3>
			</div>
		</div>
	<div class="row-fluid">
		<div class="span12">
	 <div class="portlet-body list">
	 <h3 class="form-section">分配权限  <input type="checkbox"  id="queryCheckbox"  name="queryCheckbox" value=""> 全选</h3>
	<div id="allmenu">
	</div>
		
	      <div class="form-actions">
				<button id="addSubmit" type="button" class="btn blue">保存</button>
			</div>
		</div>
		 
		</div>
	</div>
</div>

</body>
<script type="text/javascript">
 App.init();
</script>
</html>