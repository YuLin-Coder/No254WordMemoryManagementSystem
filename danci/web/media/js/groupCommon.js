$(function () {
    var brandId = window.brandId;
    //车系数据填充
    $.ajax(getIP() + "carSeries.action?list&parentId=" + brandId + "&timestamp=" + new Date().getTime()).done(function (data) {
        var html = "<option value='' selected='selected'>根据车系筛选</option>";
        $.each(data.rows, function (i, val) {

            html += "<option value='" + val.pkid + "'>" + val.seriesName + "</option>";
        });
        $("#carSeriesOption").html(html);
        $("#carSeriesOption1").html(html);
    }).fail(function (data) {
        alert("数据加载失败！");
    });

    //销售顾问数据填充
    $.ajax(getIP() + "customer.action?list&customerType=3&brandId=" + brandId + "&timestamp=" + new Date().getTime()).done(function (data) {
        var html = "<option value='' selected='selected'>根据顾问筛选</option>";

        $.each(data.rows, function (i, val) {
            var customerName = val.customerName;
            if(customerName == null || customerName == ""){
                customerName = val.nickName;
            }else{
                customerName = val.customerName;
            }
            html += "<option value='" + val.customerId + "'>" +     customerName + "</option>";
        });
        $("#sqguwen").html(html);
        $("#sqguwen1").html(html);
    }).fail(function (data) {
        alert("数据加载失败！");
    });
    //售后顾问数据填充
    $.ajax(getIP() + "customer.action?list&customerType=4&brandId=" + brandId + "&timestamp=" + new Date().getTime()).done(function (data) {
        var html = "<option value='' selected='selected'>根据顾问筛选</option>";

        $.each(data.rows, function (i, val) {
            var customerName = val.customerName;
            if(customerName == null || customerName == ""){
                customerName = val.nickName;
            }else{
                customerName = val.customerName;
            }
            html += "<option value='" + val.customerId + "'>" + customerName + "</option>";
        });
        $("#shguwen").html(html);
        $("#shguwen1").html(html);
    }).fail(function (data) {
        alert("数据加载失败！");
    });

    //根据选择的车系列出车型
    $("#carSeriesOption").change(function () {
        var option = $("#carSeriesOption").val();
        $.ajax(getIP() + "carModels.action?list&seriesId=" + option + "&brandId=" + brandId + "&timestamp=" + new Date().getTime()).done(function (data) {
            var html = "<option value='' selected='selected'>选择车型</option>";
            $.each(data.rows, function (i, val) {
                html += "<option value='" + val.pkid + "'>" + val.modelsName + "</option>";
            });
            $("#carModelsOption").html(html);
        }).fail(function (data) {
            alert("数据加载失败！");
        });
    })

    //根据选择的车系列出车型
    $("#carSeriesOption1").change(function () {
        var option = $("#carSeriesOption1").val();
        $.ajax(getIP() + "carModels.action?list&seriesId=" + option + "&brandId=" + brandId + "&timestamp=" + new Date().getTime()).done(function (data) {
            var html = "<option value='' selected='selected'>选择车型</option>";
            $.each(data.rows, function (i, val) {
                html += "<option value='" + val.pkid + "'>" + val.modelsName + "</option>";
            });
            $("#carModelsOption1").html(html);
        }).fail(function (data) {
            alert("数据加载失败！");
        });
    })

    $("#selectButton").live('click', function () {
        //var carModelId=$("#carSeriesOption").val();
        var wantCarseriesId = $("#carSeriesOption").val();
        var wantCarModelId = $("#carModelsOption").val();
        var customerType = $("#type").val();
        var sendCustomer = $("#shguwen").val();
        var salesConsultant = $("#sqguwen").val();
        if(sendCustomer == undefined){
            sendCustomer = "";
        }else if(salesConsultant == undefined){
            salesConsultant = "";
        }
        window.selectCustomer.termSerect("", wantCarModelId, wantCarseriesId, customerType, "", salesConsultant,"","","","","","","");
    })




    $("#selectButton1").live('click', function () {
        //var carModelId=$("#carSeriesOption").val();
        var wantCarseriesId = $("#carSeriesOption1").val();
        var wantCarModelId = $("#carModelsOption1").val();
        var customerType = $("#type1").val();
        var sendCustomer = $("#shguwen1").val();
        var salesConsultant = $("#sqguwen1").val();
        if(sendCustomer == undefined){
            sendCustomer = "";
        }else if(salesConsultant == undefined){
            salesConsultant = "";
        }
        window.selectCustomer.termSerect("", wantCarModelId, wantCarseriesId, customerType, "", salesConsultant,"","","","","","","");
    })


    window.selectCus = {
        term:function (carModelId, wantCarModelId, wantCarseriesId, customerType, sendCustomer, salesConsultant,tel,customerName,nickName,gender,startTime,endTime,city,listener) {
    var cus =$.Params.create();
    cus.addParams("brandId",brandId);
    cus.addParams("sendCustomer",sendCustomer);
    cus.addParams("customerType",customerType);
    cus.addParams("wantCarModelId",wantCarModelId);
    cus.addParams("wantCarSeriesId",wantCarseriesId);
    cus.addParams("carModelId",carModelId);
    cus.addParams("salesConsultant",salesConsultant);
    cus.addParams("timestamp",new Date().getTime());
    cus.addParams("tel",tel);
    cus.addParams("customerName",customerName);
    cus.addParams("nickName",nickName);
    cus.addParams("gender",gender);
    cus.addParams("startTime",startTime);
    cus.addParams("endTime",endTime);
    cus.addParams("city",city);
    cus.ajax("customer.action?list",function(data){
        listener(data);
        /*var html1 = "";
         $.each(data.rows,function(i,val){
         var gender = val.gender;
         var sex;
         var name;
         if (gender == 0) {
         sex = "未知";
         } else if (gender == 1) {
         sex = "男";
         } else if (gender == 2) {
         sex = "女";
         }
         if (val.customerName == null || val.customerName == "") {
         name = "未填写";
         } else {
         name = val.customerName;
         }
         html1 += "<tr><td><img style='width:40px;height: 40px;' src=" + val.customerImage + "></td>" +
         "<td>" + val.nickName + "</td>" +
         "<td>" + name + "</td>" +
         "<td>" + sex + "</td>" +
         "<td>" + val.city + "</td>" +
         "<td>" + val.tel + "</td>" +
         "<td>" + val.createTime + "</td>" +
         "</tr>";
         });
         var count = data.rows.length;
         $("#count").text(count);
         $('#qkList').html(html1);*/
    },function(){
        alert("失败");
    });
}
    }

//    terSerect("","",function(data1){
//        alert(data1);
//    });


    window.selectCustomer = {
        termSerect: function (carModelId, wantCarModelId, wantCarseriesId, customerType, sendCustomer, salesConsultant) {
            $.ajax(getIP() + "customer.action?selectByOrders&sendCustomer=" + sendCustomer + "&customerType=" + customerType + "&wantCarModelId=" + wantCarModelId + "&wantCarseriesId=" + wantCarseriesId + "&carModelId=" + carModelId + "&salesConsultant=" + salesConsultant + "&brandId=" + brandId + "&timestamp=" + new Date().getTime()).done(function (data) {
                var html1 = "";
                $.each(data.rows, function (i, val) {
                    var gender = val.gender;
                    var sex;
                    var name;
                    if (gender == 2) {
                        sex = "女";
                    } else if (gender == 1) {
                        sex = "男";
                    } else {
                        sex = "未知";
                    }
                    if (val.customerName == null || val.customerName == "") {
                        name = "未填写";
                    } else {
                        name = val.customerName;
                    }
                    html1 += "<tr><td><img style='width:40px;height: 40px;' src=" + val.customerImage + "></td>" +
                        "<td>" + val.nickName + "</td>" +
                        "<td>" + name + "</td>" +
                        "<td>" + sex + "</td>" +
                        "<td>" + val.city + "</td>" +
                        "<td>" + val.tel + "</td>" +
                        "<td>" + val.createTime + "</td>" +
                        "</tr>";
                })
                var count = data.rows.length;
                $("#count1").text(count);
                $("#cusotmerList").html(html1);
            });
        }
    }




});