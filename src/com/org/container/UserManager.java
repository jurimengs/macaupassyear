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
	 * �����û���Ϣmap
	 * һ��user��һ��jsonobject�� ��  15922245222: {"user":"test", "phome":"15922245222"} �ĸ�ʽ�洢��map��
	 */
	private static Map<String, JSONObject> userMap = new HashMap<String, JSONObject>();
	/**
	 * δ�н��û���ֻ����ֻ���
	 */
	private static JSONArray noAwardUser = new JSONArray();
	/**
	 * �����û��ֻ��ű���
	 */
	private static JSONArray userBackup = new JSONArray();
	/**
	 * ��ʱ�û��أ��������Ƚ����صȽ��û���
	 */
	private static JSONArray temporary = new JSONArray();

	/**
	 * ��ӵ���ʱ�齱�û��б�
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
		// ��ʼ������������
		// userMap
		// allUser
		// noAwardUser
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");		
		String allSql = "select * from smp_year_member";//��ѯ����δ�н��û�
		JSONArray allSym = null;
		try {
			allSym = commonDao.queryJSONArray(allSql, new HashMap<Integer, Object>(),false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String remove = PropertiesUtil.getValue("award", "remove");
	    String[] removeList = remove.split(",");
	    
	    // ��������������Щ�˽����ų����н�����֮��
	    List<String> s = Arrays.asList(removeList);
		    
		JSONObject o = null;
		String key = "";
		for (int i = 0; i < allSym.size(); i++) {
			o = allSym.getJSONObject(i);
			key = o.getString("moible");
			if(!s.contains(key)) {
				// ������û����������������С�
				if(StringUtils.isEmpty(o.getString("rewardstate"))) {
					// ���δ�н�
					noAwardUser.add(key);
				}
			} else {
				System.out.println("====> ������������" + key);
			}
			// where rewardstate is null and ischeck is null
			userMap.put(key, o);
		}
		
		userBackup.addAll(JSONArray.toCollection(noAwardUser));
		System.out.println("�û���Ϣ��ʼ����ɣ�δ�н�����====>"+userBackup.size());
	}
	
	/**
	 * ��ȡ����δ�н����û�
	 * @return
	 */
	public static JSONArray getAllNoAwardUser(){
		return noAwardUser;
	}
	
	/**
	 * ��ȡ����δ�н����û�
	 * @return
	 */
	public static JSONArray getUserBackup(){
		return userBackup;
	}
	
	
	/**
	 * ���н����û������õ�
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
		CommonContainer.saveData(CommonConstant.FLAG_FIFTH_START, "");	// ����Ϊδ��ʼ
	}
	
	/*
	*//**
	 * 
	 * @param phonenum �ֻ���Ϊ��ʶ
	 * @param params <key=value, key=value, ...>
	 *//*
	public static void updateUserStatus(String phonenum, Map<String, String> params){
		JSONObject aimUser = getUser(phonenum);
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String value = params.get(key);
			aimUser.put(key, value);
			System.out.println("����Ŀ���û���"+phonenum+"-----> key: " + key + "; value: " + value);
		}
	}*/
}
