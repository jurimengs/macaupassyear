package com.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.utils.FileUploadUtil;

@Controller
public class UploadController extends SmpHttpServlet implements CommonController{
	private static final long serialVersionUID = 2156792239072761671L;

	public UploadController(){
		
	}
	
	private Log log = LogFactory.getLog(UploadController.class);

	@Override
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("UploadController.......");
		// ͬԴ���Բ������ȡXXX�ϵ�Զ����Դ
		// ����������������Դ������Զ�������������ʵġ���Ȼ���˴���*Ҳ�����滻Ϊָ�������������ڰ�ȫ���ǣ����齫*�滻��ָ��������
		response.setHeader("Access-Control-Allow-Origin", "*");
		FileUploadUtil.uploadFile(request, response);
		return;
	}
}
