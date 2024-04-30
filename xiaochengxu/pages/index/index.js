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
        userid:''
    },
    tapName: function (e) {
      console.log("22222222222222222222222222222"+e);


      wx.setStorageSync('username',null);
      this.setData({ msg: "Hello World" });

      console.log('单击', e.currentTarget.dataset.itemnum);
      var name = e.currentTarget.dataset.itemnum;

      console.log("name: " + name);
      wx.navigateTo({
        url: '../login/login'
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
      
    testddd: function (e) {
      this.setData({ sexn: "男" });
      this.setData({ sexg: "" })
      console.log(88888888888888888888 + ' e.detail.value ' + e.detail.value);
      
    },
    testccc: function (e) {
      this.setData({ sexg: "女" });
      this.setData({ sexn: "" });
      console.log(88888888888888888888 + ' e.detail.value ' + e.detail.value);

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
    formReset: function () {
      console.log('form发生了reset事件')
    },
    /**
    * 生命周期函数--监听页面加载
    */
    onLoad: function (options) {
      var userName = wx.getStorageSync('username');// wx.getStorageSync('bi_openid');
      console.log("name::::::::::::" + userName);
      if (userName) {
        this.setData({ username: userName });
      }  

      if (wx.getStorageSync('username')==null)
      {
        wx.navigateTo({
          url: '../login/login'
        })

      }


    }, 
  //得到查询内容
  shuaxinclick: function (e) {
  
    this.onLoad()

  }, 
    clickMe: function (e) {
      this.setData({ msg: "Hello World" });

      console.log('单击', e.currentTarget.dataset.itemnum);
      var name = e.currentTarget.dataset.itemnum;

      console.log("name: "+name);
      wx.navigateTo({
        url: '/pages/case/case?title=' + name//这是我建立的另一个界面，你们别忘了在app.json里面加路径哦
      
      })

        return;
 
      //  console.log("用户名：" + e.detail.value.shengao + " 密码：" + this.data.userPwd);
    }
,
    //  点击日期组件确定事件  
    bindDateChange: function (e) {
        this.setData({
            date: e.detail.value
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
