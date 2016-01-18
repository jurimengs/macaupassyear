package com.org.socket;

import com.org.exception.SSocketException;


/**
 * @author Nano
 * 
 * socket ����ӿ�
 * 
 * �����ͷ����������ɴ˽ӿ�����
 * 
 * @since 2011-09-08 10:09:09
 *
 */
public interface IServerSocket extends Runnable{
	
	 public void service();
	 
	 public void close() throws SSocketException;
	 
	 public void start();
	 
	 public String getServerInfo()throws SSocketException;
	 

}
