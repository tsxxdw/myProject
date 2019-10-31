//index.js
//获取应用实例
const app = getApp()
var startPrice; //搬出楼层的价格
var endPrice; //搬入楼层的价格
var orderStr = app.globalData.domainname + '/order'
var networkRequest = require('../../js/networkRequest.js');
Page({
  data: {
    carIntroduce:'适合1-2人',
    assessmentCosts: 0,
    assessmentCostsStr: 0,
    carPrice:0,
    startPrice: 0,
    endPrice: 0,
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onLoad: function() {
    //代表不是第一次登录了
    wx.setStorageSync("firstLogin", "second");
  },
  onShow() {

  },
  getPhoneNumber: function(e) {
    console.log("start");
    console.log(e);
    console.log("end");


  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  getCarInfo: function (e) {
    var carInfo = e.detail;
    this.setData({
      carPrice: carInfo.carPrice,
      carType: carInfo.carType,
      carIntroduce: carInfo.carIntroduce,
      carBaseStr: carInfo.carBaseStr
    })
    this.getTotal();
  },
  getStartInfo: function(e) {
    var startInfo = e.detail;
    this.setData({
      startPrice: startInfo.price,
      startLadder: startInfo.ladder,
    })
    this.getTotal();
  },
  getEndInfo: function(e) {
    var endInfo = e.detail;

    this.setData({
      endPrice: endInfo.price,
      endLadder: endInfo.ladder,
    })
    this.getTotal();
  },
  getCarryDate: function(e) {

    var carryDate = e.detail;
    this.setData({
      carryDate: carryDate,
    })
  },
  getTotal: function() {
    var assessmentCosts = this.data.startPrice + this.data.endPrice + this.data.carPrice
    var assessmentCostsStr = Math.round(assessmentCosts / 100);
    this.setData({
      assessmentCosts: assessmentCosts,
      assessmentCostsStr: assessmentCostsStr,
      assessmentCostsStrMethod:'费用计算：货车费用+（下楼费用+上楼费用）*不同车辆的倍率',
      assessmentCostsStrMethodStr: Math.round(this.data.carPrice / 100) + "+" + "(" + Math.round(this.data.startPrice / 100) + "+" + Math.round(this.data.endPrice / 100) + ")*" + this.data.carBaseStr
    })
  },
  /**
   * 点击下一步
   */
  nextStep: function() {
   var b= this.checkData();
   if(b==false){
     return
   }
    var data = this.data;

    this.setData({
      openid: app.globalData.openid,
    })
    networkRequest.request(orderStr, "POST", data, function(res) {
      if (res.code == 1) {

      }
    });

  },
  onShareAppMessage: function () {
    return {
      title: '转发',
      path: '/pages/start/start',
      success: function (res) { }
    }
  },
  checkData() {
    var data = this.data;
    if (data.startAddress == null || data.startAddress.length==0) {
      wx.showToast({
        title: '搬出地址未填写',
        icon: 'fail',
        duration: 2000
      })
      return false;
    }
    
    if (data.startLadder == null || data.startLadder.length==0) {
      wx.showToast({
        title: '搬出楼层未填写',
        icon: 'fail',
        duration: 2000
      })
      return false;
    }

    if (data.endAddress == null || data.endAddress.length == 0) {
      wx.showToast({
        title: '搬入地址未填写',
        icon: 'fail',
        duration: 2000
      })
      return false;
    }
   
    if (data.endLadder == null || data.endLadder.length == 0) {
      wx.showToast({
        title: '迁入楼层未填写',
        icon: 'fail',
        duration: 2000
      })
      return false;
    }
    if (data.carryDate == null) {
      wx.showToast({
        title: '搬家时间未填写',
        icon: 'fail',
        duration: 2000
      })
      return false;
    }

    wx.showToast({
      title: '订单已提交',
      icon: 'success',
      duration: 2000
    })
    return true
  }

})