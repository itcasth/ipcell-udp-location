package com.doit.net.ipcell.udp.base;

/**
 * Created by wly on 2019/7/15.
 */
public class IpcellBody {
	private Integer length;//长度
	private String code;//命令编号
	private String content;//内容

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
