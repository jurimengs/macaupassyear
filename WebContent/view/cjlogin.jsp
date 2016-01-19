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
    <div class="login-head-logo">
    	<p class="load_big_tit size-xl center"> <span style="color:#ff962d;">杉德年会</span>抽奖宝</p>
        
    </div>
    <form action="/macaupassyear/tocj.do" method="post" id="loginform">
	</form>
	
    <div class="load-input">
		<input type="text" placeholder="请输入抽奖口令" id="enterpwd" class="input loadinbg center">
	</div>
	<div class="submit-btn" id="login" id="login" onclick="checklgn();">Enter</div>
	<!-- <a id="spreadBtn" class="spreadBtn" onclick="spreadBless(this);">展开对联</a> -->
    <div class="login-head-logo center">
    	<img src="/data/images/ico-door.png" width="146" height="89">
    </div>
    <div class="genus_name" ><p class="genus center">杉德集团网络支付组</p></div>
    
</body>
<script type="text/javascript" src="/js/business.js?v=<%=b %>"></script>	
<script>

var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;

function checklgn(){
	var enterpwd = $('#enterpwd').val();
	if(enterpwd=='') {
		showerror("口令不能为空！");
		return false;
	}
	$.ajax({
		type:"post",
		url:"/macaupassyear/cjchecklgn.do",
		data:{
			enterpwd: enterpwd
		},
		dataType:"json",
		cache:"false",
		success: login_success,
		error: login_error
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
