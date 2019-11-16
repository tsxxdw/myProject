const app = getApp();
var shopStr = app.globalData.domainname + '/shop'
var userUrl = app.globalData.domainname + '/user';
var dataUtil = require('../../js/dataUtil.js');
var networkRequest = require('../../js/networkRequest.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    dataList: [{
      'showNumber': '亲，如果很久都没数据请重新进入',
      'payMoney': '0.01',
      'createDate': '2017-08-09',
      'payStatus': '加载中'
    }, {
      'showNumber': '亲，如果很久都没数据请重新进入',
      'payMoney': '0.01',
      'createDate': '2017-08-09',
      'payStatus': '加载中'
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  
  toUpdate: function (e) {
   wx.navigateTo({
     url: '/pages/update/update?id='+e.currentTarget.dataset.id,
   })

    // var json = e.detail.value;
    // networkRequest.request(shopStr, "PUT", json, function (res) {
    //      console.info(11);
    // })

  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that=this;
    wx.setStorageSync("firstLogin", "second");
    var json = { openid: app.globalData.openid }
    networkRequest.request(userUrl + "/number", "GET", json, function (res) {
      if (res.code == 1) {
        var json1 = {
           number:res.data
          , orderByDesc: 'createDate'
          , page: 1, limit: 100
        }
        dataUtil.getPageData(shopStr, json1, that);
      }
    })
   
   
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

/**
 * 打电话
 */
  calling: function (e) {
    var that=this;
    var phone = e.currentTarget.dataset.phone;
    var index = e.currentTarget.dataset.index;
    var id = e.currentTarget.dataset.id;
    wx.makePhoneCall({
      phoneNumber: phone, //此号码并非真实电话号码，仅用于测试
      success: function () {
        var json = { openid: app.globalData.openid, id: id, callStatus:'true'}
        networkRequest.request(shopStr, "PUT", json, function (res) {
          var callStatus = 'dataList[' + index + '].callStatus'
          that.setData({
            [callStatus]: 'true'
          })
        })
        console.log("拨打电话成功！")
      },
      fail: function () {
        console.log("拨打电话失败！")
      }
    })
  },
  onShareAppMessage: function () {
    return {
      title: '转发',
      path: '/pages/start/start',
      success: function (res) { }
    }
  },
})