package com.org.dao;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.model.reflect.ReflectDbModel;
import com.org.services.HikaricpMysqlDataSourceService;
import com.org.utils.StringUtil;
import com.org.utils.ByteUtil;
import com.org.utils.DesUtil;

// TODO ��������ҳ
public class BaseDao {
	private static HikaricpMysqlDataSourceService dataSource = HikaricpMysqlDataSourceService.getInstance();
	
	protected java.sql.Connection getConnection(){
		java.sql.Connection res = dataSource.getConnection();
		return res;
	}

	protected <T> List<T> queryListByT(String sql, Map<Integer, Object> params, T entity)
			throws SQLException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		
		java.sql.Connection connection = null;
		List<T> list = new ArrayList<T>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			setStatmentParams(ps, params);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// ����
			int columnCounts = rsmd.getColumnCount();
			//
			ReflectDbModel model = new ReflectDbModel();
			Method m = null;
			while (rs.next()) {
				// ����ط��൱��ÿ��һ�ξ�newһ��,�������ݻḲ����һ�ε�����
				for (int i = 1; i <= columnCounts; i++) {
					initReflectDbModel(rs, model, i);
					if (model.getValue() != null && model.getValue() != "") {
						try {
							m = entity.getClass().getDeclaredMethod("set" + model.getKey(), model.getValue().getClass());
							m.invoke(entity, model.getValue());
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							System.out.println(e.getMessage() + ": NoSuchMethodException");
						}
					}
				}
				list.add(entity);
			}
		}finally{
			releaseAll(rs, ps, connection);
		}			
		return list;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param collumToUpper �Ƿ������շ�
	 * @param secretColumn �Ƿ��м����е�����
	 * @return
	 * @throws SQLException
	 */
	protected JSONArray queryList(String sql, Map<Integer, Object> params, boolean collumToUpper, List<String> secretColumn) throws SQLException{
		JSONArray list = new JSONArray();
		java.sql.Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			if(params != null){
				setStatmentParams(ps, params);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// ����
			int columnCounts = rsmd.getColumnCount();
			//
			JSONObject jo = null;
			String key = "";
			String value = null;
			while (rs.next()) {
				jo = new JSONObject();
				for (int i = 1; i <= columnCounts; i++) {
					//  תʵ����
					key = StringUtil.toEntityName(rsmd.getColumnName(i).toLowerCase(), collumToUpper);
					value = (rs.getObject(i) == null) ? "" : rs.getObject(i).toString();
					if(secretColumn != null && secretColumn.contains(key)){
						// ���������Ҫ���� 
						byte[] valueByte;
						try {
							valueByte = DesUtil.encryptMode(value.getBytes("UTF-8"));
							// �����ݿ�ȡ��ǰ�� ��ִ�м���
							value = ByteUtil.bytes2HexStr(valueByte);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							
						}
						
					}
					jo.put(key, value);
				}
				list.add(jo);
			}
		}finally{
			releaseAll(rs, ps, connection);
		}			
		return list;		
	}


	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param collumToUpper �Ƿ������շ�
	 * @param secretColumn �Ƿ��м����е�����
	 * @return
	 * @throws SQLException
	 */
	public JSONObject querySingle(String sql, Map<Integer, Object> params, boolean collumToUpper) throws SQLException{
		java.sql.Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		JSONObject returnObj = null;
		try{
			connection = getConnection();
			ps = connection.prepareStatement(sql);
			if(params != null){
				setStatmentParams(ps, params);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// ����
			int columnCounts = rsmd.getColumnCount();
			//
			String key = "";
			String value = null;
			while (rs.next()) {
				returnObj = new JSONObject();
				for (int i = 1; i <= columnCounts; i++) {
					//  תʵ����
					key = rsmd.getColumnName(i).toLowerCase();
					value = (rs.getObject(i) == null) ? "" : rs.getObject(i).toString();
					returnObj.put(key, value);
				}
				break;
			}
		}finally{
			releaseAll(rs, ps, connection);
		}			
		return returnObj;		
	}
	
	private void releaseAll(ResultSet rs, PreparedStatement ps,
			java.sql.Connection connection) throws SQLException {
		if(rs != null){
			rs.close();
		}
		if(ps != null){
			ps.close();
		}
		if(connection != null){
			connection.close();
		}
	}

	protected JSONArray queryList(String sql, Map<Integer, Object> params, List<String> secretColumn) throws SQLException{
		return queryList(sql, params, true, secretColumn);		
	}
	
	protected void setStatmentParams(PreparedStatement ps, Map<Integer, Object> params) throws SQLException{
		for (Iterator<Integer> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			Integer key = iterator.next();
			ps.setObject(key, params.get(key));
		}
	}

	protected static void initReflectDbModel(ResultSet rs, ReflectDbModel model,
			int index) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		String key = rsmd.getColumnName(index);
		// תʵ����
		key = StringUtil.toEntityName(rsmd.getColumnName(index), false);
		
		String paramType = rsmd.getColumnClassName(index);
		Object value = rs.getObject(index);

		// ����ĸ��д
		key = String.valueOf(key.charAt(0)).toUpperCase() + key.substring(1);
		paramType = paramType.substring(paramType.lastIndexOf(".") + 1);

		model.setKey(key);
		model.setParamType(paramType);
		model.setValue(value);
	}
}
