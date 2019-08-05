package com.doit.net.ipcell.handler;

import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.service.IHandlerFinish;
import com.doit.net.ipcell.utils.BytesUtils;

/**
 * Created by wly on 2019/8/5.
 */
public class QueryRfAckHandler implements IHandlerFinish<IpcellMessage> {
	@Override
	public void workFinish(IpcellMessage body) {
		byte[] data = body.data;
		data = BytesUtils.ReversEndian( data, data.length, true );//大小端转换
		int totalLen = IpcellMessage.getLenth( data, 0, 2 );
		int lenMax = 15;

		int l = IpcellMessage.getCode( data, lenMax, 2 );
		byte[] aByte = IpcellMessage.getByte( data, lenMax, l );
		byte[] bByte = IpcellMessage.getByte( aByte, 2, 2 );
		byte[] cByte = IpcellMessage.getByte( aByte, 4, l-4 );
		int code = IpcellMessage.getInt( bByte );
		int content = IpcellMessage.getBodyContent(cByte );
		System.out.println("===射频状态:====="+content);
	}
}
