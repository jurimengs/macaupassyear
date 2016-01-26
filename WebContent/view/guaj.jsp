<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>全民刮奖</title>
	<%@ include file="common.jsp"%>
	<link rel="stylesheet" href="/data/css/base.css" type="text/css">
	<link rel="stylesheet" href="/data/css/public.css" type="text/css">
	<link rel="stylesheet" href="/data/css/index.css" type="text/css">
	<script type="text/javascript" charset="utf-8" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>	
</head>

<body class="body-bg" style="overflow:hidden">
<!--top-->
<script language="javascript" src="/data/js/header.js?v=<%=b %>" id="headerScript" data-args="headername=我的奖品&headerbackurl=index.html"></script>

<div class="global-navb height_whole">
    <div class="code-bg">
        <div style="height: 50px;" class="qr-code" id="lotteryContainer"></div>
    </div>
</div>

<!--footer-->
<script language="javascript" src="/data/js/footer.js?v=<%=b %>" id="footerScript" data-args="selecttype=index"></script>
<!--footer over-->
<script src="/js/Lottery.js"></script>
<script>

function touchcallback(percent, mask) {
	if(percent > 50) {
		mask.style.display = "none";
	}
}


$(function(){
	var expandParams = {};
    var lottery = new Lottery('lotteryContainer', '#CCC', 'color', 138, 50, null, touchcallback);
    var awardlevel = "${usermeg.rewardstate}";
    var hideward = "";
    if(awardlevel == "1") {
    	hideward = "中 奖 啦！"+awardlevel+" 等 奖";
    } else {
    	hideward = "未   中   奖";
    }
    //lottery.init('http://www.baidu.com/img/bdlogo.gif', 'image');
    lottery.init(hideward, 'text');
});



function fmtMobile(mobile){
	return mobile.substring(0,3) + "****" + mobile.substring(7,11);
}
// document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
// document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
</script>
</body>

</html>
