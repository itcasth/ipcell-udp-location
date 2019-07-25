package com.doit.net.ipcell.handler;

import com.doit.net.ipcell.base.IpcellBody;
import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.service.IHandlerFinish;
import com.doit.net.ipcell.service.IpcellServiceManager;
import com.doit.net.ipcell.utils.BytesUtils;

/**
 * Created by wly on 2019/7/18.
 */
public class UeReportHandler implements IHandlerFinish<IpcellMessage> {
	@Override
	public void workFinish(IpcellMessage body) {
		System.out.println("UE上报处理器Handler");
		byte[] data = body.data;
		data = BytesUtils.ReversEndian( data, data.length, true );//大小端转换
		int totalLen = IpcellMessage.getLenth( data, 0, 2 );
		int lenMax = 15;
		String imsi = "";
		String imei = "";
		int operator = 0;//4表示联通WCDMA
		int rxLev = 0;//手机信号强度 定位上报
		for(int i = 0;i<4;i++){

			int l = IpcellMessage.getCode( data, lenMax, 2 );
			byte[] aByte = IpcellMessage.getByte( data, lenMax, l );
			byte[] bByte = IpcellMessage.getByte( aByte, 2, 2 );
			byte[] cByte = IpcellMessage.getByte( aByte, 4, l-4 );
			int code = IpcellMessage.getInt( bByte );
			System.out.println("消息体编号："+code);
			IpcellBody ipcellBody = new IpcellBody();
			ipcellBody.setCode( code );
			ipcellBody.setLength( l );
			ipcellBody.setData( cByte );
			IpcellServiceManager.handlerFinish( ipcellBody );
			if(i==0){
				imsi =IpcellMessage.getContent( cByte ).replace( "\u0000","" );
			}else if(i==1){
				imei =IpcellMessage.getContent( cByte ).replace( "\u0000","" );
			}else if(i==2){
				operator =IpcellMessage.getBodyContent( cByte );
			}else if(i==3){
				rxLev =IpcellMessage.getBodyContent( cByte );
			}
			lenMax += l;
		}

	}
}
