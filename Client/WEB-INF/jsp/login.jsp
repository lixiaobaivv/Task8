<%--
  Created by IntelliJ IDEA.
  User: Yzzzt
  Date: 2018/6/13
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 align="center">登录界面</h2>
<form name=form1 action="${pageContext.request.contextPath }/doLogin" method="post" onsubmit="return isValidate(form1)">
    <table align="center">
        <tr>
            <td>用户ID：</td>
            <td><input type="text" name="name" >6~8位</td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" >6~8位</td>
        </tr>

        <tr>
            <td><input type="button"  value="注册" onclick="location.href='${pageContext.request.contextPath }/register'"></td>
            <td><input type="reset" value="重置"></td>
            <td><input type="reset" value="使用手机登录" onclick="location.href='${pageContext.request.contextPath }/iphone'"></td>
            <td><input type="submit" value="确定"></td>
        </tr>
    </table>
</form>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="demo-2">
    <div class="">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>

        </div>
    </div>
</div>
<div class="container">
    <div class="login_header">
        <span></span>
    </div>


    <div class="form_header">
        <h1 id="logo">Login</h1>
        <h2 id="subheading">To explore and discover</h2>
    </div>
    <div class="signup_forms">
        <div id="signup_forms_container" class="signup_forms_container">
            <form class="signup_form_form" id="sign_form" method="post" action="${pageContext.request.contextPath }/doLogin">
                <div class="input_outer" &lt;%&ndash;id="signup_account"&ndash;%&gt;
                    &lt;%&ndash;                    <div class="input_outer">
                                            <span class="u_user"></span>
                                            <input name="name" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
                                        </div>&ndash;%&gt;
                    <div class="form_user"><input type="text" name="name" placeholder="name" id="name"></div>
                    <div class="form_password"><input type="password" name="password" placeholder="Password" id="signup_password"></div>
                </div>
                <button type="submit" id="signup_forms_submit"><span><strong>Log in</strong></span></button>
            </form>
        </div>
    </div>
    <div class="footer">
        <div class="footer_signup_link">
            <a class="signup_link" href="register">注册</a>
            <a href="../../index.html" target="_blank" class="link unnamed_1">index</a>
            <a href="../../index.html" target="_blank" class="link unnamed_2">Contact</a>
        </div>
        <div class="design_show">
            <div class="designer_info">
                <a href="https://shop303982252.taobao.com">Designed by Class 2 Software 11 <strong style="font-family:'微软雅黑'">qq空间</strong></a>
                <a href="#" target="_blank" class="face"><img id="face" src="${pageContext.request.contextPath }/static/images/face.jpg" alt="designed by RayZhang"/></a>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/js/demo-2.js"></script>
--%>
