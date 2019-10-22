/**
 * @Name：订单详情
 * @Author：duke
 */
layui.config({
	base : '../../static/' // 静态资源所在路径
}).extend({
	index : 'lib/index' // 主入口模块
}).use(['index', 'form', 'table'], function () {
    var $ = layui.$,
    table = layui.table,
    form = layui.form,
    admin = layui.admin,
    element = layui.element;

	element.render();
	
	var inventoryList = getEnumList('InventoryType');
	
	var orderType = $("#orderType").html();
	if(orderType != "") {
		$("#orderType").html(typeMap[orderType])
	}
	
	$("#warehouseCode").text(systemIsChinese == true ? order.warehouseCname : order.warehouseEname);
	/**
	 * 时间初始化
	 */
	var delTime = $("#deliveryTime").html()==""?"":new Date($("#deliveryTime").html()).pattern("yyyy/MM/dd HH:mm");
	$("#deliveryTime").html(delTime);
	var orderTime = $("#orderTime").html()==""?"":new Date($("#orderTime").html()).pattern("yyyy/MM/dd HH:mm");
	var createdate = $("#createdate").html()==""?"":new Date($("#createdate").html()).pattern("yyyy/MM/dd HH:mm");
	var actrlDlvryTm = $("#actrldlvrytmStr").html()==""?"":new Date($("#actrldlvrytmStr").html()).pattern("yyyy/MM/dd HH:mm");
	$("#orderTime").html(orderTime);
	$("#createdate").html(createdate);
	$("#actrldlvrytmStr").html(actrlDlvryTm);

	var outbound = inventoryList.filter(function (item){
		return item.data == order.outboundType
	});
	$("#outboundType").html(outbound.length > 0 ?outbound[0].name:"");
	
	
	table.render({
        elem: '#orderDetail',
        data: details==null?[]:details,
        cols: [[
            {field: 'sku', title: v_sku, width: 200},
            {field: systemIsChinese == true ? 'skuCname' : 'skuEname', title: v_skuname},
            {field: 'qty', title: v_sumnum},
            {field: 'declarePrice', title: v_declareprice},
            {field: 'currency', title: v_currency}
        ]]
    })

    //选项卡切换
    element.on('tab(component-tabs-brief)', function (obj) {
        var status = this.getAttribute('lay-id');
        if (status == 'productLabel') {
        	table.render({
        		elem: '#orderDetail',
                data: details==null?[]:details,
                cols: [[
                	{field: 'sku', title: v_sku, width: 200},
                    {field: systemIsChinese == true ? 'skuCname' : 'skuEname', title: v_skuname},
                    {field: 'qty', title: v_sumnum},
                    {field: 'declarePrice', title: v_declareprice},
                    {field: 'currency', title: v_currency}
                ]]
            }) 
        } else if (status == 'logLabel') {
            $("#log").empty();
            table.render({
                elem: '#log',
	            url: '/dataLog/list',
	            method: 'post',
	            where: {"transactionId": $("#id").val(), "category": "Order"},
	            cols: [[
	                {field: 'createDate', title: v_time, width: 180},
	                {field: 'createUser', title: v_operator, width: 150},
	                {field: 'content', title: v_action}
	            ]]

            })
            
        } else if (status == 'routeLabel') {
            $("#route").empty();
            var orderId=$("#id").val()
            table.render({
                elem: '#route',
                url: '/order/getOrderRoute',
                method: 'post',
                where: {"orderId": orderId},
                cols: [[
                    {field: 'date', title: v_time, width: 180},
                    {field: 'status', title: v_routeStatus, width: 150},
                    {field: 'statusdescription', title: v_action}
                ]]

            })

        } else {

        }
    })

});
