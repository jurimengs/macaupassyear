package com.org.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Json������
 */
public class JSONUtils {
	private static Log log = LogFactory.getLog(JSONUtils.class);
	
	public static JSONObject getJsonFromStrStrMap(Map<String, String> ssMap){
		JSONObject paramJson = new JSONObject();
		try{
			 paramJson.putAll(ssMap);
			 return paramJson;
		}catch(Exception e){
			log.info("MAPתJSON�������ݹ��̳���...");
			return null;
		}
	}
	
	public static JSONObject getJsonFromKeyValueMap(HashMap<String, String> kvMap){
		JSONObject paramJson = new JSONObject();
		try{
			 paramJson.putAll(kvMap);
			 return paramJson;
		}catch(Exception e){
			log.info("MAPתJSON�������ݹ��̳���...");
			return null;
		}
	}
	
	public static String getJsonStringFromKeyValueMap(HashMap<String, String> kvMap)  {
		JSONObject paramJson = new JSONObject();
		try{
			 paramJson.putAll(kvMap);
			 return paramJson.toString();
		}catch(Exception e){
			log.info("MAPתJSON�ַ������̳���...");
			return null;
		}
	}
	
	public static String getJsonStringFromObject(Object obj)  {
		try{
			 return JSONObject.fromObject(obj).toString();
		}catch(Exception e){
			log.info("����תJSON�ַ������̳���...");
			return null;
		}
	}
	
	public static String getJsonStringFromObject(Object obj,JsonConfig config)  {
		try{
			 return JSONObject.fromObject(obj,config).toString();
		}catch(Exception e){
			log.info("����תJSON�ַ������̳���...");
			return null;
		}
	}
	
	//��������[] �� {}�ȷ�json����ʱ�Զ�������ŵ�"{}" "[{}]" bug
	public static void jsoncheck(JSONObject paramJson){	
		if(paramJson != null){
			Iterator<?> it = paramJson.keys();
			while(it.hasNext()){
				Object key = it.next();
				Object o = paramJson.get(key);
				if(o instanceof String ){//�ַ�������
					String value = (String)o;
					if((value.startsWith("\"{") && value.endsWith("}\"")) || (value.startsWith("\"[") && value.endsWith("]\""))){
						value = value.substring(1, value.length()-1);
						paramJson.put(key, value);
					}
				}else if(o instanceof JSONObject){
					JSONObject jo = (JSONObject)o;
					jsoncheck(jo);
				}else if(o instanceof JSONArray){
					JSONArray ja = (JSONArray)o;
					for (int i = 0; i < ja.size(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						jsoncheck(jo);
					}
				}
			}
		}
	}
	
	public static JSONObject getJsonFromString(String jsonString){	
		try{
			JSONObject paramJson = JSONObject.fromObject(jsonString);
			jsoncheck(paramJson);
			return paramJson;
		}catch(Exception e){
			log.info("�ַ��� תJSON������̳���...");
			return null;
		}
	}
	
	
	public static JSONArray getJsonArrayFromCollection(Collection<?> collection)  {
		JSONArray ja = new JSONArray();
		try{
			 ja.addAll(collection);
			 return ja;
		}catch(Exception e){
			log.info("����תJSON������̳���...");
			return null;
		}
	}
	
	public static Object toBean(String jsonString,Class<?> beanClass){
		try{
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			return JSONObject.toBean(jsonObject, beanClass);
		}catch(Exception e){
			log.info("�ַ��� תJSON������̳���...");
			return null;
		}
	}
	
	public static Map<Object, Object> parseJSON2Map(JSONObject json){
        Map<Object, Object> map = new HashMap<Object, Object>();
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //����ڲ㻹������Ļ�����������
            if(v instanceof JSONArray){
                List<Map<Object, Object>> list = new ArrayList<Map<Object,Object>>();
                @SuppressWarnings("unchecked")
				Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    list.add(parseJSON2Map(it.next()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
	
	public static Map<Integer, Object> parseJSON2IntegerKeyMap(JSONObject json){
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //����ڲ㻹������Ļ�����������
            if(v instanceof JSONArray){
                List<Map<Object, Object>> list = new ArrayList<Map<Object,Object>>();
                @SuppressWarnings("unchecked")
				Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    list.add(parseJSON2Map(it.next()));
                }
                map.put(Integer.valueOf(k.toString()), list);
            } else {
                map.put(Integer.valueOf(k.toString()), v);
            }
        }
        return map;
    }
	
	public static void main(String[] args){
//		String s = "{'merid':'888002148160001','cid':'0001','kids':[{'kid':'01','tids':[{'tid':'000','orgids':[{'orgid':'00000001'}]},{'tid':'001','orgids':[{'orgid':'00000001'}]}]},{'kid':'02','tids':[{'tid':'000','orgids':[{'orgid':'00000005'},{'orgid':'00000006'},{'orgid':'00000007'},{'orgid':'00000008'}]}]},{'kid':'04','tids':[{'tid':'000','orgids':[{'orgid':'00000004'}]}]},{'kid':'06','tids':[{'tid':'000','orgids':[{'orgid':'00000003'}]}]},{'kid':'07','tids':[{'tid':'000','orgids':[{'orgid':'00000002'}]}]}]}";
//		JSONObject jo = JSONUtils.getJsonFromString(s);
//		
//		Object[] paramObjects = new Object[2];
//		paramObjects[0] = jo.getString("merid");
//		System.out.println(paramObjects[0]);
//		
//		
//		System.out.println("cid="+jo.getString("cid"));
//		JSONArray ja = jo.getJSONArray("kids");
//		
//		for (int i = 0; i < ja.size(); i++) {
//			JSONObject joo  = ja.getJSONObject(i);
//			System.out.println("kid="+joo.getString("kid"));
//			
//			JSONArray jaa = joo.getJSONArray("tids");
//			
//			for (int j = 0; j < jaa.size(); j++) {
//				JSONObject jooo  = jaa.getJSONObject(j);
//				
//				System.out.println("tid="+jooo.getString("tid"));
//				
//				JSONArray jaaa = jooo.getJSONArray("orgids");
//				
//				for (int k = 0; k < jaaa.size(); k++) {
//					JSONObject joooo  = jaaa.getJSONObject(k);
//					
//					System.out.println("orgid="+joooo.getString("orgid"));
//				}	
//			}
//			
//		}
	}
}
