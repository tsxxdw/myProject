const app = getApp()
var aaa=13
var fileUtils = require('../../js/fileUtil.js');
var networkRequest = require('../../js/networkRequest.js');
var urlStr = app.globalData.domainname + '/user/smallProcedure'
var registerStr = app.globalData.domainname + '/user/smallProcedure'
Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    avatarUrl:"/images/avatar_default.png"
  },

  onLoad: function () {
    var that = this;
    var openid = app.globalData.openid;
    if (openid != null) {
      that.setData({
        screen_width: app.globalData.screen_width - aaa * 2,
        actionWidth: app.globalData.screen_width - aaa * 2,
        actionHeight: app.globalData.screen_height - 200,
        hasUserInfo: true,
        phone: app.globalData.phone,
        nickName: app.globalData.nickName,
        avatarUrl: app.globalData.avatarUrl,
        openid: openid
      })

    }
  },
  getUserInfo: function (e) {
    var that = this;
    wx.showModal({
      title: '登录提示',
      content: '亲爱的用户您确定要登录吗？登录需要获取您的头像等基本信息！',
      success: function (res) {
        if (res.confirm) {
          var json = {};
          wx.login({
            success: function (res) {
              json.code = res.code
              json.encryptedData = e.detail.encryptedData;
              json.iv = e.detail.iv;
              wx.login({
                success: function (res) {
                  json.code = res.code,
                    wx.showLoading({
                      title: '登陆中'
                    })
                  networkRequest.postRequest(registerStr, json, function (res) {
                    console.info("登录成功")
                    if (res.code == 1) {
                      console.info("登录成功1")
                      wx.setStorageSync("firstLogin", "second");
                      app.globalData.userInfo = e.detail.userInfo
                      app.globalData.openid = res.data.openid; //设置全局用户id
                      app.globalData.phone = res.data.phoneNumber;
                      app.globalData.avatarUrl = res.data.avatarUrl;
                      app.globalData.nickName = res.data.nickName;
                      app.globalData.openid=res.data.openid;
                      that.setData({
                        hasUserInfo: true,
                        phone: app.globalData.phone,
                        nickName: app.globalData.nickName,
                        avatarUrl: app.globalData.avatarUrl,
                        openid: app.globalData.openid
                      })
                      // wx.switchTab({
                      //   url: '/pages/classification/classification',
                      // });
                    } else {
                      wx.showToast({
                        title: '网络延迟，再试一次',
                      }, 60000);

                    }
                  }, null, function () {
                    wx.hideLoading();
                  })
                }
              });

            }

          })
        } else {
          return;
        }

      }
    })

  },
  getPhoneNumber: function (e) {
    var that = this;
    //获取手机号码
    wx.login({
      success: function (res) {
        console.log('phone:', res)
        //发送请求
        wx.request({
          url: app.globalData.domainname + '/user/smallProcedure', //接口地址
          data: {
            encryptedData: e.detail.encryptedData, //encodeURIComponent(e.detail.encryptedData),
            iv: e.detail.iv,
            code: res.code,
          },
          method: 'PUT',
          header: {
            'content-type': 'application/json' //默认值
          },
          success: function (res) {
            that.setData({
              phone: res.data.data.phoneNumber
            })

          },
          fail: function (err) {
            wx.showToast({
              title: '网络延迟，再试一次',
            }, 60000)
          }
        })
      }
    })


  },

/**
 * 打电话
 */
  calling: function () {
    wx.makePhoneCall({
      phoneNumber: '13641484199', //此号码并非真实电话号码，仅用于测试
      success: function () {
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
})