package com.org.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.org.common.CommonConstant;
import com.org.container.CommonContainer;
import com.org.container.UserManager;
import com.org.services.busi.SandYearService;
import com.org.servlet.CommonController;
import com.org.servlet.SmpHttpServlet;
import com.org.util.SpringUtil;

@Controller
public class SandYearManageController extends SmpHttpServlet implements CommonController{

	private static final long serialVersionUID = 1L;
	
	public void updateYear(HttpServletRequest request, HttpServletResponse response){
		JSONObject noticeData = new JSONObject();
		Map<Integer , Object> param = new HashMap<Integer, Object>();
		String type= request.getParameter("type");
		String sql = "";
		if("1".equals(type)){
			sql="update smp_year_prize set pstatus=?";
			param.put(1, "1");
		}else if("2".equals(type)){
			sql="update smp_year_member set rewardstate=?";
			param.put(1, "");
		}else if("3".equals(type)){
			sql="update smp_year_member set pname=?";
			param.put(1, "");
		}
		try {
			SandYearService yService = (SandYearService)SpringUtil.getBean("sandYearService");
			JSONObject json = yService.yearManage(sql, param, "1");
			noticeData.put("respCode", json.getString(CommonConstant.RESP_CODE));		
			noticeData.put("respMsg", json.getString(CommonConstant.RESP_MSG));

			String deleteCurrent = "delete from SMP_YEAR_CURRENT_AWARD";
			yService.yearManage(deleteCurrent, null, "1");
			
			// Çå³ý»º´æ
			CommonContainer.removeData(CommonConstant.AWARD_FIFTH);
			CommonContainer.removeData(CommonConstant.AWARD_FIRST);
			CommonContainer.removeData(CommonConstant.AWARD_FOURTH);
			CommonContainer.removeData(CommonConstant.AWARD_LUCKY);
			CommonContainer.removeData(CommonConstant.AWARD_SECOND);
			CommonContainer.removeData(CommonConstant.AWARD_SUPER);
			CommonContainer.removeData(CommonConstant.AWARD_THIRD);
			
			CommonContainer.removeData(CommonConstant.FLAG_SUPER_START);
			CommonContainer.removeData(CommonConstant.SUPER_USERLIST);
			
			CommonContainer.removeData(CommonConstant.FLAG_FIRST_START);
			CommonContainer.removeData(CommonConstant.FIRST_USERLIST);
			
			CommonContainer.removeData(CommonConstant.FLAG_SECOND_START);
			CommonContainer.removeData(CommonConstant.SECOND_USERLIST);
			
			CommonContainer.removeData(CommonConstant.FLAG_THIRD_START);
			CommonContainer.removeData(CommonConstant.THIRD_USERLIST);
			
			CommonContainer.removeData(CommonConstant.FLAG_FOURTH_START);
			CommonContainer.removeData(CommonConstant.FOURTH_USERLIST);
			
			CommonContainer.removeData(CommonConstant.FLAG_FIFTH_START);
			CommonContainer.removeData(CommonConstant.FIFTH_USERLIST);
			
			UserManager.initUserInfo();
			
			this.write(noticeData, "utf-8", response);	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return;
	}
	
	public void updateMem(HttpServletRequest request, HttpServletResponse response){
		JSONObject noticeData = new JSONObject();
		Map<Integer , Object> param = new HashMap<Integer, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("update smp_year_member ");
		int i=1;
		String memname= request.getParameter("memname");
		String company= request.getParameter("company");
		String tablenum = request.getParameter("tablenum");
		String seatnum = request.getParameter("seatnum");
		String newMobile =request.getParameter("newMobile");
		if(i==1){
			sb.append("set ");
		}
		if(StringUtils.isNotBlank(memname)){
			sb.append("memname=?, ");
			param.put(i, memname);
			i=i+1;
		}
		if(StringUtils.isNotBlank(company)){			
			sb.append("company=?, ");	
			param.put(i, company);
			i=i+1;
		}
		if(StringUtils.isNotBlank(tablenum)){
			sb.append("tablenum=?, ");
			param.put(i, tablenum);
			i=i+1;
		}
		if(StringUtils.isNotBlank(seatnum)){
			sb.append("seatnum=?, ");
			param.put(i, seatnum);
			i=i+1;
		}
		if(StringUtils.isNotBlank(newMobile)){
			sb.append("moible=?, ");
			param.put(i, newMobile);
			i=i+1;
		}
		String sql = (String) sb.toString().subSequence(0, sb.toString().length()-2);
		sql+="where moible=?";
		param.put(i, request.getParameter("mobile"));
		try {
			SandYearService yService = (SandYearService)SpringUtil.getBean("sandYearService");
			JSONObject json = yService.yearManage(sql, param, "1");
			noticeData.put("respCode", json.getString(CommonConstant.RESP_CODE));		
			noticeData.put("respMsg", json.getString(CommonConstant.RESP_MSG));
			this.write(noticeData, "utf-8", response);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void post(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
