package com.doit.net.ipcell.server;


import java.io.IOException;
import java.net.*;

/**
 * Created by wly on 2019/7/11.
 * ipcell服务管理器
 */
public class IpcellServerManager {

	private static DatagramSocket datagramSocket;


	private static int PORT = 9202;//控制台监听端口
	private static final int TIME_OUT = 3*60*1000;

	public static boolean isStarted = false;

	public static void startListener(int port){
		try {
			PORT = port;
			datagramSocket = new DatagramSocket( PORT );
			datagramSocket.setSoTimeout( TIME_OUT );
			datagramSocket.setReceiveBufferSize( 1024*20000 );
			isStarted = true;
			new IpcellReceiverThread().start();
			new IpcellWorkThread().start();
			new IpcellSenderThread().start();
			System.out.println( "【IPCELL-Server】 started on port:"+PORT );
		}catch (Exception e){
			System.out.println( "【IPCELL-Server】 启动异常,异常原因："+e.getMessage() );
		}
	}

	public static void startListener(){
		System.out.println("开始监听");
		startListener( PORT );
	}

	private static DatagramSocket clientSocket = null;

	public static DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}


	/**
	 * 接收数据包 该方法会造成线程阻塞
	 * @param packet
	 * @return
	 */
	public static DatagramPacket receive(DatagramPacket packet){
		try {
			datagramSocket.receive( packet );
			return packet;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void stop(){
		try {
			isStarted = false;
			if(datagramSocket.isClosed()==false){
				datagramSocket.close();
			}

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 将响应数据发送给请求端
	 * @param packet
	 */
	public static void send(DatagramPacket packet){
		try {
			datagramSocket.send( packet );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
