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
        , url: '/fileDownRecord/list'
        , toolbar: '#order-table-toolbar-toolbarDemo'
        , title: ''
        , cols: [[
            {field: 'id', title: 'Id', fixed: 'left'},
            {field: 'type', title: 'type', fixed: 'left'}
           /* , {field: 'taskName', title: taskName, fixed: 'left', minWidth: 170, align: 'left'}*/
            , {
                field: 'url', title: execute, width: 120, align: 'left',
                templet: function (item) {
                    return '<a href="' + fileShowUrl + item.url + '" style="color: #548df8;">' + down + '</a>'
                }
            }
            , {
                field: 'process', title: process, minWidth: 170, align: 'left',
                templet: function (item) {
                    return item.process == null ? "" : item.process + '%'
                }
            }
            , {field: 'startTime', title: createDate, minWidth: 170, align: 'left'}
            , {field: 'endTime', title: endDate, minWidth: 170, align: 'left'}
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

