<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>中奖查询</title>
<%@ include file="common.jsp"%>
<link rel="stylesheet" href="/data/css/base.css" type="text/css">
<link rel="stylesheet" href="/data/css/public.css" type="text/css">
<link rel="stylesheet" href="/data/css/index.css" type="text/css">
</head>
<body>
<form action="/macaupassyear/queryAwardUser.do">
	<input name="moible" id="moible" style="border: 1px solid #ccc;"/>
	<input type="submit" value="查询" />
</form>
<div>
	<c:if test="${!empty aim}">
		<table>
		<tr>
			<td>姓名</td>
			<td>手机</td>
			<td>是否中奖</td>
			<td>操作</td>
		</tr>
		<tr>
			<td>${aim.memname }</td>
			<td>${aim.moible }</td>
			<td>
				<c:if test="${!empty aim.rewardstate}">
					${aim.rewardstate }
				</c:if>
				<c:if test="${empty aim.rewardstate}">
					未中奖
				</c:if>
			</td>
			<td>
				<c:if test="${!empty aim.rewardstate && aim.isprize != '0'}">
					<a href="http://payment-test.sandpay.com.cn/macaupassyear/acceptAward.do?phoneNumber=${moible }">发奖</a>
				</c:if>
			</td>
		</tr>
		</table>
	</c:if>
</div>
<script type="text/javascript" charset="utf-8" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>	
<script>

$(document).ready(function(){
	var moible = "http://payment-test.sandpay.com.cn/macaupassyear/acceptAward.do?phoneNumber="+"${yearMember.moible}";
	jQuery('#qrcode').qrcode({width: 138,height: 138,text: moible});
	var isprize = document.getElementById("prizenu").value;
	if(isprize=="0"){	
		document.getElementById("isprize").style.display="";
		document.getElementById("isnoprize").style.display="none";
	}else{
		document.getElementById("isprize").style.display="none";
		document.getElementById("isnoprize").style.display="";
		var second=3000; //间隔时间3秒钟
		setInterval('myprize()',second);
	}
});

function myprize(){
	window.location.href="/macaupassyear/queryMyPrize.do?phoneNumber=${yearMember.moible}";
}
</script>
</body>

</html>
