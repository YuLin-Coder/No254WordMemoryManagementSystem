$(function(){
	$("#pwdsub").on("click",function(){
		var oldpwd=$("#oldpwd").val();
		var newpwd=$("#newpwd").val();
		var confipwd=$("#confipwd").val();
		if(oldpwd == "" || newpwd == "" || confipwd == ""){
			alert("密码不能为空！");
			return false;
		}
		if(newpwd != confipwd){
			alert("两次输入密码不一致！");
			return false;
		}
		var p=$.Params.create();
		p.addParams("userId",usersId);
		p.addParams("oldpwd",oldpwd);
		p.addParams("newpwd",newpwd);
		p.ajax(getIP()+"powerUser.action?updatePwd",function(data){
			if(data.result == 0){
				alert("原密码输入不正确！");
			}else{
				alert("修改成功！跳至登录页重新登录。");
				window.top.location.href = getIP() + "media/login.jsp";
			}
		});
	})
})