package com.doit.net.ipcell.udp.handler;

import com.doit.net.ipcell.udp.base.IpcellBody;
import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.service.IHandlerFinish;
import com.doit.net.ipcell.udp.service.IpcellServiceManager;
import com.doit.net.ipcell.udp.utils.BytesUtils;

/**
 * Created by wly on 2019/7/18.
 */
public class BaseHandler implements IHandlerFinish<IpcellMessage> {
	@Override
	public void workFinish(IpcellMessage body) {
		System.out.println("找到处理器Handler");
		byte[] data = body.data;
		data = BytesUtils.ReversEndian( data, data.length, true );//大小端转换
		int totalLen = IpcellMessage.getLenth( data, 0, 2 );
		int lenMax = 15;
		while (lenMax< totalLen){
			int l = IpcellMessage.getCode( data, lenMax, 2 );
			byte[] aByte = IpcellMessage.getByte( data, lenMax, l );
			byte[] bByte = IpcellMessage.getByte( aByte, 2, 2 );
			byte[] cByte = IpcellMessage.getByte( aByte, 4, l-4 );
			int code = IpcellMessage.getInt( bByte );
			//String content = IpcellMessage.getContent( cByte).replace( "\u0000","" );
			IpcellBody ipcellBody = new IpcellBody();
			ipcellBody.setCode( code );
			ipcellBody.setLength( l );
			ipcellBody.setData( cByte );
			//ipcellBody.setContent( content );
			IpcellServiceManager.handlerFinish( ipcellBody );
			lenMax += l;
		}

	}
}
