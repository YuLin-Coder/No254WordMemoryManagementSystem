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
	
 <script type="text/javascript" src="<%=basePath%>media/yuangong/js/jquery-1.10.2.min.js"></script>

<script type="text/javascript" src="<%=basePath%>media/yuangong/js/ian.js"></script> 

<script type="text/javascript" src="<%=basePath%>media/yuangong/js/getIp.js"></script>
 
 	<script type="text/javascript" src="<%=basePath%>media/yuangong/js/yuangongModify.js"></script>
 	
 	<script type="text/javascript" src="<%=basePath%>media/media/js/uploadPhone.js"></script> 
<script type="text/javascript" src="<%=basePath%>media/js/ajaxfileupload.js"></script>	
<script src="<%=basePath%>media/ueditor/ueditor.config.js" type="text/javascript"></script>
<script src="<%=basePath%>media/ueditor/ueditor.all.min.js" type="text/javascript"></script>
<script src="<%=basePath%>media/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
<link href="<%=basePath%>media/css/uploadify.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>media/locatResource/js/swfobject.js" type="text/javascript"></script>
<script src="<%=basePath%>media/locatResource/js/jquery.uploadify.min.js" type="text/javascript"></script>
 	
 	
  
  <link rel="stylesheet" href="<%=basePath%>media/yuangong/css/bootstrap.min.css" type="text/css"></link>
 <link rel="stylesheet" href="<%=basePath%>media/yuangong/css/bootstrap.min.css" type="text/css"></link>
 
  	<style>
 		li{ list-style: none;}
 		.courseDbDetails{
 			width: 100%;
 			height: 100%;
 		}
 		.courseDbDetails_data li{
 			height: 40px;
 			line-height: 40px;
 			background-color:#CACAD2;
 			margin-bottom:10px;
 		}
 		/*字段名*/
 		.courseDbDetails_data .courseDbDetails_field{
 			width: 10%;
 			height: 100%;
 			padding-right:10px;
 			line-height:40px;
 			text-align:right;
 			float: left;
 			display: block;
 		}
 		/*字段值*/
 		.courseDbDetails_data .courseDbDetails_fieldValue{
 			width: 20%;
 			height: 100%;
 			text-align: left;
 			float:left;
 			display: block;
 		}
 	</style>
 </head>
<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section" >&nbsp;&nbsp;&nbsp;<font style="font-weight:bold">模范修改</font></h3>
	<div class="row-fluid">
		<div class="span12">
	 <div class="portlet-body list mbModify">
		<div class="form-horizontal m-t">
		<input type="hidden"  id="id"  name="id" />
               <div class="control-group">
                <label class="control-label">模范名称：</label>
                <div class="controls">
                    <input id="a1" name="a1" minlength="2" type="text" class="form-control" required="" aria-required="true">
                </div>
            </div>
             <div class="control-group">
                <label class="control-label">性别：</label>
                <div class="controls">
                    <select id="a2">
							<option value='男'>男</option>
						<option value='女'>女</option>
					</select>
                </div>
            </div>
            
             <div class="control-group">
                <label class="control-label">电话：</label>
                <div class="controls">
                    <input id="a3" name="a3" minlength="2" type="text" class="form-control" required="" aria-required="true">
                </div>
            </div>
             <div class="control-group">
                <label class="control-label">图片：</label>
                <div class="controls">
                <img  style="width: auto; height: auto;" id="thumbnailImage">
                <p><input id="imgurl" type="text"></p>
				<p> <input  id="photoFile" type="file" name="photoFile" />
         		<button  id="login"  onclick="submitPhoto()"  class="btn btn-primary">
         		<span class="icon-pencil" ></span>上传图片</button></p>
                </div>
            </div>
           
              
     	
            <div class="form-actions">
				<button id="add" onclick="adds()" type="button" class="btn blue">保存</button>
				<button id="return" type="button" class="btn blue">返回</button>
			</div>
         </div>
		</div>
		</div>
	</div>
</div>

</body>
</html>