layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index', //主入口模块
    formSelects: '../lib/formSelects/formSelects-v4'
}).use(['index', 'table', 'form', 'laydate','formSelects'], function () {
    layui.form.config.verify.required[1] = tip_required;
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        table = layui.table,
        formSelects = layui.formSelects;

    laydate.render({
        elem: '#test1'
        , type: 'datetime'
        , format: 'yyyy-MM-dd HH:mm'
    });
    
    var id = $("#id").val();
    if(id !== "") {
    	$(".layui-btn-primary").css("display","none");
    }

    $(document).on('click','#useWarehouseAddress',function () {
        var warehouseCode = $('#warehouseCode').val();
        $.ajax({
            url: '/shipper/queryWarehouseAddress?warehouseCode=' + warehouseCode,
            dataType: 'json',
            contentType: 'application/json',
            type: 'POST',

            success: function (data) {
                if (data.code == '0') {
                    var item = data.data;
                    $('input[name="zipCode"]').val(item.zipCode);
                    $('input[name="city"]').val(item.city);
                    $('textarea[name="address"]').val(item.address);
                    $('input[name="province"]').val(item.province);
                    $('input[name="provinceCode"]').val(item.provinceCode);
                    $('select[name="countryCode"]').val(item.countryCode);
                    $('input[name="countryName"]').val(item.countryName);
                    form.render(); //更新全部
                }else{
                    errorMsg(data.msg);
                }
            }
        });
        return false;
    });

    form.on('select(warehouse-filter)', function(data){
        var owner =  $('#owner').val();
        if(owner==""){
            $('#warehouseCode').val("");
            return errorMsg(prompt_owner);
        }
        console.log(data.value); //得到被选中的值
        $.ajax({
            url: '/channelConfig/getChannelsByWarehouse?warehouseCode=' + data.value+'&owner='+owner,
            dataType: 'json',
            contentType: 'application/json',
            type: 'POST',
            success: function (data) {
                if (data.code == '0') {
                    var list = data.data;
                    var arr = [];
                    if(list!=null && list.length>0){
                        $.each(list,function (index,item) {
                            arr.push({"name": item.channelCode, "value": item.channelCode});
                        });
                    }
                    formSelects.data('channelCode', 'local', {
                        arr: arr
                    });
                }else{
                    errorMsg(data.msg);
                }
            }
        });
        return false;
    });

    form.on('select(owner-filter)', function(data){
        var owner =data.value
        $("#warehouseCode option:not(:first)").remove();
        $("#channelCode option:not(:first)").remove();
        if(owner!=""){
            console.log(data.value); //得到被选中的值
            $.ajax({
                url: '/channelConfig/getWarehouseByOwner?owner=' + data.value,
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                success: function (data) {
                    form.render();// 重新渲染表单
                    if (data.code == '0') {
                        if (data.data == null || data.data.length == 0) {
                            return;
                        }
                        var selectValue = $('#warehouseCode').attr("value");
                        for(var i = 0; i < data.data.length; i++){
                            //var name = systemIsChinese ? res.data[i].cnName : res.data[i].enName;
                            $('#warehouseCode').append("<option value='" + data.data[i].warehouseCode + "' " + (selectValue != null && selectValue === data.data[i].warehouseCode ? 'selected' : '') + ">" + data.data[i].warehouseCode + "</option>")
                        }
                        form.render();// 重新渲染表单
                    } else {
                        layer.msg(data.msg);
                    }
                }
            });
        }

    });



    form.on('submit(component-form-customer)', function (data) {
        var shipperInfo = data.field;
        $.ajax({
            url: '/shipper/saveOrUpdate',
            dataType: 'json',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(shipperInfo),
            success: function (data) {
                if (data.code == '0') {
                    layer.msg(data.msg, {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.parent.location.reload();
                    });
                }else{
                    errorMsg(data.msg);
                }
            }
        })
        return false;
    });

    form.verify({
        emailVerify: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value!=""){
                if(!/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test(value)){
                    return mail_confirm;
                }
            }

        },
        shortCodeVerify:function (value , item) {
            if(value!=""){
                if(!/^[A-Z]{2}$/.test(value)){
                    return short_code_confirm;
                }
            }
        }
    });

    if("" != $('#id').val()){
        $("#code").addClass('layui-disabled').prop("disabled","disabled");
    }

    function initData() {
        loadCountryCode($('select[name="countryCode"]'));
        loadOwner($('select[name="owner"]'));// 加载货主数据
        //loadChannel($('select[name="expresscode"]'));// 加载承运商数据
        loadWarehouse($('select[name="warehouseCode"]'));// 加载仓库数据
        var channels = $("#channelCodes").val();
        if(channels != ''){
            var arr = channels.split(',');
            formSelects.value('channelCode',arr);
        }
    }
    // 初始化控件数据
    $(document).ready(function(){
        initData();
    });

    if("" != $('#id').val()){
        $("#owner").addClass('layui-disabled').prop("disabled","disabled");
        $("#warehouseCode").addClass('layui-disabled').prop("disabled","disabled");
    }
    //选择二字码自动带出国家名称
    form.on('select(countryCode-filter)', function (data) {
        var countryCode = data.value;
        if(countryCode === ""){
            $("#countryName").val("");
            return;
        }
        loadCountryByCountryCode($("#countryName"), countryCode);
    });
})