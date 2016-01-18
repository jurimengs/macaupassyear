package com.org;

import net.sf.json.JSONObject;


public interface Connection<T> {
	public String getId();

	public JSONObject executeQuery(JSONObject requestJson);

	/**
	 * ���ض�Ӧ���ݿ��ʵ�����Ӷ���
	 * @return
	 */
	public T getRealConnection();

	public void close(T obj);
}
