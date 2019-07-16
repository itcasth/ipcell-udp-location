package com.doit.net.ipcell.udp.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by wly on 2019/7/11.
 * Ipcell消息
 */
public class IpcellMessage extends BaseHeader{
	private final static Logger log = LoggerFactory.getLogger(IpcellMessage.class);
	private String length;//长度
	private String version;//版本号
	private String deviceNo;//
	private String number;//序号
	private String mark;//保留字段
	private String code;//命令编号
	private String flag;//应答标志
	List<IpcellBody> body;//消息体


	public List<IpcellBody> getBody() {
		return body;
	}

	public void setBody(List<IpcellBody> body) {
		this.body = body;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}




	@Override
	public String toString() {
		return "IpcellMessage{" +
				"length=" + length +
				", version='" + version + '\'' +
				", deviceNo='" + deviceNo + '\'' +
				", number=" + number +
				", mark='" + mark + '\'' +
				", code='" + code + '\'' +
				", flag='" + flag + '\'' +
				'}';
	}
}
