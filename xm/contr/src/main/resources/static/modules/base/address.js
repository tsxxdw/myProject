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

 @Name：订单管理
 @Author：Jake
 */
layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'form', 'laydate'], function() {
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table;

    //日期插件渲染
    laydate.render({
        elem: '#time'
        ,type: 'date'
        ,range: '~'
        ,format: 'yyyy-MM-dd'
    });


    //表格渲染
    table.render({
        elem: '#order-table-toolbar',
        url: '/shipper/list',
        toolbar: '#order-table-toolbar-toolbarDemo',
        title: '',
        method: 'post',
        page: true,
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field:'ownerName', title:owner, width:150},
            {field:systemIsChinese == true ?'warehouseCname' : 'warehouseEname', title:warehouseCode, width:150},
            {field:'channelCodes', title:channel, width:400},
            {field:'countryName', title:country, width:150},
            {field:'isDefault', title:default_Address, width:100,templet: function(item) {
                    if(item.isDefault == 't'){
                        return yes;
                    }else{
                        return no;
                    }

                }},
            {field: 'enable', title: enable, width: 150,templet: function (data) {
                    return data.enable == 't' ? validate : invalid;
                }},
            {field:'updateUser', title:finale_modifier, width:200},
            {field:'updateDate', title:final_Modefication_Time, width:200, sort: true},
            {
                fixed: 'right',
                title: operator,
                minWidth: 110,
                toolbar: '#operator-table-toolbar-barDemo'
            }
        ]]
    });

    //头工具栏事件
    table.on('toolbar(order-table-toolbar)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var idList = getCheckboxId(checkStatus);
        var number = idList.length;
        switch (obj.event) {

            case 'createWarehouse' :
                layer.open({
                    title: new_Address,
                    type: 2,
                    skin: 'open-class',
                    area: ['930px', '550px'],
                    content: '/shipper/toCreateorUpdate'
                });
                break;
            case 'taskeffect':
                if (number > 0) {
                    layer.confirm(confirmationOfvalidSelectedRecords + "?",
                        {
                            icon:3,
                            title:info,
                            btn: [yes, no]
                        },
                        function (index) {
                            var searchObj = $("#searchFormId").serializeObject();
                            searchObj.idList = idList;
                            searchObj.enable='t';
                            var dataObject = JSON.stringify(searchObj);
                            onclickStatusButton(dataObject);
                        });
                } else {
                    layer.open({
                        icon:0,
                        title: info
                        ,content: choose_data
                    });
                    return;
                }
                break;
            case 'invalid':
                if (number > 0) {
                    layer.confirm(confirmationFailureCheckRecord + "?",
                        {
                            icon:3,
                            title:info,
                            btn: [yes, no]
                        },
                        function (index) {
                            var searchObj = $("#searchFormId").serializeObject();
                            searchObj.idList = idList;
                            searchObj.enable='f';
                            var dataObject = JSON.stringify(searchObj);
                            onclickStatusButton(dataObject);
                        });
                } else {
                    layer.open({
                        icon:0,
                        title: info,
                        content: choose_data
                    });
                    return;
                }
                break;

        }
    });


    form.verify({
        verifyInventory: function(value){
            console.log(value + ".....................");
        }});

    //监听行工具事件
    table.on('tool(order-table-toolbar)', function(obj){
        var data = obj.data;
        var id = data.id;
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                title: addressUpdate,
                skin: 'open-class',
                area: ['930px', '500px'],
                content: '/shipper/toCreateorUpdate?id=' + id,
                cancel: function (index, layero) {
                    table.reload('order-table-toolbar', {
                        page: {
                            curr: 1
                        }
                        //,height: 300
                    });
                }
            });
        }
        if(obj.event === 'see'){
            layer.open({
                type:2,
                skin: 'open-class',
                area: ['930px', '500px'],
                title: view_detail,
                content:"show?id="+data.id
                //,shade: 0
                ,maxmin: true
                ,zIndex: layer.zIndex //重点1
            });
        }

    });

    //监听搜索
    form.on('submit(LAY-user-front-search)', function(data) {
        var field = data.field;
        //执行重载
        table.reload('order-table-toolbar', {
            url: '/shipper/list',
            method: 'POST',
            where: field
        });
        return false;
    });





    $('.layui-btn.layuiadmin-btn-useradmin').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function getCheckboxId(checkStatus) {
        var arr = new Array();
        var data = checkStatus.data;
        data.forEach(function (item) {
            arr.push(item.id);
        });
        return arr;
    }


    function onclickStatusButton(shipperAddressDto) {
        $.simpleAjax('/shipper/updateStatus' , 'post', shipperAddressDto, "application/json", changeStatusReturn);
    }

    // function onclickDeleteButton(warehouseDto) {
    //     $.simpleAjax('/warehouse/deleteWarehouse' , 'post', warehouseDto, "application/json", changeStatusReturn);
    // }

    function changeStatusReturn(data) {
        if (data.code == '0') {
            successMsg(data.msg);
        } else {
            errorMsg(data.msg);
        }
        $("#search").click();
    }

    function initData() {
        loadOwner($('select[name="owner"]'));// 加载数据
        loadWarehouse($('select[name="warehouseCode"]'));// 加载数据
    }
    // 初始化控件数据
    $(document).ready(function(){
        initData();
    });

});