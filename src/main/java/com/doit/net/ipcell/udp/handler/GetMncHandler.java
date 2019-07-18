package com.doit.net.ipcell.udp.handler;

import com.doit.net.ipcell.udp.base.IpcellBody;
import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.service.IHandlerBodyFinish;

/**
 * Created by wly on 2019/7/18.
 * 获取初始化参数处理器
 */
public class GetMncHandler implements IHandlerBodyFinish<IpcellBody> {
	@Override
	public void workFinish(IpcellBody body) {
		String content = IpcellMessage.getContent( body.getData()).replace( "\u0000","" );
		System.out.println("MNC:"+content);

	}

}

