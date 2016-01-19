<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ch">
<head>
<%@ include file="common.jsp"%>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>杉德年会-节目投票</title>
	<link rel="stylesheet" href="/data/css/base.css" type="text/css">
	<link rel="stylesheet" href="/data/css/public.css" type="text/css">
	<link rel="stylesheet" href="/data/css/index.css" type="text/css">
</head>
<body class="body-bg">
<script language="javascript" src="/data/js/header.js?v=sss3" id="headerScript" data-args="headername=投票&headerbackurl=index.html"></script>
<!--页头-->
<!--页头 结束-->
<!--<div id="votes" class="votes"></div>-->
<div class="clear pos_relative">
	<div class="global-tit-trans"></div>
    <div class="global-titleb">
    	<c:if test="${empty usermeg.pname }">请投出您宝贵的一票</c:if>
    	<c:if test="${!empty usermeg.pname }">投票结果</c:if>
    	<a class="global-titleb" href="/macaupassyear/queryProgram.do">(点击刷新)</a>
    </div>
</div>
<div class="global-navb vote-inp">
	 <ul>
	 <c:forEach items="${allProgram}" var="entity">
	 	<%-- 节目的id 便于已投票的标识处理 --%>
	    <li <c:if test="${entity.isstart ne 'y'}">style="opacity:0.5;"</c:if> id="V_${entity.pnumber }" <c:if test="${empty usermeg.pname }">onclick="choose(this, '${entity.isstart }');"</c:if>>
        	<c:if test="${empty usermeg.pname }">
	 			<%-- 未投票 --%>
	        	<div class="fl_left vote-sel"></div>
        	</c:if>
        	<c:if test="${!empty usermeg.pname }">
	 			<%-- 已投票 --%>
	        	<div class="fl_left" style="width:18px;">&nbsp;</div>
        	</c:if>
        	
        	${entity.pname}<span class="vote-num">(<font>${entity.pcount}</font>票)</span>
        	
        	<!-- 计算票数的比例 -->
        	<c:set var="voteRate" value="${(entity.pcount/ usermeg.count)*100 }" />
<%--         	${voteRate } __ ${entity.pcount} __${usermeg.count  } --%>
			<input type="hidden" class="vote_rate" value="${voteRate }" />
            <div class="vote-get" style="width:0%;"></div>
            <div class="vote-total"></div>
	  </c:forEach>
    </ul>
    <c:if test="${empty usermeg.pname }">
	    <form id="form" action="/macaupassyear/vote.do" onsubmit="return check();" method="post">
	    	<input id="voteHidden" name="pnumber" type="hidden" />
	    </form>
    	<div class="vote-btn" onclick="vote();">投票</div>
    </c:if>
   	<br />
   	<br />
   	<br />
   	<br />
</div>
<!--页尾-->
<!--页尾 结束-->
<script type="text/javascript" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>	
<script language="javascript" src="/data/js/footer.js?v=sf" id="footerScript" data-args="selecttype=activity"></script>
<script>
$(function(){
	var myVoteId = "${usermeg.pname }";
	if(!! myVoteId) {
		alert("您已投票");
		// 取对应id 的 li，设置其选择框的样式
// 		$("#"+myVoteId).find(".vote-num").after('<span class="vote-total">我的投票</span>');
		$("#V_"+myVoteId).find(".vote-num").after('<img class="vote-num" src="/data/images/ico-correct.png" width="14" height="12">');
	}
	
	var timesplit = 0;
	$(".vote_rate").each(function(){
		var thisobj = $(this);
		var divobj = thisobj.next();
		var aimWidth = thisobj.val()+"%";
		setTimeout(function(){
			divobj.animate({"width":aimWidth}, 1500);
		}, timesplit);
		timesplit += 100;
	});
});

function check(){
	if( !! $("#voteHidden").val()) {
		return true;
	}
	alert("请先选择节目");
	return false;
}

function choose(obj, isstart){
	if(isstart != "y") {
		//节目未开始不可投票
		alert("该节目还未开始或未完成，暂不可投票哦");
		return;
	}
	//alert(obj.tagName);
	var aim = $(obj);
	aim.find(".fl_left").css("backgroundImage", "url(/data/images/selected.png)");
	aim.siblings().find(".fl_left").css("backgroundImage", "url(/data/images/unselected.png)");
	$("#voteHidden").val(obj.id.split("_")[1]);
}

function vote(){
	$("#form").submit();
}

</script>
</body>
</html>
