package com.org.services.busi;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.org.common.CommonConstant;
import com.org.container.CommonContainer;
import com.org.container.UserManager;
import com.org.dao.CommonDao;
import com.org.exception.SvcException;
import com.org.util.SpringUtil;
import com.org.utils.StringUtil;
/**
 * ɼ�����
 *
 */
@Service
public class SandYearService {
	
	public JSONObject queryYearMember( String phoneNumber, String type,String name){
		JSONObject response = null;
		JSONArray allSym = new JSONArray();
		try {
			response = new JSONObject();			
			if("1".equals(type)){//��¼,�����û���Ϣ,����Ϣ
				String sql = "select a.*, b.roleflag from smp_year_member a left join smp_year_manager b on a.moible = b.moible where a.moible=? and a.memname=?";
				Map<Integer , Object> params = new HashMap<Integer, Object>();
				params.put(1, phoneNumber);
				params.put(2, name);
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
				JSONObject sym = commonDao.querySingle(sql, params, false);
				if(sym==null){
					response.put(CommonConstant.RESP_CODE, "10S710");
					response.put(CommonConstant.RESP_MSG, "�û�������");
				}else{					
					String queryRewardSql = "select a.*, b.roleflag from smp_year_member a left join smp_year_manager b on a.moible = b.moible where a.rewardstate=? or a.rewardstate=? or a.rewardstate=?";
					Map<Integer , Object> rewardParam = new HashMap<Integer, Object>();
					rewardParam.put(1, "t");
					rewardParam.put(2, "1");
					rewardParam.put(3, "2");
					JSONArray rewardArrary = commonDao.queryJSONArray(queryRewardSql, rewardParam, false);
					
					JSONObject json = commonDao.querySingle("select count(*) from smp_year_member", new HashMap<Integer, Object>(), false);
					sym.put("count", json.getString("count(*)"));
					
					response.put("usermeg", sym);
					response.put("rewardarrary", rewardArrary);
					
					CommonContainer.saveData(CommonConstant.TER_12, rewardArrary);
					
					response.put(CommonConstant.RESP_CODE, "10000");
					response.put(CommonConstant.RESP_MSG, "�ɹ�");				
				}			
			}else if ("2".equals(type)) {//��ѯͬ�����û�
				String sql = "select * from smp_year_member where moible=?";
				Map<Integer , Object> params = new HashMap<Integer, Object>();
				params.put(1, phoneNumber);
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
				JSONObject sym = commonDao.querySingle(sql, params, false);
				
				String sql1="select * from smp_year_member where tableNum=? and company=?";//ͬ����Ϣ
				Map<Integer , Object> param = new HashMap<Integer, Object>();
				param.put(1, sym.getString("tablenum"));
				param.put(2, sym.getString("company"));
				allSym = commonDao.queryJSONArray(sql1, param, false);
				response.put("usermeg", sym);
				response.put("resultInfo", allSym);
				response.put(CommonConstant.RESP_CODE, "10000");
				response.put(CommonConstant.RESP_MSG, "�ɹ�");	
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	//����ĿͶƱ
	public JSONObject updateProgram(String phoneNumber,String pnumber, String type){
		JSONObject response = new JSONObject();
		try {
			if("1".equals(type)){//ͶƱ��������ͶƱ���
				String sql = "select * from smp_year_member where moible=?";
				Map<Integer , Object> params = new HashMap<Integer, Object>();
				params.put(1, phoneNumber);
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");			
				JSONObject sym = commonDao.querySingle(sql, params, false);
				if(StringUtils.isEmpty(sym.getString("pname"))){ //û��Ͷ��Ʊ
					String upYearProgramSql = "update smp_year_program set pcount=pcount+1 where pnumber=?";//���½�ĿƱ��
					Map<Integer , Object> upparam = new HashMap<Integer, Object>();
					upparam.put(1, pnumber);
					commonDao.update(upYearProgramSql, upparam);
					
					//String queryYearP = "select * from smp_year_program where pnumber=?";//��ѯ��Ŀ����
					//Map<Integer , Object> queryparam = new HashMap<Integer, Object>();
					//upparam.put(1, pnumber);
					//JSONObject yearProgram = commonDao.querySingle(queryYearP, queryparam, false);
					
					String upYearMemberSql = "update smp_year_member set pname=? where moible=?";//�����û�ͶƱ��Ŀ
					Map<Integer , Object> upYearMemberparam = new HashMap<Integer, Object>();
					upYearMemberparam.put(1, pnumber);
					upYearMemberparam.put(2, phoneNumber);
					commonDao.update(upYearMemberSql, upYearMemberparam);
					
					String queryProsql = "select * from smp_year_program order by pnumber";//��ѯͶƱ���
					JSONArray allProgram = commonDao.queryJSONArray(queryProsql, new HashMap<Integer, Object>(),false);				
					response.put("respCode", "10000");
					response.put("respMsg", "ͶƱ�ɹ�");
					response.put("resultInfo", allProgram);
				}else{//Ͷ��Ʊ��ֱ�ӷ���ͶƱ���
					String queryProsql = "select * from smp_year_program order by pnumber";//��ѯͶƱ���
					JSONArray allProgram = commonDao.queryJSONArray(queryProsql, new HashMap<Integer, Object>(),false);				
					response.put("respCode", "10S708");
					response.put("respMsg", "��ͶƱ");
					response.put("resultInfo", allProgram);
				}
			}else if("2".equals(type)){//��ѯͶƱ���
				String sql="select * from smp_year_program order by pnumber";
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
				JSONArray allProgram = commonDao.queryJSONArray(sql, new HashMap<Integer, Object>(),false);
				response.put("respCode", "10000");
				response.put("respMsg", "�ɹ�");
				response.put("resultInfo", allProgram);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;	
	}
	
	//����ĿͶƱ
	public void updateProgramStartFlag(String isstart,String pnumber){
		String sql="update smp_year_program set isstart= ? where pnumber = ?";
		Map<Integer , Object> params = new HashMap<Integer, Object>();
		params.put(1, isstart);
		params.put(2, pnumber);
		
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		try {
			commonDao.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public JSONObject userDraw(String phoneNumber,
			String level, String type,String currentPage,String pageLine){
		JSONObject response = new JSONObject();
		try {
			if("1".equals(type)){ //��ѯ�û��н����ҵĽ�Ʒ��
				String sql = "select * from smp_year_member where moible=?";
				Map<Integer , Object> params = new HashMap<Integer, Object>();
				params.put(1, phoneNumber);
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");		
				JSONObject yearMember = commonDao.querySingle(sql, params, false);
				if(yearMember==null){
					response.put(CommonConstant.RESP_CODE, "10S710");
					response.put(CommonConstant.RESP_MSG, "�û�������");
				}else{
					if(StringUtils.isNotBlank(yearMember.getString("rewardstate"))){
						response.put("resultInfo",yearMember);
						response.put("respCode", "10000");
						response.put("respMsg", "�ɹ�");
					}else{
						response.put("respCode", "10S711");
						response.put("respMsg", "δ�н�");
					}
				}
			}else if("2".equals(type)){ //��վ�齱
				String sql = "select * from smp_year_prize where pname=?";//��ѯ����
				Map<Integer , Object> params = new HashMap<Integer, Object>();
				params.put(1, level);
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");		
				JSONObject syPrize = commonDao.querySingle(sql, params, false);
				if("0".equals(syPrize.getString("pstatus"))){//�жϸý����ȡ״̬����ֹ�ظ��齱
					response.put("respCode", "10S716");
					response.put("respMsg", "�ý����Ѿ���ȡ,�����ظ��齱");
					return response;
				}else{
					JSONArray resultInfo = new JSONArray();
					String allSql = "select * from smp_year_member where rewardstate is null and ischeck is null";//��ѯ����δ�н��û�
					JSONArray allSym = commonDao.queryJSONArray(allSql, new HashMap<Integer, Object>(),false);
					int total = Integer.parseInt(syPrize.getString("pnumber"));
					
					String ssql = "select * from smp_year_member where ischeck=?";
					Map<Integer , Object> sparam = new HashMap<Integer, Object>();
					sparam.put(1, level);
					JSONArray sMember = commonDao.queryJSONArray(ssql, sparam, false);
					
					total = total-sMember.size();
					
					int[] a = StringUtil.randomCommon(0, allSym.size(), total);
					for(int i=0;i<a.length;i++){
						int num = a[i];
						JSONObject sym = allSym.getJSONObject(num);
						resultInfo.add(sym);
						String upUserAwardSql = "update smp_year_member set rewardState=? where moible=?";
						Map<Integer , Object> upparam = new HashMap<Integer, Object>();
						upparam.put(1, level);
						upparam.put(2, sym.getString("moible"));
						commonDao.update(upUserAwardSql, upparam);
					}
					if(sMember.size()>0){						
						for(int i=0;i<sMember.size();i++){
							resultInfo.add(sMember.getJSONObject(i));
							String suSql = "update smp_year_member set rewardState=? where moible=?";
							Map<Integer , Object> suparam = new HashMap<Integer, Object>();
							suparam.put(1, level);
							suparam.put(2, sMember.getJSONObject(i).get("moible"));
							commonDao.update(suSql, suparam);
						}												
					}
					
					String updateYearPrizeSql = "update smp_year_prize set pstatus=? where pname=?";
					Map<Integer , Object> upYearPrizeParam = new HashMap<Integer, Object>();
					upYearPrizeParam.put(1, "0");
					upYearPrizeParam.put(2, level);
					commonDao.update(updateYearPrizeSql, upYearPrizeParam);
					
					if("5".equals(level)){
						CommonContainer.saveData(CommonConstant.FIFTH_USERLIST, resultInfo);
					}else if("4".equals(level)){
						CommonContainer.saveData(CommonConstant.FOURTH_USERLIST, resultInfo);
					}else if("3".equals(level)){
						CommonContainer.saveData(CommonConstant.THIRD_USERLIST, resultInfo);
					}else if("2".equals(level)){
						CommonContainer.saveData(CommonConstant.SECOND_USERLIST, resultInfo);
					}else if("1".equals(level)){
						CommonContainer.saveData(CommonConstant.FIRST_USERLIST, resultInfo);
					}else if("t".equals(level)){
						CommonContainer.saveData(CommonConstant.LUCKY_USERLIST, resultInfo);
					}				
					
					response.put("respCode", "10000");
					response.put("respMsg", "�ɹ�");
					response.put("resultInfo",resultInfo);
				}
			}else if("3".equals(type)){ //��ѯ�����н��û�(�ֽ���)
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");		
				String fivesql = "select memname from smp_year_member where rewardstate ='5'";//5�Ƚ��û�
				JSONArray fivePrizeArray = commonDao.queryJSONArray(fivesql, new HashMap<Integer, Object>(),false);
				String fivePrize = arrayToString(fivePrizeArray);
				response.put("fivePrize",fivePrize);
				
				String foursql = "select memname from smp_year_member where rewardstate ='4'";//��ѯ�����н��û�
				JSONArray forePrizeArray = commonDao.queryJSONArray(foursql, new HashMap<Integer, Object>(),false);
				String forePrize = arrayToString(forePrizeArray);
				response.put("forePrize",forePrize);
				
				String threesql = "select memname from smp_year_member where rewardstate ='3'";//3�Ƚ��û�
				JSONArray threePrizeArray = commonDao.queryJSONArray(threesql, new HashMap<Integer, Object>(),false);
				String threePrize = arrayToString(threePrizeArray);
				response.put("threePrize",threePrize);
				
				String twosql = "select memname from smp_year_member where rewardstate ='2'";//2�Ƚ��û�
				JSONArray twoPrizeArray = commonDao.queryJSONArray(twosql, new HashMap<Integer, Object>(),false);
				String twoPrize = arrayToString(twoPrizeArray);
				response.put("twoPrize",twoPrize);
				
				String onesql = "select memname from smp_year_member where rewardstate ='1'";//1�Ƚ��û�
				JSONArray onePrizeArray = commonDao.queryJSONArray(onesql, new HashMap<Integer, Object>(),false);
				String onePrize = arrayToString(onePrizeArray);
				response.put("onePrize",onePrize);
				
				String tsql = "select memname from smp_year_member where rewardstate ='t'";//1�Ƚ��û�
				JSONArray tPrizeArray = commonDao.queryJSONArray(tsql, new HashMap<Integer, Object>(),false);
				String tPrize = arrayToString(tPrizeArray);
				response.put("tPrize",tPrize);
				
				response.put("respCode", "10000");
				response.put("respMsg", "�ɹ�");
				
			}else if("5".equals(type)){ //��ҳ��ѯ
				CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
				String allsql = "select * from smp_year_member where rewardstate is not null";
				JSONArray allSym = commonDao.queryJSONArray(allsql, new HashMap<Integer, Object>(),false);
				
				int nowpage = Integer.valueOf(currentPage);
				int count = Integer.valueOf(pageLine);			
				int totalcount = allSym.size();				
				int pageStart = 0;
				int pageEnd = 0;
				int totalpage =0;
				if(totalcount>0){
					totalpage = (int) Math.ceil((double) totalcount
							/ (double) count);
				}
				if(nowpage ==1){
					pageStart =0;
					pageEnd = count;
				}else if(nowpage >1){
					if(nowpage == totalpage){
						pageStart = (totalpage - 1)*count;
						pageEnd =totalcount; 
					}else {
						pageStart = (nowpage - 1)*count;
						pageEnd =nowpage*count; 
					}	
				}
				String sql = "select * from ( select e.*, rownum rn from (select a.* from smp_year_member a where a.rewardstate is not null)e where rownum <=?) where rn >?";
				Map<Integer , Object> pagingParam = new HashMap<Integer, Object>();
				pagingParam.put(1, pageEnd);
				pagingParam.put(2, pageStart);
				JSONArray pagingSym = commonDao.queryJSONArray(sql, pagingParam,false);
				response.put("respCode", "10000");
				response.put("respMsg", "�ɹ�");
				response.put("resultInfo",pagingSym);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public JSONObject yearManage(String sql,Map<Integer , Object> params, String type){
		JSONObject response = new JSONObject();
		try {
			response.put("respCode", "10S713");
			response.put("respMsg", "�����Ϣ����ʧ��");
			CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
			if("2".equals(type)){//����
				boolean result = commonDao.addSingle(sql, params);
				if(result){
					response.put("respCode", "10000");
					response.put("respMsg", "�ɹ�");
				}
			}else if("1".equals(type)){//�޸�
				 commonDao.update(sql, params);
                 response.put("respCode", "10000");
			     response.put("respMsg", "�ɹ�");		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public JSONObject acceptAward(String phoneNumber){
		JSONObject response = new JSONObject();
		try {
			String sql = "select * from smp_year_member where moible=?";
			Map<Integer , Object> params = new HashMap<Integer, Object>();
			params.put(1, phoneNumber);
			CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
			JSONObject sym = commonDao.querySingle(sql, params, false);
			if(sym==null){
				response.put(CommonConstant.RESP_CODE, "10S710");
				response.put(CommonConstant.RESP_MSG, "�û�������");
			}else {
				if(StringUtils.isBlank(sym.getString("rewardstate"))){//�ж��Ƿ��н�
					response.put("respCode", "10S711");
					response.put("respMsg","δ�н�");
				}else{
					if("0".equals(sym.getString("isprize"))){//���콱
						response.put("respCode", "10S714");
						response.put("respMsg","���������");
					}else{
						String upsql="update smp_year_member set isprize=? where moible=?";
						Map<Integer , Object> param = new HashMap<Integer, Object>();
						param.put(1, "0");
						param.put(2, phoneNumber);
						commonDao.update(upsql, param);
						response.put("respCode", "10000");
						response.put("respMsg", "�ɹ�");
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public JSONObject queryAwardUser(String moible){

		JSONObject response = new JSONObject();
		try {
			String sql = "select * from smp_year_member where moible=?";
			Map<Integer , Object> params = new HashMap<Integer, Object>();
			params.put(1, moible);
			CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
			JSONObject sym = commonDao.querySingle(sql, params, false);
			if(sym==null){
				response.put(CommonConstant.RESP_CODE, "10S710");
				response.put(CommonConstant.RESP_MSG, "�û�������");
			}else {
				response.put(CommonConstant.RESP_CODE, "100000");
				response.put(CommonConstant.RESP_MSG, "");
				response.put("aim", sym);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	
	}
	
	public static String arrayToString(JSONArray array){
		String s = "";
		if(array==null||array.size()<=0){
			s="";
		}else{
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<array.size();i++){
				sb.append(array.get(i).toString()).append("|");
			}
			s = sb.toString().substring(0, sb.toString().length()-1);
		}		
		return s;
	}

	public JSONObject queryCurrentAward() {
		String sql = "select * from SMP_YEAR_CURRENT_AWARD";
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		JSONObject res = new JSONObject();
		try {
			res = commonDao.querySingle(sql, null, null);
		} catch (SvcException e) {
			e.printStackTrace();
			res.put(CommonConstant.RESP_CODE, "DB00002");
			res.put(CommonConstant.RESP_MSG, "���ݿ��ѯ����쳣");
		}
		return res;
	}

	public void saveCurrentAward(String currentAward, String isStart) {
		CommonDao commonDao = (CommonDao)SpringUtil.getBean("commonDao");
		Map<Integer, Object> params = new HashMap<Integer, Object>();
		params.put(1, currentAward);
		params.put(2, isStart);
		
		if(queryCurrentAward() == null) {
			// ��������ھͲ���
			String sql = "insert into SMP_YEAR_CURRENT_AWARD values (?, ?)";
			try {
				commonDao.addSingle(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// ���ھ�update
			String sql = "update SMP_YEAR_CURRENT_AWARD set current_award = ?, is_start = ?";
			try {
				commonDao.update(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public JSONArray queryuser(String key, String value){
		String[] names = value.split("\\,");
		JSONArray testarr = new JSONArray();
		JSONObject temp = null;
		for (int i = 0; i < names.length; i++) {
			temp = UserManager.getUser(names[i]);
			if(temp != null && !temp.isEmpty()) {
				testarr.add(temp);
			}
		}
		if(testarr != null && !testarr.isEmpty()){
			CommonContainer.saveData(key, testarr);
		}
		return testarr;
	}
}
