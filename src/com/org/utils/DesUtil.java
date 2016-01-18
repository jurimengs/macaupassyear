package com.org.utils;

import java.io.UnsupportedEncodingException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DesUtil {
	private static final String Algorithm = "DESede"; // ���� �����㷨,����
														// DES,DESede,Blowfish
	public static final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,(byte) 0xE2 };

	// keybyteΪ������Կ������Ϊ24�ֽ�
	// srcΪ�����ܵ����ݻ�������Դ����Ҫ��src����Ϊutf-8��ʽ
	public static byte[] encryptMode(byte[] src) {
		try {
			// ������Կ
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
			// ����
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyteΪ������Կ������Ϊ24�ֽ�
	// srcΪ���ܺ�Ļ�����
	public static byte[] decryptMode(byte[] src) {
		try {
			// ������Կ
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);

			// ����
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// ת����ʮ�������ַ���
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// ����°�ȫ�㷨,�����JCE��Ҫ������ӽ�ȥ
		Security.addProvider(new com.sun.crypto.provider.SunJCE());

		String szSrc = "1233333333333333SF��";

		System.out.println("����ǰ���ַ���:" + szSrc);

		byte[] encoded = encryptMode(szSrc.getBytes("UTF-8"));
		// �����ݿ�ȡ��ǰ�� ��ִ�м��ܣ� չʾ��ҳ��ʱ������
		String dnHex = ByteUtil.bytes2HexStr(encoded);
		System.out.println("hex����ַ���:" + dnHex);
		System.out.println("hex����ַ����ٻ���:" + ByteUtil.hex2Bytes(dnHex));

		// �õ�ҳ�洫�ݵĲ�����
		byte[] srcBytes = decryptMode(ByteUtil.hex2Bytes(dnHex));
		System.out.println("���ܺ���ַ���:" + (new String(srcBytes, "UTF-8")));
	}

}
