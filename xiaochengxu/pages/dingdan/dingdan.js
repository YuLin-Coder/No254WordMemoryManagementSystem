Page({
    data: {
        imgUrls: [
          'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg',
          'http://img06.tooopen.com/images/20160818/tooopen_sy_175866434296.jpg',
          'http://img06.tooopen.com/images/20160818/tooopen_sy_175833047715.jpg'
        ],
        remenData: {},
        topimgdata: {},
        indicatorDots: true,
        autoplay: true,
        interval: 5000,
        duration: 1000,
      title:''
    },

    /**
    * 生命周期函数--监听页面加载
    */
    onLoad: function (options) {
        var that = this;
        var thatimg = this;



      if (wx.getStorageSync('username') == null) {
        // wx.showToast({ title: '请您重新登录', icon: 'success', duration: 3000 })
        wx.navigateTo({
          url: '../login/login'
        })

      }


      if (options != null) {
        console.log("options:" + options.fxid); 

        wx.setStorage({
          key: 'fxid',
          data: options.fxid,
        });


      } 

        wx.request({
            url: getApp().globalData.url +'dingdan.action?list', //仅为示例，并非真实的接口地址
            data: {
            a9: wx.getStorageSync("userid"),
            },
            header: {
               // 'content-type': 'application/json' // 默认值
              "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
            },
            success: function (res) {
                that.setData({ remenData: res.data.rows });
              console.log('数据是1： ' + res.data.rows);
            }
        })
       

    },
  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value);


  }, 
  shuaxinclick: function (e) {


    if (wx.getStorageSync('username') == null) {
      // wx.showToast({ title: '请您重新登录', icon: 'success', duration: 3000 })
      wx.navigateTo({
        url: '../login/login'
      })

    }
    this.onLoad()

  },  

  formReset: function () {
    console.log('form发生了reset事件')
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