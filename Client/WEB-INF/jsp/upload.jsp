<%--
  Created by IntelliJ IDEA.
  User: Yzzzt
  Date: 2018/7/12
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p align="center">上传文件选择
    <%--<form action="/upload" method="post" enctype="multipart/form-data">
        　　文件名: <input type="file" name="upfile"/> <br/>
        　　<input type="submit" value="提交" />
    </form>--%>
    <img src = "${message}">
<div>
    <img src="${u.head_tx}">
</div>
<form action="${pageContext.request.contextPath }/upload/${u.id} " method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="hidden" value="${u.id}" name="id">
    <input type="submit" value="提交">
</form>
</body>
</html>
