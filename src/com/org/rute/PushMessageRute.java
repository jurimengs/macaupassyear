package com.org.rute;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.common.CommonConstant;
import com.org.container.CommonContainer;

/**
 * 消息类型处理路由
 * 决定本次请求返回的消息内容，由谁来定义
 * @author Administrator
 *
 */
@Component
public class PushMessageRute {
	/**
	 * 根据request来路由PushMessage的实例
	 * @param request
	 * @return
	 */
	public MessageHander rute(Map<String,String> paramsMap){
		String level = paramsMap.get("level");
		if(level.equals("5")) {
			// 当前抽奖设置成五等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FIFTH);
			CommonContainer.saveData(CommonConstant.FLAG_FIFTH_START, "1");	// 设置为开始
		} else if(level.equals("4")) {
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FOURTH);
			CommonContainer.saveData(CommonConstant.FLAG_FOURTH_START, "1");	// 设置为开始
		} else if(level.equals("3")) {
			// 当前抽奖设置成三等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_THIRD);	
			CommonContainer.saveData(CommonConstant.FLAG_THIRD_START, "1");	// 设置为开始
		} else if(level.equals("2")) {
			// 当前抽奖设置成二等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SECOND);	
			CommonContainer.saveData(CommonConstant.FLAG_SECOND_START, "1");	// 设置为开始
		} else if(level.equals("1")) {
			// 当前抽奖设置成一等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FIRST);
			CommonContainer.saveData(CommonConstant.FLAG_FIRST_START, "1");	// 设置为开始
		} else if(level.equals("t")) {
			// 当前抽奖设置成特等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SUPER);
			CommonContainer.saveData(CommonConstant.FLAG_SUPER_START, "1");	// 设置为开始
		}
		
		
		Object o = CommonContainer.getData(CommonConstant.MESSAGE_TYPE);
		if(o == null) {
			System.out.println("当前奖项未知，请检查提交参数是否指定奖项");
			return null;
		}
		String messageType = o.toString();
		MessageHander aim = null;
		try {
			Class<?> clazz = Class.forName("com.org.rute."+messageType);
			Constructor<?> cons = clazz.getConstructor(Map.class);
			aim = (MessageHander) cons.newInstance(paramsMap);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return aim;
	}
	
	public MessageHander ruteTemp(Map<String,String> paramsMap){
		System.out.println("ruteTemp=======>");
		String level = paramsMap.get("level");
		if(level.equals("5")) {
			// 当前抽奖设置成五等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FIFTH);
			CommonContainer.saveData(CommonConstant.FLAG_FIFTH_START, "1");	// 设置为开始
		} else if(level.equals("4")) {
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FOURTH);
			CommonContainer.saveData(CommonConstant.FLAG_FOURTH_START, "1");	// 设置为开始
		} else if(level.equals("3")) {
			// 当前抽奖设置成三等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_THIRD);	
			CommonContainer.saveData(CommonConstant.FLAG_THIRD_START, "1");	// 设置为开始
		} else if(level.equals("2")) {
			// 当前抽奖设置成二等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SECOND);	
			CommonContainer.saveData(CommonConstant.FLAG_SECOND_START, "1");	// 设置为开始
		} else if(level.equals("1")) {
			// 当前抽奖设置成一等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FIRST);
			CommonContainer.saveData(CommonConstant.FLAG_FIRST_START, "1");	// 设置为开始
		} else if(level.equals("t")) {
			// 当前抽奖设置成特等奖
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SUPER);
			CommonContainer.saveData(CommonConstant.FLAG_SUPER_START, "1");	// 设置为开始
		}
		
		
		Object o = CommonContainer.getData(CommonConstant.MESSAGE_TYPE);
		if(o == null) {
			System.out.println("当前奖项未知，请检查提交参数是否指定奖项");
			return null;
		}
		String messageType = o.toString();
		MessageHander aim = null;
		try {
			Class<?> clazz = Class.forName("com.org.temp."+messageType);
			Constructor<?> cons = clazz.getConstructor(Map.class);
			aim = (MessageHander) cons.newInstance(paramsMap);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return aim;
	}
	
	
}
