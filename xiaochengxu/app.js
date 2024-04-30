//app.js

App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
 

    wx.checkSession({
      success: function () {
        //session 未过期，并且在本生命周期一直有效
          
        console.log('app:进来 ');
      },
      fail: function () {
        //登录态过期
        wx.login() //重新登录
      }
    }) 
  },
  
  globalData: {
    userInfo: null,
   // url:'http://tomcat2.wicp.vip/ssmzhaopin/',
    baseUrl: 'http://localhost:8080/',
   url:'http://localhost:8080/danci/'
  }
})