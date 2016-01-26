<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>席位</title>
<%@ include file="common.jsp"%>
<link rel="stylesheet" href="/data/css/base.css" type="text/css">
<link rel="stylesheet" href="/data/css/public.css" type="text/css">
<style type="text/css">
#seatUl li {
	margin-left: 50px;
}
</style>
</head>
<body class="body-bg">
<!--top-->
<script language="javascript" src="/data/js/header.js?v=<%=b %>" id="headerScript" data-args="headername=我的席位&headerbackurl=index.jsp"></script>
<!--top over-->
<div class="clear main_tit">
	<div class="size-xxl"><strong>${usermeg.tablenum}</strong><span class="size-xs marg_lef_1em">桌</span></div>
    <div class="clear">
    	<div class="fl_left">公司：${usermeg.company }</div>
        <div class="s_tab fl_right"><a href="/view/map.html">座位圖</a></div>
    </div>
</div>
<div class="clear">
	<div class="global-tit-trans"></div>
    <div class="global-titleb">我的同桌</div>
</div>
<div class="global-navb">
    <ul id="seatUl">
    	<li>${usermeg.memname }</li>
    	<c:forEach items="${otherMembers}" var="entity">
	    	<c:if test="${entity.memname ne usermeg.memname }">
	        	<li style="opacity:0.5;">${entity.memname}</li>
	        </c:if>
    	</c:forEach>
    </ul>
</div>
<!--footer-->
<script language="javascript" src="/data/js/footer.js?v=<%=b %>" id="footerScript" data-args="selecttype=activity"></script>
<script language="javascript" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>
<script language="javascript">
$(function(){
	var timesplit = 0;
	$("#seatUl").children().each(function(){
		var liobj = $(this);
		setTimeout(function(){
			liobj.animate({"margin-left":"0px"});
		}, timesplit);
		timesplit += 100;
	});
});

function move(obj){
	alert($(obj).css("margin-left"));
}
</script>
<!--footer 结束-->
</body>

</html>