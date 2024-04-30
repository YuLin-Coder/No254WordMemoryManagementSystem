 $(function () {
            $("#return").on("click", function () {
                history.go(-1);

            });


            var listrole = $.Params.create();
            listrole.ajax(getIP() + "leixing.action?list", function (data) {
                if (data.success) {
                    var html = "<option>请选择类型</option>";
                    $.each(data.rows, function (i, val) {
                        html += "<option value='" + val.id + "'>" + val.a1 + "</option>";
                    })
                    $("#a2").html(html);
                }
            });

            var ue = UE.getEditor('contents');

            var eid = GetQueryString("eid");


            var p = $.Params.create();
            p.addParams("id", eid);
            p.ajax(getIP() + "shangpin.action?list", function (data) {
                var html = "";
                var val = data.rows[0];


                $("#a1").val(val.a1);
                $("#a2").val(val.a2);
             


                $("#id").val(eid);

                ue.addListener("ready", function () {
                    ue.setContent(val.a6);
                })
            })

        });


            function  adds(){
	
            	
            	if($('#a2').val()=='请选择类型')
        		{
        		
        		alert('请选择类型');
        		return;
        		}
         
            	
                var ue = UE.getEditor('contents');
 
                var a1 = $("#a1").val();
                var a2 = $("#a2").val();
                var a3 = '';
                var a4 ='';
	
                var a5 = $("#imgurl").val();
                var a6 = ue.getContent();  
                
                var a7 ='';
  	
                var id = $("#id").val();
                var edit=$.Params.create();
                var url=getIP()+"shangpin.action?update";
                edit.addParams("a1",a1); 
                edit.addParams("a2",a2); 
                edit.addParams("a3",a3); 
                edit.addParams("a4",a4); 
                edit.addParams("a6",a6); 
   		
                if(a5=="")
                {
			
                }
                else
                { 
                    edit.addParams("a5",a5);
                }
              
                
                edit.addParams("id",id);
                edit.ajax(url,function (data){
                    if(data.success){
                        alert("保存成功");
                        window.location.href=getIP()+"media/shangpin/shangpinList.jsp";
                    }
                })
            }
            function GetQueryString(name)
            {
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if(r!=null)return  unescape(r[2]); return null;
            }
   
