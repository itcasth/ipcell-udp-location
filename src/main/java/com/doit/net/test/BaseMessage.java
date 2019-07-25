package com.doit.net.test;


import java.io.Serializable;
import java.util.List;

/**
 * Created by wly on 2019/7/19.
 */
public class BaseMessage extends BaseSocket implements Serializable {
	private int length;//长度
	private short name;//设备名称
	private byte code;//命令编号
	private byte[] data;//用于存放接收的字节数组
	List<Body> body;//消息体


	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public short getName() {
		return name;
	}

	public void setName(short name) {
		this.name = name;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public List<Body> getBody() {
		return body;
	}

	public void setBody(List<Body> body) {
		this.body = body;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	//省略setter getter方法
}
