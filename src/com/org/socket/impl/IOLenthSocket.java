package com.org.socket.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.org.exception.SSocketException;
import com.org.utils.StringUtil;

public class IOLenthSocket extends IOSocket {
	private static Log log = LogFactory.getLog(IOLenthSocket.class);

	public void write(byte[] data, boolean flush) {
		byte[] datalength = StringUtil.charFill(data.length+"", '0', "left", 4).getBytes();
		data = StringUtil.union(datalength, data);
		super.write(data, flush);
	}
	public byte[] read() {
		byte[] resp = null;
		if(socket != null && !isClose()){			
			try {
				ins = socket.getInputStream();	
				int len = 4;
				byte[] respLength = new byte[len];
				int n = ins.read(respLength, 0, len);
				if(n == 4){
					int respLen = Integer.parseInt(new String(respLength));
					//resp = new byte[respLen];
					//n = ins.read(resp, 0, respLen);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] b = new byte[512];
					int l = 0;
					int c = 0;
					while((l=ins.read(b,0,512)) > 0){
					    int m = Math.min(l, respLen-c);
                        out.write(b, 0, m);
                        c += m;
                        if(c >= respLen){
                            break;
                        }
					}
					resp = out.toByteArray();
//					if(n == respLen){
//						
//					}else
//						throw new SSocketException("��ȡ�޷���ȷ����,��������...");
				}else{
					throw new SSocketException("��ȡ����ǰ��4λ���ȹ����쳣,��������... ʵ�ʶ�ȡ���� : " + n);
				}
			} catch (IOException e) {
				log.info("IOLenthSocket ��ȡ���ݳ����쳣");
				if(ins != null){
					try {
							ins.close();
						} catch (IOException ie) {
							ie.printStackTrace();
						}
				}
				e.printStackTrace();
			}
		}
		return resp;
	}
	
}
