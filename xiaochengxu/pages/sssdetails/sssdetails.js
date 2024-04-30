
var app = getApp();
var WxParse = require('../../wxParse/wxParse.js');

Page({

data:{
  content: '<div style="text-align:center;">《静夜思》· 李白<br />床前明月光，<br />疑是地上霜。 <br />举头望明月， <br />低头思故乡。<br /><img src="http://www.xiexingcun.com/Poetry/6/images/53e.jpg" alt="" /><br /><img src="http://www.xiexingcun.com/Poetry/6/images/53.jpg" alt="" /><br /><br /><img src="http://www.xiexingcun.com/Poetry/6/images/53b.jpg" alt="" /><br />div>',
 

},

    onLoad: function (options) {

      

      if (options != null) {
        
        console.log("optionsid:" + options.id);

        wx.setStorage({
          key: 'fxid',
          data: options.id,
        });


      }





        //this.setData({
        //    title: options.id
        //})
        // 页面初始化 options为页面跳转所带来的参数
        //弹幕  
        var that = this;
        wx.request({
          url: "http://tomcat2.wicp.vip/xiaoyuan/vxinwen.action?list&rows=10&page=0",
            data: {
                id:  options.id,
               
            },
            header: {
                'content-type': 'application/json'
            },
            method: 'GET',
            success: function (res) {
              console.log("内容是："+res.data.Data[0]["a2"]);
                //console.log(that.data.danmuList);
                //var danmuList = util.loadXMLStr(res.data);
                //console.log(danmuList);
                that.setData({
                    title: res.data.rows[0]["a1"],
                  content: res.data.rows[0]["a2"]
                })
                console.log(that.data.danmuList);
                var temp = WxParse.wxParse('article', 'html', that.data.content, that, 5);
                that.setData({
                  article: temp
                })
             
            }
        })
 
      


        
    },
    onReady: function () {
        // 页面渲染完成
    },
    onShow: function () {
        // 页面显示
    },
    onHide: function () {
        // 页面隐藏
    },
    onUnload: function () {
        // 页面关闭
    }
   ,
  onShareAppMessage: function () {
    var userName = wx.getStorageSync('bi_openid');


    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '自定义转发标题',
      path: '/pages/index/index?fxid=' + userName,
      success: function (res) {
        // 转发成功
        console.log(888888);
      },
      fail: function (res) {
        // 转发失败
      }
    }




  }
})