package com.org.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.org.common.CommonConstant;
import com.org.common.PageConstant;
import com.org.util.SpringUtil;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(DispatcherServlet.class);
	private static Map<String, Method> mtdContainer = new HashMap<String, Method>();
	
	public DispatcherServlet() {
		super();
	}
	
	public void init() throws ServletException {
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String targetUrl ="";
		String servletName = "";
		String _servletName ="";
		try{
			/* 0. �������ݲ�����  */
			response.setHeader("Pragma","no-cache"); 
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0); 
			
			if(request.getSession().getAttribute(CommonConstant.SERVLET) == null){
				request.getSession().setAttribute(CommonConstant.SERVLET, this);
			}
		    
			String uri = request.getRequestURI();
			_servletName = uri.substring(uri.indexOf("/")+1, uri.indexOf(".do"));

			servletName = _servletName;
			String mtdName = "";
			if(_servletName.indexOf("/") != -1){
				servletName = _servletName.substring(0, _servletName.indexOf("/"));
				mtdName = _servletName.substring(_servletName.lastIndexOf("/")+1);
			}
			servletName += "Controller";
			
			// Controller��spring������ȡ��
			CommonController aim = (CommonController)SpringUtil.getBean(servletName);
			/*****�ж��Ƿ�Ϊ�ظ��ύ*************************/
			String token=request.getParameter("token");
			if(token!=null){
				HttpSession session=request.getSession();
				synchronized(session){
					Object value=session.getAttribute(servletName);
					if(value!=null){
						//�ظ��ύ����ֱ�Ӳ���
						return ;
					}
					session.setAttribute(servletName, UUID.randomUUID());
				}
				String tokenParam=request.getSession().getAttribute("token")==null?"":request.getSession().getAttribute("tokenParam").toString();
				
				if(token.equals(tokenParam)){
					request.getSession().removeAttribute("token");
				}else{
					return;
				}
			}
			/*******************************************/
			if(StringUtils.isEmpty(mtdName)){
				aim.post(request, response);
			} else {
				Method m = null;
				String mtdKey = servletName + mtdName;
				if(mtdContainer.containsKey(mtdKey)) {
					m = mtdContainer.get(mtdKey);
				} else {
					m = aim.getClass().getDeclaredMethod(mtdName, new Class<?>[]{HttpServletRequest.class, HttpServletResponse.class});
					mtdContainer.put(servletName + mtdName, m);
				}
				
				m.invoke(aim, request, response);
			}
			//����ִ���꣬����tokenParam
			request.getSession().removeAttribute(servletName);
			log.debug("DispatcherServlet-->" + _servletName);
		}catch(Exception se){
			se.printStackTrace();
			//request.getSession().removeAttribute(CT.COMP_LOCAL_USER);
			targetUrl = PageConstant.ERROR;
			request.setAttribute("respCode", "SYS001");
			request.setAttribute("respMsg", "ϵͳ����");
			try {
				this.forward(targetUrl, request, response);		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void redirect(String targetUrl,HttpServletResponse response) throws Exception{
		response.sendRedirect(targetUrl);
	}

	public void destroy() {
	}
	
	private void forward(String targetUrl,HttpServletRequest request, HttpServletResponse response) throws Exception{
		RequestDispatcher rd = request.getRequestDispatcher(targetUrl);
		rd.forward(request, response);
	}
}
