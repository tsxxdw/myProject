/**

f @Name：订单管理
 @Author：duke
 */
var statusList=[];
layui.config({
	base: '../../static/' //静态资源所在路径
}).extend({
	index: 'lib/index' //主入口模块
    ,formSelects: '../lib/formSelects/formSelects-v4'
}).use(['jquery', 'index', 'table', 'form', 'laydate', 'formSelects'], function() {
	var $ = layui.$,
		form = layui.form,
		laydate = layui.laydate,
		table = layui.table,
		element = layui.element,
        formSelects = layui.formSelects;
	
    

	statusList = getEnumList('OrderStatus');
    var warehouseMap = $('#dataCache').data('warehousemap');
    var ownerMap = $('#dataCache').data('ownermap');
    // $("#numberValue").focus(function () {
    //     var number = $("#numberValue").val();
    //     number = number.split(';').join('\n');
    //     layer.prompt({
    //         formType: 2,
    //         title: input_tip,
    //         value: number,
    //         btn: [b_yes, b_no],
    //         area: ['500px', '350px'], //自定义文本域宽高
    //         yes: function (index, layero) {
    //             // 获取文本框输入的值
    //             var value = layero.find(".layui-layer-input").val();
    //             if (!value) {
    //                 $("#numberValue").val('');
    //                 layer.close(index);
    //             } else {
    //                 value = value.replace(/\n/g, ";")
    //                 $("#numberValue").val(value);
    //                 layer.close(index);
    //             }
    //         }
    //     })
    // })

    var d=new Date();
    var n=new Date(d.getTime()-86400000*6);
    var eDate = d.pattern("yyyy-MM-dd");
    var sDate = n.pattern("yyyy-MM-dd");
    $("#time").val(sDate + " ~ " + eDate);

	//日期插件渲染
	laydate.render({
	  elem: '#time',
	  type: 'date',
	  range: '~',
	  lang: systemIsChinese ? 'cn' : 'en',
	  format: 'yyyy-MM-dd',
	  max: 0
	});

	//是否超出31天
	function checkDate() {
		var flag = false;
		var v_date = $("#time").val();
		if(v_date != null) {
			var sDate =  new Date(Date.parse(v_date.split(" ~ ")[0].replace(/-/g, "/")))
			var eDate =  new Date(Date.parse(v_date.split(" ~ ")[1].replace(/-/g, "/")))
			var iDay = dateDifference(sDate,eDate);
			if(iDay > 31) {
				flag = true;
			}
		}
		return flag;
	}
	//开始日期sDate1和结束日期sDate2是yyyy-MM-dd格式 
	function dateDifference(sDate1, sDate2) {
	    var dateSpan, tempDate, iDays;
	    sDate1 = Date.parse(sDate1);
	    sDate2 = Date.parse(sDate2);
	    dateSpan = sDate2 - sDate1;
	    dateSpan = Math.abs(dateSpan);
	    iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
	    return iDays;
    };

	//表格渲染
	table.render({
      elem: '#order-table-toolbar',
      url: '/order/list',
      method: 'post',
      toolbar: '#order-table-toolbar-toolbarDemo',
      parseData: function(res){ //res 即为原始返回的数据
    	  return {
    		  "code": res.code, //解析接口状态
    		  "msg": res.msg, //解析提示文本
    		  "count": res.count, //解析数据长度
    		  "data": res.data==null?null:res.data.orderList, //解析数据列表
    		  "data1": res.data==null?null:res.data.statusCntList // 状态数量列表
    	  };
      },
      cols: [[
        {type: 'checkbox', fixed: 'left'},
        //{field:'id', title:v_number, width:200, fixed: 'left', unresize: true, sort: true},
        {field: 'refNumber', title: v_refnumber, width: 200, fixed: 'left',  sort: true},
        {field: 'warehouseCode', title: v_warehouse, width: 180, sort: true, templet: function (d) {
            var showName = systemIsChinese == true ? 'cnName' : 'enName';
            return null != d.warehouseCode ? (warehouseMap[d.warehouseCode] == undefined ? "" : warehouseMap[d.warehouseCode][showName]) : "";
        }},
        {field: 'owner', title: v_owner, width: 180, templet: function (d) {
            return null != d.owner ? (ownerMap[d.owner] == undefined ? "" : ownerMap[d.owner]['name']) : "";
        }},
        {field: 'trackingNo', title: v_trackingno, width: 180},
        {field: 'expressCode', title: v_expresscode, width: 150, sort: true},
        {field: 'countryCode', title: v_countrycode, width: 150},
        {field: 'sumNum', title: v_sumnum, width: 150},
        {field: 'createDate', title: v_createdate, width: 200, sort: true, templet: function (item) {
                return new Date(item.createDate).pattern("yyyy/MM/dd HH:mm");
            }
        },
        {field: 'deliveryTime', title: v_deliverytime, width: 200, sort: true, templet: function (item) {
        	return new Date(item.deliveryTime).pattern("yyyy/MM/dd HH:mm");
        }
        },
        {field: 'orderType', title: v_ordertype, width: 150, templet: function (data) {
	            return typeMap[data.orderType];
	        }
        },
        {field: 'status', title: v_status, width: 150, templet: function (data) {
        		for(i=0;i<statusList.length;i++){
        			if(statusList[i].data == data.status ){
        				return statusList[i].name;
        			}
        		}
	        }
        },
        {fixed: 'right', title: v_operate, minWidth: 150, templet: function (item) {
	            var option = '<a class="layui-btn un-background-btn" lay-event="see" title=' + v_see + '><i class="layui-icon icon-eye"></i></a>';
	            if (item.status === 0 || item.status === 9) {
	            	option += '<a class="layui-btn un-background-btn" lay-event="edit" title=' + v_edit + '><i class="layui-icon icon-border-color"></i></a>';
	            } else {
	            	option +='<a class="layui-btn un-background-btn layui-btn-disabled" title=' + v_edit + '><i class="layui-icon icon-border-color"></i></a>';
	            }
                  option += '<a class="layui-btn un-background-btn" lay-event="copy" title=' + v_copy + '><i class="layui-icon icon-docs"></i></a>';
	            return option;
	        }
        }
      ]],
      page: true,
	  where: $("#searchForm").serializeObject(),
      done: function (res) {
          var statusCnt = res.data1;
          //重置状态标签数量
          $("span[id^=status_]").text(0);
          if (statusCnt != null) {
              $.each(statusCnt, function (index, item) {
                  $("#status_" + item.status).text(item.sumCnt);
              });
          }
      }
    });

    //头工具栏事件
    table.on('toolbar(order-table-toolbar)', function(obj){
      var checkStatus = table.checkStatus(obj.config.id);
       var idList = getCheckboxId(checkStatus);
       var number = idList.length;
      switch(obj.event){
        case 'getCheckData':
          var data = checkStatus.data;
          layer.alert(JSON.stringify(data));
          break;
        case 'getCheckLength':
          var data = checkStatus.data;
          layer.msg('选中了：'+ data.length + ' 个');
          break;
        case 'isAll':
          layer.msg(checkStatus.isAll ? '全选': '未全选');
          break;
        case 'lay_event_add':
          $('body').append('<a id="add-link-btn" lay-href="order/add" style="display:none;" lay-text="' + v_createnew + '"></a>');
          $('#add-link-btn').click().remove();
          break;
        case 'storageCheckData':
        	//新建已预报状态改为暂存
        	var data = checkStatus.data;
            var ids = new Array();
            $.each(data,function (index,element) {
              ids.push(element.id);
            })
            if(ids.length <= 0){
            	layer.alert(v_choose_order, {icon:0,btn: pageConfirm, title: v_title});
            	return;
            }
            layer.confirm(v_storage_confirm, {
                btn: [yes,no],
                title: v_title, //按钮
                icon: 3
            }, function(){
            	 var status = $("#status").val();
            	 $.ajax({
                     url: '/order/updateOrderStatus',
                     dataType: 'json',
                     type: 'POST',
                     contentType: 'application/json',
                     data: JSON.stringify({orderIds:ids,sourceStatus:status,targetStatus:9}),
                     success: function (data) {
                    	console.log(JSON.stringify(data));
                     	if (data.code == '0') {
                     		var errCount = data.count;
                     		var allCount = ids.length;
                     		var msg = "";
                            var sucCount = allCount - errCount;
                            if(errCount > 0){
                     			msg =storage_suc+sucCount+ "<br/>";
                     			msg += storage_fail+errCount+"<br/>";
                     			msg += data.msg;
                     		}else{
                     			msg =storage_suc+allCount+"<br/>";
                     			msg += storage_fail+ " 0";
                     		}
                            if(sucCount==allCount){
                                successMsg(msg,function(){
                                    window.location.reload();
                                });
                            }else {
                                errorMsg(msg);
                            }
                     	}else{
                     		errorMsg(data.msg);
                     	}

                     }
                 })
            }, function(){
                layer.close();
            });
            break
        case 'newCheckData':
        	//暂存状态改为新建
        	var data = checkStatus.data;
            var ids = new Array();
            $.each(data,function (index,element) {
              ids.push(element.id);
            })
            if(ids.length <= 0){
            	layer.alert(v_choose_order, {icon:0,btn: pageConfirm, title: v_title});
            	return;
            }

            layer.confirm(v_new_confirm, {
                btn: [yes,no], //按钮
                title: v_title,
                icon: 3
            }, function(){
            	 $.ajax({
                     url: '/order/updateOrderStatus',
                     dataType: 'json',
                     type: 'POST',
                     contentType: 'application/json',
                     data: JSON.stringify({orderIds:ids,sourceStatus:9,targetStatus:0}),
                     success: function (data) {
                     	if (data.code == '0') {
                     		var errCount = data.count;
                     		var allCount = ids.length;
                     		var msg = "";
                            var sucCount = allCount - errCount;
                            if(errCount > 0){
                     			msg =transfer_succ+sucCount+ "<br/>";
                     			msg += transfer_fail+errCount+"<br/>";
                     			msg += data.msg;
                     		}else{
                     			msg =transfer_succ+allCount+"<br/>";
                     			msg += transfer_fail+" 0";
                     		}
                     		if(sucCount==allCount){
                                successMsg(msg,function(){
                                    window.location.reload();
                                });
                            }else {
                                errorMsg(msg);
                            }
                     	} else {
                     		errorMsg(data.msg);
                     	}
                     }
                 })
            }, function(){
                layer.close();
            });
            break;
          case 'forecast':
              var data = checkStatus.data;
              var ids = new Array();
              $.each(data,function (index,element) {
                  ids.push(element.id);
              });
              if(ids.length <= 0){
                  layer.alert(v_choose_order, {icon:0,btn: pageConfirm, title: v_title});
                  return;
              }
              layer.confirm(v_forecast_confirm, {
                  btn: [yes, no], //按钮
                  title: v_title,
                  icon: 3
              }, function () {
                  $.ajax({
                      url: '/order/forecast',
                      data: JSON.stringify(ids),
                      type: 'POST',
                      dateType: 'json',
                      contentType: 'application/json',
                      success: function (data) {
                          var msg = '';
                          $.each(data.data, function (index, element) {
                              msg = element.data + element.msg + '</br>' + msg;
                          })
                          if (data.code == '0') {
                              successMsg(msg);
                          } else {
                              errorMsg(msg);
                          }
                      }
                  })
              }, function () {
                  layer.close();
              });
              break;
          case 'issue':
              var data = checkStatus.data;
              var ids = new Array();
              $.each(data,function (index,element) {
                  ids.push(element.id);
              });
              if(ids.length <= 0){
                  layer.alert(v_choose_order, {icon:0,btn: pageConfirm, title: v_title});
                  return;
              }
              layer.confirm(v_issue_confirm, {
                  btn: [yes, no], //按钮
                  title: v_title,
                  icon: 3
              }, function () {
                  $.ajax({
                      url: '/order/issue',
                      data: JSON.stringify(ids),
                      type: 'POST',
                      dateType: 'json',
                      contentType: 'application/json',
                      success: function (data) {
                          var msg = '';
                          $.each(data.data, function (index, element) {
                              msg = element.data + element.msg + '</br>' + msg;
                          })
                          if (data.code == '0') {
                              successMsg(msg);
                          } else {
                              errorMsg(msg);
                          }
                      }
                  })

              }, function () {
                  layer.close();
              })
              break;
          case 'cancel':
              var data = checkStatus.data;
              var ids = new Array();
              $.each(data, function (index, element) {
                  ids.push(element.id);
              });
              if (ids.length <= 0) {
                  layer.alert(v_choose_order, {icon:0,btn: pageConfirm, title: v_title});
                  return;
              }
              layer.confirm(v_cancel_confirm, {
                  btn: [yes, no], //按钮
                  title: v_title,
                  icon: 3
              }, function () {
                  $.ajax({
                      url: '/order/interceptOrder',
                      data: JSON.stringify(ids),
                      type: 'POST',
                      dateType: 'json',
                      contentType: 'application/json',
                      success: function (data) {
                          var msg = data.msg;
                          if (data.code == '0') {
                              successMsg(msg);
                          } else {
                              errorMsg(msg);
                          }
                      }
                  })

              }, function () {
                  layer.close();
              })
              break;
          case 'intercept':
              var data = checkStatus.data;
              var ids = new Array();
              $.each(data, function (index, element) {
                  ids.push(element.id);
              });
              if (ids.length <= 0) {
                  layer.alert(v_choose_order, {icon:0,btn: pageConfirm, title: v_title});
                  return;
              }
              layer.confirm(v_intercept_confirm, {
                  btn: [yes, no], //按钮
                  title: v_title,
                  icon: 3
              }, function () {
                  $.ajax({
                      url: '/order/interceptOrder',
                      data: JSON.stringify(ids),
                      type: 'POST',
                      dateType: 'json',
                      contentType: 'application/json',
                      success: function (data) {
                          if (data.code == '0') {
                              successMsg(data.msg);
                          } else {
                              errorMsg(data.msg);
                          }
                      }
                  })

              }, function () {
                  layer.close();
              })
              break;
          case 'export':
              if(number > 0){
                  layer.confirm(confirmExportCheckedRecord + "?",
                      {
                          icon:3,
                          title:info,
                          btn: [yes, no]
                      },
                      function (index) {
                          $('#checkBoxIdList').val(idList);
                          $('#searchForm').attr("target", "export_iframe");
                          $('#searchForm').attr("action", "/order/exportOrder");
                          $('#searchForm').submit();
                          layer.close(index);
                      });
              }else {
                  layer.confirm(verifyThatAllRecordsAreExported + "?",
                      {
                          icon:3,
                          title:info,
                          btn: [yes, no]
                      },
                      function (index) {
                          $('#checkBoxIdList').val(idList);
                          $('#searchForm').attr("target", "export_iframe");
                          $('#searchForm').attr("action", "/order/exportOrder");
                          $('#searchForm').submit();
                          layer.close(index);
                      });
                  return;
              }
              break;
      };
    });

    function getCheckboxId(checkStatus) {
        var arr = new Array();
        var data = checkStatus.data;
        data.forEach(function (item) {
            arr.push(item.id);
        });
        return arr.length>0?arr:'';
    }

    //监听行工具事件
    table.on('tool(order-table-toolbar)', function(obj){
      var data = obj.data;
      if(obj.event === 'see'){
          layer.open({
              type:2,
              skin: 'open-class',
              area: ['900px', '500px'],
              title: v_refnumber + "：" + data.refNumber,
              content: "../../order/getDetailInfo/" + data.id
              //,shade: 0
              ,maxmin: true
              ,zIndex: layer.zIndex //重点1
         	});
      } else if(obj.event === 'edit') {
    	  $('body').append('<a id="order-update-btn" lay-href="order/update/' + data.id +'" style="display:none;" lay-text="'+v_create+'/'+v_orderupdate+'"></a>');
          $('#order-update-btn').click().remove();
      }else if(obj.event == 'copy'){
          $('body').append('<a id="order-copy-btn" lay-href="order/update/' + data.id +'?type=1'+'" style="display:none;" lay-text="'+v_create+'/'+v_orderupdate+'"></a>');
          $('#order-copy-btn').click().remove();
      }
    });

	//监听搜索
	form.on('submit(LAY-user-front-search)', function(data) {
        var params = $("#searchForm").serializeObject();
        var warehouseCodes = formSelects.value("warehouseCodes", "val");
        if (warehouseCodes != null && warehouseCodes.length > 0) {
            params['warehouseCodes'] = warehouseCodes.join(",");
        }else {
            params['warehouseCodes'] = "";
        }
        var expressCodes = formSelects.value("expressCodes", "val");
        if (expressCodes != null && expressCodes.length > 0) {
            params['expressCodes'] = expressCodes.join(",");
        }else {
            params['expressCodes'] = "";
        }
		//执行重载
		table.reload('order-table-toolbar', {
			where: params,
            page: {
                curr: 1 //重新从第 1 页开始
            }
		});
		return false;
	});

	//下拉选择事件监听
	form.on('select(timeInfo)', function(data){
		console.log(JSON.stringify(data));
		$("#time").val('');
	});
	form.on('select(numberType)', function(data){
		$("#numberValue").val('');
	});
	form.on('select(userInfo)', function(data){
		$("#userValue").val('');
	});


	$(document).on('click', '#batchInput', function () {
        var number = $("#numberValue").val();
        number = number.split(';').join('\n');
        layer.prompt({
            formType: 2,
            title: input_tip,
            value: number,
            btn: [b_yes, b_no],
            area: ['500px', '350px'], //自定义文本域宽高
            yes: function (index, layero) {
                // 获取文本框输入的值
                var value = layero.find(".layui-layer-input").val();
                if (!value) {
                    $("#numberValue").val('');
                    layer.close(index);
                } else {
                    value = value.replace(/\n/g, ";")
                    $("#numberValue").val(value);
                    layer.close(index);
                }
            }
        })
    });

	//状态切换
    $(document).on('click','#tabUlId li', function (obj) {
        var status = this.getAttribute('lay-id');
        $("#status").val(status);

        $('.layui-nav-child .layui-this').removeClass('layui-this');
        $('#lockedStatus').val('');
        $("#checkBoxIdList").val('');
        var params = $("#searchForm").serializeObject();
        var toolbar = $("#order-table-toolbar").html();
        var html ="";
        status = parseInt(status);
        if(status == 10){//已预报
            html = $("#toolbar-options0").html();
        }else if(status == 0){//新建
            html = $("#storage").html();
        }else if(status == 9 ){//暂存
            html =$("#newCheck").html();
            //alert(html);
        }else if(status == 3) {
            html = $("#intercept").html(); //拦截
        }else {
            html = $("#commomExport").html();
        }
        

        table.reload('order-table-toolbar', {
            where: params,
            toolbar: html,
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        return false;
    }).on('click','#tabUlId li dl dd',function () {
        var status = $(this).closest('li').attr('lay-id');
        $("#status").val(status);
        var sbtn = $(this).find('a');
        if(sbtn!=null && sbtn.length>0){
            var code = sbtn.data('code');
            $('#lockedStatus').val(code);
        }else{
            $('.layui-nav-child .layui-this').removeClass('layui-this');
            $('#lockedStatus').val('');
            $("#checkBoxIdList").val('');
        }
        var params = $("#searchForm").serializeObject();
        var toolbar = $("#order-table-toolbar").html();
        var html ="";
        status = parseInt(status);
        if(status == 10){//已预报
            html = $("#toolbar-options0").html();
        }else if(status == 0){//新建
            html = $("#storage").html();
        }else if(status == 9 ){//暂存
            html =$("#newCheck").html();
        }else if(status == 3) {
            html = $("#intercept").html(); //拦截
        }else {
            html = $("#commomExport").html();
        }
        
        table.reload('order-table-toolbar', {
            where: params,
            toolbar: html,
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        return false;
    });
    /*element.on('tab(component-tabs-hash)', function (obj) {
        var status = this.getAttribute('lay-id');
        $("#status").val(status);
        var sbtn = $(this).find('dl .layui-this a');
        if(sbtn!=null && sbtn.length>0){
            var code = sbtn.data('code');
            $('#lockedStatus').val(code);
        }else{
            $('.layui-nav-child .layui-this').removeClass('layui-this');
            $('#lockedStatus').val('');
            $("#checkBoxIdList").val('');
        }
        var params = $("#searchForm").serializeObject();
        var toolbar = $("#order-table-toolbar").html();
        //重置状态标签数量
        $("span[id^=status_]").text(0);
        var html ="";
        status = parseInt(status);
        if(status == 10){//已预报
        	html = $("#toolbar-options0").html();
        }else if(status == 0){//新建
        	html = $("#storage").html();
        }else if(status == 9 ){//暂存
        	html =$("#newCheck").html();
        	//alert(html);
        }else if(status == 3) {
            html = $("#intercept").html(); //拦截
        }else {
            html = $("#commomExport").html();
        }
        table.reload('order-table-toolbar', {
            where: params,
            toolbar: html,
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        return false;
    });*/

    function initData() {
		loadOwner($('select[name="owner"]'));// 加载货主数据
		loadChannel();// 加载承运商数据
		loadWarehouse();// 加载仓库数据
	}
    // 初始化控件数据
    $(document).ready(function(){
	    initData();
    });

    /**
     * 加载仓库
     * @param{} item 控件对象
     * @param{} isProperty 是否为属性
     */
    function loadWarehouse() {
        layui.use('form', function(){
            var $ = layui.$;
            // 后台数据请求
            $.post("/common/warehouse", '', function(res) {
                if (res.code == '0') {
                    if (res.data == null || res.data.length == 0) {
                        return;
                    }
                    var dataValue = $("select[xm-select='warehouseCodes']").attr("value");
                    var dataValueArr = [];
                    if (dataValue != null) {
                        dataValueArr = dataValue.split(",");
                    }
                    var dataArr = [];
                    for(var i = 0; i < res.data.length; i++){
                        var value = res.data[i].code;
                        var name = systemIsChinese ? res.data[i].cnName : res.data[i].enName;
                        dataArr.push({"name":name, "value": value, "selected": $.inArray(value, dataValueArr) != -1 ? "selected" : ""})
                    }
                    // 本地初始化数据
                    formSelects.data("warehouseCodes", 'local', {arr: dataArr});
                } else {
                    layer.msg(res.msg);
                }
            });
        });
    }

    /**
     * 加载渠道
     * @param{} item 控件对象
     * @param{} isProperty 是否为属性
     */
    function loadChannel() {
        layui.use('form', function(){
            var $ = layui.$;
            // 后台数据请求
            $.post("/common/channel", '', function(res) {
                if (res.code == '0') {
                    if (res.data == null || res.data.length == 0) {
                        return;
                    }
                    var dataValue = $("select[xm-select='expressCodes']").attr("value");
                    var dataValueArr = [];
                    if (dataValue != null) {
                        dataValueArr = dataValue.split(",");
                    }
                    var dataArr = [];
                    for(var i = 0; i < res.data.length; i++){
                        var value = res.data[i].channelCode;
                        dataArr.push({"name":value, "value": value, "selected": $.inArray(value, dataValueArr) != -1 ? "selected" : ""})
                    }
                    // 本地初始化数据
                    formSelects.data("expressCodes", 'local', {arr: dataArr});
                } else {
                    layer.msg(res.msg);
                }
            });
        });
    }
});
