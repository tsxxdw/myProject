/**

 @Name：订单管理
 @Author：duke
 */
layui.config({
	base: '../../static/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
}).use(['index', 'table', 'form', 'laydate'], function() {
	layui.form.config.verify.required[1] = v_required;
	var $ = layui.$,
		form = layui.form,
		laydate = layui.laydate,
		table = layui.table;
	
	//事件
	var active = {
		addRow: function() {
			var tableBody = $(this).closest('.layui-form').find('.layui-table tbody');
			var no = tableBody.find('tr').length;
			var trStr = $('#order-edit-row-temp').html();
			var trNode = $(trStr);
			trNode.find('td').eq(0).html(no+1);
			tableBody.append(trNode);
			form.render('select');
		},
		deleteRow: function() {
			var tbody = $(this).closest('.layui-form').find('.layui-table tbody');
			$(this).parent().parent().remove();
			//tr序号重新排序
			var trList = tbody.find('tr');
			$.each(trList, function(i,item){
				$(item).find('td').eq(0).html(i+1);
			});
		}
	};

	$(document).on('click','.layui-btn.event-btn', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call($(this)) : '';
	});
	
	//输入sku后校验查询sku信息
    $(document).on('blur', '#productInfo [name="sku"]', function () {
    	var orginValue = $(this);
    	var flag = false;
		$("input[name='sku']").not(orginValue).each(function () {
			if (orginValue.val() == $(this).val()) {
				orginValue.val("");
				flag = true;
				return;
			}
		});
		if(flag == true) {
			errorMsg(v_sku_repeat);
			return ;
		}
        var skuObject = $(this);
        var sku = $(this).val();
        var owner = $("#owner").val();
        if (owner != "") {
            if (sku != "") {
            	var productVo = loadProductByOwnerSku(owner,sku);
            	if (productVo != null) {
                    skuObject.parents("tr").find("td:eq(2)").find("input").val(systemIsChinese?productVo.cnName:productVo.enName);
                    skuObject.parents("tr").find("td:eq(4)").find("input").val((productVo.declarePrice == null || productVo.declarePrice == "")?productVo.price:productVo.declarePrice);
                    skuObject.parents("tr").find("td:eq(5)").find("select").val(productVo.currency);
                    form.render('select');
                }
            } else {
            	errorMsg(prompt_sku);
            }
        } else {
        	errorMsg(prompt_owner);
        }
    });


    /*只支持字母、数字、中横线、下横线，4位<=长度<=50位*/
    $(document).on('blur', '#refNumber', function (data) {
        var reg = /^[A-Za-z0-9-_]{4,50}$/;
        layui.$(this).css('border-color','#b6e4f1');
        layui.$(this).closest('.layui-form-item').find('.error-tip').remove();
        if (this.value == '' ){
            layui.$(this).after('<span class="error-tip">'+ v_required +'</span>');
            layui.$(this).css('border-color','red');
            return;
        }

        if (this.value != '' && !reg.test(this.value)) {
            layui.$(this).after('<span class="error-tip">'+ v_ordernumber_rule +'</span>');
            layui.$(this).css('border-color','red');
            return;
        }
        var orderNumber = $(this).val();
        var owner = $("#owner").val();
        if (owner != "") {
            if (orderNumber != "") {
                var productVo = checkRefNumberByOwner(owner,orderNumber);
                if (productVo != null) {
                    layui.$(this).after('<span class="error-tip">'+ v_order_check +'</span>');
                    layui.$(this).css('border-color','red');
                    return false;
                }
            }
        } else {
            layui.$(this).after('<span class="error-tip">'+ prompt_owner +'</span>');
            layui.$(this).css('border-color','red');
            return;
        }
    }).on('focus', '#refNumber', function (data) {
        layui.$(this).css('border-color','#b6e4f1');
        layui.$(this).closest('.layui-form-item').find('.error-tip').remove();
    });


    form.on('submit(formSubmit)', function(data){
    	//邮箱校验
    	var emailReg =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	var email = $("#email").val();
    	if(email.trim().length > 0 && !emailReg.test(email)){
    		errorMsg(v_email_error);
    		return false;
    	}
    	
    	var countryCode = $("#countryCode").val();
    	var province = $("#province").val();
        
        if((countryCode === "CA" || countryCode === "US") && province.trim().length == 0){
        	errorMsg(v_province_required);
        	return false;
        }
    	var size = $("#detailBody").find("tr").length;
    	if(size <= 0) {
    		errorMsg(v_detail_null);
        	return false;
        }
    	
    	var field = data.field;
        field.orderDetails = [];
        var intNum = /^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/;
        var flag = false;
        var nowCurrency = "";
        var currencyFlag = false;
        $("#productInfo").find("tbody").find("tr").each(function (index, item) {
        	var orderNumber = $("#id").val();
            var sku = $(item).find("input[name='sku']").val();
            var qty = $(item).find("input[name='qty']").val();
            var declarePrice = $(item).find("input[name='declarePrice']").val();
            //var catId = $(item).find("select[name='catId']").val();
            var currency = $(item).find("select[name='currency']").val();

            if(qty != "" && !intNum.test(qty)) {
            	flag = true;
            }
            if(declarePrice!="" && !intNum.test(declarePrice)) {
            	flag = true;
            }
            //校验币种
            if(nowCurrency == "") {
                nowCurrency = currency;
            }else {
                if (currency != nowCurrency) {
                    currencyFlag = true;
                    return false;
                }
            }

            var skuName = $(item).find("input[name='skuName']").val();
            
            field.orderDetails.push({"sku":sku, "skuName":skuName, "qty":qty, "declarePrice":declarePrice,  "currency":currency});
        })
        if(flag) {
            layer.msg(v_qty_valid + "," + v_declareprice_valid);
            return false;
        }
        if(currencyFlag) {
            errorMsg(v_currency_valid);
            return false;
        }

        $.ajax({
            url: '/order/saveOrUpdate',
            dataType: 'json',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(field),
            success: function (data) {
            	if (data.code == 0) {
            		successMsg(data.msg,function(){
            			window.location.reload();
            		});
            	} else {
            		errorMsg(data.msg);
            	}
            }
        })

        return false;
    });
    
    
    //根据货主，重新加载仓库
    form.on('select(owner-filter)', function(data) {
        $("#expressCode").children().remove();
        $("#expressCode").append("<option value=''>" + v_select + "</option>");
        $("#warehouseCode").children().remove();
        $("#warehouseCode").append("<option value=''>" + v_select + "</option>");
        var owner = data.value;
        if(owner === "") {
        	return false;
        }
        loadWarehouseByOwner($("#warehouseCode"),owner);
    });
    
    //根据仓库，重新加载渠道
    form.on('select(warehouse-filter)', function(data) {
        $("#expressCode").children().remove();
        $("#expressCode").append("<option value=''>" + v_select + "</option>");
        var warehouseCode = data.value;
        var owner =  $("#owner").val();
        if(warehouseCode === "") {
        	return false;
        }
        if(owner === "") {
        	return false;
        }
        
        loadChannelByOwnerWarehouse($("#expressCode"),owner,warehouseCode);
    });

    //选择二字码自动带出国家名称
    form.on('select(countryCode-filter)', function (data) {
        var countryCode = data.value;
        if(countryCode === ""){
        	$("#country").val("");
        	return;
        }
        loadCountryByCountryCode($("#country"), countryCode);
        setProvinceCode();
    });
    
    //省州失去焦点事件
    $("#province").blur(function() {
    	var provinceEname = $("#province").val();
    	if(provinceEname !== ""){
    		setProvinceCode();
    	}
	});
    
    //获取并设置省州二字码
    function setProvinceCode() {
    	var countryCode = $("#countryCode").val();
    	var provinceEname = $("input[name=province]").val();
    	if(countryCode != "" && provinceEname != "") {
    		var provinceCode = getProvinceCodeByProvince(countryCode,provinceEname);
    		$("#provinceCode").val(provinceCode);
    	}
    }
    

    
    function initData() {
    	loadIncoterm($('select[name="incoterm"]'));
    	loadCountryCode($('select[name="countryCode"]'));
    	loadEnumValues('InventoryType',$('select[name="outboundType"]'),true);// 加载数据
		loadOwner($('select[name="owner"]'));// 加载货主数据
		//loadBaseChannel($('select[name="expressCode"]'));// 加载承运商数据
		//loadWarehouse($('select[name="warehouseCode"]'));// 加载仓库数据
	}
    // 初始化控件数据
    $(document).ready(function(){
	    initData();
    });
   

});

