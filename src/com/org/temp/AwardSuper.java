package com.org.temp;

import java.util.Map;

import net.sf.json.JSONArray;

import com.org.common.CommonConstant;

/**
 * �صȽ�
 */
public class AwardSuper extends ParentAward implements BuMessageHander{
	private Map<String,String> paramsMap = null;
	public AwardSuper(){}
	public AwardSuper(Map<String,String> paramsMap){
		this.paramsMap = paramsMap;
	}
	
	@Override
	public JSONArray getMessage() {
		String level = paramsMap.get("level");

		// Ϊ���齱���ļ��ݡ�
		String buCounts = paramsMap.get("buCounts");
		int awardCount = Integer.valueOf(buCounts);
		return bucj(CommonConstant.SUPER_USERLIST, level, awardCount);
	}
}
