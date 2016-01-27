<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理层抽獎</title>
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

<body class="shaker">

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
	            <!-- container -->
	            <div class="main">
	                <div class="spring-winprize">
	                	<section id="section09" class="demo">
	                    </section>
	                </div>
	                <!-- tedengjiang -->
	                <!-- tedengjiang over-->
	                <!-- wudengjiang sidengjiang sandengjiang -->
	                <div id="odiv" class="winpri-simp-box-bg center">
                    	<font id="opersitTerfont" class="w-level">
                    	管理层发奖
                    	</font>
                    </div>
	                <div class="center">
	                	<input placeholder="请输入奖品个数" style="text-align: center; border: 1px solid #333; opacity: 0.5;" id="buCounts" name="buCounts"  />
	                	<input type="hidden" name="selectLeve" id="selectLeve" value="7"/>
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
		<div class="top-mid-bg" id="drawNumber"><font class="top-mid-txt">获奖者</font></div>
		<div class="modal-body" id="drawList"></div>
	</div>
</div>

</body>
<script type="text/javascript">

//g_audio.push({song_id:"aaa",song_fileUrl:"http://payment-test.sandpay.com.cn/files/music/start.wav"});
var award = {};

var locked = false;
function change(){
	if(locked) {
		return;
	}
	var buCounts = document.getElementById("buCounts").value;
	if(!!! buCounts) {
		alert("请先设置奖项个数");
		return;
	}
	locked = true;
	gustart();
}

// 回收的时候，不能点击抽奖
var recyling = false;
function draw(){
	if(recyling) {
		return;
	}
	// 点击锁
	var leveValue = document.getElementById("selectLeve").value;
	if(leveValue == "t") {
		awardover = "ing";
	}

	var buCounts = document.getElementById("buCounts").value;
	if(!!! buCounts) {
		alert("请先设置奖项个数");
		return;
	}
	
	$.ajax({
		type: "post",
		url: "/macaupassyear/managercj.do",
		timeout: 45000,
		data: {
			level: leveValue, 
			buCounts: buCounts
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
		var prizehtml = "";
		var drawList = data.memArray;
		
		// 3. 4 .5 g等奖
		var arrtemp = [];
		var arr = [];
		var linecount = 4;

		for (var i=0; i< drawList.length ;i++ ) {
			var indextemp = i % linecount;
			if(indextemp == 0) {
				arrtemp = [];
				arr[arr.length] = arrtemp;
			}
			arrtemp[indextemp] = drawList[i];
		 
		}
		var index = 1;
		for(var n=0; n<arr.length; n++){
			var linearr = arr[n];
			prizehtml+="<ul><li>";
			for(var j=0; j<linearr.length; j++){
				var temp = linearr[j];
				prizehtml+="<div class=\"person_box fl_left\"><strong>"+index+":"+temp.memname+"<span class=\"com_name\">"+temp.company+"</span></strong><dt>"+temp.moible+"</dt></div>";
				index ++;
			}
			prizehtml+="</li></ul>";
		}
		document.getElementById("drawList").innerHTML=prizehtml;
		document.getElementById("maskid").style.display="block";
		gustop();
		locked = false;
	}else{
		gustop();
		var msg = data.respMsg;
		alert(msg);
	}

}

</script>
</html>
