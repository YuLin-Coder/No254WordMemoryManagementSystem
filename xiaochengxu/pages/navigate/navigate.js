// pages/boy_shuju/boy_shuju.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    logs: [],
    date: '2016-11-08',
    array: [],
    index: 0,
    remenData: {},
    juese:''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var userName = wx.getStorageSync('username');
    console.log("name::::::::::::" + userName);
    if (userName) {
      this.setData({ username: userName });
    } 
    var JueSe = wx.getStorageSync('juese');

if(JueSe)
{
 
    this.setData({ juese: wx.getStorageSync('juese') });
}    
console.log("JueSe::::::::::::" + JueSe);
    this.setData({ avatar: wx.getStorageSync('headimg') });

    if (options != null) {
      console.log("options:" + options.fxid);


      wx.setStorage({
        key: 'fxid',
        data: options.fxid,
      });


    }

    var that = this;
    wx.request({
      url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=loadhaizibyuserid', //仅为示例，并非真实的接口地址
      data: {
        userid: userName
      },
      method: 'get',
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        //'Content-Type': 'application/json'  
      },
      success: function (res) {
        console.log('ddddddd:' + res.data);

        var bbb = [];
        bbb.push('请选择');
        for (let i = 0; i < res.data.Data.length; i++) {
          bbb.push(res.data.Data[i].a1);
        }
        that.setData({ array: bbb });

      }
    })
    wx.request({
      url: "http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=getuserinfo",
      data: {
        openid: wx.getStorageSync('bi_openid')
      },
      header: {
        'content-type': 'application/json'
      },
      method: 'GET',
      success: function (res) {
        that.setData({ avatar: res.data.avatar });
        console.log('sync_avatar: ' + res.data.avatar);

      }
    })   

    var nick_name = wx.getStorageSync('username');
    console.log("nick_name::::::::::::" + nick_name);
      this.setData({ nick_name: nick_name });

      wx.request({
        url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=loadzengzhang', //仅为示例，并非真实的接口地址
        data: {
          userid: userName,
          a1: 0
        },
        method: 'get',
        header: {
          "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
          //'Content-Type': 'application/json'  
        },
        success: function (res) {
          console.log('eeeeeeeeeee11:' + res.data);

          that.setData({ remenData: res.data.Data });
          that.setData({ a1: res.data.a1 });
          that.setData({ a2: res.data.a2 });
          that.setData({ a3: res.data.a3 });


        }
      })




  }, listenerPickerSelected: function (e) {
    //改变index值，通过setData()方法重绘界面

    var userName = wx.getStorageSync('bi_openid');
    if (userName) {
      this.setData({ userName: userName });
    }

    this.setData({
      index: e.detail.value
    });
    console.log('a11111111: ' + e.detail.value + ' userid: ' + userName);

    var that = this;


    if (e.detail.value == 0) {
    
      that.setData({ remenData: ""});
      that.setData({ a1: "" });
      that.setData({ a2: "" });
      that.setData({ a3: "" });
    
    }

    else
    {
 

    wx.request({
      url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=loadzengzhang', //仅为示例，并非真实的接口地址
      data: {
        userid: userName,
        a1: (e.detail.value == 0) ? 0 : e.detail.value - 1
      },
      method: 'get',
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        //'Content-Type': 'application/json'  
      },
      success: function (res) {
        console.log('eeeeeeeeeee11:' + res.data);

        that.setData({ remenData: res.data.Data });
        that.setData({ a1: res.data.a1 });
        that.setData({ a2: res.data.a2 });
        that.setData({ a3: res.data.a3 });
       
 
      }
    })
  }

  },

  sxrefsh: function (e) {
    wx.showToast({ title: '刷新成功', icon: 'success', duration: 1000 })

    var thatone = this;
    var userName = wx.getStorageSync('bi_openid');
    console.log("name::::::::::::" + userName);
    if (userName) {
      this.setData({ userName: userName });
    }

    //console.log("username1111111111111: "+userName);
    wx.request({
      url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=loadhaizibyuserid', //仅为示例，并非真实的接口地址
      data: {
        userid: userName
      },
      method: 'get',
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        //'Content-Type': 'application/json'  
      },
      success: function (res) {
        console.log('ddddddd:' + res.data);

        var bbb = [];
        bbb.push('请选择');
        for (let i = 0; i < res.data.Data.length; i++) {
          bbb.push(res.data.Data[i].a1);
        }
        thatone.setData({ array: bbb });

      }
    })


    return;
  },



  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
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