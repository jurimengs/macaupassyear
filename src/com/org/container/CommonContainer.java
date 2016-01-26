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
	// 缓存数据
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
	 * 清除缓存
	 * @param key
	 * @param data
	 */
	public static void removeData(String key) {
		dataMap.remove(key);
	}

	/**
	 * 保存到内存
	 * @param key
	 * @param data
	 */
	public static void saveData(String key, Object data) {
		dataMap.put(key, data);
	}

	/**
	 * 保存到内存
	 * @param key
	 * @param data
	 */
	public static Object saveData(String key, String data) {
		dataMap.put(key, data);
		return dataMap.get(key);
	}

	/**
	 * 保存到内存的同时，也保存到数据库
	 * 2016-01-22 保存到数据库的操作，可以设计成异步线程处理
	 * @param key
	 * @param data
	 */
	public static void saveDataCacheAndDB(String key, String level, JSONArray data) {
		// 1保存到缓存
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
