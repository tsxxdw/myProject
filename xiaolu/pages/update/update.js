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
    sex: '男'
  },
  switchChange: function (e) {
    let temp = 'data.callStatus'
    if (e.detail.value) {
      this.setData({
        [temp]: 'true'
      })
    } else {
      this.setData({
        [temp]: 'false'
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
     var id= options.id;
     var json={id:id,limit:1,page:1};
    networkRequest.request(shopStr, "GET", json, function (res) {
      var data=res.data[0];
      that.setData({
        data:data
      })
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  //表单提交
  formSubmit: function (e) {
    var json = e.detail.value;
    networkRequest.request(shopStr, "PUT", json, function (res) {
        if(res.code='1'){
          wx.showToast({
            title: '修改成功',
            icon: 'succes',
            duration: 1000,
          })
        }else{
          wx.showToast({
            title: '系统维护中',
            duration: 1000,
          })
        }
    })
  }
})