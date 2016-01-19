package com.org.container;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.common.CommonConstant;
import com.org.dao.CommonDao;
import com.org.util.SpringUtil;
import com.org.utils.PropertiesUtil;

public class UserManager {
	/**
	 * 所有用户信息map
	 * 一个user是一个jsonobject， 以  15922245222: {"user":"test", "phome":"15922245222"} 的格式存储在map中
	 */
	private static Map<String, JSONObject> userMap = new HashMap<String, JSONObject>();
	/**
	 * 未中奖用户：只存放手机号
	 */
	private static JSONArray noAwardUser = new JSONArray();
	/**
	 * 所有用户手机号备份
	 */
	private static JSONArray userBackup = new JSONArray();
	/**
	 * 临时用户池，用于三等奖、特等奖用户池
	 */
	private static JSONArray temporary = new JSONArray();

	/**
	 * 添加到临时抽奖用户列表
	 * @param phonenum
	 */
	public static void addUserToTemporary(String phonenum){
		if(userMap.containsKey(phonenum)){
			temporary.add(phonenum);
		}
	}
	
	public static JSONArray getAllTemporaryUser(){
		return temporary;
	}
	
	public static void initAllTemporaryUser(){
		temporary = new JSONArray();
	}
	
	public static void initUserInfo(){
		// 初始化这三个数据
		// userMap
		// allUser
		// noAwardUser
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");		
		String allSql = "select * from smp_year_member";//查询所有未中奖用户
		JSONArray allSym = null;
		try {
			allSym = commonDao.queryJSONArray(allSql, new HashMap<Integer, Object>(),false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String remove = PropertiesUtil.getValue("award", "remove");
	    String[] removeList = remove.split(",");
	    
	    // 嫌疑人名单。这些人将被排除在中奖名单之外
	    List<String> s = Arrays.asList(removeList);
		    
		JSONObject o = null;
		String key = "";
		for (int i = 0; i < allSym.size(); i++) {
			o = allSym.getJSONObject(i);
			key = o.getString("moible");
			if(!s.contains(key)) {
				// 如果该用户不在嫌疑人名单中。
				if(StringUtils.isEmpty(o.getString("rewardstate"))) {
					// 如果未中奖
					noAwardUser.add(key);
				}
			} else {
				System.out.println("====> 嫌疑人名单：" + key);
			}
			// where rewardstate is null and ischeck is null
			userMap.put(key, o);
		}
		
		userBackup.addAll(JSONArray.toCollection(noAwardUser));
		System.out.println("用户信息初始化完成，未中奖人数====>"+userBackup.size());
	}
	
	/**
	 * 获取所有未中奖的用户
	 * @return
	 */
	public static JSONArray getAllNoAwardUser(){
		return noAwardUser;
	}
	
	/**
	 * 获取所有未中奖的用户
	 * @return
	 */
	public static JSONArray getUserBackup(){
		return userBackup;
	}
	
	
	/**
	 * 已中奖的用户把他拿掉
	 * @param phonenum
	 */
	public static void removeAwardUser(int index){
		noAwardUser.remove(index);
	}
	public static void removeAwardUser(Object o){
		noAwardUser.remove(o);
	}
	
	public static JSONObject getUser(String phonenum){
		return userMap.get(phonenum);
	}
	
	public static void restoreToBackup(){
		noAwardUser = new JSONArray();
		noAwardUser.addAll(JSONArray.toCollection(userBackup));
		CommonContainer.saveData(CommonConstant.FLAG_FIFTH_START, "");	// 设置为未开始
	}
	
	/*
	*//**
	 * 
	 * @param phonenum 手机号为标识
	 * @param params <key=value, key=value, ...>
	 *//*
	public static void updateUserStatus(String phonenum, Map<String, String> params){
		JSONObject aimUser = getUser(phonenum);
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String value = params.get(key);
			aimUser.put(key, value);
			System.out.println("更新目标用户："+phonenum+"-----> key: " + key + "; value: " + value);
		}
	}*/
}
