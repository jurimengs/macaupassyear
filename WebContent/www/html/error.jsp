<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    
<meta name="apple-mobile-web-app-capable" content="yes" />    
<meta name="format-detection" content="telephone=no" />  
<title>微信api测试</title>
<%@ include file="/www/include/common.jsp"%>
<style type="text/css">
.wx_priview {
	max-width:320px;
}
</style>

<script type="text/javascript" charset="utf-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" charset="utf-8" src="/www/js/wx-expand.js?v=d"></script>
<script type="text/javascript" charset="utf-8" src="/www/js/jquery-1.11.1.min.js"></script>

<script type="text/javascript">

wx.ready(function(){
	alert("loading success");
});


function codebarScannCallback(res){
	var result = res.resultStr;
	alert(result);
}
</script>

</head>

<body>
用户：${usermeg }

<br />

中奖：${rewardarrary }


<form action="/macaupassyear/queryYearMember.do" method="post">
	<input type="text" name="phoneNumber" value="15921385617" />
	<input type="hidden" name="type" value="1" />
	<input type="submit" value="submit" />
</form>
</body>
</html>
