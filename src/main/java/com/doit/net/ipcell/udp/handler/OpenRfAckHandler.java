package com.doit.net.ipcell.udp.handler;

import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.service.IHandlerFinish;

/**
 * Created by wly on 2019/7/18.
 * 开启射频应答处理器
 */
public class OpenRfAckHandler implements IHandlerFinish<IpcellMessage> {
	@Override
	public void workFinish(IpcellMessage body) {
		System.out.println("开启射频应答");
	}
}
