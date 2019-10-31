const app = getApp();
var orderStr = app.globalData.domainname + '/order'
var dataUtil = require('../../js/dataUtil.js');

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
    var openid=app.globalData.openid;
    var json={openid:opneid}
   
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
    var json={openid:app.globalData.openid}
    dataUtil.getAllData(orderStr, json, this);
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

 
})