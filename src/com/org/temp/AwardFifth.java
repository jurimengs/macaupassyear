package com.org.temp;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.common.CommonConstant;

/**
 * ��Ƚ�
 */
public class AwardFifth extends ParentAward implements BuMessageHander{
	private Map<String,String> paramsMap = null;
	public AwardFifth(){}
	public AwardFifth(Map<String,String> paramsMap){
		this.paramsMap = paramsMap;
	}
	public static void main(String[] args) {
		System.out.println(new JSONObject().toString());
	}
	
	@Override
	public JSONArray getMessage() {
		String level = paramsMap.get("level");

		// Ϊ���齱���ļ��ݡ�
		String buCounts = paramsMap.get("buCounts");
		int awardCount = Integer.valueOf(buCounts);
		
		return bucj(CommonConstant.FIFTH_USERLIST, level, awardCount);
	}
}
