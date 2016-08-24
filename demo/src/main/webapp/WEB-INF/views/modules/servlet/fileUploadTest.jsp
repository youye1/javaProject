<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/8/24
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglibs.jsp"%>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h4>文件上传测试</h4>
<label>请选择上传的文件：</label>
<form action="${ctx}/servlet/fileUploadServletTest"
      method="post" enctype="multipart/form-data">
    <input type="file" name="file" id="file"/>
    <br/>
    <input type="submit" value="上传"/>

</form>
</body>
</html>
