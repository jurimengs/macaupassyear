package com.org.connection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.org.Connection;
import com.org.common.CommonConstant;
import com.org.container.CommonContainer;
import com.org.exception.BusinessException;
import com.org.utils.JSONUtils;
import com.org.utils.ResultSetUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikaricpMysqlConnection implements Connection<java.sql.Connection> {

	@Override
	public String getId() {
		return CommonConstant.DB_MYSQL;
	}

	public JSONObject queryList(JSONObject param, String sql) {
		// ��ҳ��¼��
		//Integer pageCounts = Integer.valueOf(param.getString("pageCounts"));
		//param.remove("pageCounts");
		// ҳ��
		//Integer currentPage = Integer.valueOf(param.getString("currentPage"));
		//param.remove("currentPage");

		JSONObject sqlParamsJSON = param.getJSONObject("sqlParams");
		Map<Integer, Object> sqlParams = JSONUtils
				.parseJSON2IntegerKeyMap(sqlParamsJSON);

		java.sql.Connection con = null;

		JSONObject res = new JSONObject();
		try {
			con = template.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSetUtil.setStatmentParams(ps, sqlParams);

			// ��ѯ����Ҫrollback�����Ҫ�������¾͵�rollback
			ResultSet rs = ps.executeQuery();

			res.put(CommonConstant.RESP_CODE, "10000");
			res.put(CommonConstant.RESP_MSG, "��ѯ�ɹ�");

			JSONArray list = ResultSetUtil.parseResultSetToJSONArray(rs, true);
			JSONObject totalObj = new JSONObject();
			// ��Ϣ��¼
			res.put("record", list);
			// ���������ĸ�����Ϣ
			res.put("totalObj", totalObj);
		} catch (SQLException e) {
			try {
				if (con != null && !con.isClosed() && !con.getAutoCommit()) {
					con.rollback();
				}
			} catch (SQLException e1) {

				res.put(CommonConstant.RESP_CODE, "10001");
				res.put(CommonConstant.RESP_MSG, "��ѯ�����쳣" + e.getMessage());
				res.put("record", "");
				res.put("totalObj", new JSONObject());
			}
		} catch (IllegalArgumentException e) {
			res.put(CommonConstant.RESP_CODE, "10001");
			res.put(CommonConstant.RESP_MSG,
					"IllegalArgumentException" + e.getMessage());
			res.put("record", "");
			res.put("totalObj", new JSONObject());
		} catch (IllegalAccessException e) {
			res.put(CommonConstant.RESP_CODE, "10001");
			res.put(CommonConstant.RESP_MSG,
					"IllegalAccessException" + e.getMessage());
			res.put("record", "");
			res.put("totalObj", new JSONObject());
		} catch (InvocationTargetException e) {
			res.put(CommonConstant.RESP_CODE, "10001");
			res.put(CommonConstant.RESP_MSG,
					"InvocationTargetException" + e.getMessage());
			res.put("record", "");
			res.put("totalObj", new JSONObject());
		}

		return res;
	}

	public JSONObject executeQuery(JSONObject requestJson) {
		JSONObject queryParams = requestJson.getJSONObject("queryParams");
		String sql = requestJson.getString("sql");

		JSONObject result = new JSONObject();
		result = queryList(queryParams, sql);

		JSONObject totalObj = (JSONObject) result.remove("totalObj");
		JSONArray record = (JSONArray) result.remove("record");

		String currentPage = totalObj.containsKey("currentPage") ? totalObj
				.getString("currentPage") : "0";
		String totalCounts = totalObj.containsKey("totalCounts") ? totalObj
				.getString("totalCounts") : "0";
		String totalPageCounts = totalObj.containsKey("totalPageCounts") ? totalObj
				.getString("totalPageCounts") : "0";
		result.put("currentPage", currentPage);
		result.put("totalCounts", totalCounts);
		result.put("totalPageCounts", totalPageCounts);
		result.put("memo1", "");
		result.put("memo2", "");
		result.put(CommonConstant.RESP_CODE, "10000");
		result.put(CommonConstant.RESP_MSG, "�����ɹ�");
		result.put("busiInfo", record);
		return result;
	}

	public DataSource getDataSource() {
		return template;
	}


	public HikaricpMysqlConnection(Properties pro) {
		HikariConfig dbConfig = new HikariConfig(pro);
		HikariDataSource temp = new HikariDataSource(dbConfig);
		this.template = temp;
	}

	public HikaricpMysqlConnection() throws BusinessException {
		HikariDataSource temp = loadHikariDataSource();
		this.template = temp;
	}

	@Override
	public void close(java.sql.Connection obj) {
		try {
			if (obj != null && !obj.isClosed()){
				obj.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public java.sql.Connection getRealConnection() {
		try {
			if(template == null || template.getConnection() == null){
				// template��ʧЧ
				System.out.println("template == null || template.getConnection() == null");
				template = loadHikariDataSource();
			}
			return template.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}

	private HikariDataSource loadHikariDataSource() throws BusinessException{

		InputStream pin = null;

		Properties pro = new Properties();
		String fileName = "/WEB-INF/config/directdb_spring_db.properties";
		try {
			pin = CommonContainer.getServletContext().getResourceAsStream(
					fileName);
			pro.load(pin);
		} catch (IOException e) {
			throw new BusinessException("load properties error : " + fileName);
		}

		HikariConfig dbConfig = new HikariConfig(pro);
		HikariDataSource temp = new HikariDataSource(dbConfig);
		return temp;
	}
	
	private DataSource template;
}
