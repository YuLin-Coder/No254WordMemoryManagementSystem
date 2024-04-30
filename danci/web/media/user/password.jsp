<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="../media.jsp"></jsp:include>
<script type="text/javascript" src="media/user/js/password.js"></script> 
<style>
.form-horizontal .control-group{padding-top:20px;padding-bottom:20px;}
</style>
<div class="tab-content contents">
	<h3 class="form-section">修改密码
	</h3>
	<form action="#" class="form-horizontal">
 <div class="portlet-body">
	<div class="row-fluid">
		<div class="control-group">
		<label class="control-label" for="inputWarning">原密码</label>
		<div class="controls">
			<input type="password" class="span6 m-wrap" id="oldpwd" />
			<span name="oldPwd" class="help-inline"></span>
		</div>

	</div>
	<div class="control-group">
		<label class="control-label" for="inputWarning">新密码</label>
		<div class="controls">
			<input type="password" class="span6 m-wrap" id="newpwd" />
			<span name="newPwd" class="help-inline"></span>
		</div>
	</div>
		<div class="control-group">
		<label class="control-label" for="inputWarning">确认密码</label>
		<div class="controls">
			<input type="password" class="span6 m-wrap" id="confipwd" />
			<span name="newPwd2" class="help-inline"></span>
		</div>
	</div>
</div>
</div>
<div class="form-actions">
		<button type="button" id="pwdsub" class="btn blue form_button">保存</button>
	</div>

</form>
</div>

