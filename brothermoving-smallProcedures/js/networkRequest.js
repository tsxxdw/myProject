function request(urlStr, method, json, successCallback, failCallback, completeCallback) {
  wx.request({
    url: urlStr,
    contentType: 'application/json; charset=UTF-8',
    data: json,
    method: method,
    success: function (res) {
      successCallback(res.data);
    },
    fail: function (e) {
      if (failCallback != null) {
        failCallback(e);

      }
    },
    complete: function () {
      if (completeCallback != null) {
        completeCallback();
      }
    }
  })
}

function getRequest(urlStr, json, successCallback, failCallback, completeCallback) {
  wx.request({
    url: urlStr,
    contentType: 'application/json; charset=UTF-8',
    data: json,
    method: 'GET',
    success: function (res) {
      successCallback(res.data);
    },
    fail: function (e) {
      if (failCallback != null) {
        failCallback(e);

      }
    },
    complete: function () {
      if (completeCallback != null) {
        completeCallback();
      }
    }
  })
}


function postRequest(urlStr, json, successCallback, failCallback, completeCallback) {
  wx.request({
    url: urlStr, //接口地址
    data: json,
    method: 'POST',
    contentType: 'application/json; charset=UTF-8',
    success: function (res) {
      successCallback(res.data);
    },
    fail: function (e) {
      if (failCallback != null) {
        failCallback(e);
      }
    },
    complete: function () {
      if (completeCallback != null) {
        completeCallback();
      }
    }
  })

}


module.exports = {
  request: request,
  postRequest: postRequest,
  getRequest: getRequest
}