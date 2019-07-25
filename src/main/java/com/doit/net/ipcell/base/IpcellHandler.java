package com.doit.net.ipcell.base;

/**
 * Created by Administrator on 2019/7/11.
 */
public interface IpcellHandler {
	/**
	 * 处理ipcell消息
	 * @param ipcellMessage
	 */
	void handler(IpcellMessage ipcellMessage);
}
