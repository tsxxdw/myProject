/*
 *
 *    WingSing CONFIDENTIAL
 *    _____________________
 *
 *    [2014] - [2015] WingSing Supply Chain Management Co. (Shenzhen) Ltd.
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained herein is, and remains the property of
 *    WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 *    any. The intellectual and technical concepts contained herein are proprietary
 *    to WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 *    may be covered by China and Foreign Patents, patents in process, and are
 *    protected by trade secret or copyright law. Dissemination of this information
 *    or reproduction of this material is strictly forbidden unless prior written
 *    permission is obtained from WingSing Supply Chain Management Co. (Shenzhen)
 *    Ltd.
 */

/**

 @Name：发货地址详情
 @Author：Jake
 */
layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'common'], function() {
    var $ = layui.$;

    $("#warehouseCode").text(systemIsChinese == true ? address.warehouseCname : address.warehouseEname);

    

});