package com.doit.net.ipcell.server;

import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.service.IpcellServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wly on 2019/7/11.
 * ipcell工作线程
 */
public class IpcellWorkThread extends Thread{

	private static final Logger log = LoggerFactory.getLogger(IpcellWorkThread.class);

	private static BlockingQueue<IpcellMessage> workQueue = new LinkedBlockingQueue<IpcellMessage>();

	private static Map<String,IpcellMessage> handlerMap = new HashMap<String,IpcellMessage>();

	public IpcellWorkThread(){
		setName( "Ipcell-Work" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		log.info( "ipcell udp server work thread started" );
		while (IpcellServerManager.isStarted){
			try {
				IpcellMessage ipcellMessage = workQueue.take();
				IpcellServiceManager.handlerFinish( ipcellMessage );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void push(IpcellMessage ipcellMessage){
		try {
			log.info( "add ipcell work queue header:{}",ipcellMessage.getCode() );
			workQueue.put( ipcellMessage );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
