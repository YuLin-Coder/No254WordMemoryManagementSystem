
var app = getApp();
var WxParse = require('../../wxParse/wxParse.js');

Page({

data:{
  content: '',
  urlcurrent: getApp().globalData.url,
  biaoti:'',
  neirong:'',
 detailstid:''
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
       that.detailstid = options.id;
        wx.request({
          url: getApp().globalData.url+"vshangpin.action?list&rows=10&page=0",
            data: {
                id:  options.id, 
            },
            header: {
               // 'content-type': 'application/json'
              "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
            },
            method: 'GET',
            success: function (res) {
             // console.log("内容是："+res.data.rows[0]["a6"]);
         
                that.setData({
                  title: res.data.rows[0]["a1"],
                 // content: res.data.rows[0]["a6"].replace("http://localhost:8080/caipin/", getApp().globalData.url),
                  content: res.data.rows[0]["a6"],
                   biaoti:res.data.rows[0]["a1"]
               
                })

              console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
               // console.log(that.data.danmuList);
              var temp = WxParse.wxParse('article', 'html', that.data.content, that, 5);
               // that.setData({
                //  article: 'temp'
               // })
             
            }
        })
  
    },
  shoucangClick: function (e) { 
    var that = this;

    console.log('userid' + wx.getStorageSync("userid") +"  neirong:::"+this.data.dianhua);
    wx.request({
      url: getApp().globalData.url +'shangpin.action?yuding', //仅为示例，并非真实的接口地址
      data: {
        a1:this.data.biaoti,
        a3: this.data.content,
        a7: wx.getStorageSync("userid") 
        
      },
      method: 'POST',//POST提交保证中文不乱码
      header: {
       // 'content-type': 'application/json' // 默认值
        'Content-Type': "application/x-www-form-urlencoded;charset=utf-8"
      },
      success: function (res) {
       // that.setData({ remenData: res.data.Data });
        console.log('数据是： ' + res.data.Data);

        wx.showToast({
          title: '收藏成功',
         // image: '../image/hy_bt2.png',
          duration: 2000
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