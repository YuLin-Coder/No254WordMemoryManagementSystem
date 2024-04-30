<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html lang="en">
<head>
	<meta charset="utf-8" />
 	<jsp:include page="../media.jsp"></jsp:include>
 	 	 	<link rel="stylesheet" type="text/css" href="media/css/bootstrap-cerulean.css" />
 	<script type="text/javascript" src="media/js/pageMedia.js"></script>
 	<script type="text/javascript" src="media/locatResource/js/locatList.js"></script>
   <link rel="stylesheet" type="text/css" href="media/loadingCss/reset.css" />
 	<link rel="stylesheet" type="text/css" href="media/loadingCss/style.css" />
 </head>

<body class="page-header-fixed" >
<div class="tab-content contents"> 
	<h3 class="form-section">&nbsp;&nbsp;&nbsp;<font style="font-weight:bold" >嗨WEWORK > 嗨位置</font></h3>
	<div class="row-fluid">
		<div class="span12">
		 	<div class="btn-group listsearch horizontal">
		 <input id="name" class="m-wrap span12 inputsearch" type="text" placeholder="请输入位置名称" />
	            <span id="searchList" class="btn btn-success">查询</span> 
	     </div> 
	     <div class="btn-group pull-right listsearch">
	        <mui-btn  id="addbutton" class="mui-btn mui-btn--fab mui-btn--primary mui-btn--small">+</mui-btn >
	      <mui-btn  id="delbutton" class="mui-btn mui-btn--fab mui-btn--primary mui-btn--small">-</mui-btn >
		</div>
<div class="l-wrapper" id="loading">
  <svg viewBox="0 0 120 120" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
      <g id="circle" class="g-circles g-circles--v2">
          <circle id="12" transform="translate(35, 16.698730) rotate(-30) translate(-35, -16.698730) " cx="35" cy="16.6987298" r="10"></circle>
          <circle id="11" transform="translate(16.698730, 35) rotate(-60) translate(-16.698730, -35) " cx="16.6987298" cy="35" r="10"></circle>
          <circle id="10" transform="translate(10, 60) rotate(-90) translate(-10, -60) " cx="10" cy="60" r="10"></circle>
          <circle id="9" transform="translate(16.698730, 85) rotate(-120) translate(-16.698730, -85) " cx="16.6987298" cy="85" r="10"></circle>
          <circle id="8" transform="translate(35, 103.301270) rotate(-150) translate(-35, -103.301270) " cx="35" cy="103.30127" r="10"></circle>
          <circle id="7" cx="60" cy="110" r="10"></circle>
          <circle id="6" transform="translate(85, 103.301270) rotate(-30) translate(-85, -103.301270) " cx="85" cy="103.30127" r="10"></circle>
          <circle id="5" transform="translate(103.301270, 85) rotate(-60) translate(-103.301270, -85) " cx="103.30127" cy="85" r="10"></circle>
          <circle id="4" transform="translate(110, 60) rotate(-90) translate(-110, -60) " cx="110" cy="60" r="10"></circle>
          <circle id="3" transform="translate(103.301270, 35) rotate(-120) translate(-103.301270, -35) " cx="103.30127" cy="35" r="10"></circle>
          <circle id="2" transform="translate(85, 16.698730) rotate(-150) translate(-85, -16.698730) " cx="85" cy="16.6987298" r="10"></circle>
          <circle id="1" cx="60" cy="10" r="10"></circle>
      </g>
  </svg>
</div>
	 <div class="portlet-body list">
		<table class="table table-striped tableshort" id="listtable">
	           <thead>
	               <tr>
	                   <th style="width:5%;"><input type="checkbox"  id="queryCheckbox"  name="queryCheckbox" value=""></th>
	                   <th style="width:8%;">位置名称</th>
	                   <th style="width:8%;">缩略图</th>
	                    <th style="width:8%;">地址</th>
	                    <th style="width:8%;">联系电话</th>
	                   <th style="width:15%;">操作</th>
	               </tr>
	           </thead>
				<tbody id="qkList">
				<tr>
					<td colspan="10">暂无数据</td>
				</tr>
				</tbody>
	
	       </table>
	      <div class="pagination" id="pagination">
	                    <div class="float_right padding5 paging">
	                        <div id="divFood" class="float_left padding_top4">
	                        <span float="left">每页<font id="rowscount">0</font>条/共<font id="allcount">0</font>条 第<font id="nowpage">0</font>页/共<font id="allpage">0</font>页</span>
	                        <span onclick="firstPage()" float="left" class="pagefl"><a>首页</a></span>
	                        <span onclick="prePage()" float="left" class="pagefl"><a>上一页</a></span>
	                        <span onclick="nextPage()" float="left" class="pagefl"><a>下一页</a></span>
	                        <span onclick="lastPage()" float="left" class="pagefl"><a>末页</a></span>
	                       <!--  <span display="" float="left" class="pagefl" style="margin-right:10px;">跳转到第 <input style="width:20px;height:15px;margin-top:6px;" type="text" /> 页</span> -->
	                       <span display="" float="left" class="pagefl">跳转到第 
	                       		<select onchange="jumpPage(this)">
	                       			<option selected="selected" value="0">1</option>
	                       		</select> 页</span>
	                       
	                        </div>
	                    </div>
	                    <div class="clear"></div>
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