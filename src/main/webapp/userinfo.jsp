<%--
  Created by IntelliJ IDEA.
  User: TONKIA
  Date: 2019/3/28
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
    <title>修改用户信息</title>
</head>

<body style="padding: 20px;">
<h1>修改用于信息</h1>

<form method="post" action="/doctor/modify" enctype="multipart/form-data">

    <div class="form-group">
        <label>头像</label>
        <input type="file" name="file" class="form-control"/><br/>
    </div>

    <div class="form-group">
        <label>昵称：</label>
        <input type="text" class="form-control" value="${doctorInfo.nikeName}" name="nikeName"></input>
    </div>

    <div class="form-group">
        <label>擅长：</label>
        <textarea class="form-control" rows="3" name="expert">${doctorInfo.expert}</textarea>
    </div>

    <div class="form-group">
        <label>医院：</label>
        <input type="text" class="form-control" value="${doctorInfo.hospital}" name="hospital"></input>
    </div>

    <div class="form-group">
        <label>职位</label>
        <select class="form-control" name="qualificate">
            <option value="主任医师" <c:if test="${doctorInfo.qualificate=='主任医师'}"> selected </c:if> >主任医师</option>
            <option value="副主任医师" <c:if test="${doctorInfo.qualificate=='副主任医师'}"> selected </c:if> >副主任医师</option>
            <option value="主治医师" <c:if test="${doctorInfo.qualificate=='主治医师'}"> selected </c:if> >主治医师</option>
            <option value="医师" <c:if test="${doctorInfo.qualificate=='医师'}"> selected </c:if> >医师</option>
        </select>
    </div>

    <hr/>
    <input class="btn btn-primary" type="submit" value="提交修改"/>
</form>
</body>
</html>
