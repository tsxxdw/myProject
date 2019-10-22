layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'form', 'laydate'], function () {
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table;
    element = layui.element;

    table.render({
        elem: '#order-table-toolbar',
        url: '/orderIssueRule/lowerlist',
        method: 'POST',
        toolbar: '#order-table-toolbar-toolbarDemo',
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'organizationid', title: '组织'},
            {field: 'rulename', title: '规则名称'},
            {field: 'ruledescrption', title: '规则描述'},
            {field: 'status', title: '状态'},
            {field: 'operatoruser', title: '最后操作人'},
            {field: 'operatordate', title: '最后操作时间'},
            {fixed: 'right', title: '', toolbar: '#order-table-toolbar-barDemo'},
        ]],
        page: true,
        where: $("#searchForm").serializeObject(),
    });

    table.on('toolbar(order-table-toolbar)', function (obj) {
        switch (obj.event) {
            case 'lay_event_add':
                layer.open({
                    type: 2,
                    skin: 'open-class',
                    area: ['1000px', '700px'],
                    title: add,
                    content: '/orderIssueRule/add/',
                    end: function () {
                        location.href = '/orderIssueRule/index';
                    }
                });
                break;
        }
    });

    table.on('tool(order-table-toolbar)', function (obj) {
        switch (obj.event) {
            case 'edit':
                var data = obj.data;
                layer.open({
                    type: 2,
                    skin: 'open-class',
                    area: ['1000px', '700px'],
                    title: add,
                    content: '/orderIssueRule/edit/' + data.id,
                    end: function () {
                        location.href = '/orderIssueRule/index';
                    }
                });
                break;
        }
    })

});