<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/8/23
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form action="${ctx}/sys/loginAction" method="post">
    <div>
        <label for="userName">账户：</label>
        <input type="text" id="userName" name="userName"/>
    </div>
    <div>
        <label for="password">密码：</label>
        <input type="password" id="password" name="password"/>
    </div>
    <div>
        <input type="submit" value="登录"/>
    </div>
</form>
</body>
</html>
