age({  

  data: {
    logs: [],
    date: '2016-11-08',
    array: ['美国', '中国', '巴西', '日本'],
    index: 0,
    remenData: {},
  },
  onLoad: function (options) {
    
    this.setData({
      title: options.title
    }) 
    var avatar = wx.getStorageSync('sync_avatar');
    var userName = wx.getStorageSync('bi_openid');

 
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
        for (let i = 0; i < res.data.Data.length; i++) {
          bbb.push(res.data.Data[i].a1);
        }
        that.setData({ array: bbb });
      }
    })
/*
    if (avatar) {
      this.setData({ avatar: avatar });
    }
    var nick_name = wx.getStorageSync('nick_name');
    console.log("nick_name::::::::::::" + nick_name);
    if (avatar) {
      this.setData({ nick_name: nick_name });
    } 
*/
 
   var that=this;
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

  },
  listenerPickerSelected: function (e) {
    //改变index值，通过setData()方法重绘界面
 
    var userName = wx.getStorageSync('bi_openid');
    if (userName) {
      this.setData({ userName: userName });
    } 
    this.setData({
      index: e.detail.value
    });
 
    var that = this;
    wx.request({
      url: 'http://xchone.dagezi888.com:8031/jiekou/Handler1.ashx?bz=loadqiwangzhi', //仅为示例，并非真实的接口地址
      data: {
        userid: userName,
        a1: e.detail.value
      },
      method: 'get',
      header: {
        "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
        //'Content-Type': 'application/json'  
      },
      success: function (res) {
        console.log('eeeeeeeeeee11:' + res.data);

        that.setData({ remenData: res.data.Data });
        console.log('数据是： ' + res.data.Data);

      }
    })  
  },

  data: {
    flag: true,
  },

  /**
   * 弹出层函数
   */
  //出现
  show: function () {

    this.setData({ flag: false })

  },
  //消失

  hide: function () {

    this.setData({ flag: true }),
      wx.navigateTo({
        url: '../qiwangzhi/qiwangzhi'
      })
      

  }
  
})
