<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=10"/>
<title>后台管理系统</title>
 <jsp:include page="media.jsp"></jsp:include>
</head>

<style type="text/css">
body{
		padding:0;
		margin:0;	
		overflow-y:hidden;
		overflow-x:hidden;
	}
	.main{
		height:100%;
		width:100%;
		overflow-y:hidden;
		overflow-x:hidden;
		position:absolute;
	}
	.top{
		width:100%;
		height:42px;
		position:absolute;
		top:0;
		z-index:102;
	}
	.bottom{
		width:100%;
		height:100%;
		position:absolute;
		top:0;
		z-index:101;
	}
    .left{
        height:100%;
        width:225px;
        position:absolute;
        top:42px;
        }
    .right{
        margin-left: 220px;
        height:100%;
        padding-top: 40px;
    }
    /*
	body{
		padding:0;
		margin:0;	
		overflow-y:hidden;
		overflow-x:hidden;
	}
	.main{
		height:100%;
		width:100%;
		overflow-y:hidden;
		overflow-x:hidden;
		position:absolute;
	}
	.top{
		width:85%;
		height:50px;
		position:absolute;
		top:0;
		z-index:102;
		right:0px;
	}
	.bottom{
		width:85%;
		height:100%;
		position:absolute;
		top:50px;
		z-index:101;
		left:15%;
	}
    .left{
        height:800px;
        width:15%;
        position:absolute;
        top:0px;
        }
    .right{
        height:100%;
    }
    iframe{border:0px;}*/
</style>

<body>
	<div class="main">
		<div class="top">
        <iframe name="top" style="border-top:0px;" width="100%" height="100%" src="media/top.jsp"></iframe>
    </div>
		<div class="bottom">
			<div class="left">
				<iframe name="left" width="100%" height="100%" src="media/left.jsp"></iframe>
			</div>
			<div class="right">
				<iframe name="right" width="100%" height="100%" src="" ></iframe>
			</div>
		</div>
	</div>
	<!--  <div class="main">
		<div class="top">
        	<iframe name="top" style="border-top:0px;" width="100%" height="100%" src="media/top.jsp"></iframe>
    	</div>
    	<div class="left">
				<iframe name="left" width="100%" height="100%" src="media/left.jsp"></iframe>
			</div>
		<div class="bottom">
			<div class="right">
				<iframe name="right" width="100%" height="100%" src="" ></iframe>
			</div>
		</div>
	</div>-->
</body>
</html>