package com.org.temp;

import net.sf.json.JSONArray;

import com.org.container.UserManager;
import com.org.utils.StringUtil;

/**
 * ��Ƚ�
 */
public class ParentAward {
	/**
	 * ���齱
	 * @param key Ŀ�꽱��
	 * @param count ������
	 * @return
	 */
	public synchronized JSONArray bucj(String key, String level, int count){
		JSONArray userList = createRandomUser(count);
		System.out.println("�н�������������" + userList.toString());
		return userList;
	}
	
	/**
	 * �����������
	 * 
	 * @param count
	 *            ����ŵĸ���
	 * @return
	 */
	public JSONArray createRandomUser(int count) {
		JSONArray noAwardUser = UserManager.getAllNoAwardUser();
		System.out.println("noAwardUser size ====> " + noAwardUser.size());
		System.out.println("userBackup size ====> " + UserManager.getUserBackup().size());
		JSONArray res = new JSONArray();
		// Ϊ�˹�ƽ��Ӧ����һ������������index
		int min = 0;
		int max = noAwardUser.size()-1;
		// �����������
		int[] indexs = StringUtil.randomCommon(min, max, count);

		// �������ɵ����������ȡarr��Ӧ��Ԫ�أ���Ԫ��Ϊ�ֻ��ţ�
		String aimPhonenum = null;
		for (int i = 0; i < indexs.length; i++) {
			//System.out.println("�������==> "+indexs[i]);
			// ����������ȡ�ֻ���
			aimPhonenum = noAwardUser.getString(indexs[i]);
			// ��Ŀ���������ȡ�����ŵ�����У�����һ��jsonobject��
			res.add(UserManager.getUser(aimPhonenum));
		}
		
		// ��ȡ����Ŀ�꣬��δ�н��б���ȥ��
		
		for (int i = 0; i < res.size(); i++) {
			aimPhonenum = res.getJSONObject(i).getString("moible");
			UserManager.removeAwardUser(aimPhonenum);
		}
		// ��ȡ����Ŀ�꣬��δ�н��б���ȥ��
		
		UserManager.removeAwardUser(res);

		return res;
	}

}
