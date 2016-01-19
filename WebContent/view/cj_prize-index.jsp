<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>抽奖</title>
<%@ include file="common.jsp"%>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js?v=<%=b %>"></script>
<link href="/data/css/cj_globel.css" rel="stylesheet" type="text/css">
<link href="/data/css/cj_pop-win-prize.css" rel="stylesheet" type="text/css">
<script type="text/javascript" charset="utf-8" src="/js/browserinfo.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/business.js"></script>

<style type="text/css">
.init_class {
	left: 42%;
	top: 50%;
	opacity:0;
}
</style>
</head>

<body>

<div class="cloudbg">
	<ul class="prizebg">
		<li class="lightsbg">
				<!-- denglong -->
	    		<div class="lanternsbg">
	            	<section id="section_lan01" class=" fl_left"></section>
	                <section id="section_lan02" class=" fl_left"></section>
	                <section id="section_lan03" class=" fl_left"></section>
	                <section id="section_lan04" class=" fl_left"></section>
	                <section id="section_lan05" class=" fl_left"></section>
	                <section id="section_lan06" class=" fl_left"></section>
	            </div>
	            <!-- fly menu -->
	            <div class="flymenu" style="display:block;"></div>
	            <!-- container -->
	            <div class="main">
	                <div class="spring-winprize">
	                	<section id="section09" class="demo">
	                    </section>
	                </div>
	                <!-- tedengjiang -->
	                <div id="tdiv" class="winpri-box-bg center" style="display: none; opacity: 0;">
	                    <span >
	                    	<font id="terfont" class="w-level">
	                    		特等奖
	                    	</font>
	                    </span>
	                    <input type="hidden" name="selectLeve" id="selectLeve" value="${currentAwards }"/>
	                    <div class="clear"></div>
					   <!-- <div id="dt"></div> -->
	                    <div class="w-num-total" style="display: none;">
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                        <div class="fl_left w-num-blackbg"><div class="round_div"><div>8</div></div></div>
	                    </div>
	                </div>
	                <!-- tedengjiang over-->
	                <!-- wudengjiang sidengjiang sandengjiang -->
	                <div id="odiv" class="winpri-simp-box-bg center" style="display:block;">
                    	<font id="opersitTerfont" class="w-level">
                    		<c:if test="${currentAwards eq '5' }">五等奖</c:if>
                    		<c:if test="${currentAwards eq '4' }">四等奖</c:if>
                    		<c:if test="${currentAwards eq '3' }">三等奖</c:if>
                    		<c:if test="${currentAwards eq '2' }">二等奖</c:if>
                    		<c:if test="${currentAwards eq '1' }">一等奖</c:if>
                    	</font>
                    </div>
                    <!-- chuizi -->
	                <div class="bot-drum center" id="kaishi" onclick="change()">
	                    	<section id="section01" class=" fl_left">
	                        </section>
	                        <section id="section02" class=" fl_left">
	                        </section>
	                </div>
	                <!-- fanhuasijin -->
	                <div class="bot-drum center" id="tingz" style="display: none" onclick="draw()">
	                    	<section id="section03" class=" fl_left">
	                        </section>
	                        <section id="section04" class=" fl_left">
	                        </section>
	                </div>
	                <!-- dayuanhua -->
	                <div class="bot-flower">
		               	<section class="section_flower_mid fl_left"></section>
		               	<section class="section_flower fl_left"> </section>
	               </div>
	            </div>
	         </div>
	         <!-- container over-->
	    </li>
	</ul>
</div>
<div class="message" id="maskid" style="display: none">
	<div class="modal">
	   	<!--add-->
		<div class="modal-bg bg-left"></div>
	    <div class="modal-bg bg-right"></div><!--&times;-->
	       <!--add over-->
		<button class="close" onclick="document.getElementById('maskid').style.display='none'; "><h1 style="display:block; margin-top:0px;"><img src="/data/images/close.png" width="32" height="32"></h1>
		</button>
		<div class="top-mid-bg" id="drawNumber"><font class="top-mid-txt">恭喜以下五等奖获得者</font></div>
		<div class="modal-body" id="drawList"></div>
	</div>
</div>

<div id="bucj" style="display:none;color:#ccc;" onclick="bucj();">
	补抽奖
</div>

	<div class="message" id="bucjmask" style="display: none">
		<div class="modal">
		   	<!--add-->
			<div class="modal-bg bg-left"></div>
		    <div class="modal-bg bg-right"></div> 
			<button class="close" onclick="document.getElementById('bucjmask').style.display='none'; "><h1 style="display:block; margin-top:0px;"><img src="/data/images/close.png" width="32" height="32"></h1>
			</button>
			<div class="top-mid-bg" id="drawNumber"><font class="top-mid-txt">请设置补抽奖的个数</font></div>
			<div class="modal-body" id="drawList" style="text-align:center;">
				<form action="/macaupassyear/tobucj.do" onsubmit="return submitToBucj();" method="post" target="_blank">
					<input type="text" id="buCounts" name="buCounts" />
					<input type="hidden" id="currentLevel" name="currentAwards" value="${currentAwards }" />
					<input type="submit" class="btn" value="进入补抽奖" />
				</form>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">

//g_audio.push({song_id:"aaa",song_fileUrl:"http://payment-test.sandpay.com.cn/files/music/start.wav"});
var award = {};

var awardover = "false";
var locked = false;
function change(){
	if(locked) {
		return;
	}
	locked = true;
	if(awardover == "ing") {
		// 如果是特等奖， 并且已经结束了，
		//alert("抽奖在正进行中");
		return;
	} else if(awardover == "true"){
		//alert("抽奖已结束，明年继续");
		return;
		
	}
	
	if($("#currentLevel").val() == "3" || $("#currentLevel").val() == "2" || $("#currentLevel").val() == "1" || $("#currentLevel").val() == "t") {
		$("#bucj").show();
	}
	
	var level = $("#selectLeve").val();
	if(level=='1'){
		// 接下来要抽1等奖， 要把2等奖的人全收了。
		recyleAllUser();
		$("#opersitTerfont").text("一等奖");
	}
	if(level=='t'){
		// 接下来要抽特等奖， 要把1等奖的人全收了。
		recyleAllUser();
		terAward(startRotate);
		return;
	}
	gustart();
}

// 回收的时候，不能点击抽奖
var recyling = false;
function draw(){
	if(recyling) {
		return;
	}
	var leveValue = document.getElementById("selectLeve").value;
	if(leveValue == "t") {
		awardover = "ing";
	}
	$.ajax({
		type: "post",
		url: "/macaupassyear/userDraw.do",
		timeout: 45000,
		data: {
			level: leveValue
		},
		dataType: "json",
		cache: false,
		success: draw_success
	}); 
}

function bucj(){
	$("#bucjmask").show();
}

function submitToBucj(){
	var buCounts = $("#buCounts").val();
	if(!!! buCounts) {
		return false;
	}
}

function draw_success(data, status){
	if(data.respCode=='10000'){
		var currentLevel = $("#selectLeve").val();
		$("#currentLevel").val(currentLevel);
		if(currentLevel=='5'){
			$("#selectLeve").val("4");
			$("#opersitTerfont").text("四等奖");
			var drawNumHtml = "<font class=\"top-mid-txt\">恭喜以下五等奖获得者</font>";
			document.getElementById("drawNumber").innerHTML=drawNumHtml;
		}
		if(currentLevel=='4'){
			$("#selectLeve").val("3");
			$("#opersitTerfont").text("三等奖");
			var drawNumHtml = "<font class=\"top-mid-txt\">恭喜以下四等奖获得者</font>";
			document.getElementById("drawNumber").innerHTML=drawNumHtml;
		}
		if(currentLevel=='3'){
			$("#selectLeve").val("2");
			$("#opersitTerfont").text("二等奖");
			var drawNumHtml = "<font class=\"top-mid-txt\">恭喜以下三等奖获得者</font>";
			document.getElementById("drawNumber").innerHTML=drawNumHtml;
		}
		if(currentLevel=='2'){
			$("#selectLeve").val("1");
		}
		if(currentLevel=='1'){
			$("#selectLeve").val("t");
		}
		
		var prizehtml = "";
		var drawList = data.memArray;
		var for1 = 0;
		
		var ter = "";
		if(currentLevel=='1'){

			// 一等奖特效
			gustop();
			createLocation(locations_first, drawList, 4000);
			return;
		}else if(currentLevel=='2'){
			// 二等奖特效
			gustop();
			createLocation(locations_sec, drawList, 2500);
			return;
		}else if(currentLevel=='t'){
			// 特等奖的中奖者信息
			ter = drawList[0].moible+","+drawList[0].company+","+drawList[0].memname;
			// 特等奖用摇奖的形式
			stopRotate(ter);
			gustop();
			locked = false;
			return;
		}else{
			// 3. 4 .5等奖
			var i=0;
			for1 = drawList.length/5;
			for(var n=0;n<for1;n++){
				prizehtml+="<ul><li>";
				for(var m=0;m<5;m++){
					prizehtml+="<div class=\"person_box fl_left\"><strong>"+drawList[i].memname+"<span class=\"com_name\">"+drawList[i].company+"</span></strong><dt>"+drawList[i].moible+"</dt></div>";
					i=i+1;
				}
				prizehtml+="</ul></li>";
				if(i%25==0){
					prizehtml+="<div class=\"dot_line fl_left \" ></div>";
				}
			}
			document.getElementById("drawList").innerHTML=prizehtml;
			document.getElementById("maskid").style.display="block";
			gustop();
			locked = false;
		}
	}else{
		gustop();
		var msg = data.respMsg;
		alert(msg);
	}
}

</script>
</html>
