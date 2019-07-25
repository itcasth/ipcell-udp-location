package com.doit.net.ipcell.handler;

import com.doit.net.ipcell.base.IpcellBody;
import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.service.IHandlerBodyFinish;

/**
 * Created by wly on 2019/7/18.
 * 获取初始化参数处理器
 */
public class GetTacHandler implements IHandlerBodyFinish<IpcellBody> {
	@Override
	public void workFinish(IpcellBody body) {
		String content = IpcellMessage.getContent( body.getData()).replace( "\u0000","" );
		System.out.println("TAC:"+content);

	}

}

