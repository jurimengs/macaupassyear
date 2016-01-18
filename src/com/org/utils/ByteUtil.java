package com.org.utils;


public class ByteUtil {

	//private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	//�ϲ��ֽڵ�����
	public static byte[] union(byte[] bs1,byte[] bs2){
		byte[] bs = new byte[bs1.length+bs2.length];
		for (int i = 0; i < bs1.length; i++)
			bs[i] = bs1[i];
		for (int i = 0; i < bs2.length; i++)
			bs[bs1.length+i] = bs2[i];
		return bs;
	}
	
	//��ȡָ�������ֽ�����
	public static byte[] sub(byte[] bs,int first,int length){
		length = length >(bs.length - first)?(bs.length-first):length;
		byte[] sub = new byte[length];
		for (int i = 0; i < length; i++) {
			sub[i] = bs[first+i];
		}
		return sub;
	} 
	
	/**
	 * ����������תbase64�ַ���
	 * @param bsb
	 * @return 
	 */
	public static String bytes2Base64str(byte[] bsb){
		if(bsb != null){
			return Base64Encoder.encode(bsb);
		}
		return null;
	}
	/**
	 * base64�ַ���ת����������
	 * @param base64str
	 * @return
	 */
	public static byte[] bas64str2Bytes(String base64str){
		if(base64str != null){
			return Base64Decoder.decode(base64str);
		}
		return null;
	}
	
	/**
	 * ����������תʮ�������ַ���
	 * @param b
	 * @return
	 */
	public static String bytes2HexStr(byte[] bs) {
		StringBuffer sb=new StringBuffer();
    	for (int i = 0; i < bs.length; i++) {
    		int nn = bs[i]< 0 ? bs[i] + 256 : bs[i];
    		String t = Integer.toHexString(nn).toUpperCase();
    		sb.append(t.length()<2?"0"+t:t);
		}
    	return sb.toString();
	}
	
	/**
	 * ʮ������ת����������
	 * @param s
	 * @return
	 */
	public static final byte[] hex2Bytes(String s) {
		if(s == null){
			return null;
		}
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),16);
		}
		return bytes;
	}
	
	
	/**
     * Asciiת��Ϊ16���Ʒ�ʽ
     * ���磺�ַ�ABת��Ϊ0xAB��Ӧ���ַ���
     * @param bs ��ת����byte����
     * @return ѹ��Ϊ16���ƺ��byte����
     */
    public static byte[] ascii2hex(byte[] bs) {
        byte[] res = new byte[bs.length / 2];
        for (int i = 0, n = bs.length; i < n; i += 2) {
            res[i / 2] = (byte) (Integer.parseInt(new String(bs, i, 2), 16));
        }
        return res;
    }
    
    /**
     * 16����ת��ΪAscii
     * ���磺 �ַ�0xABת��ΪAscii�������ַ�AB
     * @param bs ��ת����16���Ƶ�����
     * @return ��ѹ���Ascii����
     */
    public static byte[] hex2ascii(byte[] bs) {
        byte[] res = new byte[bs.length * 2];
        for (int i = 0; i < bs.length; i++) {
            int ti = bs[i];
            ti = ti < 0 ? ti + 256 : ti;
            String t = Integer.toHexString(ti);
            if (t.length() < 2) t = "0" + t;
            res[i * 2] = (byte) t.charAt(0);
            res[i * 2 + 1] = (byte) t.charAt(1);
        }
        return res;
    }
    
    public static void main(String []args){
    	//System.out.println(Integer.toHexString(29999));
    	//System.out.println(Integer.toString(15, 2));
    }
    
}
