package com.doit.net.ipcell.service;

import com.doit.net.ipcell.base.IpcellMessage;

/**
 * Created by wly on 2019/7/11.
 * 处理成功接口
 */
public interface IHandlerFinish<T extends IpcellMessage> {
	void workFinish(T body);
}
