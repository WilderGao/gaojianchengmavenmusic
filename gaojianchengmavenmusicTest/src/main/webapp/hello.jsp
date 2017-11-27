<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/21
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/hello/hi" method="post" enctype="multipart/form-data">
    <input type="file" name="fileId" value="文件上传">
    <input type="submit" value="上传">
</form>
</body>
</html>
