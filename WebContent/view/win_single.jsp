<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.org.common.CommonConstant"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1, user-scalable=0" />
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>澳門通×MOME年會-中獎信息</title>
	<%@ include file="common.jsp"%>
	<link rel="stylesheet" href="/data/css/base.css" type="text/css">
	<link rel="stylesheet" href="/data/css/public.css" type="text/css">
	<link rel="stylesheet" href="/data/css/index.css" type="text/css">
</head>
<body class="body-bg">
<!--页头-->
<script language="javascript" src="/data/js/header.js?v=<%=b %>" id="headerScript" data-args="headername=獲獎單&headerbackurl=index.html"></script>
<!--页头 结束-->
<!--5-->
<!--获奖人员名单-->
<div id="tt" style="padding-bottom:60px; margin-bottom:60px;"></div>
<!--获奖人员名单结束-->
<!--页尾-->
<script language="javascript" src="/data/js/footer.js?v=<%=b %>" id="footerScript" data-args="selecttype=index"></script>

<!--页尾 结束-->
<script src="/data/js/zepto.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/data/js/base.js"></script>
<script type="text/javascript" src="/data/js/service.js"></script> 
<script type="text/javascript" src="/js/cookie.min.js"></script>

<script type="text/javascript">
var AwardFifth = "<%=CommonConstant.AWARD_FIFTH%>";
var AwardFourth = "<%=CommonConstant.AWARD_FOURTH%>";
var AwardThree = "<%=CommonConstant.AWARD_THIRD%>";
var AwardSecond = "<%=CommonConstant.AWARD_SECOND%>";
var AwardFirst = "<%=CommonConstant.AWARD_FIRST%>";
var AwardLucky = "<%=CommonConstant.AWARD_LUCKY%>";

var FifthAwardName = "<%=CommonConstant.FIFTH_AWARD_NAME%>";
var FourthAwardName = "<%=CommonConstant.FOURTH_AWARD_NAME%>";
var ThreeAwardName = "<%=CommonConstant.THREE_AWARD_NAME%>";
var SecondAwardName = "<%=CommonConstant.SECOND_AWARD_NAME%>";
var FirstAwardName = "<%=CommonConstant.FIRST_AWARD_NAME%>";
var LuckyAwardName = "<%=CommonConstant.LUCKY_AWARD_NAME%>";

var FifthAwardNum = "<%=CommonConstant.FIFTH_AWARD_NUM%>";
var FourthAwardNum = "<%=CommonConstant.FOURTH_AWARD_NUM%>";
var ThreeAwardNum = "<%=CommonConstant.THREE_AWARD_NUM%>";
var SecondAwardNum = "<%=CommonConstant.SECOND_AWARD_NUM%>";
var FirstAwardNum = "<%=CommonConstant.FIRST_AWARD_NUM%>";
var LuckyAwardNum = "<%=CommonConstant.LUCKY_AWARD_NUM%>";

/* cookie.set("flag4", "test");
alert(cookie.get("flag4")); */

$(document).ready(function(){
	var es = new EventSource('/message.do');
	es.onmessage = function(e) {
		var html = "";
		if(!! e.data) {
			var jsonData = eval("("+e.data+")");
			var json5 = jsonData[AwardFifth];
			var flag5 = cookie.get("flag5");
			if(json5.length>0){
				html += '<div class="clear"><div class="global-tit-trans"></div><div class="global-titleb">五等奖（<font>'+FifthAwardNum+'</font>名）<span class="vote-num">'+FifthAwardName+'</span></div></div><div class="global-nav-winpri"><ul>';
				//页面中最多只展示12个，多出的点击展开按钮
				if(json5.length>12){
					for(var i=0;i<11;i++){
						html += '<li class="pri-cell fl_left">'+json5[i].memname+'<h5 class="sub_name"> '+json5[i].company+'</h5></li>';
					}
					html += '<div id="backFifthList" ';
					if(flag5 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					for(var i=11;i<json5.length;i++){
						html += '<li class="pri-cell fl_left">'+json5[i].memname+'</span><h5 class="sub_name"> '+json5[i].company+'</h5></li>';
					}
					html += '</div><div id="openAwardFifth" ';
					if(flag5 == "true"){
						html += 'style="display:block;">';
					}else{
						html += 'style="display:none;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="openAwardFifth();">展开</div></li></div><div id="closeAwardFifth" ';
					if(flag5 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="closeAwardFifth();">收起</div></li></div></ul></div>';		
				}else{
					for(var i=0;i<json5.length;i++){
						html += '<li class="pri-cell fl_left">'+json5[i].memname+'<h5 class="sub_name"> '+json5[i].company+'</h5></li>';
					}
					html += '</ul></div>';
				}
			}
			
			var json4 = jsonData[AwardFourth];
			var flag4 = cookie.get("flag4");
			if(json4.length>0){
				html += '<div class="clear"><div class="global-tit-trans"></div><div class="global-titleb">四等奖（<font>'+FourthAwardNum+'</font>名）<span class="vote-num">'+FourthAwardName+'</span></div></div><div class="global-nav-winpri"><ul>';
				if(json4.length>12){
					for(var i=0;i<11;i++){
						html += '<li class="pri-cell fl_left">'+json4[i].memname+'<h5 class="sub_name"> '+json4[i].company+'</h5></li>';
					}
					html += '<div id="backFourthList" ';
					if(flag4 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					for(var i=11;i<json4.length;i++){
						html += '<li class="pri-cell fl_left">'+json4[i].memname+'<h5 class="sub_name"> '+json4[i].company+'</h5></li>';
					}
					html += '</div><div id="openAwardFourth" ';
					if(flag4 == "true"){
						html += 'style="display:block;">';
					}else{
						html += 'style="display:none;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="openAwardFourth();">展开</div></li></div><div id="closeAwardFourth" ';
					if(flag4 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="closeAwardFourth();">收起</div></li></div></ul></div>';
				}else{
					for(var i=0;i<json4.length;i++){
						html += '<li class="pri-cell fl_left">'+json4[i].memname+'<h5 class="sub_name"> '+json4[i].company+'</h5></li>';
					}
					html += '</ul></div>';
				}
			}
			
			var json3 = jsonData[AwardThree];
			var flag3 = cookie.get("flag3");
			if(json3.length>0){
				html += '<div class="clear"><div class="global-tit-trans"></div><div class="global-titleb">三等奖（<font>'+ThreeAwardNum+'</font>名）<span class="vote-num">'+ThreeAwardName+'</span></div></div><div class="global-nav-winpri"><ul>';
				if(json3.length>12){
					for(var i=0;i<11;i++){
						html += '<li class="pri-cell fl_left">'+json3[i].memname+'<h5 class="sub_name"> '+json3[i].company+'</h5></li>';
					}
					html += '<div id="backThirdList" ';
					if(flag3 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					for(var i=11;i<json3.length;i++){
						html += '<li class="pri-cell fl_left">'+json3[i].memname+'<h5 class="sub_name"> '+json3[i].company+'</h5></li>';
					}
					html += '</div><div id="openAwardThird" ';
					if(flag3 == "true"){
						html += 'style="display:block;">';
					}else{
						html += 'style="display:none;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="openAwardThird();">展开</div></li></div><div id="closeAwardThird" ';
					if(flag3 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="closeAwardThird();">收起</div></li></div></ul></div>';
				}else{
					for(var i=0;i<json3.length;i++){
						html += '<li class="pri-cell fl_left">'+json3[i].memname+'<h5 class="sub_name"> '+json3[i].company+'</h5></li>';
					}
					html += '</ul></div>';
				}
			}
			
			var json2 = jsonData[AwardSecond];
			var flag2 = cookie.get("flag2");
			if(json2.length>0){
				html += '<div class="clear"><div class="global-tit-trans"></div><div class="global-titleb">二等奖（<font>'+SecondAwardNum+'</font>名）<span class="vote-num">'+SecondAwardName+'</span></div></div><div class="global-nav-winpri"><ul>';
				if(json2.length>12){
					for(var i=0;i<11;i++){
						html += '<li class="pri-cell fl_right">'+json2[i].memname+'<h5 class="sub_name"> '+json2[i].company+'</h5></li>';
					}
					html += '<div id="backSecondList" ';
					if(flag2 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					for(var i=11;i<json2.length;i++){
						html += '<li class="pri-cell fl_right">'+json2[i].memname+'<h5 class="sub_name"> '+json2[i].company+'</h5></li>';
					}
					html += '</div><div id="openAwardSecond" ';
					if(flag2 == "true"){
						html += 'style="display:block;">';
					}else{
						html += 'style="display:none;">';
					}
					html += '<li class="pri-cell fl_right" style=" border-bottom:none;"><div class="spead-btn" onclick="openAwardSecond();">展开</div></li></div><div id="closeAwardSecond" ';
					if(flag2 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="closeAwardSecond();">收起</div></li></div></ul></div>';
				}else{
					for(var i=0;i<json2.length;i++){
						html += '<li class="pri-cell fl_left">'+json2[i].memname+'<h5 class="sub_name"> '+json2[i].company+'</h5></li>';
					}
					html += '</ul></div>';
				}
			}
			
			var json1 = jsonData[AwardFirst];
			var flag1 = cookie.get("flag1");
			if(json1.length>0){
				html += '<div class="clear"><div class="global-tit-trans"></div><div class="global-titleb">一等奖（<font>'+FirstAwardNum+'</font>名）<span class="vote-num">'+FirstAwardName+'</span></div></div><div class="global-nav-winpri"><ul>';
				if(json1.length>12){
					for(var i=0;i<11;i++){
						html += '<li class="pri-cell fl_left">'+json1[i].memname+'<h5 class="sub_name"> '+json1[i].company+'</h5></li>';
					}
					html += '<div id="backFirstList" ';
					if(flag1 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					for(var i=11;i<json1.length;i++){
						html += '<li class="pri-cell fl_left">'+json1[i].memname+'<h5 class="sub_name"> '+json1[i].company+'</h5></li>';
					}
					html += '</div><div id="openAwardFirst" ';
					if(flag1 == "true"){
						html += 'style="display:block;">';
					}else{
						html += 'style="display:none;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="openAwardFirst();">展开</div></li></div><div id="closeAwardFirst" ';
					if(flag1 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="closeAwardFirst();">收起</div></li></div></ul></div>';
				}else{
					for(var i=0;i<json1.length;i++){
						html += '<li class="pri-cell fl_left">'+json1[i].memname+'<h5 class="sub_name"> '+json1[i].company+'</h5></li>';
					}
					html += '</ul></div>';
				}
			}
			
			var json0 = jsonData[AwardLucky];
			var flag0 = cookie.get("flag0");
			if(json0.length>0){
				html += '<div class="clear"><div class="global-tit-trans"></div><div class="global-titleb">特等奖（<font>'+LuckyAwardNum+'</font>名）<span class="vote-num">'+LuckyAwardName+'</span></div></div><div class="global-nav-winpri"><ul>';
				if(json0.length>12){
					for(var i=0;i<11;i++){
						html += '<li class="pri-cell fl_left">'+json0[i].memname+'<h5 class="sub_name"> '+json0[i].company+'</h5></li>';
					}
					html += '<div id="backLuckyList" ';
					if(flag0 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					for(var i=11;i<json0.length;i++){
						html += '<li class="pri-cell fl_left">'+json0[i].memname+'<h5 class="sub_name"> '+json0[i].company+'</h5></li>';
					}
					html += '</div><div id="openAwardLucky" ';
					if(flag0 == "true"){
						html += 'style="display:block;">';
					}else{
						html += 'style="display:none;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="openAwardLucky();">展开</div></li></div><div id="closeAwardFirst" ';
					if(flag0 == "true"){
						html += 'style="display:none;">';
					}else{
						html += 'style="display:block;">';
					}
					html += '<li class="pri-cell fl_left"><div class="spead-btn" onclick="closeAwardLucky();">收起</div></li></div></ul></div>';
				}else{
					for(var i=0;i<json0.length;i++){
						html += '<li class="pri-cell fl_left">'+json0[i].memname+'<h5 class="sub_name"> '+json0[i].company+'</h5></li>';
					}
					html += '</ul></div>';
				}
			}
			
		    $("#tt").empty();
		    $("#tt").append(html);
		}
	};

	es.addEventListener('myevent', function(e) {
		//alert(e.data);
	}); 

});

function openAwardFifth(){
	cookie.set("flag5","false");
	document.getElementById("backFifthList").style.display = "block";
	document.getElementById("openAwardFifth").style.display = "none";
	document.getElementById("closeAwardFifth").style.display = "block";
}

function closeAwardFifth(){
	cookie.set("flag5","true");
	document.getElementById("backFifthList").style.display = "none";
	document.getElementById("openAwardFifth").style.display = "block";
	document.getElementById("closeAwardFifth").style.display = "none";
}

function openAwardFourth(){
	cookie.set("flag4","false");
	document.getElementById("backFourthList").style.display = "block";
	document.getElementById("openAwardFourth").style.display = "none";
	document.getElementById("closeAwardFourth").style.display = "block";
}

function closeAwardFourth(){
	cookie.set("flag4","true");
	document.getElementById("backFourthList").style.display = "none";
	document.getElementById("openAwardFourth").style.display = "block";
	document.getElementById("closeAwardFourth").style.display = "none";
}

function openAwardThird(){
	cookie.set("flag3","false");
	document.getElementById("backThirdList").style.display = "block";
	document.getElementById("openAwardThird").style.display = "none";
	document.getElementById("closeAwardThird").style.display = "block";
}

function closeAwardThird(){
	cookie.set("flag3","true");
	document.getElementById("backThirdList").style.display = "none";
	document.getElementById("openAwardThird").style.display = "block";
	document.getElementById("closeAwardThird").style.display = "none";
}

function openAwardSecond(){
	cookie.set("flag2","false");
	document.getElementById("backSecondList").style.display = "block";
	document.getElementById("openAwardSecond").style.display = "none";
	document.getElementById("closeAwardSecond").style.display = "block";
}

function closeAwardSecond(){
	cookie.set("flag2","true");
	document.getElementById("backSecondList").style.display = "none";
	document.getElementById("openAwardSecond").style.display = "block";
	document.getElementById("closeAwardSecond").style.display = "none";
}

function openAwardFirst(){
	cookie.set("flag1","false");
	document.getElementById("backFirstList").style.display = "block";
	document.getElementById("openAwardFirst").style.display = "none";
	document.getElementById("closeAwardFirst").style.display = "block";
}

function closeAwardFirst(){
	cookie.set("flag1","true");
	document.getElementById("backFirstList").style.display = "none";
	document.getElementById("openAwardFirst").style.display = "block";
	document.getElementById("closeAwardFirst").style.display = "none";
}

function openAwardLucky(){
	cookie.set("flag0","false");
	document.getElementById("backLuckyList").style.display = "block";
	document.getElementById("openAwardLucky").style.display = "none";
	document.getElementById("closeAwardLucky").style.display = "block";
}

function closeAwardLucky(){
	cookie.set("flag0","true");
	document.getElementById("backLuckyList").style.display = "none";
	document.getElementById("openAwardLucky").style.display = "block";
	document.getElementById("closeAwardLucky").style.display = "none";
}
</script>
</body>
</html>
