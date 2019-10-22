layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
    , formSelects: '../lib/formSelects/formSelects-v4'
    , common: '../modules/common'
}).use(['index', 'table', 'form', 'laydate', 'formSelects', 'common'], function () {
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table,
        formSelects = layui.formSelects;
    element = layui.element;

    //日期插件渲染
    laydate.render({
        elem: '#time'
        , type: 'date'
        , range: '~'
        , format: 'yyyy-MM-dd'
    });

    //表格渲染
    table.render({
        elem: '#order-table-toolbar'
        , url: '/channel/list'
        , toolbar: '#order-table-toolbar-toolbarDemo'
        , title: ''
        , cols: [[
            {type: 'checkbox', field: 'id', fixed: 'left'}
            , {field: 'channelCode', title: channelCode, fixed: 'left', width: 170, align: 'left'}
            , {field: 'channelChineseName', title: channelChineseName, width: 200, align: 'left'}
            , {field: 'channelEnglishName', title: channelEnglishName, width: 200, align: 'left'}
            , {field: 'status', title: status, width: 200, align: 'left'}
            , {field: 'updateDate', title: lastUpdateDate, width: 180, align: 'left'}
            , {field: 'remark', title: remark, minWidth: 300, align: 'left'}
            , {
                fixed: 'right',
                title: execute,
                align: 'left',
                width: 110,
                templet: "#oper"
            }
        ]]
        , page: true
        //回调函数查询不同状态数据总数
    });

    //监听搜索
    form.on('submit(LAY-user-front-search)', function (data) {
        var field = data.field;
        field['checkBoxIdList'] = null;
        //执行重载
        table.reload('order-table-toolbar', {
            where: field,
            page: {
                curr: 1
            }
        });
        return false;
    });

    //状态切换
    element.on('tab(component-tabs-hash)', function (obj) {
        var parms = $("#searchForm").serializeObject();
        table.reload('order-table-toolbar', {
            where: parms
        });
        layer.msg(this.innerHTML);
    });

    //监听行工具事件
    table.on('tool(order-table-toolbar)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                skin: 'open-class',
                area: ['950px', '400px'],
                title: v_edit,
                content: "/channel/update?checkBoxId=" + data.id,
                end: function () {
                    $("#search").click();
                }
            });
        } else if (obj.event === 'see') {
            layer.open({
                type: 2,
                skin: 'open-class',
                area: ['950px', '400px'],
                title: channelCode+':' + data.channelCode,
                content: "/channel/detail?checkBoxId=" + data.id
            });
        }
    });

    //头工具栏事件
    table.on('toolbar(order-table-toolbar)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        var idList = getCheckboxId(checkStatus);
        var number = idList.length;
        var searchObj = $("#searchFormId").serializeObject();
        searchObj.checkBoxIdList = idList;
        switch (obj.event) {
            case 'taskeffect':
                if (number > 0) {
                    layer.confirm(confirmationOfvalidSelectedRecords + "?",
                        {
                            icon:3,
                            title: info,
                            btn: [yes, no]
                        },
                        function (index) {
                            onclickStatusButton(searchObj, 1);
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
            case 'invalid':
                if (number > 0) {
                    layer.confirm(confirmationFailureCheckRecord + "?",
                        {
                            icon:3,
                            title: info,
                            btn: [yes, no]
                        },
                        function (index) {
                            onclickStatusButton(searchObj, 0);
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
            case 'lay_event_add':
                layer.open({
                    type: 2,
                    skin: 'open-class',
                    area: ['930px', '550px'],
                    title: newlyAdded,
                    content: "/channel/add",
                    end: function () {
                        $("#search").click();
                    }
                });
                break;
        }
    });

    /*点击生效失效按钮-------start--------*/

    function getCheckboxId(checkStatus) {
        var arr = new Array();
        var data = checkStatus.data;
        data.forEach(function (item) {
            arr.push(item.id);
        });
        return arr;
    }

    function onclickStatusButton(dto, status) {
        var dataObject = JSON.stringify(dto);
        $.simpleAjax('/channel/updateStatus?status=' + status, 'post', dataObject, "application/json", changeStatusReturn);
    }

    function changeStatusReturn(data) {
        if (data.code == '0') {
            layer.msg(data.msg, {
                icon: 1,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            });
        } else {
            layer.msg(data.msg, {
                icon: 5,
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            })
        }
        $("#search").click();
    }

    /*点击生效失效按钮-------end--------*/


    form.on('select(selectType-filter)', function (obj) {
        $("input[name='selectValue']").val(null);

    })

    function initData() {
        loadOwner($("select[name='owner']"));
    }

    // 初始化控件数据
    $(document).ready(function () {
        initData();
    });

});

