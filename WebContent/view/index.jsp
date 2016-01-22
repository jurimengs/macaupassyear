<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>澳門通×MOME年会-首页</title>
<%@ include file="common.jsp"%>
    <link rel="stylesheet" href="/data/css/base.css?v=1" type="text/css">
	<link rel="stylesheet" href="/data/css/public.css" type="text/css">
	<link rel="stylesheet" href="/data/css/index.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="/data/css/widget/slider/slider.css" />
    <link rel="stylesheet" type="text/css" href="/data/css/widget/slider/slider.default.css" />
	<script src="/data/js/zepto.min.js" type="text/javascript"></script>
	<style type="text/css">
	
	
	/* 页面抖一抖 */
	.shaker {
		animation: shake 0.5s 1;
		-webkit-animation: shake 0.5s 1; /*Safari and Chrome*/
	}
	
	@keyframes shake
	{
		0% {transform: rotate(15deg) translate(0, 0px);}
		20% {transform: rotate(-5deg) translate(0px, 0px);}
		40% {transform: rotate(15deg) translate(0, 0px);}
		60% {transform: rotate(-5deg) translate(0, 0px);}
		80% {transform: rotate(15deg) translate(0, 0px);}
		100% {transform: rotate(-5deg) translate(0px, 0px);}
	}
	
	@-webkit-keyframes shake /*Safari and Chrome*/
	{
		0% {-webkit-transform: rotate(15deg) translate(0, 0px);}
		20% {-webkit-transform: rotate(-5deg) translate(0px, 0px);}
		40% {-webkit-transform: rotate(15deg) translate(0, 0px);}
		60% {-webkit-transform: rotate(-5deg) translate(0, 0px);}
		80% {-webkit-transform: rotate(15deg) translate(0, 0px);}
		100% {-webkit-transform: rotate(-5deg) translate(0px, 0px);}
	}
	</style>
</head>
<body class="body-bg">
<!--top-->
<div class="ico-more clear active_view">
 <img src="/data/images/ico-more.png"  width="36" height="36" onclick="gotoUrl('/macaupassyear/toHelp.do')">
</div>
<!--top over-->
<div class="main-ico clear">
	<ul>
    	<li class="center ico-seat active_view" onclick="window.location.href='/macaupassyear/querySameTableMemberList.do?phoneNumber=${usermeg.moible}';"><a href="javascript:void(0);" >我的席位</a></li>
        <li class="center ico-prize active_view" onclick="window.location.href='/macaupassyear/queryMyPrize.do?phoneNumber=${usermeg.moible}';"><a href="javascript:void(0);">我的奖品</a></li>
    </ul>
</div>
<div class="sub-ico clear">
	<ul>
    	<!-- <li class="center ico-traffic active_view" onclick="window.location.href='/view/traffic.jsp';"><a href="/view/traffic.jsp">交通</a></li> -->
        <li class="center ico-parking active_view" onclick="shakeaward();"><a>摇一摇</a></li>
        <li class="center ico-vote active_view" onclick=""><a href="/macaupassyear/toguaj.do">刮一刮</a></li>
    </ul>
</div>
<div class="index-slider clear">
	<div id="slider" class="ui-slider" style="visibility: hidden;">
		<div >
			<a href="#"><img src="/data/images/Advert-01.png" ></a>
		</div>
		<!--
		<div >
			<a href="#"><img src="/data/images/Advert-02.png" ></a>
		</div>
        
		<div >
			<a href="#"><img src="/data/images/c.jpg" ></a>
		</div>-->
	</div>
</div>
<div class=" pos_relative clear">
	<div class="global-tit-trans"></div>
    <div class="global-titleb">谁中了大奖</div>
	<div class="global-btn" onclick="getAwardList();">获奖单</div>
</div>
<ul class="msg-list-wrap" id="msgid">
<c:if test="${fn:length(rewardarrary)>0 }">
<c:forEach items="${rewardarrary}" var="entity">
 <li style="white-space:nowrap">
 	 <div class="fl_left" style="width:20%; font:normal 1.15em 'Microsoft Yahei';">${entity.memname}</div>
 	 <div class="fl_right prize-get">
		 <c:if test="${entity.rewardstate eq '1'}">
		 一等奖
		 </c:if>
		 <c:if test="${entity.rewardstate eq '2'}">
		 二等奖
		 </c:if>
		  <c:if test="${entity.rewardstate eq 't'}">
		 特等奖
		 </c:if>
	 </div>
	 <div class="clear"></div>
 	 <div class="fl_left" style="width:33%;color:#cc99cc;">${entity.moible}</div>
	 
	 <div class="fl_right" style="width:42%; text-align:right;color:#cc99cc;">${entity.company}</div>
		 
	
 </li>
</c:forEach>
</c:if>
<c:if test="${fn:length(rewardarrary)<=0 }">
<li class="center" style="background:none;">大奖等你吃饱了才出来哦~</li>
</c:if>
	
</ul>
<!--  
<form action="/macaupassyear/queryMyPrize.do" method="post" id="myPrize">
 <input type="hidden" name="phoneNumber" value="${usermeg.moible}"/>
</form>
<form action="/macaupassyear/querySameTableMemberList.do" method="post" id="mySeat">
 <input type="hidden" name="phoneNumber" value="${usermeg.moible}"/>
</form>
-->
<!--footer-->
<script language="javascript" src="/data/js/footer.js?v=<%=b %>" id="footerScript" data-args="selecttype=index"></script>
<!--footer over-->
<script type="text/javascript" src="/data/js/extend/touch.js"></script>
<script type="text/javascript" src="/data/js/extend/matchMedia.js"></script>
<script type="text/javascript" src="/data/js/extend/event.ortchange.js"></script>
<script type="text/javascript" src="/data/js/extend/parseTpl.js"></script>
<script type="text/javascript" src="/data/js/core/gmu.js"></script>
<script type="text/javascript" src="/data/js/core/event.js"></script>
<script type="text/javascript" src="/data/js/core/widget.js"></script>
<script type="text/javascript" src="/data/js/widget/slider/slider.js"></script>
<!--<script type="text/javascript" src="js/widget/slider/arrow.js"></script>-->
<script type="text/javascript" src="../data/js/widget/slider/dots.js"></script>
<script type="text/javascript" src="../data/js/widget/slider/$touch.js"></script>
<script type="text/javascript" src="../data/js/widget/slider/$autoplay.js"></script>
<script type="text/javascript" src="../data/js/widget/slider/$lazyloadimg.js"></script>
<script type="text/javascript" src="../data/js/widget/slider/imgzoom.js"></script>
<script type="text/javascript" src="../data/js/base.js"></script>
<script type="text/javascript" src="../data/js/service.js"></script>
<script type="text/javascript" src="/js/cookie.min.js"></script>
<script>

function shakeaward(){
	$("body").addClass("shaker");
	$.ajax({
		type:"post",
		url:"/macaupassyear/shakeaward.do",
		data:{},
		dataType:"json",
		cache:"false",
		success : shakesuccess,
		error : shakeerror
	});
}


function shakesuccess(data){
	var msg = "";
	if(data.respCode == "10000") {
		msg = "您已进入抽奖队列, 当抽奖完成后点击\"我的奖品\"查看中奖信息";
	} else {
		msg = data.respMsg;
	}
	setTimeout(function(){
		alert(msg);
		initshake();
	}, 2000);
}

function shakeerror(){
	setTimeout(function(){
		alert("系统异常");
		initshake();
	}, 2000);
}

function initshake(){
	$("body").removeClass("shaker");
}

function fmtRewardState(types){
	if(types=='5'){
		return "五等";
	}else if(types=='4'){
		return "四等";
	}else if(types=='3'){
		return "三等";
	}else if(types=='2'){
		return "二等";
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
$('#slider').slider({imgZoom: false ,ready:function(){$('#slider').css('visibility','visible');}});

function mySeat(){
	document.getElementById("mySeat").submit();
	/* $.ajax({
		type:"post",
		url:"/macaupassyear/querySameTableMemberList.do",
		data:{
			phoneNumber:mobile
		},
		dataType:"json",
		cache:"false",
		success:seat_success,
		error:seat_error
	}); */
}
function seat_success(data,status){
	if(data.respCode=='10000'){
		window.location.href="/view/seat.jsp";
	}else{	
		//var msg = data.respMsg;
	}
}
function seat_error(){
	alert("进入年会失败！");
}
function gotoUrl(url){
	window.location.href=url;
}
function myPrize(){
	document.getElementById("myPrize").submit();
}
function getAwardList(){
	$.ajax({
		type:"post",
		url:"/message/whetherAward.do",
		data:{},
		dataType:"json",
		cache:"false",
		success:award_success,
		error:award_error
	});
}

function award_success(data){
	if(data.msg == "" || data.msg == "true"){
		alert("还未开始抽奖！");
	}else if(data.msg == "false"){
		cookie.set("flag5","true");
		cookie.set("flag4","true");
		cookie.set("flag3","true");
		cookie.set("flag2","true");
		cookie.set("flag1","true");
		cookie.set("flag0","true");
		gotoUrl("/view/win_single.jsp");
	}
}

function award_error(){
	alert("进入获奖单失败！");
}
</script>
</body>

</html>
