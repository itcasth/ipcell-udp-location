package com.doit.net.ipcell.udp.service;

import com.doit.net.ipcell.udp.base.IpcellMessage;

/**
 * Created by wly on 2019/7/11.
 * 处理成功接口
 */
public interface IHandlerFinish<T extends IpcellMessage> {
	void workFinish(T body);
}
