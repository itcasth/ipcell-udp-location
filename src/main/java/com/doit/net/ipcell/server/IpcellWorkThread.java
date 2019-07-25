package com.doit.net.ipcell.server;

import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.service.IpcellServiceManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wly on 2019/7/11.
 * ipcell工作线程
 */
public class IpcellWorkThread extends Thread{


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
			workQueue.put( ipcellMessage );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
