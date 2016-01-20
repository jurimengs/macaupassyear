package com.org.rute;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;

import com.org.container.CommonContainer;
import com.org.container.UserManager;
import com.org.utils.StringUtil;

/**
 * 五等奖
 */
@Component
public class ParentAward {
	/**
	 * 获取中奖名单
	 * @param key 目标奖项
	 * @param count 奖数量
	 * @return
	 */
	public synchronized JSONArray init(String key, String level, int count){
		JSONArray userList = getExistintList(key);
		if(userList == null) {
			// 如果已存在，则去除，以防重复抽奖
			userList = createRandomUser(count);
		}
		System.out.println("中奖名单＝＝＝》" + userList.toString());
		CommonContainer.saveDataCacheAndDB(key, level, userList);
		return userList;
	}

	/**
	 * 获取中奖名单
	 * @param key 目标奖项
	 * @param count 奖数量
	 * @return
	 */
	public synchronized JSONArray initTemporary(String key, String level, int count){
		JSONArray userList = getExistintList(key);
		if(userList == null) {
			// 如果已存在，则去除，以防重复抽奖
			userList = createTemporaryRandomUser(count);
		}
		System.out.println("中奖名单＝＝＝》" + userList.toString());
		CommonContainer.saveDataCacheAndDB(key, level, userList);
		return userList;
	}
	
	/**
	 * 补抽奖
	 * @param key 目标奖项
	 * @param count 奖数量
	 * @return
	 */
	public synchronized JSONArray bucj(String key, String level, int count){
		JSONArray userList = createRandomUser(count);
		System.out.println("中奖名单＝＝＝》" + userList.toString());
		return userList;
	}
	
	/**
	 * 生成随机号码
	 * 
	 * @param count
	 *            随机号的个数
	 * @return
	 */
	public JSONArray createRandomUser(int count) {
		JSONArray noAwardUser = UserManager.getAllNoAwardUser();
		System.out.println("noAwardUser size ====> " + noAwardUser.size());
		System.out.println("userBackup size ====> " + UserManager.getUserBackup().size());
		JSONArray res = new JSONArray();
		// 为了公平，应该先一次性生成所有index
		int min = 0;
		int max = noAwardUser.size()-1;
		// 生成随机索引
		int[] indexs = StringUtil.randomCommon(min, max, count);

		// 根据生成的随机索引，取arr对应的元素（该元素为手机号）
		String aimPhonenum = null;
		for (int i = 0; i < indexs.length; i++) {
			//System.out.println("随机索引==> "+indexs[i]);
			// 根据索引获取手机号
			aimPhonenum = noAwardUser.getString(indexs[i]);
			// 将目标从容器中取出，放到结果中（这是一个jsonobject）
			res.add(UserManager.getUser(aimPhonenum));
		}
		
		// 已取出的目标，从未中奖列表中去除
		
		for (int i = 0; i < res.size(); i++) {
			aimPhonenum = res.getJSONObject(i).getString("moible");
			UserManager.removeAwardUser(aimPhonenum);
		}
		// 已取出的目标，从未中奖列表中去除
		
		UserManager.removeAwardUser(res);

		return res;
	}
	
	/**
	 * 生成临时奖的随机号码
	 * 
	 * @param count
	 *            随机号的个数
	 * @return
	 */
	public JSONArray createTemporaryRandomUser(int count) {
		JSONArray noAwardUser = UserManager.getAllTemporaryUser();
		System.out.println("getAllTemporaryUser  ====> " + noAwardUser.toString());
		JSONArray res = new JSONArray();
		// 为了公平，应该先一次性生成所有index
		int min = 0;
		int max = noAwardUser.size();
		// 生成随机索引
		int[] indexs = StringUtil.randomCommon(min, max, count);
		if(indexs == null) {
			return new JSONArray();
		}

		// 根据生成的随机索引，取arr对应的元素（该元素为手机号）
		String aimPhonenum = null;
		for (int i = 0; i < indexs.length; i++) {
			//System.out.println("随机索引==> "+indexs[i]);
			// 根据索引获取手机号
			aimPhonenum = noAwardUser.getString(indexs[i]);
			// 将目标从容器中取出，放到结果中（这是一个jsonobject）
			res.add(UserManager.getUser(aimPhonenum));
		}
		
		// 将已中奖的用户从 未中奖用户池中去除
		for (int i = 0; i < res.size(); i++) {
			aimPhonenum = res.getJSONObject(i).getString("moible");
			UserManager.removeAwardUser(aimPhonenum);
		}
		
		// 抽奖完成后，初始化掉所有的临时用户，为下个抽奖备用
		UserManager.initAllTemporaryUser();

		return res;
	}

	public JSONArray getExistintList(String listFlag) {
		if (CommonContainer.getData(listFlag) != null) {
			// 如果已经有了，就直接返回
			JSONArray res = (JSONArray) CommonContainer.getData(listFlag);
			return res;
		}
		return null;
	}

	public static void main(String[] args) {
	}

}
