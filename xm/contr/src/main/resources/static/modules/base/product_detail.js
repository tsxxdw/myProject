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
    formSelects: '../lib/formSelects/formSelects-v4'
}).use(['form', 'laydate', 'formSelects', 'table'], function () {
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table,
        formSelects = layui.formSelects;
    var element = layui.element;
    element.render();

    //头工具栏事件
    element.on('tab(product-detail-table-toolbar)', function (obj) {
        var status = this.getAttribute('lay-id');
        var id = $("#productDtoId").val();
        data={"checkBoxId":id};
        if (status === 'log') {
            $.simpleAjax('detailLog', 'POST', JSON.stringify(data), "application/json", returnFunction);
        }

    });


    function returnFunction(datas) {
        if(datas.code=='0'){
            var dat=datas.data;
            dat.forEach(function (item) {
                var $trTemp = $("<tr></tr>");
                $trTemp.append("<td>"+ item.createDate +"</td>");
                $trTemp.append("<td>"+ item.createUser +"</td>");
                $trTemp.append("<td>"+ item.content +"</td>");
                $trTemp.appendTo("#logInfo");
            });

        }else {
            alert(data.msg)
        }
    }
});