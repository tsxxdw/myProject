layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'table', 'form', 'laydate','upload'], function () {
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table
        ,upload = layui.upload;

    laydate.render({
        elem: '#test1'
        , type: 'datetime'
        , format: 'yyyy-MM-dd HH:mm'
    });

    //事件
    var active = {
        addRow: function () {
            var tableBody = $(this).closest('.layui-form').find('.layui-table tbody');
            var no = tableBody.find('tr').length;
            var trStr = $('#order-edit-row-temp').html();
            var trNode = $(trStr);
            trNode.find('td').eq(0).html(no + 1);
            tableBody.append(trNode);
        },
        deleteRow: function () {
            var tbody = $(this).closest('.layui-form').find('.layui-table tbody');
            $(this).parent().parent().remove();
            //tr序号重新排序
            var trList = tbody.find('tr');
            $.each(trList, function (i, item) {
                $(item).find('td').eq(0).html(i + 1);
            });
        }
    };

    $(document).on('click', '.layui-btn.event-btn', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call($(this)) : '';
    });
    //选完文件后不自动上传
    upload.render({
        elem: '#test8'
        ,url: '/product/importProduct'
        ,auto: false
        ,accept: 'file'
        //,multiple: true
        ,bindAction: '#test9'
        ,done: function(data){
            $('#uploadNewTip').html('');
            var tips = [];
            if (data.code == '0') {
                layer.msg(data.msg, {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                });
            } else {
                if(data.data!=null){
                    $.each(data.data,function (i,item) {
                        tips.push('<p>'+line+(parseInt(i)+2)+'：'+ item +'</p>');
                    })
                }
                $('#uploadNewTip').html(tips.join(''));
            }
        }
    });
    upload.render({
        elem: '#test10'
        ,url: '/product/excelUpdateProduct'
        ,auto: false
        ,accept: 'file'
        //,multiple: true
        ,bindAction: '#test11'
        ,done: function(data){
            $('#uploadTip').html('');
            var tips = [];
            if (data.code == '0') {
                layer.msg(data.msg, {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                });
            } else {
                if(data.data!=null){
                    $.each(data.data,function (i,item) {
                        tips.push('<p>'+line+(parseInt(i)+2)+'：'+ item +'</p>');
                    })
                }
                $('#uploadTip').html(tips.join(''));
            }
        }
    });

});