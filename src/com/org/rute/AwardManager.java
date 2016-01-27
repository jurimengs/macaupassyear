package com.org.rute;

import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;

import com.org.common.CommonConstant;
import com.org.utils.PropertiesUtil;

/**
 * π‹¿Ì≤„∑¢Ω±
 */
@Component
public class AwardManager extends ParentAward implements MessageHander{
	private Map<String,String> paramsMap = null;
	public AwardManager(){}
	public AwardManager(Map<String,String> paramsMap){
		this.paramsMap = paramsMap;
	}
	
	@Override
	public JSONArray getMessage() {
		String level = paramsMap.get("level");
		String buCounts = paramsMap.get("buCounts");
		int awardCount = Integer.valueOf(buCounts);
		return bucj(CommonConstant.MANAGER_USERLIST, level, awardCount);
	}
}
