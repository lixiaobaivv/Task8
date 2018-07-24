<%--
  Created by IntelliJ IDEA.
  User: Yzzzt
  Date: 2018/7/8
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
    <json:property name="id" value="${s.id}"/>
    <json:property name="姓名" value="${s.name}"/>
    <json:property name="头像" value="${s.picture_tx}"/>
    <json:property name="描述" value="${s.state_ms}"/>
    <json:property name="创建人" value="${s.create_by}"/>
    <json:property name="更新人" value="${s.update_by}"/>
    <json:property name="创建时间" value="${s.create_at}"/>
    <json:property name="更新时间" value="${s.update_at}"/>
</json:object>