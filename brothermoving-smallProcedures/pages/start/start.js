//login.js
//获取应用实例
var app = getApp();
var networkRequest = require('../../js/networkRequest.js');
var loginStr = app.globalData.domainname + '/user/smallProcedure'
var registerStr = app.globalData.domainname + '/user/smallProcedure'
Page({
  data: {
    remind: '加载中',
    angle: 0,
    userInfo: {}
  },

  goToIndex: function () {
    wx.switchTab({
      url: '/pages/index/index',
    });
  },

  onLoad: function () {
    if (wx.getStorageSync("firstLogin")) {
      console.info(wx.getStorageSync("firstLogin") == null)
      wx.switchTab({
        url: '/pages/index/index',
      });
    }
  },
  
  onShow: function () {
    // console.log('onLoad')
    // var that = this
    // app.getUserInfo(function (userInfo) {
    //   that.setData({
    //     userInfo: userInfo
    //   })
    // })
  },
  onReady: function () {
    var _this = this;
    setTimeout(function () {
      _this.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange(function (res) {
      var angle = -(res.x * 30).toFixed(1);
      if (angle > 14) {
        angle = 14;
      } else if (angle < -14) {
        angle = -14;
      }
      if (_this.data.angle !== angle) {
        _this.setData({
          angle: angle
        });
      }
    });
  },
});