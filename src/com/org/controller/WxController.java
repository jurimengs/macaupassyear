package com.org.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.CT;
import com.org.utils.PropertiesUtil;
import com.org.utils.StringUtil;
import com.org.utils.WxUtil;

@Controller
public class WxController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public WxController(){
		
	}
	
	public void sandbao(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("token" + this.getParamMap(request));
		String echostr = request.getParameter("echostr");
		if(StringUtils.isNotEmpty(echostr)) {
			String wxsignature = request.getParameter("signature");
			String wxtimestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			
			boolean signResult = WxUtil.checkSignature(wxsignature, wxtimestamp, nonce);
			if(!signResult) {
				log.info("򞺞�e�`");
				return;
			}
			log.info("�y��ɹ�");
			// ��ʾ���״���ǩ
			this.write(echostr, CT.ENCODE_UTF8, response);
			return;
		}
		
		String token = WxUtil.getToken();
		String timestamp = String.valueOf(StringUtil.getTimestamp()); // �������ǩ����ʱ���
		String nonceStr = UUID.randomUUID().toString(); // ���ǩ��������¼1
		String url = request.getRequestURL().toString();
		String signature = WxUtil.localSign(timestamp, nonceStr, url); // �����Ҫʹ�õ�JS�ӿ��б�����JS�ӿ��б����¼2
		String appid = PropertiesUtil.getValue("wx", "appid");
		
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("url", url);
		request.setAttribute("signature", signature);
		request.setAttribute("cacheToken", token);
		request.setAttribute("appId", appid);
		
		//this.forward("/www/html/wxtest.jsp", request, response);
		this.forward("/view/login.jsp", request, response);
		return;
	}
	
	private Log log = LogFactory.getLog(WxController.class);
	
	// TODO �����õ�
	public void toTest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("toTest: " + this.getParamMap(request));
		String token = WxUtil.getToken();
		String timestamp = String.valueOf(StringUtil.getTimestamp()); // �������ǩ����ʱ���
		String nonceStr = UUID.randomUUID().toString(); // ���ǩ��������¼1
		String url = request.getRequestURL().toString();
		String signature = WxUtil.localSign(timestamp, nonceStr, url); // �����Ҫʹ�õ�JS�ӿ��б�����JS�ӿ��б����¼2
		String appid = PropertiesUtil.getValue("wx", "appid");
		
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("url", url);
		request.setAttribute("signature", signature);
		request.setAttribute("cacheToken", token);
		request.setAttribute("appId", appid);
		this.forward("/view/codebarscann.jsp", request, response);
		return;
	}
	
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
	
}
