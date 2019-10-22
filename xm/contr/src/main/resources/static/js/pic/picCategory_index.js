var isupload=false;
$(function () {

    getDate();
    blruArray = new Set();
    var nowSort='-1';
    map = new Map();
})
/*添加三角形的点击效果的css*/
function setSanJiaoXingcss() {
    $(".user-arrow").click(function () {

        if ($(this).hasClass("rotate")) { //点击箭头旋转180度
            $(this).removeClass("rotate");
            $(this).removeClass("open");
            $(this).addClass("rotate1");
            $(this).addClass("noOpen");


        } else { //展开状态

            $(this).removeClass("rotate1"); //再次点击箭头回来
            $(this).removeClass("noOpen");
            $(this).addClass("rotate");
            $(this).addClass("open");
        }

    })

//    $("#list").each(function (index) {
//        number++;
//        if (number % 2 == 0) {
//            $(this).find("td ul li:first-child")
//
//        }
//    })
}

//function removeSanJiaoXing(obj) {//判断改节点下是否有子节点，如果没用，则移除三角形
//    $(obj).each(function (index) {
//          $(this).children().
//    })
//}

function getDate() {

   // var jsonDataStr='{\'id\':1}';
    var jsonDataStr='';
    $.ajax({
        url: '/picCategory',
        dataType:"json",
        contentType: 'application/json; charset=UTF-8',
        type: 'GET',
        data: {'json':jsonDataStr},
        success: function (res) {
            if (res.code==1) {
                var list = res.data
                setDate(list)
                setSanJiaoXingcss(); //增加所有分类一个三角形
                hideAllcategory();//隐藏所有的分类
                setInputblru();
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })
}
function setInputblru(){
    $("#list .inputt").blur(function(){
        var categorysort=$.trim($(this).val());
        if(categorysort==''||isNaN(categorysort)){
            alert("请确保填，且为数字");
            return;
        }
        var t= {"id": $(this).attr("idd").slice(3),"categorySort":$(this).val()};
        var idd=parseInt($(this).attr("idd").slice(3));
        var sort=map.get(idd);
        if(parseInt($(this).val()) != sort){
            blruArray.add(JSON.stringify(t));
        }

    });
}


/*保存排序*/
function saveSort() {
    var str="[";
    blruArray.forEach(function (item) {
        str=str+item.toString() + ",";
    });

    if(str.length>1){
        str=str.substring(0,str.length-1)
    }else{
        alert("请先修改再保存");
        return;
    }
    str+="]";
    console.log(map.size);

    $.ajax({

        // headers必须添加，否则会报415错误
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        url: '/picCategory/list',
        type: "PUT",
        //contentType: "application/json",
        data: str,
        dataType:'json',


        success: function (res) {
            if(res.code== 1){
                alert(res.message);
                window.location.reload();
            }else{
                alert("update fail");
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })
}
function hideAllcategory(){
    $("#list>tr:nth-child(even)").hide();
    /*隐藏所有的li，移除最底层分类的三角形*/
    $("#list tr:nth-child(even)").each(function (index){
        $(this).find("td ul li").each(function (index){
            $(this).hide();//隐藏所有的li
            if($(this).children().length == 1){//判断是不是最底层的li，如果是，则隐藏最底层的三角形
                $(this).children().children().first().children().hide();
            }

        })
    })
}
function showAllcategory(){
    $("#list>tr:nth-child(even)").hide();
    /*隐藏所有的li，移除最底层分类的三角形*/
    $("#list tr:nth-child(even)").each(function (index){
        $(this).find("td ul li").each(function (index){
            $(this).show();//隐藏所有的li
            if($(this).children().length == 1){//判断是不是最底层的li，如果是，则隐藏最底层的三角形
                $(this).children().children().first().children().hide();
            }

        })
    })
}
/*添加货主那一行*/
function setOwnerhtml(owner,id,categorygrade,categorysort,categoryname) {
    var htmlStr = '<tr><td style="border:0px;"><img onclick="toggleOwner(this)" style="margin-left:0px;maigin-top:15px;width:15px;height:12px;float:left;margin-left: 45%;margin-top:3px" src="/static/images/sanjiaoxing.png"  class="user-arrow noOpen" />' + owner + '</td><td style="border:0px" colspan="1"></td><td style="border:0px" colspan="1"><div style="float:left;width:100%;height:40px;padding-top:10px;"><img onclick="/*deleteDate('+id+')*/" style="margin-right:4px;float:right;width:22px;height:18px;" src="/static/images/huishouzhan.png" /><img onclick="/*updateOwner('+id+','+categorysort+','+categorygrade+',\''+owner+'\')*/" style="margin-right:4px;float:right;width:22px;height:18px;" src="/static/images/Withdrawal_edit.png" /> <img onclick="addCategory(\''+id+'\','+categorygrade+',\''+owner+'\')" style="margin-right:4px;float:right;width:22px;height:18px;" src="/static/images/jiahao.png" /></div></td><td style="border:0px" colspan="1"><input idd="idd'+id+'" value="'+categorysort+'" class="inputt" style="width:36px;height:20px;margin-top:10px;padding:0 0 0 8px;" /></td></tr><tr><td style="border:0px" colspan="4"><ul id="id' + id + '"></ul></td></tr>'
    return htmlStr;
}

/*第一级分类的显示与隐藏的点击效果*/
function toggleOwner(obj){
    $(obj).parent().parent().next().toggle();
    $(obj).parent().parent().next().children().children().children().toggle();
}
/*添加子分类的数据*/
function setElement(categorygrade, str, id, parentcategory,categorysort,owner) {
//    if (categorygrade % 2 == 1) {
    return setli(categorygrade, str, id, parentcategory,categorysort,owner);
//    } else {
//      return setul(categorygrade, str, id, parentcategory,categorysort,owner);
    //}
}

function setli(categorygrade, categoryname, id, parentcategory,categorysort,owner) {
    // var htmlStr='<li  id=id'+id+'><div style="float:left;width:'+(170+categorygrade*40)+'px;"><img onclick="$(this).parent().next().next().nextAll().toggle()" style="width:15px;height:12px;margin-top:15px;" src="/images/sanjiaoxing.png" class="user-arrow" /></div><div style="height:40px;line-height:40px;border-bottom:1px solid #D1E4E8;"><div style="float:left">'+str+'</div></div><div style="float:right;width:13.95%;padding-bottom:0px;"><div style="float:left;width:49.8%"><img style="float:right;width:15px;height:12px;" src="/images/huishouzhan.png" /><img style="float:right;width:15px;height:12px;" src="/images/Withdrawal_edit.png" /> <img style="float:right;width:15px;height:12px;" src="/images/jiahao.png" /></div><div style="float;width:50.2%"></div> <div></li>';
    var htmlStr = '<li  id=id' + id + '><div style="height:40px;line-height:40px;border-bottom:1px solid #D1E4E8;"><div style="float:left;height:27px;width:' + (170 + categorygrade * 40) + 'px;"><img onclick="$(this).parent().parent().nextAll().toggle()" style="width:15px;height:12px;margin-top:15px;" src="/static/images/sanjiaoxing.png" class="user-arrow noOpen" /></div><div style="float:left">' + categoryname + '</div><div style="float:right;width:16.95%;padding-bottom:0px;"><div style="float:left;width:54.8%;height:40px;padding-top:15px;"><img onclick="deleteDate(\''+id+'\')" style="margin-left:4px;float:right;width:22px;height:18px;" src="/static/images/huishouzhan.png" /><img onclick="updateCategory(\''+id+'\',\''+owner+'\',\''+categoryname+'\','+categorysort+')"  style="margin-left:4px;float:right;width:22px;height:18px;" src="/static/images/Withdrawal_edit.png" /> <img categorygrade='+categorygrade+' onclick="addCategory(\''+id+'\','+categorygrade+',\''+owner+'\')" style="margin-left:4px;float:right;width:22px;height:18px;" src="/static/images/jiahao.png" /></div><div style="float;width:45.2%;height:40px;line-height:40px;"><input idd="idd'+id+'" value="'+categorysort+'"  class="inputt" style="width:36px;height:20px;margin-top:10px;margin-left:21%;padding:0 0 0 8px;" /> <input type="file" id="picidd'+id+'" multiple="multiple" /> <button onclick="uploadPic(\''+id+'\')">上传</button> </div> <div></div></li>';

    return htmlStr;
}
//
//function setul(categorygrade, str, id, parentcategory,categorysort,owner) {
//    var htmlStr = '<li  id=id' + id + '><div style="height:40px;line-height:40px;border-bottom:1px solid #D1E4E8;"><div style="float:left;height:27px;width:' + (170 + categorygrade * 40) + 'px;"><img onclick="$(this).parent().parent().nextAll().toggle()" style="width:15px;height:12px;margin-top:15px;" src="/images/sanjiaoxing.png" class="user-arrow noOpen" /></div><div style="float:left">' + str + '</div><div style="float:right;width:13.95%;padding-bottom:0px;"><div style="float:left;width:49.8%;height:40px;padding-top:15px;"><img onclick="deleteDate('+id+')" style="float:right;width:15px;height:12px;" src="/images/huishouzhan.png" /><img onclick="updateCategory('+id+')"  style="float:right;width:15px;height:12px;" src="/images/Withdrawal_edit.png" /> <img categorygrade='+categorygrade+' onclick="addCategory('+id+','+categorygrade+',\''+owner+'\')" style="float:right;width:15px;height:12px;" src="/images/jiahao.png" /></div><div style="float;width:50.2%;height:40px;line-height:40px;"><input idd="idd'+id+'" value="'+categorysort+'"  class="inputt" style="width:30px;height:20px;margin-top:10px;margin-left:17%;padding:0px;" /></div> <div></div></li>';
//    //htmlStr=htmlStr+'<div style="width:100%;height:1px;border:1px solid #F60"><div>'
//    return htmlStr;
//}



/*设置数据*/
function setDate(list) {
    var owner = ""; //目前的货主
    var grade = 1; // 目前的分类等级
    var sort = 1; //目前的序号
    var categoryNumber=list.length;
    var ownerNumber=0;
    for (i = 0; i < list.length; i++) {
        map.set(list[i].id,list[i].categorySort);
        if (list[i].categoryGrade == '0') {
            $("#list").append(setOwnerhtml(list[i].owner, list[i].id,list[i].categoryGrade,list[i].categorySort,list[i].categoryName));
            categoryNumber--;
            ownerNumber++;
        }
        if (list[i].categoryGrade != '0') {
            $("#id" + list[i].parentCategory).append(setElement(list[i].categoryGrade, list[i].categoryName, list[i].id, list[i].parentcategory,list[i].categorySort,list[i].owner));
        }

    }
    $("#categoryNumber").text(categoryNumber);
    $("#ownerNumber").text(ownerNumber);
}





/*新增货主*/
function addOwner(id,categorygrade,owner){
    $("#addOwner").show();
}
/*新增货主*/
function submitAddOwner (){
    var categorysort=$("#addOwner input[ name='categorysort']").val();
    var owner=$("#addOwner select[ name='owner']").val();
    var categorygrade=0;
    categorysort=$.trim(categorysort);
    if(categorysort==''||isNaN(categorysort)||owner==''){
        alert("请确保信息填写完整,且正确");
        return;
    }
    var category={"owner":owner,"categoryGrade":categorygrade,"categorySort":categorysort};
    $.ajax({
        url: '/picCategory',
        dataType:"json",
        contentType:"application/json",
        type: 'POST',
        data: JSON.stringify(category),
        success: function (data) {
            if (data.ret == '0') {
                alert("新添成功");
                window.location.reload();
            }else{
                alert(data.msg)
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })


}
/*新增分类*/
function addCategory(id,categorygrade,owner){
    $("#addCategory input[ name='id']").val(id);
    $("#addCategory input[ name='categorygrade']").val(categorygrade+1);
    $("#addCategory_owner").text(owner);
    $("#addCategory").show();
}
/*新增分类*/
function submitAddCategory (){
    var parentcategory=$("#addCategory input[ name='id']").val();
    var owner=$("#addCategory_owner").text();
    var categoryname=$("#addCategory input[ name='categoryname']").val();
    var categorysort=$("#addCategory input[ name='categorysort']").val();
    var categorygrade=$("#addCategory input[ name='categorygrade']").val();
    categorysort=$.trim(categorysort);
    categoryname=$.trim(categoryname);
    if(owner==null||owner.trim()==''||categorysort==''||isNaN(categorysort)||categoryname==''){
        alert("请确保信息填写完整,且正确");
        return;
    }



    var category={"parentCategory":parentcategory,"categoryName":categoryname,"categorySort":categorysort,"categoryGrade":categorygrade};
    $.ajax({
        url: '/picCategory',
        dataType:"json",
        contentType:"application/json",
        type: 'POST',
        data: JSON.stringify(category),
        success: function (res) {
            if (res.code == 0) {
                alert(res.message);
                window.location.reload();
            }else{
                alert(res.message)
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })

}
/*编辑分类*/
function updateCategory(id,owner,categoryname,categorysort){
    $("#updateCategory").show();
    $("#updateCategory input[ name='id']").val(id);
    $("#updateCategory_owner").text(owner);
    $("#updateCategory input[ name='categoryname']").val(categoryname);
    $("#updateCategory input[ name='categorysort']").val(categorysort);
}
/*编辑分类*/
function submitUpdateCategory (){
    var categoryname= $("#updateCategory input[ name='categoryname']").val();
    var categorysort= $("#updateCategory input[ name='categorysort']").val();
    var id = $("#updateCategory input[ name='id']").val();
    categoryname=$.trim(categoryname);
    categorysort=$.trim(categorysort);
    if(categorysort==''||isNaN(categorysort)||categoryname==''){
        alert("请确保信息填写完整,且正确");
        return;
    }


    var category={"id":id,"categoryName":categoryname,"categorySort":categorysort};
    $.ajax({
        url: '/picCategory',
        dataType:"json",
        contentType:"application/json",
        type: 'PUT',
        data: JSON.stringify(category),
        success: function (res) {
            if (res.code == 0) {
                alert(res.message)
                window.location.reload();
            }else{
                alert(res.message)
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })

}
/*编辑货主*/
function updateOwner(id,categorysort,categorygrade,owner){
    $("#updateOwner").show();
    $("#updateOwner_owner").text(owner);
    $("#updateOwner input[ name='id']").val(id);
    //$("#updateOwner_owner").text(owner);
    $("#updateOwner input[ name='categorygrade']").val(categorygrade);
    $("#updateOwner input[ name='categorysort']").val(categorysort);
}
/*编辑货主*/
function submitUpdateOwner (){
    var categorysort= $("#updateOwner input[ name='categorysort']").val();
    var categorygrade= $("#updateOwner input[ name='categorygrade']").val();
    var id = $("#updateOwner input[ name='id']").val();
    categorysort=$.trim(categorysort);
    if(categorysort==''||isNaN(categorysort)){
        alert("请确保信息填写完整,且正确");
        return;
    }


    var category={"id":id,"categorygrade":categorygrade,"categorysort":categorysort};
    $.ajax({
        url: '/product/updateProductCategory',
        dataType:"json",
        contentType:"application/json",
        type: 'POST',
        data: JSON.stringify(category),
        success: function (data) {
            if (data.ret == '0') {
                alert("修改成功");
                window.location.reload();
            }else{
                alert(data.msg)
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })

}
/*关闭新建和编辑的div*/
function closeAll(){
    $("#addOwner").hide();
    $("#addCategory").hide();
    $("#updateCategory").hide();
    $("#updateOwner").hide();

}

/*把所有分类收起*/
function allClose(){
    $(".open").each(function (index){
        $(this).click();
    })
}

function allOpen(){
    $(".noOpen").each(function (index){
        $(this).click();
    })
}

function deleteDate(id){
    var category={"id":id};
    $.ajax({
        url: '/picCategory',
        dataType:"json",
        contentType:"application/json",
        type: 'DELETE',
        data: JSON.stringify(category),
        success: function (res) {
            if (res.code == 1) {
                alert(res.message);
                window.location.reload();
            }else{
                alert(res.message)
            }


        },
        error: function (data) {
            layer.alert("服务内部错误");
        }

    })
}

/**
 * 上传图片
 * @param id
 */
function uploadPic(id) {
    if(isupload==true){
        alert("正在上传请不要重复点击")
        return false;
    }
    isupload=true;
    var formData = new FormData();
    var filespicidd = document.getElementById('picidd' + id);

    var files = filespicidd.files;
    for (i = 0; i < files.length; i++) {
        formData.append('files', files[i]);
    }
    formData.append('id', id+"");
    $.ajax({
        url: '/pic/fileUpload',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        // async:false,
        success: function (res) {
            isupload=false;
            if(res.code==1){
                alert(res.message);
            }else {
                alert(res.message);
                alert("错误名称："+res.data);
            }
        },fail:function (e) {
            isupload=false;

        }
    })
    alert("正在上传中")
}
