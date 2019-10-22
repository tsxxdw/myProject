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
        , url: '/appsecret/list'
        , toolbar: '#order-table-toolbar-toolbarDemo'
        , title: ''
        , cols: [[
            /*{type: 'checkbox', field: 'id', fixed: 'left'}
            ,*/ {field: 'name', title: owner, fixed: 'left',  align: 'left'}
            , {field: 'appId', title: 'API ID',  align: 'left'}
            , {field: 'secretKey', title: 'API Secret',  align: 'left'}
            , {
                fixed: 'right',
                align: 'left',title:v_operate,
                templet: function (item) {
                    if(!(item.appId != '' && item.appId != null)){
                        return $("#oper").html();
                    }else{
                        return "";
                    }
                }
            }
        ]]
        , page: true
        //回调函数查询不同状态数据总数
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
        switch (obj.event) {
            case 'generateAppID':
                layer.confirm(generate_appId+'?', function(index){
                    $.ajax({
                        url: '/appsecret/generateCusomerKey',
                        data: data.code,
                        type: 'POST',
                        dateType: 'json',
                        contentType: 'application/json',
                        success: function (data) {
                            if(data.code == '0'){
                                successMsg(data.msg);
                            }else {
                                errorMsg(data.msg);
                            }
                            layer.close(index);
                        }
                    });


                   /* $.post('/appsecret/generateCusomerKey', {
                        code: data.code
                    }, function(data){
                        alert(data.code == '0');
                        if(data.code == '0'){
                            successMsg(data.msg);
                        }else {
                            errorMsg(data.msg);
                        }
                        layer.close(index);
                    }, function () {
                        layer.close();
                    });*/
                });

                break;
        }
    });

    //头工具栏事件
   /* table.on('toolbar(order-table-toolbar)', function (obj) {
        var data = obj.data;
        switch (obj.event) {
            case 'generateAppID':
                alert('generateAppID');
                layer.confirm("是否生成APP ID?",
                    {
                        icon:3,
                        title: info,
                        btn: [yes, no]
                    },
                    function (index) {
                        var result = null;
                        $.simpleAjax('/appsecret/generateCusomerKey?code=' + data.code, 'get', null, "application/json", result);
                    });
                break;
        }
    });*/

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

   /* function initData() {
        loadOwner($("select[name='owner']"));
    }

    // 初始化控件数据
    $(document).ready(function () {
        initData();
    });*/

});

