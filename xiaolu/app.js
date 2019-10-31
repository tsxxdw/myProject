//app.js
var networkRequest = require('js/networkRequest.js');
App({
  globalData: {
    screen_width: wx.getSystemInfoSync().windowWidth,
    screen_height: wx.getSystemInfoSync().windowHeight,
    loginSuccess: false,
    userInfo: null,
    session_key: null,
    code: null,
    domainname: 'http://localhost:9069'
    // domainname:'http://1f49q55928.imwork.net:32789'//家里
    // domainname:'http://1f49q55928.imwork.net:18165'//公司
    // domainname: 'https://pic3.waimaimingtang.com'

    // loginStatus:'微信登录'
  },
  onLaunch: function () {
    var that = this;
    var json = {};
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    var loginStr = this.globalData.domainname + '/user/smallProcedure'
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // 用户登录
    wx.login({
      success: res => {
        json.code = res.code
        networkRequest.getRequest(loginStr, json, function (res) {
          console.info("登录成功")
          if (res.code == 1) {
            console.info("登录成功1")
            that.globalData.openid = res.data.openid; //设置全局用户id
            that.globalData.phone = res.data.phoneNumber;
            that.globalData.avatarUrl = res.data.avatarUrl;
            that.globalData.nickName = res.data.nickName;
            that.globalData.identity = res.data.identity;

          } else {

          }
        })
      }
    })


    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              console.info("app2 getUserInfo");
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                console.info("app3 userInfoReadyCallback");
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  }
})