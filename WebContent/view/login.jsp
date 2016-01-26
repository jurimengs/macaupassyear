<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1, user-scalable=0" />
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no,email=no" name="format-detection" />
<title>登录</title>
<%@ include file="common.jsp"%>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>
<link rel="stylesheet" href="/data/css/base.css" type="text/css">
<link rel="stylesheet" href="/data/css/public.css" type="text/css">
<style type="text/css">
#blessDivLeft , #blessDivRight {
	position: absolute;
	top: 55px;
	background:#FFFF37;
	width:10%;
	opacity:0.7;
	text-align: center;
	height: 55%;
	padding-top:15px;
	display: none;
}

#blessDivMiddle {
	position: absolute;
	top: 20px;
	left: 34%;
	background:#FFFF37;
	height:25px;
	width:30%;
	opacity:0.7;
	text-align: center;
	display: none;
	line-height: 26px;
}

#blessDivLeft {
	left: 20px;
}

.spreadBtn {
	margin: 1.2em 0.9em;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #fff;
	font-size: 1.2em;
	-moz-border-radius: 2px;      /* Gecko browsers */
    -webkit-border-radius: 2px;   /* Webkit browsers */
    border-radius:2px;  
}

#blessDivRight {
	right: 20px;
}
.mask{
	background-color: black; width: 100%; height: 100%; display: none;
}
</style>
</head>
<body class="login-bg">
	<div class="mask"></div><!-- 做黑幕层的 -->
    <div class="default"></div>
    <div id="blessDiv">
    	<c:set var="leftwords" value="一 纪 耕 耘 拼 梦 想 百 俊 树 杉 成 林" />
    	<c:set var="rightwords" value="十 载 力 行 待 远 航 千 才 康 德 流 馨" />
    	<c:set var="words" value="共圆金融梦" />
    	<div id="blessDivLeft">
	    	<c:forEach items="${fn:split(leftwords,' ') }" var="lw">
	    		${lw }<br />
	    	</c:forEach>
    	</div>
    	<div id="blessDivMiddle">${words }</div>
	    <div id="blessDivRight">
	    	<c:forEach items="${fn:split(rightwords,' ') }" var="lw">
	    		${lw }<br />
	    	</c:forEach>
	    </div>
    	
	    <!-- <div id="blessDivLeft">一<br />张<br />卡<br />逐<br />鹿<br />天<br />下<br />仰<br />望<br />我<br />辈</div>
	    <div id="blessDivMiddle">水漫大地</div>
	    <div id="blessDivRight">天<br />下<br />人<br />尽<br />在<br />杉<br />德<br />财<br />富<br />相<br />随</div> -->
    </div>
    <div class="login-head-logo">
    	<p class="load_big_tit size-xl center">歡迎登入 <span style="color:#ff962d;"> 【澳門通×MOME】年會</span></p>
        
    </div>
    <form action="/macaupassyear/toIndex.do" method="post" id="loginform">
    <div class="load-input">
		<!-- <input type="text" placeholder="姓名" id="userName" class="input loadinbg center">
        <div class="load-inp-line"></div> -->
		<input type="tel" placeholder="手機號碼" id="phoneNumber" class="input loadinbg center" >
	</div>
	</form>
	
	<div class="submit-btn" id="login" id="login" onclick="checklgn();">Welcome！</div>
	<!-- <a id="spreadBtn" class="spreadBtn" onclick="spreadBless(this);">展开对联</a> -->
    <div class="center">
    	<img src="/data/images/logo_mp_mome.png">
    </div>
    
</body>
<script type="text/javascript" src="/js/business.js?v=<%=b %>"></script>	
<script>
$('<img/>').load('/data/images/load-640-960.jpg',function(){
	//$('.default').html("");
	showBless();
});

function showBless(){
	/* $("#blessDivLeft").slideDown();
	setTimeout(function () {
		$("#blessDivRight").slideDown();
	}, 500);
	setTimeout(function () {
		$("#blessDivMiddle").fadeIn();
	}, 800);
	setTimeout(function () {
		$("#blessDivLeft").fadeOut();
		$("#blessDivRight").fadeOut();
		$("#blessDivMiddle").fadeOut();
		$('.default').fadeOut();
		$('.mask').show();
		$('.mask').fadeOut(1500);
	}, 3500); */
	displaylogin();
}
function displaylogin () {
		$("#blessDivLeft").fadeOut();
		$("#blessDivRight").fadeOut();
		$("#blessDivMiddle").fadeOut();
		$('.default').fadeOut();
		$('.mask').show();
		$('.mask').fadeOut(1500);
	}
function spreadBless(btn){
	var blessDiv = $("#blessDivLeft");
	if(blessDiv.css("display") == "block") {
		$("#blessDivLeft").hide();
		$("#blessDivRight").hide();
		$("#blessDivMiddle").hide();
		$(btn).text("展开对联");
	} else {
		$("#blessDivLeft").show();
		$("#blessDivRight").show();
		$("#blessDivMiddle").show();
		$(btn).text("关闭对联");
	}
}

//var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})|(\d{8})$/;

function checklgn(){
	var mobile = $('#phoneNumber').val();
	/* var username = $('#userName').val();
	if(username=='') {
		showerror("请输入姓名！");
		return false;
	} */
	if(!reg.test(mobile)) {
		alert("请输入正确的手机号码！");
		return false;
	}
	$.ajax({
		type:"post",
		url:"/macaupassyear/queryYearMember.do",
		data:{
			phoneNumber:mobile
			/* userName:username */
		},
		dataType:"json",
		cache:"false",
		success:login_success,
		error:login_error
	});
}

function login_success(data,status){
	if(data.respCode=='10000'){
		$("#loginform").submit();
	}else{	
		var msg = data.respMsg;
		alert(msg);
	}
}
function login_error(){
	alert("进入年会失败！");
}
</script>	

</html>
