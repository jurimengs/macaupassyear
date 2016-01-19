<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>扫描领奖错误</title>
<%@ include file="common.jsp"%>
<link rel="stylesheet" href="/data/css/base.css" type="text/css">
<link rel="stylesheet" href="/data/css/public.css" type="text/css">
<link rel="stylesheet" href="/data/css/index.css" type="text/css">
</head>
<body>
${errmsg.respCode }
${errmsg.respMsg }
<a href="javascript:void(0);" onclick="window.location.href='/macaupassyear/toHelp.do';">返回扫码页面</a>
</body>
</html>