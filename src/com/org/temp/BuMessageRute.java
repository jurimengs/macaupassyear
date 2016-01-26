package com.org.temp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.org.common.CommonConstant;
import com.org.container.CommonContainer;

/**
 * ��Ϣ���ʹ���·��
 * �����������󷵻ص���Ϣ���ݣ���˭������
 * @author Administrator
 *
 */
@Component
public class BuMessageRute {
	/**
	 * ����request��·��PushMessage��ʵ��
	 * @param request
	 * @return
	 */
	public BuMessageHander rute(Map<String,String> paramsMap){
		String level = paramsMap.get("level");
		if(level.equals("5")) {
			// ��ǰ�齱���ó���Ƚ�
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FIFTH);
			CommonContainer.saveData(CommonConstant.FLAG_FIFTH_START, "1");	// ����Ϊ��ʼ
		} else if(level.equals("4")) {
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FOURTH);
			CommonContainer.saveData(CommonConstant.FLAG_FOURTH_START, "1");	// ����Ϊ��ʼ
		} else if(level.equals("3")) {
			// ��ǰ�齱���ó����Ƚ�
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_THIRD);	
			CommonContainer.saveData(CommonConstant.FLAG_THIRD_START, "1");	// ����Ϊ��ʼ
		} else if(level.equals("2")) {
			// ��ǰ�齱���óɶ��Ƚ�
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SECOND);	
			CommonContainer.saveData(CommonConstant.FLAG_SECOND_START, "1");	// ����Ϊ��ʼ
		} else if(level.equals("1")) {
			// ��ǰ�齱���ó�һ�Ƚ�
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_FIRST);
			CommonContainer.saveData(CommonConstant.FLAG_FIRST_START, "1");	// ����Ϊ��ʼ
		} else if(level.equals("t")) {
			// ��ǰ�齱���ó��صȽ�
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SUPER);
			CommonContainer.saveData(CommonConstant.FLAG_SUPER_START, "1");	// ����Ϊ��ʼ
		} else if(level.equals("6")) {
			// ��ǰ�齱���óɹ�Ӧ���ṩ��
			CommonContainer.saveData(CommonConstant.MESSAGE_TYPE, CommonConstant.AWARD_SUPPLIER);
			CommonContainer.saveData(CommonConstant.FLAG_SUPPLIER_START, "1");	// ����Ϊ��ʼ
		}
		
		
		Object o = CommonContainer.getData(CommonConstant.MESSAGE_TYPE);
		if(o == null) {
			System.out.println("��ǰ����δ֪�������ύ�����Ƿ�ָ������");
			return null;
		}
		String messageType = o.toString();
		BuMessageHander aim = null;
		try {
			Class<?> clazz = Class.forName("com.org.temp."+messageType);
			Constructor<?> cons = clazz.getConstructor(Map.class);
			aim = (BuMessageHander) cons.newInstance(paramsMap);
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
