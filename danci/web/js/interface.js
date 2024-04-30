
/***
预约
**/

function addOrder(){

	

//	alert("预约功能暂不开放，敬请期待！");
//	return false;
	var expertId1=$("#expertId1").val();
	var expertId2=$("#expertId2").val();
	var expertId3=$("#expertId3").val();
	var remark=$("#remark").val();
	var expertName1=$('#expertId1 option:selected').text();
	var expertName2=$('#expertId2 option:selected').text();
	var expertName3=$('#expertId3 option:selected').text();
	var eopenId=$('#expertId1 option:selected').attr("openId");
	var sex=$("#sex img[src='weixin/images/sex1.png']").attr("sex");
	var birthday=$("#birthday").val();
	var height=$("#height").val();
	var weight=$("#weight").val();
	var occapation=$("#occapation").val();
	var admissionTime=$("#admissionTime").val();
	var chiefComplaint=$("#chiefComplaint").val();
	var illPrenent=$("#illPrenent").val();
	var illPost=$("#illPost").val();
	var illPersonal=$("#illPersonal").val();
	var illFamily=$("#illFamily").val();
	var allergyHistory=$("#allergyHistory").val();
	var admissionCheck=$("#admissionCheck").val();
	var labCheck=$("#labCheck").val();
	var imgCheck=$("#imgCheck").val();
	var otherCheck=$("#otherCheck").val();
	var preDiagnosis=$("#preDiagnosis").val();
	var treatPlan=$("#treatPlan").val();
	var illSerious=$("#illSerious").val();
//	var admissionCheckId=$("#admi").val();
//	var labCheckId=
//	var imgCheckId=
//	var otherCheckId=
//	var expertOpenId="";
	var expertName="";
	if(expertId1 != "0"){
		expertOpenId=expertId1+",";
		expertName=expertName1+",";
	}
	if(expertId2 != "0"){
		expertOpenId+=expertId2+",";
		expertName+=expertName2+",";
	}
	if(expertId3 != "0"){
		expertOpenId+=expertId3+",";
		expertName+=expertName3+",";
	}
				
	expertOpenId=expertOpenId.substring(0,expertOpenId.length-1);
	if(expertOpenId == ""){
		alert("请选择专家！");
		return false;
	}
	if((expertName1==expertName2 && expertId1 !=0 && expertId2 !=0) ||
		(expertName1==expertName3 && expertId1 !=0 && expertId3 !=0) || 
		(expertName2==expertName3 && expertId2 !=0 && expertId3 !=0)){
		alert("专家有重复,请另选专家");
		return false;
	}
	if(remark==""){
		alert("请描述病情");
		return false;
	}

	expertName=expertName.substring(0,expertName.length-1);
	var add=$.Params.create();
	add.addParams("expertOpenid",expertOpenId);
	add.addParams("memberOpenid",memberId);
	add.addParams("publicNo",brandId); 
	add.addParams("sex",sex);
	add.addParams("birthday",birthday);
	add.addParams("height",height);
	add.addParams("weight",weight);
	add.addParams("occaption",occapation);
	add.addParams("admissionTime",admissionTime);
	add.addParams("chiefComplaint",chiefComplaint);
	add.addParams("illPrenent",illPrenent);
	add.addParams("illPost",illPost);
	add.addParams("illPersonal",illPersonal);
	add.addParams("illFamily",illFamily);
	add.addParams("allergyHistory",allergyHistory);
	add.addParams("admissionCheck",admissionCheck);
	add.addParams("labCheck",labCheck);
	add.addParams("imgCheck",imgCheck);
	add.addParams("otherCheck",otherCheck);
	add.addParams("preDiagnosis",preDiagnosis);
	add.addParams("treatPlan",treatPlan);
	add.addParams("illSerious",illSerious);
	add.addParams("filed1",expertName); 
	add.addParams("eopenId",eopenId);
	add.addParams("expertName1",expertName1);
	add.addParams("filed4",place); 
	add.ajax(getIP()+"memberOrder.action?add",function(data){
		alert(data.msg);
		if(place == "1"){
			window.location.href="weixin/hy/memberOrderList.jsp?&memberId";
		}
    })
}

/***取消报名***/
function cancleup(cid,eid,obj){
	if($(obj).html=="报名"){
		return false;
	}
	if(confirm("确定要取消吗？")){
		$(obj).html("<font color='#5BA2FF'>报名</font>");
		var update=$.Params.create();
		update.addParams("courseId",cid); 
		update.addParams("memberId",memberId); 
		update.addParams("filed1","1"); 
		update.ajax(getIP()+"logcsign.action?deleteByelements",function(data){
			if(data.success== false){
				alert(data.msg);
			}
			getData();
		})
	}
}

/*****取消收藏******/
function canclecollect(cid,eid,col){
	if(confirm("确定要取消收藏吗？")){
		$(col).attr("src","weixin/images/demand_collect0.png");
		var update=$.Params.create();
		update.addParams("courseId",cid);  
		update.addParams("memberId",memberId); 
		update.addParams("filed1","1"); 
		update.ajax(getIP()+"logcollect.action?deleteByelements",function(data){
			if(data.success== false){
				alert(data.msg);
			}
			getData();
		})
	}
	
}

/*******点播课程记录**********/
function addDbEvent(cid,eid){
	var add=$.Params.create();
	add.addParams("courseId",cid); 
	add.addParams("expertId",eid); 
	add.addParams("memberId",memberId); 
	add.addParams("filed4",place); 
	add.ajax(getIP()+"logRequests.action?add",function(data){
		getData();
	})
}

function addInformation(eid){
	var add=$.Params.create();
	add.addParams("informationId",eid);
	add.addParams("memberId",memberId);
	add.ajax(getIP()+"loginformationread.action?add",function(data){
		
	})
}
