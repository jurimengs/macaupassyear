<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ch">
<head>
<%@ include file="common.jsp"%>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>当前节目标识管理</title>
	<link rel="stylesheet" href="/data/css/base.css" type="text/css">
	<link rel="stylesheet" href="/data/css/public.css" type="text/css">
	<link rel="stylesheet" href="/data/css/index.css" type="text/css">
</head>
<body class="body-bg">
<!--页头-->
<!--页头 结束-->
<!--<div id="votes" class="votes"></div>-->
<div class="global-navb vote-inp">
	 <ul>
	 <c:forEach items="${allProgram}" var="entity">
	 	<%-- 节目的id 便于已投票的标识处理 --%>
	    <li id="V_${entity.pnumber }" <c:if test="${empty usermeg.pname }">onclick="choose(this);"</c:if>>
        	<c:if test="${!empty entity.isstart }">
	 			<%-- 已开始 --%>
	 			已开始
	        	<div class="fl_left" style="width:18px;">&nbsp;</div>
        	</c:if>
        	${entity.pname}
        	<form id="form" action="/macaupassyear/updateProgramStartFlag.do" onsubmit="return check();" method="post">
		    	<input value="更新节目状态" type="submit" />
		    	<input name="isstart" type="hidden" value="${entity.isstart }" />
		    	<input name="pnumber" type="hidden" value="${entity.pnumber }" />
		    </form>
	  </c:forEach>
    </ul>
   	<br />
   	<br />
   	<br />
   	<br />
</div>
<!--页尾-->
<!--页尾 结束-->
<script type="text/javascript" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>	
<script>

</script>
</body>
</html>
