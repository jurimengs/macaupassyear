package com.org.rute;

import net.sf.json.JSONArray;

/**
 * ʵ�ֱ��ӿڵ��࣬����в���Ҫ����Ļ�����������вι��췽������������ΪMap<String,String>
 * @author Administrator
 *
 */
public interface MessageHander {
	/**
	 * �����н���Ϣ������ֻ������������룬�������ÿ���
	 * @return
	 */
	public JSONArray getMessage();
}
