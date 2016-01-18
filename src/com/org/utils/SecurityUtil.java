/**
 * Copyright : http://www.sandpay.com.cn , 2007-2014
 * Project : lms
 * $Id$
 * $Revision$
 * Last Changed by SJ at 2014��3��4�� ����4:07:24
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * SJ         2014��3��4��        Initailized
 */
package com.org.utils;

import java.io.UnsupportedEncodingException;

/**
 * ��ȫ��Կ��
 */
public class SecurityUtil {
	public static String createPersionalKey(String sessionId) {
		byte[] encoded;
		try {
			encoded = DesUtil.encryptMode(sessionId.getBytes("UTF-8"));
			String dnHex = ByteUtil.bytes2HexStr(encoded);
			System.out.println("hex����ַ���:" + dnHex);
			return dnHex;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
