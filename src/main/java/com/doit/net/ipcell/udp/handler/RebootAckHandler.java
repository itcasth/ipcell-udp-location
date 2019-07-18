package com.doit.net.ipcell.udp.handler;

import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.service.IHandlerFinish;

/**
 * Created by wly on 2019/7/18.
 * 重启应答处理器
 */
public class RebootAckHandler implements IHandlerFinish<IpcellMessage> {
	@Override
	public void workFinish(IpcellMessage body) {
		System.out.println("重启应答");
	}
}
