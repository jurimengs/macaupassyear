<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>现场指导</title>
<%@ include file="common.jsp"%>
<link rel="stylesheet" href="../data/css/base.css" type="text/css">
<link rel="stylesheet" href="../data/css/public.css" type="text/css">
<script type="text/javascript" charset="utf-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="../data/js/zepto.min.js?v=<%=b %>" type="text/javascript"></script>
<script type="text/javascript">
if("${usermeg.roleflag}" == 'manager') {
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${appId}', // 必填，公众号的唯一标识
	    timestamp: '${timestamp }', // 必填，生成签名的时间戳
	    nonceStr: '${nonceStr }', // 必填，生成签名的随机串
	    signature: '${signature }',// 必填，签名，见附录1
	    jsApiList: [
			'onMenuShareTimeline',
			'onMenuShareAppMessage',
			'onMenuShareQQ',
			'onMenuShareWeibo',
			'onMenuShareQZone',
			'startRecord',
			'stopRecord',
			'onVoiceRecordEnd',
			'playVoice',
			'pauseVoice',
			'stopVoice',
			'onVoicePlayEnd',
			'uploadVoice',
			'downloadVoice',
			'chooseImage',
			'previewImage',
			'uploadImage',
			'downloadImage',
			'translateVoice',
			'getNetworkType',
			'openLocation',
			'getLocation',
			'hideOptionMenu',
			'showOptionMenu',
			'hideMenuItems',
			'showMenuItems',
			'hideAllNonBaseMenuItem',
			'showAllNonBaseMenuItem',
			'closeWindow',
			'scanQRCode',
			'chooseWXPay',
			'openProductSpecificView',
			'addCard',
			'chooseCard',
			'openCard'
		] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});


	wx.error(function(res){
		var s = "";
		for (var k in res) {
			s += k + ":" + res[k];
		}
		alert("error:\n"+s);
	});

	wx.ready(function(){
		wxReady = true;
		alert("扫码器工具加载完成");
		//alert("loading success");
		//$("#codebarscannBtn").click();
	});
}

var wxReady = false;
function codebarScann(callback){
	if(!wxReady) {
		alert("正在加载扫码器，请稍候");
		return;
	}
	wx.scanQRCode({
	    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
	    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
	    success: callback
	});
}

function codebarScannCallback(res){
	var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
	//alert(result);
	window.location.href = result;
}

</script>
</head>
<body class="body-bg">
<!--页头-->
<script language="javascript" src="../data/js/header.js?v=<%=b %>" id="headerScript" data-args="headername=现场指导&nbsp; &headerbackurl=index.html"></script>
<!--页头 结束-->
<div class="clear main_tit pos_relative" >
	<div class="fl_left" style="margin-bottom:25%;">
		<div class="size-m clear">毛丽敏<span class="size-s marg_lef_1em">指导</span></div>
        <p>13801668985</p>
    </div>
    <div class="fl_right" style="margin-bottom:25%;">
    	<div class="size-m clear">穆建义<span class="size-s marg_lef_1em">技术</span></div>
        <p>15901918148</p>
    </div>
</div>
<div class="clear">
	<div class="global-tit-trans"></div>
    <div class="global-titleb">需要帮助的小伙伴，请联系以上人员</div>
</div>
<c:if test="${usermeg.roleflag eq 'manager'}">
<div class="global-navb height_whole guide-bg">
	<div class="submit-btn" id="loginout" onclick="codebarScann(codebarScannCallback);" >扫码发奖</div>
</div>
</c:if>
 
<!--<div class="global-navb">
	<h2 class="global-titleb">帮助</h2>
    <ul class="traffic-text">
    	<li style="font-weight:bold;">年会中有需要帮助的同事，请联系以下人员</li>
        <li>[年会]&nbsp;&nbsp;毛丽敏&nbsp;&nbsp; 13801668985</li>
        <li>[技术]&nbsp;&nbsp;桂勇保&nbsp;&nbsp; 15921026818</li>
    </ul>
</div>
<div class="submit-btn" id="loginout">
    退出登录
</div>-->
<!--页尾-->
<script language="javascript" src="../data/js/footer.js?v=<%=b %>" id="footerScript" data-args="selecttype=user_more"></script>
<!--页尾 结束-->
<script type="text/javascript" src="../data/js/base.js?v=<%=b %>"></script>
<script type="text/javascript" src="../data/js/service.js?v=<%=b %>"></script>	
<script>
</script>
</body>

</html>