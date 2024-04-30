
var sel=$.Params.create();
//初始化ztree
var setting = {
		view: {
			addHoverDom: addHoverDom,//点击事件
			removeHoverDom: removeHoverDom,//移除事件
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,//显示移除按钮
			showRenameBtn: showRenameBtn//显示编辑按钮
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename,
			onClick: zTreeOnClick
		}
	};
	
	var zNodes ;
	var  str="";
	var getTree=$.Params.create();
    //通过ajax获取菜单表里的数据并遍历处理
	getTree.ajax(getIP()+"powerMenu.action?list",function(data){
		$.each(data.rows,function(i,val){
			if(i==0){
				str+="{id:'"+val.id+"',pId:'"+val.parentId+"',name:'"+val.menuName+"',open:true}"
			}else
			{
				str+=",{id:'"+val.id+"',pId:'"+val.parentId+"',name:'"+val.menuName+"',open:true}"
			}	
		})
	  zNodes = eval("[{id:'0',pId:'100',name:'菜单管理',open:true}," + str + "]");
		initTree();
	})
	/**
	 * 重新刷新树数据
	 * @return
	 */
	function initTreeNode()
	{
		var zNodes ="";
		var  str="";
		var getTreeInit=$.Params.create();
	    //通过ajax获取菜单表里的数据并遍历处理
		getTreeInit.ajax(getIP()+"powerMenu.action?list",function(data){
			$.each(data.rows,function(i,val){
				if(i==0){
					str+="{id:'"+val.id+"',pId:'"+val.parentId+"',name:'"+val.menuName+"',open:true}"
				}else
				{
					str+=",{id:'"+val.id+"',pId:'"+val.parentId+"',name:'"+val.menuName+"',open:true}"
				}	
			})
		  zNodes = eval("[{id:'0',pId:'100',name:'菜单管理',open:true}," + str + "]");
		  initTree();
		})	
	}
	
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		//return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	//删除节点调用事件
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var deleteSel=$.Params.create();
		deleteSel.addParams("id",treeNode.id);
		deleteSel.ajax(getIP()+"powerMenu.action?deleteTree",function(data){
		alert(data.msg);
		}) ;
	}
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	//编辑后调用事件
	function onRename(e, treeId, treeNode, isCancel) {
		 showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
//	     alert(treeNode.getParentNode().id); 
		 var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		  var nodes = treeObj.getNodes();
		  sel.addParams("nodes",JSON.stringify(nodes));
		  sel.addParams("treeNodeName",treeNode.name);
		  sel.addParams("id",treeNode.id);
		  sel.addParams("parentId",treeNode.getParentNode().id);
		  sel.ajax(getIP()+"powerMenu.action?save",function(data){
 		  alert(data.msg);
 		  window.location.reload();//刷新页面
 		 // setTimeout("initTreeNode()",8000);
     	})
	}
	function showRemoveBtn(treeId, treeNode) {
		if(treeNode.id==0)
		{
		return	false;
		}else
		{
			return true;
		}
		//return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		if(treeNode.id==0)
		{
		return	false;
		}else
		{
			return true;
		}
		//return !treeNode.isLastNode;
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		//alert(treeNode.level);//显示树的层级属性
//		if(treeNode.level!=2)
//		{
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {id:null, pId:treeNode.id, name:"new node" + (newCount++)});
			return false;
		});
//		}
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	// 初始化ztree
	function initTree()
	{
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#selectAll").bind("click", selectAll);
	}
	/**
	 *  点击节点的事件
	 * @param event
	 * @param treeId
	 * @param treeNode
	 * @return
	 */
	function zTreeOnClick(event, treeId, treeNode) {
//	    alert(treeNode.id + ", " + treeNode.name);
	    var selTree=$.Params.create();
	    selTree.addParams("treeId",treeNode.id);
	    selTree.ajax(getIP()+"powerMenu.action?findByTreeId",function(data){
	    	var len=data.list.length;
	    	if(len>0)
	    	{
	    		$.each(data.list,function(i,val){
	    			$("#treeUrl").val(val.url);
	    			if(val.icon!=null && val.icon!="")
	    			{
	    				$("#img").attr("src",getIP()+val.icon)	
	    				$("#img").show();
	    			}else
	    			{
	    				$("#img").hide();
	    			}
		    	})	
	    	}
      	})
	    $("#treeid").val(treeNode.id);
	    
	};
	
	$(document).ready(function(){
		
		$(document).ui_loading({
			message:"我在加载..."
		})
		 
		
	/**
	 * 保存事件
	 */ 
	  $("#save").on("click",function(){
		  
		  var fileName=$("#fileName").val();
		  var url = $("#treeUrl").val();
		  var treeId= $("#treeid").val();
			 if(fileName=="")
			 {
			 alert("请选择上传的文件！");
			 return;
			 }
		  var file = $('#fileName').val();
		// 检查是否已选择上传文件
		　　if (file != '') {
		　　　　var filename = file.replace(/.*(\/|\\)/, '');
		　　　　var fileext = (/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
		　　　　// 检查文件格式
		　　　　if (fileext == 'png') {
		　　　　　　// 上传excel文件
		           $("#loading").show();
			　　　　　　$.ajaxFileUpload({
			　　　　　　　　url: "powerMenu.action?updateByTreeId&treeId="+treeId+"&url="+url,
			　　　　　　　　secureuri: false,
			　　　　　　　　dataType: "text",
			　　　　　　　　fileElementId: 'fileName',
			　　　　　　　　success: function(data){       // data是后台返回过来的在上传并保持excel内容过程中的用户提示信息
							},
			　　　　　    　});
			　　　　}
			　　　　else {
			　　　　　　alert('文件格式必须是*.png');
			　　　　}
			　　} 
		
//		  var selTree=$.Params.create();
//		  selTree.addParams("treeId",treeId);
//		  selTree.addParams("url",url);
//		  selTree.ajax(getIP()+"powerMenu.action?updateByTreeId",function(data){
//      		alert(data.msg);
//      	})
	    
	  });
	})
