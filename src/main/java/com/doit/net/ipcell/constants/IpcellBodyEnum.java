package com.doit.net.ipcell.constants;

/**
 * Created by Administrator on 2019/7/18.
 */
public enum IpcellBodyEnum {
	IPCELL_WORK_FCN(0x0120,10);
	private int code;
	private int len;

	IpcellBodyEnum(int code, int len) {
		this.code = code;
		this.len = len;
	}
}
