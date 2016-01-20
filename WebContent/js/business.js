
var g_audio = window.g_audio = new Audio(); //创建一个audio播放器
var g_event = window.g_event = new function() {
    var events = ['load', 'abort', 'canplay', 'canplaythrough', 'durationchange', 'emptied', 'ended', 'error', 'loadeddata', 'loadedmetadata', 'loadstart', 'pause', 'play', 'playing', 'progress', 'ratechange', 'seeked', 'seeking', 'stalled', 'suspend', 'timeupdate', 'volumechange', 'waiting', 'mediachange'];
    g_audio.loop = false;
    g_audio.autoplay = true;
    g_audio.isLoadedmetadata = false;
    g_audio.touchstart = true;
    g_audio.audio = true;
    g_audio.elems = {};
    g_audio.isSupportAudio = function(type) {
        type = type || "audio/mpeg";
        try {
            var r = g_audio.canPlayType(type);
            return g_audio.canPlayType && (r == "maybe" || r == "probably");
        } catch(e) {
            return false;
        }
    };
    g_audio.push = function(meta) {
        g_audio.previousId = g_audio.id;
        g_audio.id = meta.song_id;
        g_audio.previousSrc = g_audio.src;
        g_audio.previousTime = 0.00;
        g_audio.src = g_audio.currentSrc = meta.song_fileUrl;
        g_audio.isLoadedmetadata = false;
        g_audio.autobuffer = true;
        g_audio.load();
        g_audio.play();
        if (g_audio.previousSrc !== g_audio.src) {
            g_audio.play();
        }
    };
    for (var i = 0,
    l = events.length; i < l; i++) { (function(e) {
            var fs = [];
            this[e] = function(fn) {
                if (typeof fn !== 'function') {
                    for (var k = 0; k < fs.length; k++) {
                        fs[k].apply(g_audio);
                    }
                    return;
                }
                fs.push(fn);
                g_audio.addEventListener(e,
                function() {
                    fn.apply(this);
                });
            };
        }).apply(this, [events[i]]);
    }
    this.ended(function() { //播放结束
    });
    this.load(function() { //加载
        this.pause();
        this.play();
    });
    this.loadeddata(function() {
        this.pause();
        this.play();
    });
    this.loadedmetadata(function() {
        this.isLoadedmetadata = true;
    });
    this.error(function() { //请求资源时遇到错误
    });
    this.pause(function() { //歌曲暂停播放
    });
    this.play(function() { //歌曲播放
    });
};




/**
 * 特等奖抽奖台显示特效
 */
function terAward(callback){
	if($("#selectLeve").val() == "t"){
		$("#odiv").animate({marginLeft: "-1000px"}, 600, null, function (){
			// 先隐藏自己
			$(this).hide();
			// 再处理轮转数字
			var tdiv = $("#tdiv");
			// 做掉下来的效果，先把它往上定位
			tdiv.css("marginTop", "-80px");
			tdiv.show();
			tdiv.animate({marginTop: "0px", opacity:"1"}, 200, null, function(){
				tdiv.animate({marginTop: "-15px"}, 100, null, function(){
					tdiv.animate({marginTop: "0px"}, 100, function(){
						$(".w-num-total").fadeIn();
						callback();
					});
				});
			});
		});
	}
}

var rotateNumArr = {
	0:[1],
	1:[1,2,3,4,5,6,7,8,9,0],
	2:[1,2,3,4,5,6,7,8,9,0],
	3:[1,2,3,4,5,6,7,8,9,0],
	4:[1,2,3,4,5,6,7,8,9,0],
	5:[1,2,3,4,5,6,7,8,9,0],
	6:[1,2,3,4,5,6,7,8,9,0],
	7:[1,2,3,4,5,6,7,8,9,0],
	8:[1,2,3,4,5,6,7,8,9,0],
	9:[1,2,3,4,5,6,7,8,9,0],
	10:[1,2,3,4,5,6,7,8,9,0]
};

var rollspeed = {
	0:0,
	1:1,
	2:1,
	3:1.5,
	4:1,
	5:2,
	6:1,
	7:2.5,
	8:1.5,
	9:2,
	10:1.5
};

function gustart(){
	startPlay("start");
	document.getElementById("kaishi").style.display="none";
	document.getElementById("tingz").style.display="block";
}

function gustop(){
	stopPlay();
	document.getElementById("kaishi").style.display="block";
	document.getElementById("tingz").style.display="none";
}

function startRotate(){
	gustart();
	var newIndexArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
	$(".w-num-blackbg").children(":first").text("1");
	// 开始
	var delayTemp = 300;
	$(".w-num-blackbg").each(function(i){
		var objtemp = $(this).children(":first");
		// 开始转
		var delay = newIndexArr[i] * delayTemp;
		if(i != 0) {
			setTimeout(function(){
				startRoll(objtemp, rotateNumArr[i], rollspeed[i]);
			}, delay);
		}
	});
}

/**
 * 特抽奖效果
 */
function stopRotate(awardInfo){
	gustop();
	var newIndexArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
	//var newIndexArr = resort(numIndex);
	$(".w-num-blackbg").children(":first").text("1");
		// 停止
		var delayTemp = 1500;
		// 停止转
		
		$(".w-num-blackbg").each(function(i){
			var objtemp = $(this).children(":first");
			// 特等奖的时候 awardInfo 必不为空
			// 只有在特等奖的时候，才慢慢的显示结果到页面
			var delay = newIndexArr[i] * delayTemp;
			if(newIndexArr[i] >= 9) {
				// 最后几个数字，每个再加一秒
				delay += 1500 * (newIndexArr[i] - 9 + 1);
			}
			var awardInfoarr = awardInfo.split(",");
			setTimeout(function(){
				stopRoll(objtemp, awardInfoarr, i);
			}, delay);
		});
		awardover = "true";
}

/**
 * 单个数字roll
 */
function startRoll(obj, arr, rollspeed){
	var roundLen = arr.length;
	for (var i = 0; i < roundLen; i++) {
		$(obj).append("<div>"+arr[i]+"</div>");
	}
	$(obj).css({
		animation: "num_rotate "+rollspeed+"s infinite linear"
	});
}

/**
 * 单个数字stop
 * @obj 
 * @awardInfo
 * 	中奖信息: moible+"," company+","+ memname;
 * @index
 */
function stopRoll(obj, awardInfoarr, index){
	var phone = awardInfoarr[0];
	var words = phone.charAt(index);
	
	var jqobj = $(obj);
	if(!!words) {
		jqobj.text(words);
		jqobj.css({
			//animationIterationCount: "1"
			animation:""
		});
		if(index == 10) {
			stopPlay();
			var memname = awardInfoarr[2];
			$("#terfont").parent().append("<font id='terMemname' class='w-level'> &nbsp;<strong>"+memname+"</strong></font>");
		}
	}
}

function showGunius(callback){
	try{
		g_audio.elems["id"] = "bbb";
	    if(browser.browserName == "safari") {
		    g_audio.push({song_id:"bbb",song_fileUrl:"/files/music/wuya.mp3"}, callback);
	    } else {
		    g_audio.push({song_id:"bbb",song_fileUrl:"/files/music/wuya.wav"}, callback);
	    }
	} catch(e) {
		alert(e);
	}
}

function resort(arr){
    arr.sort(function(){
    	return 0.5 - Math.random(); 
    });
    var newArr = arr.join().split(",");
    return newArr;
}


function startPlay(music){
	try{
		g_audio.elems["id"] = "aaa";
	    if(browser.browserName == "safari") {
		    g_audio.push({song_id:"aaa",song_fileUrl:"/files/music/"+music+".mp3"});
	    } else {
		    g_audio.push({song_id:"aaa",song_fileUrl:"/files/music/"+music+".wav"});
	    }
	} catch(e) {
		alert(e);
	}
}

function stopPlay(){
	/* try{
	    g_audio.pause();
	} catch(e) {
		alert(e);
	} */
	// 播放终止音
	startPlay("over");
}

function recyleAllUser(){
	recyling = true;
	var delay = 0;
	var flyList = $(".flymenu").children();
	if(flyList.length <= 0) {
		recyling = false;
		return;
	}
	
	flyList.each(function(i){
		var obj = $(this);
		setTimeout(function(){
			obj.show();
			obj.animate({opacity:0, left: "42%", top: "50%"}, 600, null, function(){
				obj.remove();
				if(i >= flyList.length -1) {
					recyling = false;
				}
			});
		}, delay);
		delay += 100;
	});
}



//二等奖的定位
var locations_sec = [
	{id:"0", aimloc:{opacity:1, left: "4%", top: "12%"}, className:"f_m_bg pos_absol section_f_tol01", sectionClassName:["fl_left section_fly01", "fl_left section_fly02"]},
	{id:"1", aimloc:{opacity:1, left: "0%", top: "27%"}, className:"f_m_bg pos_absol section_f_tol02", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]},
	{id:"2", aimloc:{opacity:1, left: "0%", top: "42%"}, className:"f_m_bg pos_absol section_f_tol01", sectionClassName:["fl_left section_fly01", "fl_left section_fly02"]},
	{id:"3", aimloc:{opacity:1, left: "4%", top: "57%"}, className:"f_m_bg pos_absol section_f_tol02", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]},
	{id:"4", aimloc:{opacity:1, left: "18%", top: "47.5%"}, className:"f_m_bg pos_absol section_f_tol01", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]},
	
	{id:"5", aimloc:{opacity:1, left: "79%", top: "12%"}, className:"f_m_bg pos_absol section_f_tol02", sectionClassName:["fl_left section_fly01", "fl_left section_fly02"]},
	{id:"6", aimloc:{opacity:1, left: "83%", top: "27%"}, className:"f_m_bg pos_absol section_f_tol01", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]},
	{id:"7", aimloc:{opacity:1, left: "83%", top: "42%"}, className:"f_m_bg pos_absol section_f_tol02", sectionClassName:["fl_left section_fly01", "fl_left section_fly02"]},
	{id:"8", aimloc:{opacity:1, left: "79%", top: "57%"}, className:"f_m_bg pos_absol section_f_tol01", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]},
	{id:"9", aimloc:{opacity:1, left: "65%", top: "47.5%"}, className:"f_m_bg pos_absol section_f_tol02", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]}
];

//一等奖的定位
var locations_first = [
	{id:"s", aimloc:{opacity:1, left: "13%", top: "47.5%"}, className:"f_m_bg pos_absol section_f_tol01", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]},
	{id:"s", aimloc:{opacity:1, left: "70%", top: "47.5%"}, className:"f_m_bg pos_absol section_f_tol02", sectionClassName:["fl_left section_fly03", "fl_left section_fly04"]}
];

/**
*
* 创建定位
*/
function createLocation(locations, awardInfo, delay){
	var loc;
	for (var i = 0; i < awardInfo.length; i++) {
		var moible = awardInfo[i].moible;
		var memname = awardInfo[i].memname;
		//var company = awardInfo[i].company;
		loc = locations[i];
		var newObject = createPosition(moible, memname, loc.className, loc.sectionClassName);
		$(".flymenu").append(newObject);
	}
	flyToNewLocation(locations, delay);
}

function flyToNewLocation(locations, delay){
	var delayTemp = 0;
	var list = $(".flymenu").children();
	//alert(list.length);
	list.each(function(i){
		var obj = $(this);
		setTimeout(function(){
			//alert(i+":"+locations[i]);
			obj.show();
			obj.animate(locations[i].aimloc, 600);
			// 如果是最后一个，解锁
			if(i >= list.length - 1) {
				locked = false;
			}
		}, delayTemp);
		delayTemp += delay;
	});
}

//参数是二等奖的定位
//createLocation(locations_sec);
//createLocation(locations_first);

/**
* 
* 创建的时候，位置在初始位置
* @mobile 中奖用户手机号 (string)
* @name 中奖用户姓名 (string)
* @className 外div元素的样式 (string)
* @sectionClassName 子元素section的样式 (array)
*/
function createPosition(mobile, name, className, sectionClassName){
	var str = 
	'<div class="' + className + ' init_class">'+
		'<p class="center f_name">'+mobile+'<h3 class="center" style="font-size:22px;">'+name+'</h3></p>'+
		'<section class="'+sectionClassName[0]+'"></section>'+
		'<section class="'+sectionClassName[1]+'"></section>'+
	'</div>';
	return $(str);
}
