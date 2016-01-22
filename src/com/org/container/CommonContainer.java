package com.org.container;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import com.org.dao.CommonDao;
import com.org.util.SpringUtil;

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
		// 2保存到数据库
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		String upUserAwardSql = "update smp_year_member set rewardstate='"+level+"' where moible in(";
		String str = "";
		int paramIndex = 0;
		for (int i = 0; i < data.size(); i++) {
			paramIndex = i +1;
			str += "?,";
			params.put(paramIndex, data.getJSONObject(i).getString("moible"));
			// 保存内存中用户的中奖状态
			data.getJSONObject(i).put("rewardstate", level);
		}
		str = str.substring(0, str.length()-1);
		upUserAwardSql += str;
		upUserAwardSql += ")";

		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");	
		try {
			commonDao.update(upUserAwardSql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Object getData(String key){
		if(dataMap.containsKey(key) && dataMap.get(key) != null){
			return dataMap.get(key);
		}
		return null;
	}
	
}
