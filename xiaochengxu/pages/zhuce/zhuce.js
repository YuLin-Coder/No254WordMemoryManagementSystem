//logs.js
const util = require('../../utils/util.js')

Page({
    data: {
        logs: [],
        date: '2016-11-08',
        array: [],
        index: 0,
        time: '08:30',
        sexn:'',
        sexg:'',
        strendtime:'',
        username:'',
        password:'',
        userid:''
    },
    tapName: function (e) {
      console.log("22222222222222222222222222222"+e);


      this.setData({ msg: "Hello World" });

      console.log('单击', e.currentTarget.dataset.itemnum);
      var name = e.currentTarget.dataset.itemnum;

      console.log("name: " + name);
      wx.navigateTo({
        url: '/pages/addin/addin'//这是我建立的另一个界面，你们别忘了在app.json里面加路径哦

      })

      return;
    },
    sxrefsh: function (e) {
      wx.showToast({ title: '刷新成功', icon: 'success', duration: 1000 }) 

var thatone=this;
var userName = wx.getStorageSync('bi_openid');
console.log("name::::::::::::" + userName);
if (userName) {
  this.setData({ userName: userName });
}   
 

      return;
    }, 
    formSubmit: function (e) {
      console.log('form发生了submit事件，携带数据为：', e.detail.value); 
       
       /*
      if (this.data.sexn == "" && this.data.sexg=="")
      {
        wx.showToast({
          title: '请您选择性别',
          duration: 2000
        })

        return;
      }*/ 
     
 


    },
  //获取用户输入的用户名
  loginNameinput: function (e) {
    console.log("e.detail.value:::::" + e.detail.value);
    this.setData({
      username: e.detail.value
    })
  },
  //获取用户输入的密码
  passwordinput: function (e) {
    console.log("e.detail.value:::::" + e.detail.value);
    this.setData({
      password: e.detail.value
    })
  },
    formReset: function () {
      console.log('form发生了reset事件')
    },
    /**
    * 生命周期函数--监听页面加载
    */
    onLoad: function (options) {
      var userName = wx.getStorageSync('bi_openid');
      console.log("name::::::::::::" + userName);
      if (userName) {
        this.setData({ userName: userName });
      }  
 
      
  }, 
  loginBtnClick: function (e) {

    var that = this;
    wx.request({
      url: getApp().globalData.url + 'powerUser.action?addtwo',
      data: {
        loginName: this.data.username,
        password: this.data.password

      },
      method: 'get',
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        //'Content-Type': 'application/json'  
      },
      success: function (res) {
        console.log(res.data);

        that.setData({ username: res.data.username });

  
        if (res.data.IsSuccess == false) {
          wx.showToast({
            title: '用户名已存在',
            image: '../image/hy_bt.png',
            duration: 2000
          })
        }
        else {

          wx.showToast({
            title: '注册成功',
            image: '../image/hy_bt2.png',
            duration: 2000
          }),
            setTimeout(function () {

            wx.navigateBack();

              /*
              wx.switchTab({
                url: '../index/index',
              })*/
              /*
              wx.navigateTo({
                url: '../boy_case/boy_case'
              })*/

            }, 1000)
        }
      }
    })


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
    changeIndicatorDots: function (e) {
        this.setData({
            indicatorDots: !this.data.indicatorDots
        })
    },
    changeAutoplay: function (e) {
        this.setData({
            autoplay: !this.data.autoplay
        })
    },
    intervalChange: function (e) {
        this.setData({
            interval: e.detail.value
        })
    },
    durationChange: function (e) {
        this.setData({
            duration: e.detail.value
        })
    },
    onShareAppMessage: function (res) {

    
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
