package com.doit.net.test;

/**
 * Created by wly on 2019/7/19.
 */
public class Body {
	private short length;//长度
	private byte code;//命令编号
	private String content;//内容 接收字符串内容
	private Integer con;//内容 接收整形数据
	private int conLenth;//消息体字节长度
	private byte[] data;//用于接收该消息体的字节数组
	//省略setter getter方法

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
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
