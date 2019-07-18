package com.doit.net.ipcell.udp.handler;

import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.service.IHandlerFinish;

/**
 * Created by wly on 2019/7/18.
 * 关闭射频应答处理器
 */
public class CloseRfAckHandler implements IHandlerFinish<IpcellMessage> {
	@Override
	public void workFinish(IpcellMessage body) {
		//关闭射频应答
		System.out.println("关闭射频应答");
	}
}
