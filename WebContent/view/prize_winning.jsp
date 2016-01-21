<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>我的奖品</title>
<%@ include file="common.jsp"%>
<link rel="stylesheet" href="/data/css/base.css" type="text/css">
<link rel="stylesheet" href="/data/css/public.css" type="text/css">
<link rel="stylesheet" href="/data/css/index.css" type="text/css">
</head>
<body class="body-bg" style="overflow:hidden">
<!--top-->
<script language="javascript" src="/data/js/header.js?v=<%=b %>" id="headerScript" data-args="headername=我的奖品&headerbackurl=index.html"></script>
<div class="clear main_tit">
	<div class="size-xxxl"><span id="sss"></span><span class="size-s marg_lef_1em">奖</span></div>
    <div class="clear">
    	<div class="fl_left">恭喜中奖，猴年大吉！</div>
    </div>
</div>
<div id="isprize" style="display: none">
<div class="clear">
	<div class="global-tit-trans"></div>
    <div class="global-titleb">您已领奖</div>
</div>
<div class="global-navb height_whole">
    <div class="global-navb height_whole prize-bg">
    </div>
</div>
</div>

<div id="isnoprize" style="display: none">
<div class="clear">
	<div class="global-tit-trans"></div>
    <div class="global-titleb">领奖人较多时，请依次排队领奖哦~</div>
</div>
<div class="global-navb height_whole">
    <div class="code-bg">
    	<p class="center">速去领奖台炫出你的<span class="tit-code">二维码</span>吧！</p>
        <div class="qr-code" id="qrcode"></div>
    </div>
</div>
</div>
<input type="hidden" value="${yearMember.isprize}" id="prizenu"/>
<!--footer-->
<script language="javascript" src="/data/js/footer.js?v=<%=b %>" id="footerScript" data-args="selecttype=index"></script>
<!--footer over-->
<script type="text/javascript" charset="utf-8" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>	
<script type="text/javascript" charset="utf-8" src="/js/jquery.qrcode.min.js?v=<%=b %>"></script>
<script>
var sh;

$(function(){
	var s = ${yearMember.rewardstate};
	var strTransfer = fmtRewardState(s);
	document.getElementById("sss").innerHTML = strTransfer;
});

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
		var second=10000; //间隔时间3秒钟
		sh=setInterval('myprize()',second);
	}
});

function myprize(){
var mobile = ${yearMember.moible};
	$.ajax({
		type:"post",
		url:"/macaupassyear/lotteryDrawState.do",
		data:{
			phoneNumber:mobile
		},
		dataType:"json",
		cache:"false",
		success:query_success,
		error:query_error
	});
}

function query_success(data,status){
	if(data.respCode=='10000'){
		var isprize = data.isprize;
		if(isprize=="0"){	
			document.getElementById("isprize").style.display="";
			document.getElementById("isnoprize").style.display="none";
			clearInterval(sh);
		}else{
			document.getElementById("isprize").style.display="none";
			document.getElementById("isnoprize").style.display="";
		}
	}else{	
		var msg = data.respMsg;
		alert(msg);
	}
}

function query_error(){
	alert("查询抽奖状态失败");
}

var currentPage = 1;
var nextpage = true;
function fmtRewardState(types){
	if(types=='5'){
		return "五等";
	}else if(types=='4'){
		return "四等";
	}else if(types=='3'){
		return "三等";
	}else if(types=='t12'){
		return "特等、一等，二等";
	}else if(types=='bt12'){
		return "补抽";
	}else if(types=='h'){
		return "欢乐";
	}
	
}
function pmtRewardState(types){
	if(types=='5'){
		return "picture5";
	}else if(types=='4'){
		return "picture4";
	}else if(types=='3'){
		return "picture3";
	}else if(types=='t12'){
		return "picture2";
	}else if(types=='bt12'){
		return "picture1";
	}else if(types=='h'){
		return "picture1";
	}
}
function fmtMobile(mobile){
	return mobile.substring(0,3)+"****"+mobile.substring(7,11);
}
// document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
// document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
</script>
</body>

</html>
