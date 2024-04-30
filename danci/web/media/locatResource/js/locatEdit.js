$(function(){
$("#return").on("click",function(){
	history.go(-1);
	});
	$("#add").on("click",function(){
		//var baseStr=$("#imageStr").val();//base64图片值
		var name=$("#name").val();
		var tel=$("#tel").val();
		var address=$("#address").val();
		
		if(name=="" || name.length==0)
		{
		alert("位置名称不许为空！");
		return;
		}
		if(tel=="" || tel.length==0)
		{
		alert("联系电话不许为空！");
		return;
		}
		if(address=="" || address.length==0)
		{
		alert("地址不许为空！");
		return;
		}
		 if($('#imageUrl').val() == ""){
            alert("必须选择一张图片");
            return;
        }
		$("#addform").attr("action",getIP()+"localResource.action?add");
		$("#addform").submit();
	});
	
	
	$('#file_upload').uploadify({
        'swf': 'media/locatResource/js/uploadify.swf',    //指定上传控件的主体文件
        'uploader': getIP()+'util/uploadFile.action',    //指定服务器端上传处理文件

        //其他配置项
        width: 108,                          // 按钮的宽度
        height: 32,                         // 按钮的高度
//        'buttonClass':'uploadBtn',
        'buttonText': "上传",                 // 按钮上的文字
        'buttonImage' : 'media/images/uploadBtn.png',
        'buttonCursor': 'hand',                // 按钮的鼠标图标

        'fileObjName': 'Filedata',            // 上传参数名称
        'fileSizeLimit' : '1MB',
        // 两个配套使用
        'fileTypeExts': "*.jpg;*.png",             // 扩展名
        'fileTypeDesc': "请选择 jpg png 文件",     // 文件说明

        'auto': true,                // 选择之后，自动开始上传
        'multi': false,               // 是否支持同时上传多个文件
        'onUploadSuccess': function (file, data, response) {
            var object = eval('(' + data + ')');
            $('#fileImage').attr('src', "data/material/" + object.imageUrl);

            $('#imageUrl').val(object.imageUrl);

            $('#thumbnailImage').attr('src', "data/material/" + object.imageUrl);
            $('#imageShowDiv').show();
            $('.editDiv').height($('.editDiv').height()+100);
        }
    });
    //图片上传成功之后 可以删除图片
    $('#deleteImage').on('click', function () {
        $('#imageShowDiv').hide();
        $('#thumbnailImage').attr('src', "media/images/newsDefault.jpg");
        $.ajax({
            type: "POST",
            url:getIP()+"util/deleteFile.action",
            data:{imageUrl:$('#imageUrl').val()},// 你的formid
            async:false,
            success: function(data) {
                if(data.success){
                    //删除成功做处理
                    console.info(data.msg);

                }else{
                    //删除失败 做处理
                }
            },error: function(data) {
                //做处理
            }
        });
        //删除隐藏域
        $('#imageUrl').val("");
        //
        $('#fileImage').attr('src', "");
    });
	
})
