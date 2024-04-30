
var app = getApp();
var WxParse = require('../../wxParse/wxParse.js');

Page({

data:{
  content: '',
  pingjiaren:'',
  songcanyuan:'',
 
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
 
       
       //设置评价人名称
      this.setData({
        pingjiaren: wx.getStorageSync('username')
     
      })

      console.log('11111111111111111111111  ' + wx.getStorageSync('username'));


        //this.setData({
        //    title: options.id
        //})
        // 页面初始化 options为页面跳转所带来的参数
        //弹幕  
        var that = this;
       that.detailstid = options.id;
        wx.request({
          url: getApp().globalData.url+"yuangong.action?list&rows=10&page=0",
            data: {
                id:  options.id,
               
            },
            header: {
                'content-type': 'application/json'
            },
            method: 'GET',
            success: function (res) {
              //console.log("内容是："+res.data.rows[0]["a2"]);
                //console.log(that.data.danmuList);
                //var danmuList = util.loadXMLStr(res.data);
                //console.log(danmuList);
                that.setData({
                  songcanyuan: res.data.rows[0]["a1"],
                  content: '',//res.data.rows[0]["a2"],
                  //shijian: res.data.rows[0]["a3"]
                })
               // console.log(that.data.danmuList);
                var temp = WxParse.wxParse('article', 'html', that.data.content, that, 5);
                //that.setData({
                //  article: temp
               // })
             
            }
        })
 
      


        
    },   
  pingjiaclick: function (e) {
    var that = this;

    console.log('数据是： ' + that.detailstid + ' daaaaaaaaaaaaa  ' + this.data.content);
    wx.request({
      url: getApp().globalData.url + "pingjia.action?add",
      data: {
        a1: this.data.pingjiaren,
        a2:this.data.songcanyuan,
        a3: this.data.content
      },
      method: 'POST',//post提交保证中文不乱码
      header: { 
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
      },
      success: function (res) {
        // that.setData({ remenData: res.data.Data });
        console.log('数据是： ' + res.data.Data);

        wx.showToast({
          title: '评价成功',
          //image: '../image/hy_bt2.png',
          duration: 2000
        })
      }
    }) 

  },  
  //获取评价人信息
  dingcanreninput: function (e) {
    console.log(e.detail.value);
    this.setData({
      pingjiaren: e.detail.value
    })
  },
  //获取送餐人信息
  songcanyuaninput: function (e) {
     
    this.setData({
      songcanyuan: e.detail.value
    })

  },
  //获取内容信息
  neironginput: function (e) {

console.log(e.detail.value);
    this.setData({
      content: e.detail.value
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