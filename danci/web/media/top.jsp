<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
	<title>后台管理系统</title>
	<meta http-equiv="X-UA-Compatible" content="IE=10"/>
	
 	<jsp:include page="media.jsp"></jsp:include>
 	<link rel="stylesheet" type="text/css" href="media/css/bootstrap-cerulean.css" />
 	
 	<!-- <script type="text/javascript" src="media/homePage.js"></script> --> 
	 <script type="text/javascript">
	 		$(document).ready(function(){

                $('[name=usersNames]').text("欢迎您：" + userName);
                $('#logout').click(function () {
                    if (confirm("您确认要退出系统吗？")) {
                    	 var back = $.Params.create();
	                   	 back.ajax("powerUser.action?logOut", function (data) {
	                   	 	if(data.success){
	                   	 		window.top.location.href = getIP() + "media/login.jsp";
	                   	 	}
	                   	 })
                    }
                });
                $("#leftmenuclick>li>a").click(function () {
                    return false;
                })

            })
</script>
</head>
<body class="page-header-fixed">
<!-- 顶部开始 -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- 顶部右侧 -->       
				<ul class="nav pull-right" id="topurl">
				<li class="dropdown" id="header_notification_bar">
						<a name="usersNames" href="#" class="dropdown-toggle" data-toggle="dropdown">
						</a>
					</li>
					<!-- 小功能入口，消息提醒 -->
					<li class="dropdown" id="header_notification_bar">
						<a id="logout" href="#" class="dropdown-toggle" data-toggle="dropdown">
						退出系统
						</a>
					</li>
					<li class="dropdown" id="header_notification_bar">
						<a id="updPwd" target='right' href="media/user/password.jsp" class="dropdown-toggle">
						修改密码
						</a>
					</li>
				</ul>
			</div>
		</div>
</div>
<!-- 顶部结束 -->


</body>
</html>