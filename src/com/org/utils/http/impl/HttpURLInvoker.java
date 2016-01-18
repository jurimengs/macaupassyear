package com.org.utils.http.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.log.LogUtil;
import com.org.log.impl.LogUtilMg;
import com.org.util.CT;
import com.org.utils.JSONUtils;
import com.org.utils.http.HttpTool;
/**
 * @author Nano
 * 
 * URL ͨѶ���߷�װ
 */
public class HttpURLInvoker implements HttpTool {

	public static Logger log = LoggerFactory.getLogger(HttpURLInvoker.class);

	public  String httpGet(String url,String charset) {
		HttpURLConnection connection = null;
		BufferedReader reader  = null;
	    StringBuffer sb = new StringBuffer();
		try {
			LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "1.������HttpGet����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
			URL getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "2.�ɹ��������ӵ�["+url+"]����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
		    reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),charset));
			String line = "";
			while ((line = reader.readLine()) != null) {
				  sb.append(line);
			}
			LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "3.�ɹ�������ݳ���["+sb.toString().length()+"],���Ͽ�����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
		} catch (MalformedURLException e) {
			LogUtil.log(CT.LOG_CATEGORY_ERR, "URL��ַ����ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
		} catch (IOException e) {
			LogUtil.log(CT.LOG_CATEGORY_ERR, "��������������ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
		} finally{
			 try {
					if(reader != null) reader.close();
					if(connection != null) connection.disconnect();
				} catch (IOException e) {
					LogUtil.log(CT.LOG_CATEGORY_ERR, "�ر�����������ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
				}
		}
		return sb.toString();
	}
	
	public  String httpPost(String content,String url,String charset){
		 HttpURLConnection connection = null;
		 DataOutputStream out = null;
		 BufferedReader reader = null;
		 StringBuffer sb = new StringBuffer();
		try {
			LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "1.������HttpPost����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
			
	        URL postUrl = new URL(url);
	        connection = (HttpURLConnection) postUrl.openConnection();
	        // �����Ƿ���connection���
	        connection.setDoOutput(true);
	        // �����Ƿ���connection����
	        connection.setDoInput(true);
	        // Default is GET
	        connection.setRequestMethod("POST");
	        // Post������ʹ�û���
	        connection.setUseCaches(false);
	        
	        //connection.setFollowRedirects(true);
	        
	        connection.setInstanceFollowRedirects(true);
	        
	        // ����application/x-www-form-urlencoded
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length));   
	        // ���ñ���Ҫ��connect֮ǰ���
	        connection.connect();
	   
	        LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "2.�ɹ��������ӵ�["+url+"]����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
			
	        out = new DataOutputStream(connection.getOutputStream());
	        //out.writeBytes(content); 
	        out.write(content.getBytes(charset));
	        out.flush();
	       
	        reader = new BufferedReader(new InputStreamReader( connection.getInputStream(),charset));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "3.�ɹ�������ݳ���["+sb.toString().length()+"],���Ͽ�����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
		} catch (MalformedURLException e) {
			LogUtil.log(CT.LOG_CATEGORY_ERR, "URL��ַ����ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
		} catch (IOException e) {
			LogUtil.log(CT.LOG_CATEGORY_ERR, "��������������ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
		} finally{
			 try {
				    if(out != null) out.close();
					if(reader != null) reader.close();
					if(connection != null) connection.disconnect();
				} catch (IOException e) {
					LogUtil.log(CT.LOG_CATEGORY_ERR, "�ر�����������ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
				}
		}
		
		return sb.toString();
	}

	public JSONObject httpPost(JSONObject requestJson, String url,String charset) {
		 HttpURLConnection connection = null;
		 DataOutputStream out = null;
		 BufferedReader reader = null;
		 JSONObject resultJson = null;
		try {
			LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "1.������HttpPost����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
			
			Iterator<?> it = requestJson.keySet().iterator();
	    	StringBuffer content = new StringBuffer();
			while(it.hasNext()){
				Object nextObj = it.next();
				if(nextObj != null) {
					String name = nextObj.toString();
					content.append(name).append(CT.SYMBOL_DYH).append(requestJson.getString(name)).append(CT.SYMBOL_ADF);	
				}
			}
			String data = content.substring(0, content.length()-1);
			
	        URL postUrl = new URL(url);
	        connection = (HttpURLConnection) postUrl.openConnection();
	        // �����Ƿ���connection���
	        connection.setDoOutput(true);
	        // �����Ƿ���connection����
	        connection.setDoInput(true);
	        // Default is GET
	        connection.setRequestMethod("POST");
	        // Post������ʹ�û���
	        connection.setUseCaches(false);
	        
	        //connection.setFollowRedirects(true);
	        
	        connection.setInstanceFollowRedirects(true);
	        
	        // ����application/x-www-form-urlencoded
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes(charset).length));   
	        // ���ñ���Ҫ��connect֮ǰ���
	        connection.connect();
	   
	        LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "2.�ɹ��������ӵ�["+url+"]����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
			
	        out = new DataOutputStream(connection.getOutputStream());
	        //out.writeBytes(content); 
	        out.write(data.getBytes(charset));
	        out.flush();
	       
	        reader = new BufferedReader(new InputStreamReader( connection.getInputStream(),charset));
	        StringBuffer buffer = new StringBuffer();
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	buffer.append(line);
	        }
	        
	        String httpResult = buffer.toString();
	        if(httpResult.trim().length() > 0){
	        	resultJson = JSONUtils.getJsonFromString(httpResult);
	        }
	        
	        LogUtil.log(CT.LOG_CATEGORY_COMMUNICATION, "3.�ɹ�������ݳ���["+buffer.toString().length()+"],���Ͽ�����", null, LogUtilMg.LOG_INFO, CT.LOG_PATTERN_COMMUNICATION);
		} catch (MalformedURLException e) {
			LogUtil.log(CT.LOG_CATEGORY_ERR, "URL��ַ����ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
		} catch (IOException e) {
			LogUtil.log(CT.LOG_CATEGORY_ERR, "��������������ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
		} finally{
			 try {
				    if(out != null) out.close();
					if(reader != null) reader.close();
					if(connection != null) connection.disconnect();
				} catch (IOException e) {
					LogUtil.log(CT.LOG_CATEGORY_ERR, "�ر�����������ʧ��", e, LogUtilMg.LOG_ERROR, CT.LOG_PATTERN_ERR);
				}
		}
		
		return resultJson;
	}

	/**
	 * ����ʵ��
	 */
	public String simplePost(JSONObject jsonParam, String remoteUrl,
			String charSet) {
		return null;
	}

	public JSONObject wxHttpsPost(JSONObject paramContent, String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject wxHttpsPost(String paramContent, String url) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONObject wxHttpsGet(JSONObject paramContent, String url) {
		// TODO Auto-generated method stub
		return null;
	}
}
