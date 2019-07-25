package com.doit.net.ipcell.handler;

import com.doit.net.ipcell.base.IpcellBody;
import com.doit.net.ipcell.base.IpcellMessage;
import com.doit.net.ipcell.service.IHandlerBodyFinish;

/**
 * Created by wly on 2019/7/18.
 * 获取初始化参数处理器
 */
public class GetRunstateHandler implements IHandlerBodyFinish<IpcellBody> {
	@Override
	public void workFinish(IpcellBody body) {
		//System.out.println("获取初始化参数处理器Handler");
		int content = IpcellMessage.getBodyContent( body.getData() );
		System.out.println("运行状态:"+content);

	}

}

