<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年会管理</title>
<%@ include file="common.jsp"%>
<c:if test="${empty manage }">
<c:redirect url="managelogin.jsp" />
</c:if>
</head>
<body>
<table>
 <tr>
  <td>手机号:(请输入要修改目标用户的手机号)</td>
 </tr>
 <tr>
  <td><input type="text" id="mobile"/></td>
 </tr>
 <tr>
  <td>姓名：(请输入目标用户的新的姓名)</td>
 </tr>
 <tr>
  <td><input type="text" id="memname"/></td>
 </tr>
 <tr>
  <td>桌位：(请输入目标用户的新的桌位号)</td>
 </tr>
 <tr>
  <td><input type="text" id="tablenum"/></td>
 </tr>
 <tr>
  <td>新手机号：(请输入目标用户的新的手机号)</td>
 </tr>
 <tr>
  <td><input type="text" id="newMobile"/></td>
 </tr>
 
 <tr style="display:none;">
  <td>公司名称：</td>
  <td><input type="text" id="company"/></td>
 </tr>
 <tr style="display:none;">
  <td>座位号：</td>
  <td><input type="text" id="seatnum"/></td>
 </tr>
</table>
<br /><br />
<input type="button" onclick="updateMem()" value="修改人员信息"/><br /><br />
<input type="button"  value="清空抽奖状态" onclick="updateYear('1')"/><br /><br />
<input type="button"  value="清空用户中奖信息" onclick="updateYear('2')"/><br /><br />
<input type="button"  value="清空用户投票信息" onclick="updateYear('3')"/><br /><br />
<br/><br />
<div><a href="award_query.jsp">领奖失败补领(如果扫码领奖出现故障可以使用此功能代替)</a></div><br /><br />
<div><a href="/macaupassyear/toProgramFlag.do">当前节目标记(每个节目完成后，应由人工标记该节目为已完成，届时方可抽奖)</a></div>
<!-- 
<div>
	<form action="/macaupassyear/tobucj.do" method="post" target="_blank">
	<select name="currentAwards">
	<option value="t">特</option>
	<option value="1">一</option>
	<option value="2">二</option>
	<option value="3">三</option>
	<option value="4">四</option>
	<option value="5">五</option>
	</select>
	<input type="text" name="buCounts" />
	<input type="submit" value="补抽奖"/>
	</form>
</div> -->
</body>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>	
<script>
 function updateYear(type) {
	 $.ajax({
			type:"post",
			url:"/macaupassyearmanage/updateYear.do",
			data:{
				type:type
			},
			dataType:"json",
			cache:"false",
			success:update_success,
			error:update_error
		});
}
 
function update_success(data,status) {
	if(data.respCode=='10000'){
		alert("成功");
	}else{
		var msg = data.respMsg;
		alert(msg);
	}
} 

function update_error() {
	alert("修改信息失败");
} 

function updateMem(){
	var memname = $('#memname').val();
	var company = $('#company').val();
	var tablenum = $('#tablenum').val();
	var seatnum = $('#seatnum').val();
	var newMobile = $('#newMobile').val();
	var mobile = $('#mobile').val();
	
	$.ajax({
		type:"post",
		url:"/macaupassyearmanage/updateMem.do",
		data:{
			memname:memname,
			company:company,
			tablenum:tablenum,
			seatnum:seatnum,
			newMobile:newMobile,
			mobile:mobile
		},
		dataType:"json",
		cache:"false",
		success:update_success,
		error:update_error
	});
}
</script>
</html>