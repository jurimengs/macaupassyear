package com.org.asynchronous;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

import com.org.container.UserManager;
import com.org.dao.CommonDao;
import com.org.util.SpringUtil;

import net.sf.json.JSONArray;

public class SaveThread  implements Callable<String> {
	private String level;
	private JSONArray data;
	
	public SaveThread(String level, JSONArray data) {
		this.level = level;
		this.data = data;
	}

	@Override
	public String call() throws Exception {
		if(StringUtils.isEmpty(level) || data == null || data.size() <= 0) {
			return null;
		}
		// 2保存到数据库
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		String upUserAwardSql = "update smp_year_member set rewardstate='"+level+"' where moible in(";
		String str = "";
		int paramIndex = 0;
		for (int i = 0; i < data.size(); i++) {
			String aimPhonenum = data.getJSONObject(i).getString("moible");
			paramIndex = i +1;
			str += "?,";
			params.put(paramIndex, aimPhonenum);
			// 保存内存中用户的中奖状态
			UserManager.getUser(aimPhonenum).put("rewardstate", level);
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
		
		return null;
	}
	
}
