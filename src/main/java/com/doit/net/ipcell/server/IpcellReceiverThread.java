package com.doit.net.ipcell.server;

import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.utils.BytesUtils;

import java.net.DatagramPacket;

/**
 * Created by wly on 2019/7/11.
 * IPCELL接收线程
 */
public class IpcellReceiverThread extends Thread {

	private static final int BUFFER_SIZE = 1024;

	public IpcellReceiverThread(){
		setName( "IPCELL-Receiver" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		while (IpcellServerManager.isStarted){
			try {
				byte[] bytes = new byte[BUFFER_SIZE];
				DatagramPacket datagramPacket = new DatagramPacket( bytes,bytes.length );
				if(datagramPacket==null){
					continue;
				}
				IpcellServerManager.receive( datagramPacket );
				if(datagramPacket==null){
					continue;
				}
				//添加到工作线程队列
				IpcellMessage ipcellMessage = new IpcellMessage(  );
				byte[] data = datagramPacket.getData();
				ipcellMessage.data = data;
				data = BytesUtils.ReversEndian( data, 15, true );
				ipcellMessage = ipcellMessage.decode( data,ipcellMessage );
				ipcellMessage.setSocketAddress( datagramPacket.getSocketAddress() );
				IpcellWorkThread.push( ipcellMessage );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
