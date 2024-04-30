
$(function(){
	if(brandId=="2"){
		$("#4sdian").hide();
		$("#s4sindex").show();
        var selBrand = $.Params.create();
        selBrand.ajax("customer.action?selectBrandInfo",function(data){
            var html = "";
            $.each(data.rows,function(i,val){
                html+="<div class='span3'>"+
                    "<a class='btn red'>"+
                    "<span>"+val.aboutName+"</span>"+
                    "<em>粉丝总数："+val.allCustomer+"</em>"+
                "<em>今日新增："+val.newCustomer+"</em>"+
                "</a>"+
                "</div>"
            })
            $("#brandList").html(html);
        },function(){

        });
	}else{
		$("#s4sindex").hide();
		//获取粉丝数量并赋值
	    $.ajax("customer.action?list&brandId="+brandId+"&customerType=0&attentionState=1").done(function(data){
	       $("#fansNumber").html(data.total);

	    }).fail(function(data){

	    });
		//获取潜客数量并赋值
	    $.ajax("customer.action?list&brandId="+brandId+"&customerType=1&attentionState=1").done(function(data){
	        $("#qkNumber").html(data.total);
	 
	     }).fail(function(data){

	     });
	    //获取车主数量并赋值
	    $.ajax("customer.action?list&brandId="+brandId+"&customerType=2&attentionState=1").done(function(data){
	        $("#carNumber").html(data.total);

	     }).fail(function(data){

	     });
	    //获取预约数量并赋值
	    $.ajax("appointmentRecord.action?getAppointmentbydate&brandId="+brandId).done(function(data){
	    	//alert(data);
	        $("#yuyue").html(data+"人");
	       // alert(data.total);
	     }).fail(function(data){

	     });

	    //获取点评数量并赋值
	    $.ajax("appointmentRecord.action?getAppointmentbydateps&brandId="+brandId).done(function(data){
	        $("#dianping").html(data+"人");
	      //  alert(data.total);
	     }).fail(function(data){

	     });
	    //获取询价数量并赋值
	    $.ajax("statisticsInquiry.action?getcountbydate&brandId="+brandId).done(function(data){
	        $("#xunjia").html(data+"人");
	      //  alert(data.total);
	     }).fail(function(data){

	     });
	    
		$.ajax(getIP()+"notice.action?list").done(function(data){
	        var html="";
	        $.each(data.rows,function(i,val){	
	        	if(i==9){
	        		return false;
	        	}
	        	if(val.noticeStatus==1){
	        		var ncontent =val.noticeContent+"<span class='label label-warning'>新</span>";
	        	}else if(val.noticeStatus==2){
	        		var ncontent =val.noticeContent;
	        	}
	        	
	            html +="<li>" +ncontent+"" +            
	                "<span style='float:right;'>"+val.createTime+"</span></tr>";
	        });
	        $("#gonggao").html(html);
	    }).fail(function(data){
	        alert("数据加载失败！");
	    });
		$("#4sdian").show();
	}
})