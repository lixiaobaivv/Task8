<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="width:500px;margin:0px auto;text-align:center">


    <div style="text-align:center;margin-top:40px">

        <form method="post" action="${pageContext.request.contextPath }/user/${id}" id="myForm">
            <input type="hidden" name="_method" value="POST">
            用户资料： <br><br>
            头像：<input name="userPhoto" value="${u.head_tx}" type="file" id="pics"> <br><br>
            <img src="" alt="" id="img1" style="display: none">
            <input style="width:100px" type="submit" value="上传" id="submit" ><br><br>
            账号名：<input name="name" value="${u.name}" type="text"> <br><br>
            密码：<input name="password" value="${u.password}" type="text"> <br><br>
            手机号：<input name="userIphone" value="${u.userIphone}" type="text"> <br><br>

            邮箱：<input name="userEmail" value="${u.emali}" type="text"> <br><br>
            <input type="hidden" value="${u.id}" name="userId">
            <input type="submit" value="更新用户信息">
        </form>
    </div>
</div>


<script>
    $("#pics").change(function () {
        var objUrl = getObjectURL(this.files[0]);
        console.log("objUrl = " + objUrl);
        console.log();
        if (objUrl) {
            $("#img1").attr("src", objUrl);
            $("#img1").removeClass("hide");
        }
    });
    $('#submit').on('click',function() {
        var formData = new FormData(document.querySelector('#myForm'));
        $.ajax({
            url:'url',
            data:formData,
            type: "POST",
            succuss:function(res){
                console.log(res);
                debugger;
            },
            fail:function(){
                console.log(arguments)
                debugger;
            },
            complete:function(){
                console.log(arguments)
                debugger;
            }
        })
    })
    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        }
        else if (window.URL != undefined) {
            // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        }
        else if (window.webkitURL != undefined) {
            // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }
</script>
<script src="${pageContext.request.contextPath }/static/jquery-3.2.1.js"></script>ipt>