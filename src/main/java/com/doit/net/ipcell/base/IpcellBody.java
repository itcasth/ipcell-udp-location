package com.doit.net.ipcell.base;

/**
 * Created by wly on 2019/7/15.
 */
public class IpcellBody extends BaseHeader{
	private int length;//长度
	private int code;//命令编号
	private String content;//内容
	private Integer con;//整形数据
	private int conLenth;//字节长度
	private byte[] data;//字节数组

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCon() {
		return con;
	}

	public void setCon(Integer con) {
		this.con = con;
	}

	public int getConLenth() {

		return conLenth;
	}

	public void setConLenth(int conLenth) {
		this.conLenth = conLenth;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
