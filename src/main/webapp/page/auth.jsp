<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />


    <title>认证</title>
    <meta name="keywords" content="汇方平台,专注于基层诊所诊前一站式服务">
    <meta name="description" content="汇方平台,专注于基层诊所诊前一站式服务">
</head>
<body>

    <div>
        <a href="<%=path%>/auth/redirectToAuth"><img src="<%=path%>/asset/image/wx_mp_auth_250-50.png" /></a>
    </div>
</body>
</html>