<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
	<title>后台管理系统</title>
 	<jsp:include page="media.jsp"></jsp:include>
 	<meta http-equiv="X-UA-Compatible" content="IE=10"/>
 	
 <script type="text/javascript" src="media/js/left.js"></script>
<style type="text/css">
.page-sidebar{
	margin:0px;
}
.page-sidebar.nav-collapse.collapse{
	height:9000px;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
 //$('[name=usersNames]').text("欢迎您：" + userName);
$("[class=sub-menu] li a").live("click",function(){
	$("[class=sub-menu] li").removeClass();
	$(this).parent().addClass("active");
})
})
</script>
</head>

<body class="page-header-fixed" id="scollcon">
	<div class="page-container row-fluid" style="width:100%">
	<!-- 顶部左侧
	<div class="leftbrand" name="usersNames"></div> -->
	<!-- 左侧菜单开始 -->
		<div class="page-sidebar nav-collapse collapse">
			<ul class="page-sidebar-menu" id="leftmenuclick">
				<!-- <li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">会员管理</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li><a class="ajaxify start" target="right" href="media/member/memberList.jsp">会员管理 </a></li>
					</ul> 
				</li>
				<li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">专家管理</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li><a class="ajaxify" target="right" href="media/expert/expertList.jsp">专家管理 </a></li>
					</ul>
				</li>
				<li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">课程管理12</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li><a class="ajaxify" target="right" href="media/course/courseList.jsp">在线课程管理 </a></li>
						<li><a class="ajaxify" target="right" href="media/course/courseDB.jsp">点播课程管理 </a></li>
					</ul>
				</li>
				<li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">微信公众号管理</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li><a class="ajaxify" target="right" href="media/weixin/wxUserList.jsp">粉丝管理 </a></li>
						<li><a class="ajaxify" target="right" href="media/weixin/material1.jsp">素材管理 </a></li>
						<li><a class="ajaxify" target="right" href="media/weixin/pushMessage.jsp">消息推送 </a></li>
						
					</ul>
				</li>
				<li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">新闻资讯</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">						
						<li><a class="ajaxify" target="right" href="media/information/informationList.jsp">新闻资讯 </a></li>
						<li><a class="ajaxify" target="right" href="media/information/informationDetaial.jsp">系统公告 </a></li>
					</ul>
				</li>
				<li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">数据分析</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li><a class="ajaxify" target="right" href="media/tongji/wxgz.jsp">微信关注量统计 </a></li>
						<li><a class="ajaxify" target="right" href="media/tongji/qxgz.jsp">取消关注量统计 </a></li>
						<li><a class="ajaxify" target="right" href="media/tongji/zwtj.jsp">职位统计 </a></li>
						<li><a class="ajaxify" target="right" href="media/tongji/yytj.jsp">医院统计 </a></li>
					</ul>
				</li>
				<li class="open">
					<a><i><img src="media/images/menu1.png"></i> 
						<span class="title">系统管理</span><span class="selected"></span>
					</a>
					<ul class="sub-menu">
						<li><a class="ajaxify" target="right" href="media/power/menuList.jsp">菜单管理 </a></li>
						<li><a class="ajaxify" target="right" href="media/power/roleList.jsp">角色管理 </a></li>
						<li><a class="ajaxify" target="right" href="media/power/usersList.jsp">用户管理 </a></li>
						<li><a class="ajaxify" target="right" href="media/log/loginLogList.jsp">登录日志 </a></li>
					</ul>
				</li> -->
			</ul>
		</div>
<!-- 左侧菜单结束 -->
</div>
</body>
<script>

		jQuery(document).ready(function() {       
			
		   App.init();
  
		   
		});
	
	</script>
</html>