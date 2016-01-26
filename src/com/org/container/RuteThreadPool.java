package com.org.container;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���н���������Ƚ�������
 * @author Administrator
 *
 */
public class RuteThreadPool {
	/**
	 * ���̳߳��н�������н�������󣬷�ɢ������ѹ��
	 */
	private static ExecutorService rute =  Executors.newCachedThreadPool();
	private Log log = LogFactory.getLog(RuteThreadPool.class);
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run() {
				shutdown();
			}
		});
	}

	public static <T> Future<T> submit(Callable<T> callable) throws InterruptedException, ExecutionException  {
		return rute.submit(callable);
	}
	
	public static void shutdown(){
		rute.shutdown();
	}
}
