layui.config({
    base: '../../static/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
    , formSelects: '../lib/formSelects/formSelects-v4'
}).use(['index', 'form', 'laydate', 'formSelects'], function () {
    layui.form.config.verify.required[1] = tip_required;
    var $ = layui.$,
        form = layui.form,
        laydate = layui.laydate,
        formSelects = layui.formSelects;

    var warehouses = new Array();
    var owners = new Array();

    function renderTimeInput() {
        var allTimeInput = $(document).find('.date-time-input')
        allTimeInput.each(function (index, el) {
            $(this).removeAttr("lay-key"); //解决重复关闭模态框后再次点击闪退问题
            laydate.render({
                elem: this
                , type: 'time'
                , format: 'HH:mm'
                , trigger: 'click'
            });
        });
    }

    renderTimeInput();
    loadChannel();

    var dateTimeRange = $(document).find('.date-time-range')
    dateTimeRange.each(function (index, el) {
        $(this).removeAttr("lay-key");
        laydate.render({
            elem: this
            , type: 'datetime'
            , range: '~'
            , format: 'yyyy-MM-dd HH:mm'
            , trigger: 'click'
        });
    });

    form.on('switch(switchBtn)', function (data) {
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //开关是否开启，true或者false
        console.log(data.value); //开关value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象
        if (data.elem.checked) {
            $(this).closest('.layui-input-block').find('.time-priority').show();
        } else {
            $(this).closest('.layui-input-block').find('.time-priority').hide();
        }
    });

    $(document).on('click', '.time-range-delete', function () {
        $(this).closest('.time-select-box').remove();
    }).on('click', '.time-range-add', function () {
        var str = '<div class="time-select-box pd-left"><div>'
            + ' <input type="text" class="layui-input date-time-input" id="testTimeStart2" placeholder="起始时间" lay-key="3"/>'
            + ' <input type="text" class="layui-input date-time-input" id="testTimeEnd2" placeholder="截止时间" lay-key="4">'
            + ' <button class="layui-btn layui-btn-sm layui-btn-normal time-range-delete" type="button">删除</button></div>'
            + '<div class="time-tips"><span class="time-tips-start">北京时间：13:00</span><span class="time-tips-end">北京时间：17:56</span></div></div>';
        $(this).closest('.layui-input-block').append(str);
        renderTimeInput();
    });

    //获取已选择的仓库
    form.on('checkbox(warehouse-filter)', function (data) {
        var warehouseCode = data.elem.value; //得到checkbox原始DOM对象
        var isChecked = data.elem.checked;
        if (isChecked) {
            warehouses.push(warehouseCode);
        } else {
            var index = warehouses.indexOf(warehouseCode);
            warehouses.splice(index, 1);
        }

        loadChannel();
    });

    //获取已选择的货主
    form.on('checkbox(owner-filter)', function (data) {
        var owner = data.elem.value; //得到checkbox原始DOM对象
        var isChecked = data.elem.checked;
        if (isChecked) {
            owners.push(owner);
        } else {
            var index = warehouses.indexOf(owner);
            owners.splice(index, 1);
        }

    });

    //周六是否选中
    form.on('checkbox(saturday-filter)', function (data) {
        var isChecked = data.elem.checked;
        if (isChecked) {
            $('#saturday-haircount').attr('lay-verify', 'required');
            $('#saturday-haircount').attr('ws-verify', 'required');
        } else {
            $('#saturday-haircount').removeAttr('lay-verify');
            $('#saturday-haircount').removeAttr('ws-verify');
            $('#saturday-haircount').css('border-color', '#b6e4f1');
            $('#saturday-haircount').next('span').remove();
        }
    });

    //周日是否选中
    form.on('checkbox(sunday-filter)', function (data) {
        var isChecked = data.elem.checked;
        if (isChecked) {
            $('#sunday-haircount').attr('lay-verify', 'required');
            $('#sunday-haircount').attr('ws-verify', 'required');
        } else {
            $('#sunday-haircount').removeAttr('lay-verify');
            $('#sunday-haircount').removeAttr('ws-verify');
            $('#sunday-haircount').css('border-color', '#b6e4f1');
            $('#sunday-haircount').next('span').remove();
        }
    });

    //加载渠道
    function loadChannel() {
        // 后台数据请求
        $.post("/common/getChannelByWarehouseCodes", {'warehouseCodes': warehouses.join(',').toString()}, function (res) {
            if (res.code == '0') {
                if (res.data == null || res.data.length == 0) {
                    return;
                }
                var dataValue = $("select[xm-select='channelcodes']").attr("value");
                var dataArr = [];
                for (var i = 0; i < res.data.length; i++) {
                    var value = res.data[i];
                    var name = systemIsChinese ? res.data[i] : res.data[i];
                    dataArr.push({
                        "name": name,
                        "value": value,
                        "selected": dataValue.indexOf(value) > 0 ? "selected" : ""
                    })
                }
                // 本地初始化数据
                formSelects.data("channelcodes", 'local', {arr: dataArr});
            } else {
                layer.msg(res.msg);
            }
        });
    }

    function loadOrderType() {
        var orderTypes = $('#orderTypes').val();
        var dataValue = $("select[xm-select='ordertypes']").attr("value");
        var dataArr = [];
        $.each(orderTypes, function (i, e) {
            dataArr.push({
                "name": e.value,
                "value": e.key,
                "selected": dataValue.indexOf(e.key) > 0 ? "selected" : ""
            });
        });
        // 本地初始化数据
        formSelects.data("ordertypes", 'local', {arr: dataArr});
    }

    form.on('submit(component-form-order-rule)', function (data) {
        var formData = data.field;
        formData.owners = owners;
        formData.warehousecodes = warehouses;
        formData.channelcodes = formSelects.value("channelcodes", "val");
        formData.ordertypes = formSelects.value("ordertypes", "val");
        formData.goodsdate = formData.goodsdate == 'on' ? true : false;
        formData.makdate = formData.makdate == 'on' ? true : false;
        formData.flagcreatedate = formData.flagcreatedate == 'on' ? true : false;
        var checkBoxs = $('#weekday').find('input[type="checkbox"]:checked');
        var hairRuleDateBeans = new Array();

        //勾选的工作日
        var weekday_data = {};
        var weekdays = [];
        $.each(checkBoxs, function (i, e) {
            weekdays.push($(e).val());
        });
        weekday_data.weekdays = weekdays;
        weekday_data.weektype = 1;
        weekday_data.haircount = $('#weekday-haircount').val();
        var weekDay_dates = $('#saturday-timeRange').find('div.time-select-box');
        weekday_data.lowerTimeRanges = [];
        $.each(weekDay_dates, function (i, e) {
            var startdate = $(e).find('div').eq(0).find('input').eq(0).val();
            var enddate = $(e).find('div').eq(0).find('input').eq(1).val();
            weekday_data.lowerTimeRanges.push({'startdate': startdate, 'enddate': enddate});
        });
        hairRuleDateBeans.push(weekday_data);

        //周六
        if ($('#saturday').prop('checked')) {
            var sat_dates = $('#saturday-timeRange').find('div.time-select-box');
            var satDay_data = {};
            satDay_data.lowerTimeRanges = [];
            satDay_data.weektype = 2;
            satDay_data.haircount = $('#saturday-haircount').val();
            $.each(sat_dates, function (i, e) {
                var startdate = $(e).find('div').eq(0).find('input').eq(0).val();
                var enddate = $(e).find('div').eq(0).find('input').eq(1).val();
                satDay_data.lowerTimeRanges.push({'startdate': startdate, 'enddate': enddate});
            });
            hairRuleDateBeans.push(satDay_data);
        }

        //周日
        if ($('#sunday').prop('checked')) {
            var sun_dates = $('#sunday-timeRange').find('div.time-select-box');
            var sunDay_data = {};
            sunDay_data.lowerTimeRanges = new Array();
            sunDay_data.weektype = 3;
            sunDay_data.haircount = $('#sunday-haircount').val();
            $.each(sun_dates, function (i, e) {
                var startdate = $(e).find('div').eq(0).find('input').eq(0).val();
                var enddate = $(e).find('div').eq(0).find('input').eq(1).val();
                sunDay_data.lowerTimeRanges.push({'startdate': startdate, 'enddate': enddate});
            });
            hairRuleDateBeans.push(sunDay_data);
        }
        formData.hairRuleDateBeans = hairRuleDateBeans;
        $.ajax({
            url: '/orderIssueRule/saveOrUpdateLowerHair',
            type: 'POST',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (data) {
                if (data.code == '0') {
                    successMsg(data.msg, function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else {
                    errorMsg(data.msg);
                }
            }
        });

    });


    $(document).ready(function () {
        //显示货主
        var owners = $('#owners').find('span');
        $.each(owners, function (i, e) {
            var ownerList = $('#ownerList').val();
            var owner = $(e).find('input[type=checkbox]').val();
            if (ownerList.indexOf(owner) > 0) {
                $(e).find('input[type=checkbox]').prop('checked', true);
            }
        });

        //显示仓库
        var owners = $('#warehouseCodes').find('span');
        $.each(owners, function (i, e) {
            var ownerList = $('#warehouseCodeList').val();
            var owner = $(e).find('input[type=checkbox]').val();
            if (ownerList.indexOf(owner) > 0) {
                $(e).find('input[type=checkbox]').prop('checked', true);
            }
        });
        form.render('checkbox');

        loadOrderType();
    });


});