<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no" />
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title></title>
<%@ include file="common.jsp"%>
	<style type="text/css">
	
	/* 页面抖一抖 */
	.shaker {
		animation: shake 0.5s 1;
		-webkit-animation: webkitshake 5s 1; /*Safari and Chrome*/
	}
	
	@keyframes shake
	{
from {left:0px; transform: rotate(15deg)}
to {left:200px; transform: rotate(-5deg)}
	}
	
	@-webkit-keyframes webkitshake /*Safari and Chrome*/
	{
from {left:0px; transform: rotate(15deg)}
to {left:200px; transform: rotate(-5deg)}
/* 
	0% {transform: rotate(15deg) translate(0, 0px);}
	20% {transform: rotate(-5deg) translate(0px, 0px);}
	40% {transform: rotate(15deg) translate(0, 0px);}
	60% {transform: rotate(-5deg) translate(0, 0px);}
	80% {transform: rotate(15deg) translate(0, 0px);}
	100% {transform: rotate(-5deg) translate(0px, 0px);}
	 */
	}
	</style>
</head>
<body >
<div class="shaker" style="position:relative; width: 100px; height: 100px; background-color: red;">&nbsp;</div>
<script>
//alert(navigator.userAgent);
function shakeaward(){
	$("body").addClass("shaker");
}


</script>
</body>

</html>
