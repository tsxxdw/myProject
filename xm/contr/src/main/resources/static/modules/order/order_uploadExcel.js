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

    //选完文件后不自动上传
    upload.render({
        elem: '#selectFile'
        ,url: '/order/importOrder'
        ,auto: false
        ,accept: 'file'
        //,multiple: true
        ,bindAction: '#uploadFile'
        ,done: function(data){
            $('#uploadTip').html('');
            var tips = [];
            if (data.code == '0') {
                layer.msg(data.msg, {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                });
            } else {
                if(data.data != null){
                    $.each(data.data, function (i,item) {
                        tips.push('<p>' + line + (parseInt(i)+2) + ' ：'+ item +'</p>');
                    });
                } else {
                		tips.push(data.msg);
                }
                $('#uploadTip').html(tips.join(''));
            }
        }
    });

});