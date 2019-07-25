package com.doit.net.ipcell.server;

import com.doit.net.ipcell.constants.IpcellConstants;
import com.doit.net.ipcell.handler.*;
import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.message.IpcellMessageCreator;
import com.doit.net.ipcell.service.IpcellServiceManager;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wly on 2019/7/11.
 * Ipcell发送线程
 */
public class IpcellSenderThread extends Thread {

	private static BlockingQueue<IpcellMessage> senderQueue = new LinkedBlockingQueue<IpcellMessage>();
	public IpcellSenderThread(){
		setName( "Ipcell-Sender" );
	}

	@Override
	public void run(){
		init();
	}

	private void init() {
		while (true){
			try {
				IpcellMessage ipcellMessage = senderQueue.take();
				SocketAddress socketAddress = ipcellMessage.getSocketAddress();
				byte[] bytes =ipcellMessage.getBytes(ipcellMessage);
				DatagramPacket packet = new DatagramPacket( bytes, bytes.length, socketAddress );
				DatagramSocket socket = getSocket();
				if(socket==null){
					System.out.println("Not found ipcell socket");
				}
				socket.send( packet );
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public static DatagramSocket getSocket(){
		return getDatagramSocket();
	}

	private static int PORT = 9201;
	private static DatagramSocket datagramSocket;
	private static final int TIME_OUT = 3*60*1000;

	private static DatagramSocket getDatagramSocket(){
		try{
			if(datagramSocket==null){
				datagramSocket = new DatagramSocket( PORT );
				datagramSocket.setSoTimeout( TIME_OUT );
				datagramSocket.setReceiveBufferSize( 1024*20000 );
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return datagramSocket;
	}


	public static void put(IpcellMessage ipcellMessage){
		try {
			senderQueue.put( ipcellMessage );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		IpcellServerManager.startListener();

		IpcellServiceManager.addCallBack( String.valueOf( IpcellConstants.IPCELL_QUERY_ACK ),new BaseHandler() );
		IpcellServiceManager.addCallBack( String.valueOf( IpcellConstants.IPCELL_CLOSE_RF_ACK ),new CloseRfAckHandler() );
		IpcellServiceManager.addCallBack( String.valueOf( IpcellConstants.IPCELL_OPEN_RF_ACK ),new OpenRfAckHandler() );
		IpcellServiceManager.addCallBack( String.valueOf( IpcellConstants.IPCELL_REBOOT_ACK ),new RebootAckHandler() );
		IpcellServiceManager.addCallBack( String.valueOf( IpcellConstants.IPCELL_REPORT ),new UeReportHandler() );

		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_WORK_FREQ) ,new GetFcnHandler() );
		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_MCC) ,new GetMccHandler() );
		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_MNC) ,new GetMncHandler() );
		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_TAC) ,new GetTacHandler() );
		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_SET_POWER) ,new GetPowerHandler() );
		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_PSC) ,new GetPscHandler() );



		IpcellServiceManager.addBodyBack( String.valueOf(IpcellConstants.IPCELL_RUN_STATE) ,new GetRunstateHandler() );
		IpcellMessageCreator.startLocate( IpcellConstants.IP,IpcellConstants.PORT,"460052125622110" );
		//IpcellMessageCreator.setRedirect3G( IpcellConstants.IP,IpcellConstants.PORT,"10663");
		//IpcellMessageCreator.queryInitParam( IpcellConstants.IP,IpcellConstants.PORT );
		//IpcellMessageCreator.sendHeartBeat( IpcellConstants.IP,IpcellConstants.PORT );
		//IpcellMessageCreator.setParam( IpcellConstants.IP,IpcellConstants.PORT,"460","01","10688","158","0","321" );
		//IpcellMessageCreator.queryPsc( IpcellConstants.IP,IpcellConstants.PORT  );
		//运行状态 0x0084 0x0080 0x0081
		//IpcellMessageCreator.setParam( IpcellConstants.IP,IpcellConstants.PORT,"460","01","10688","158","0","321" );


	}
}
