package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.org.common.CommonConstant;
import com.org.container.CommonContainer;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;

@Controller
public class MessageController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 1L;

	/**
	 * 消息推送
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject temp = getAwardList();
		String msg = "";
		if(!temp.isEmpty()) {
			msg = temp.toString();
		}
		StringBuffer toPush = new StringBuffer();
		toPush.append("data:");
		toPush.append(msg);
		toPush.append("\n\n");

		response.setHeader("Content-Type","text/event-stream");
		// 不需要这行了，在dispatcher中已经添加了
		// response.setHeader("Cache-Control","no-cache"); 
		// 不需要这行了，在dispatcher中已经添加了
		// response.setDateHeader("Expires", 0);
		this.write(toPush.toString(), CommonConstant.UTF8, response);
		response.getOutputStream().flush();
		return;		
	}
	
	public void whetherAward(HttpServletRequest request, HttpServletResponse response){
		try {
			JSONObject temp = getAwardList();
			JSONObject noticeData = new JSONObject();
			String msg = "";
			if(!temp.isEmpty()){
				String a = temp.getString(CommonConstant.AWARD_FIRST);
				String b = temp.getString(CommonConstant.AWARD_SECOND);
				String c = temp.getString(CommonConstant.AWARD_THIRD);
				String d = temp.getString(CommonConstant.AWARD_FOURTH);
				String e = temp.getString(CommonConstant.AWARD_FIFTH);
				String f = temp.getString(CommonConstant.AWARD_LUCKY);
				String g = temp.getString(CommonConstant.AWARD_SUPPLIER);
				if(a=="" && b=="" && c=="" && d=="" && e=="" && f=="" && g==""){
					msg = "true";
				}else{
					msg = "false";
				}
			}
			noticeData.put("msg", msg);		
			this.write(noticeData, "utf-8", response);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return;
	}
	
	public JSONObject getAwardList(){
		JSONObject temp = new JSONObject();
		// 特等奖中奖名单
		Object t = null;
		if(CommonContainer.getData(CommonConstant.FLAG_FIRST_START) == "1") {
			// 如果已经开始了
			t = CommonContainer.getData(CommonConstant.SUPER_USERLIST);
		}
		// 一等奖中奖名单
		Object a = null;
		if(CommonContainer.getData(CommonConstant.FLAG_FIRST_START) == "1") {
			// 如果已经开始了
			a = CommonContainer.getData(CommonConstant.FIRST_USERLIST);
		}
		// 二等奖中奖名单
		Object b = null;
		if(CommonContainer.getData(CommonConstant.FLAG_FIRST_START) == "1") {
			b = CommonContainer.getData(CommonConstant.SECOND_USERLIST);
		}
		// 三等奖中奖名单
		Object c = CommonContainer.getData(CommonConstant.THIRD_USERLIST);
		// 四等奖中奖名单
		Object d = CommonContainer.getData(CommonConstant.FOURTH_USERLIST);
		// 五等奖中奖名单
		Object e = CommonContainer.getData(CommonConstant.FIFTH_USERLIST);
		// 幸运奖中奖名单
		Object f = CommonContainer.getData(CommonConstant.LUCKY_USERLIST);
		// 供应商奖项中奖名单
		Object g = CommonContainer.getData(CommonConstant.SUPPLIER_USERLIST);
		if(t != null) {
			temp.put(CommonConstant.AWARD_SUPER, t);
		}else{
			temp.put(CommonConstant.AWARD_SUPER, "");
		}
		if(a != null) {
			temp.put(CommonConstant.AWARD_FIRST, a);
		}else{
			temp.put(CommonConstant.AWARD_FIRST, "");
		}
		if(b != null) {
			temp.put(CommonConstant.AWARD_SECOND, b);
		}else{
			temp.put(CommonConstant.AWARD_SECOND, "");
		}
		if(c != null) {
			temp.put(CommonConstant.AWARD_THIRD, c);
		}else{
			temp.put(CommonConstant.AWARD_THIRD, "");
		}
		if(d != null) {
			temp.put(CommonConstant.AWARD_FOURTH, d);
		}else{
			temp.put(CommonConstant.AWARD_FOURTH, "");
		}
		if(e != null) {
			temp.put(CommonConstant.AWARD_FIFTH, e);
		}else{
			temp.put(CommonConstant.AWARD_FIFTH, "");
		}
		if(f != null) {
			temp.put(CommonConstant.AWARD_LUCKY, f);
		}else{
			temp.put(CommonConstant.AWARD_LUCKY, "");
		}
		if(g != null) {
			temp.put(CommonConstant.AWARD_SUPPLIER, g);
		}else{
			temp.put(CommonConstant.AWARD_SUPPLIER, "");
		}
		return temp;
	}

}
