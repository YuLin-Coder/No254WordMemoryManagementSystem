<%@page language="java" pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
 <script type="text/javascript" src="<%=basePath%>media/dingdan/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>media/dingdan/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>media/dingdan/js/ian.js"></script> 

<script type="text/javascript" src="<%=basePath%>media/dingdan/js/getIp.js"></script>
 
 	<script type="text/javascript" src="<%=basePath%>media/dingdan/js/dingdanModify.js"></script>
  
  <link rel="stylesheet" href="<%=basePath%>media/dingdan/css/bootstrap.min.css" type="text/css"></link>
 <link rel="stylesheet" href="<%=basePath%>media/dingdan/css/bootstrap.min.css" type="text/css"></link>
 
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
 	<script language="javascript">
 	var a10s='支付宝';
    function aab(a) {
         
        if(a==1)
        { 
         document.getElementById("m1").className = "on";
         document.getElementById("m2").className = "off";
         a10s='支付宝';
         
        }
        else if(a==2)
        {
          document.getElementById("m1").className = "off";
          document.getElementById("m2").className = "on";
         a10s='微信';
        }
         
       
        }
        
  $(function(){
  
   var myDate = new Date();
    
   $('#a11').val(myDate.toLocaleString());
  
  })
  
  
</script>
 
 	<style type="text/css">
.on{margin:5px;border:3px solid #F60;filter:alpha(Opacity=100)}
.off{margin:5px;border:1px solid #CCC;filter:alpha(Opacity=20)}

 	</style>
 	
 </head>
<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section" >&nbsp;&nbsp;&nbsp;<font style="font-weight:bold">支付</font></h3>
	<div class="row-fluid">
		<div class="span12">
	 <div class="portlet-body list mbModify">
		<div class="form-horizontal m-t">
		<input type="hidden"  id="id"  name="id" />
             <div class="control-group">
                <label class="control-label">菜品名：</label>
                <div class="controls">
                    <input id="a1" name="a1" minlength="2" disabled="disabled" type="text" class="form-control" required="" aria-required="true">
              <input type="hidden" value="" id="a8">
              
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">金额：</label>
                <div class="controls">
                    <input id="a6" name="a6" disabled="disabled" minlength="2" placeholder="请输入金额" type="text" class="form-control" required="" aria-required="true">&nbsp;元
                </div>
            </div>
            
            <div class="control-group">
             <div id="ZFMethods">
               <a  onclick="aab(1)"><img border="0" id="m1" src="<%=basePath%>media/images/zfb.jpg"  name="01" class="on"></a>
                <a  onclick="aab(2)"><img id="m2" border="0" src="<%=basePath%>media/images/wx.jpg"  name="02"  class="off"></a>
                 </div> 
            </div>
            
            <div class="control-group">
                <label class="control-label">支付时间：</label>
                <div class="controls">
                    <input id="a11" name="a11" minlength="2" placeholder="请输入金额" type="text" class="form-control" required="" aria-required="true">
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