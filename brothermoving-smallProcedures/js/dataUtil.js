var networkRequest = require('networkRequest.js');

function getAllData(urlStr, json,that, successCallback, failCallback, completeCallback) {
  networkRequest.request(urlStr, "GET", json,that, function(res) {
    if (res.code == 1) {
         that.data.dataList=res.data;
    }
    
  })
}

function getPageData(urlStr, json, that, successCallback, failCallback, completeCallback) {
  networkRequest.request(urlStr, "GET", json, that, function (res) {
    if (res.code == 1) {
      that.data.dataList = res.data;
    }
  })
}

module.exports = {
  getAllData: getAllData,
  getPageData: getPageData
}