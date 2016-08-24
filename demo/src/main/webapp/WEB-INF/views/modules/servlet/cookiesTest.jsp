<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/8/24
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<html>
<head>
    <title>Cookies测试</title>
</head>
<body>
<form action="${ctx}/servlet/cookiesTest" method="post">
    <div>
        <label for="userName">用户名：</label>
        <input type="text" id="userName" name="userName"/>
    </div>
    <div>
        <label for="loginName">登录名：</label>
        <input type="text" id="loginName" name="loginName"/>
    </div>
    <div>
        <input type="submit" value="提交"/>
    </div>
</form>
</body>
</html>
