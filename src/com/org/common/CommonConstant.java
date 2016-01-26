package com.org.common;

public class CommonConstant {
	
	public static String UTF8 = "utf-8";
	public final static String DB_MONGO = "mongo";
	public final static String DB_MYSQL = "hikaricp-mysql";
	public final static String DB_HIKARICP = "hikaricp-oracle";
	public final static String RESP_CODE = "respCode";
	public final static String RESP_MSG = "respMsg";
	public final static String FILE_PATH = "filePath";
	public final static String FORM_PARAMS = "formParams";
	public final static String MESSAGE_TYPE = "messageType";
	public final static String RUTE_RULE = "ruteRule";
	
	// 当前中奖名单
	public final static String TER_12 = "ter12";
	
	// 当前奖项
	public final static String FLAG_CURRENT_AWARDS = "flagCA"; // 当前奖项。存储到数据库，预防突发终止事件

	// 供应商奖中奖名单
	public final static String FLAG_SUPPLIER_START = "supplierflag"; // 是否开奖标识 
	public final static String SUPPLIER_USERLIST = "supplierUserlist";
	// 特等奖中奖名单
	public final static String FLAG_SUPER_START = "superflag"; // 是否开奖标识 
	public final static String SUPER_USERLIST = "superUserlist";
	// 一等奖中奖名单
	public final static String FLAG_FIRST_START = "firstflag"; // 是否开奖标识 
	public final static String FIRST_USERLIST = "firstUserlist";
	// 二等奖中奖名单
	public final static String FLAG_SECOND_START = "secondflag";
	public final static String SECOND_USERLIST = "secondUserlist";
	// 三等奖中奖名单
	public final static String FLAG_THIRD_START = "thirdflag";
	public final static String THIRD_USERLIST = "thirdUserlist";
	// 四等奖中奖名单
	public final static String FLAG_FOURTH_START = "fourthflag";
	public final static String FOURTH_USERLIST = "fourthUserlist";
	// 五等奖中奖名单
	public final static String FLAG_FIFTH_START = "fifthflag";
	public final static String FIFTH_USERLIST = "fifthUserlist";
	// 幸运奖中奖名单
	public final static String FLAG_LUCKY_START = "luckyflag";
	public final static String LUCKY_USERLIST = "luckyUserlist";

	public final static String AWARD_SUPPLIER = "AwardSupplier";
	public final static String AWARD_SUPER = "AwardSuper";
	public final static String AWARD_FIRST = "AwardFirst";
	public final static String AWARD_SECOND = "AwardSecond";
	public final static String AWARD_THIRD = "AwardThree";
	public final static String AWARD_FOURTH = "AwardFourth";
	public final static String AWARD_FIFTH = "AwardFifth";
	public final static String AWARD_LUCKY = "AwardLucky";
	
	//奖品名称
	public final static String FIRST_AWARD_NAME = "澳門通加值券/現金3000元";
	public final static String SECOND_AWARD_NAME = "澳門通加值券/現金1500元";
	public final static String THREE_AWARD_NAME = "澳門通卡1000元";
	public final static String FOURTH_AWARD_NAME = "澳門通卡500元";
	public final static String FIFTH_AWARD_NAME = "澳門通卡300元";
	public final static String LUCKY_AWARD_NAME = "6000元 澳門通卡";
	
	//奖品个数
	public final static int SUPER_AWARD_NUM = 1;
	public final static int FIRST_AWARD_NUM = 2;
	public final static int SECOND_AWARD_NUM = 10;
	public final static int THREE_AWARD_NUM = 20;
	public final static int FOURTH_AWARD_NUM = 50;
	public final static int FIFTH_AWARD_NUM = 100;
	public final static int LUCKY_AWARD_NUM = 1;
	
	public static final Integer ASC = -1;
	//　如果分页的条数超过 50000了，将对查询进行优化，这个量是用于区分分页条数是否超标
	public static final int LARGE_RECORD = 50000;
	//public static final int LARGE_RECORD = 50000;
	/**
	 * 
	 */
	public static final String CHANNEL_NAME = "channerName";
	/**
	 * 
	 */
	public static final String SERVLET = "servlet-dispatcher";
}
