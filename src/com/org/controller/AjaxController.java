package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;

@Controller
public class AjaxController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public AjaxController(){
		
	}
	
	private Log log = LogFactory.getLog(AjaxController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("AjaxController.......");
		// ͬԴ���Բ������ȡXXX�ϵ�Զ����Դ
		// ����������������Դ������Զ�������������ʵġ���Ȼ���˴���*Ҳ�����滻Ϊָ�������������ڰ�ȫ���ǣ����齫*�滻��ָ��������
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject tes = new JSONObject();
		tes.put("data", "sssssffff");
		this.write(tes.toString(), "UTF-8", response);
		return;
	}
}
