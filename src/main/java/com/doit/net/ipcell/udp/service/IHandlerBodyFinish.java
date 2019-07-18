package com.doit.net.ipcell.udp.service;

import com.doit.net.ipcell.udp.base.IpcellBody;

/**
 * Created by wly on 2019/7/11.
 * 处理成功接口
 */
public interface IHandlerBodyFinish<T extends IpcellBody> {
	void workFinish(T body);
}
