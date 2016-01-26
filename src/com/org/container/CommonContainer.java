package com.org.container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import com.org.asynchronous.SaveThread;

public class CommonContainer {
	public static Map<Object, Object> map = new HashMap<Object, Object>();
	// ��������
	public static Map<String, Object> dataMap = new HashMap<String, Object>();

	public static void saveContext(ServletContext servletContext) {
		map.put("servletContext", servletContext);
	}

	public static ServletContext getServletContext(){
		if(map.get("servletContext") != null){
			return (ServletContext)map.get("servletContext");
		}
		return null;
	}

	/**
	 * �������
	 * @param key
	 * @param data
	 */
	public static void removeData(String key) {
		dataMap.remove(key);
	}

	/**
	 * ���浽�ڴ�
	 * @param key
	 * @param data
	 */
	public static void saveData(String key, Object data) {
		dataMap.put(key, data);
	}

	/**
	 * ���浽�ڴ�
	 * @param key
	 * @param data
	 */
	public static Object saveData(String key, String data) {
		dataMap.put(key, data);
		return dataMap.get(key);
	}

	/**
	 * ���浽�ڴ��ͬʱ��Ҳ���浽���ݿ�
	 * 2016-01-22 ���浽���ݿ�Ĳ�����������Ƴ��첽�̴߳���
	 * @param key
	 * @param data
	 */
	public static void saveDataCacheAndDB(String key, String level, JSONArray data) {
		// 1���浽����
		dataMap.put(key, data);
		if(data==null || data.size() <= 0) {
			return;
		}
		
		Callable<String> event = new SaveThread(level, data);
		try {
			RuteThreadPool.submit(event);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
	}

	public static Object getData(String key){
		if(dataMap.containsKey(key) && dataMap.get(key) != null){
			return dataMap.get(key);
		}
		return null;
	}
	
}
