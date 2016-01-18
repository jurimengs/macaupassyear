package com.org.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.org.common.CommonConstant;

/**
 */
public class FileUploadUtil {
	//private static final String excelUploadPath = SmpPropertyUtil.getValue("filepath", "upload_file_path");
	private static String excelUploadPath = "";

	public FileUploadUtil() {
		super();
	}
	
	/**
	 * �ϴ��ļ�
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws IOException
	 */
	public static JSONObject uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(StringUtils.isEmpty(excelUploadPath)) {
			excelUploadPath = request.getSession().getServletContext().getRealPath("/");
		}
		
		JSONObject res = new JSONObject();
		// �½�һ��SmartUpload����
		SmartUpload su = null;
		try {
			su = initSmartUpload(request, response);
		} catch (java.lang.SecurityException e) {
			//e.printStackTrace();
			res.put(CommonConstant.RESP_CODE, "ERROR");
			res.put(CommonConstant.RESP_MSG, "�ϴ��ļ�������Ҫ��");
			return res;
		}
		
		JSONObject formParams = new JSONObject();
		Enumeration<?> paramKey = su.getRequest().getParameterNames();
		while (paramKey.hasMoreElements()) {
			Object key = paramKey.nextElement();
			if(key != null) {
				formParams.put(String.valueOf(key), su.getRequest().getParameter(String.valueOf(key)));
			}
		}

		//���Ҫʵ���ļ��������ϴ�����ֻ����forѭ������getFile(0)�е�0��Ϊi����
		File file = su.getFiles().getFile(0);
		file.getSize();
		if(file != null) {
			//����·�� 
			StringBuffer physicalPathTemp = new StringBuffer();
			//���·����ŵ����ݿ��
			StringBuffer relativePathTemp = new StringBuffer();
			relativePathTemp = relativePathTemp.append("files/").append(DateUtil.getCurrentShortDateStr()).append("/");
			
			String fileName = new String(file.getFileName().getBytes(), "UTF-8");
			physicalPathTemp = physicalPathTemp.append(excelUploadPath).append(relativePathTemp);
			// ��֤����·��Ŀ¼����
			java.io.File dir = new java.io.File(physicalPathTemp.toString());
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			relativePathTemp = relativePathTemp.append(fileName);
			
			// �����ļ�
			file.saveAs(physicalPathTemp.append(fileName).toString());
			formParams.put(CommonConstant.FILE_PATH, "/"+relativePathTemp.toString());
		}
		
		// ���ļ����·���ٷ��س�ȥ
		res.put(CommonConstant.RESP_CODE, "10000");
		res.put(CommonConstant.RESP_MSG, "");
		res.put(CommonConstant.FORM_PARAMS, formParams);
		
		return res;
	}

	private static SmartUpload initSmartUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, SmartUploadException {
		SmartUpload su = new SmartUpload();
		// �ϴ���ʼ��
		JspFactory jspFactory = null;
        jspFactory = JspFactory.getDefaultFactory();
        PageContext pc = jspFactory.getPageContext((HttpServlet)request.getSession().getAttribute(CommonConstant.SERVLET),request,response,"",true,8192,true);
		su.initialize(pc);
		log.info("�ϴ����������ֽڳ���: " + request.getContentLength());
		// �趨�ϴ�����
		// 1.����ÿ���ϴ��ļ�����󳤶ȡ�
		su.setMaxFileSize(5000000);
		// 2.�������ϴ����ݵĳ��ȡ�
		su.setTotalMaxFileSize(50000000);
		// 3.�趨�����ϴ����ļ���ͨ����չ�����ƣ�,������doc,txt�ļ���
		su.setAllowedFilesList("jpg,jpeg");
		// 4.�趨��ֹ�ϴ����ļ���ͨ����չ�����ƣ�,��ֹ�ϴ�����exe,bat,jsp,htm,html��չ�����ļ���û����չ�����ļ���
		su.setDeniedFilesList("exe,bat,jsp,htm,html,xls,xlsx,,");
		// �ϴ��ļ�
		try {
			su.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return su;
	}

	private static Log log = LogFactory.getLog(FileUploadUtil.class);
}
