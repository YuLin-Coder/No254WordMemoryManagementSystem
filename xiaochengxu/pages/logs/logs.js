Page({
    data: {
        imgUrls: [
          'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg',
          'http://img08.tooopen.com/20191023/tooopen_sy_164947494761595.jpg',
          'http://img08.tooopen.com/20191031/tooopen_sy_164852485236874.jpg'
        ],
        remenData: {},
        topimgdata: {},
        indicatorDots: true,
        autoplay: true,
        interval: 5000,
        duration: 1000,
        timu:''
    },

    /**
    * 生命周期函数--监听页面加载
    */
    onLoad: function (options) {
        var that = this;
        var thatimg = this;

      if (options != null) {
        console.log("options:" + options.fxid); 

        wx.setStorage({
          key: 'fxid',
          data: options.fxid,
        });


      }


        wx.request({
          url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=tongxunlulist', //仅为示例，并非真实的接口地址
            data: {
                strW: ' where  1=1  ',
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success: function (res) {
                that.setData({ remenData: res.data.Data });
                console.log('数据是： ' + res.data.Data);
            }
        })
        var thatbanner=this;
        wx.request({
          url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=loadBanner', //仅为示例，并非真实的接口地址
            data: {
              //  strW: ' where  1=1 ',
            },
            header: {
                'content-type': 'application/json' // 默认值
            },
            success: function (res) { 
              console.log('topimgdata: ' + res.data.Data );
                thatimg.setData({ topimgdata: res.data.Data });
                var bbb = [];
                //console.log('图片数据是： ' + res.data.Data[0]["img_url"]);
                for (let i = 0; i < res.data.Data.length; i++) {
                  bbb.push(res.data.Data[i].img_url);
                }
              thatbanner.setData({ imgUrls: bbb }); 

            }
        })

    },
    formSubmit: function (e) {
       
 
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