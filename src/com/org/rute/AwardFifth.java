package com.org.rute;

import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;

import com.org.common.CommonConstant;
import com.org.utils.PropertiesUtil;

/**
 * ��Ƚ�
 */
@Component
public class AwardFifth extends ParentAward implements MessageHander{
	private Map<String,String> paramsMap = null;
	public AwardFifth(){}
	public AwardFifth(Map<String,String> paramsMap){
		this.paramsMap = paramsMap;
	}
	
	@Override
	public JSONArray getMessage() {
		String level = paramsMap.get("level");
		int awardCount = Integer.valueOf(PropertiesUtil.getValue("award", level));
		
		return init(CommonConstant.FIFTH_USERLIST, level, awardCount);
	}
}
