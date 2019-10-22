/*
 *   WingSing CONFIDENTIAL
 *   _____________________
 *
 *   [2014] - [2015] WingSing Supply Chain Management Co. (Shenzhen) Ltd.
 *   All Rights Reserved.
 *
 *   NOTICE: All information contained herein is, and remains the property of
 *   WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 *   any. The intellectual and technical concepts contained herein are proprietary
 *   to WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 *   may be covered by China and Foreign Patents, patents in process, and are
 *   protected by trade secret or copyright law. Dissemination of this information
 *   or reproduction of this material is strictly forbidden unless prior written
 *   permission is obtained from WingSing Supply Chain Management Co. (Shenzhen)
 *   Ltd.
 */

layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
    , formSelects: '../lib/formSelects/formSelects-v4'
}).use(['index', 'form', 'laydate', 'formSelects'], function () {
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        formSelects = layui.formSelects;




    //监听提交
    form.on('submit(component-form)', function (data) {
        var searchObj = $("#searchFormId").serializeObject();
        $.ajax({
            url: '/product/updateProcess',
            type: 'POST',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(searchObj),
            success: function (data) {
                if (data.code == '0') {
                    layer.msg(data.msg, {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else {
                    layer.msg(data.msg, {
                        icon: 5,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }
            }
        });
        return false;
    });
});