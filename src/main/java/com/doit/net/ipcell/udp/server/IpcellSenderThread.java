package com.doit.net.ipcell.udp.server;

import com.doit.net.ipcell.udp.base.IpcellMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wly on 2019/7/11.
 * Ipcell发送线程
 */
public class IpcellSenderThread extends Thread {
	private final static Logger log = LoggerFactory.getLogger(IpcellSenderThread.class);

	private static BlockingQueue<IpcellMessage> senderQueue = new LinkedBlockingQueue<IpcellMessage>();
	public IpcellSenderThread(){
		setName( "Ipcell-Sender" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		log.info( "ipcell udp sender thread started " );
		while (true){
			try {
				IpcellMessage ipcellMessage = senderQueue.take();
				byte[] bytes = ipcellMessage.getBytes();
				log.info( "send code:{}",ipcellMessage.getCode() );
				DatagramPacket packet = new DatagramPacket( bytes, bytes.length, ipcellMessage.getSocketAddress() );
				DatagramSocket socket = getSocket();
				if(socket==null){
					log.warn( "Not found ipcell socket:{}",ipcellMessage.getInetSocketAddress().getPort() );
				}
				socket.send( packet );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static DatagramSocket getSocket(){
		return IpcellServerManager.getDatagramSocket();
	}

	public static void put(IpcellMessage ipcellMessage){
		try {
			log.info( "添加消息 code:{}到发送队列",ipcellMessage.getCode() );
			senderQueue.put( ipcellMessage );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
