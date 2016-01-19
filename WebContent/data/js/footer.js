(function(){ 
	var param = document.getElementById('footerScript').getAttribute('data-args');
	var selecttype = "index";
	if (param != null && param != '') {
		var params = param.split("&");
		for(var i=0;i<params.length;i++){
			var m = params[i].split("=");
			if(m[0]=='selecttype'){
				selecttype=decodeURIComponent(m[1]);
			}
		}
	}
	var footerhtml = new Array();
		footerhtml.push("<div class=\"my-footer\">");
		footerhtml.push("<ul class=\"square-list clr\">");
		footerhtml.push("<li><p class=\"square-list-content index in_nianhui\" onclick=\"window.location.href='/macaupassyear/toIndex.do';\">年会</p></li>");
		/*footerhtml.push("<li><p class=\"square-list-content"+(selecttype == '' ? "" : "")+"\" "+(selecttype == 'activity' ? "" : "onclick=\"javascript:SD.goUrl('');\"")+" ></p></li>");
		footerhtml.push("<li><p class=\"square-list-content"+(selecttype == '' ? "" : "")+"\" "+(selecttype == 'user_more' ? "" : "onclick=\"javascript:SD.goUrl('');\"")+"></p></li>");*/
		footerhtml.push("</ul>");
		footerhtml.push("</div>");
	document.write(footerhtml.join(''));
})();

$(function(){
	$(".active_view").click(function(){
		$(this).css('background-color', '#b51031');
		$(this).css('border-radius', '3px');
	});
});
