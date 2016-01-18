package com.org.temp;

import java.util.Map;

import net.sf.json.JSONArray;

import com.org.common.CommonConstant;

/**
 * ���Ƚ�
 */
public class AwardSecond extends ParentAward implements BuMessageHander{
	private Map<String,String> paramsMap = null;
	public AwardSecond(){}
	public AwardSecond(Map<String,String> paramsMap){
		this.paramsMap = paramsMap;
	}

	@Override
	public JSONArray getMessage() {
		String level = paramsMap.get("level");

		// Ϊ���齱���ļ��ݡ�
		String buCounts = paramsMap.get("buCounts");
		int awardCount = Integer.valueOf(buCounts);
		return bucj(CommonConstant.SECOND_USERLIST, level, awardCount);
	}
	
}
