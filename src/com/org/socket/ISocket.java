package com.org.socket;

import com.org.exception.SSocketConnTimeoutException;
import com.org.exception.SSocketException;

/**
 * @author Nano
 * 
 * �����ͷ������ͻ����ɴ˽ӿ�����
 * 
 * @since 2011-09-08 10:09:09
 * 
 */
public interface ISocket {
	
	/**
	 * @param hostName ����
	 * @param port �˿�
	 * @param timeoutSeconds ��Ϊ��λ
	 * @throws SSocketConnTimeoutException
	 * @throws EaiRuntimeException
	 */
	public void connect(String hostName,int port,int timeoutSeconds) throws SSocketConnTimeoutException,SSocketException;
	
	/**
	 * @return ��ȡ�ֽ�����
	 * 
	 * @throws SSocketException
	 */
	public byte[] read() throws SSocketException;
	
	/**
	 * 
	 * @param data �����͵�����
	 * 
	 * @param flush �Ƿ�һ�η���
	 * 
	 * @throws SSocketException
	 */
	public void write(byte[] data,boolean flush) throws SSocketException;
	
	public void close()throws SSocketException;
	
	public boolean isClose()throws SSocketException;
	

}
