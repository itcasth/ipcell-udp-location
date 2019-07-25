package com.doit.net.ipcell.base;

import com.doit.net.ipcell.utils.BytesUtils;
import com.doit.net.ipcell.utils.Packet;
import com.doit.net.ipcell.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by wly on 2019/7/11.
 * Ipcell消息
 */
public class IpcellMessage extends BaseHeader implements Serializable{
	private final static Logger log = LoggerFactory.getLogger(IpcellMessage.class);
	private int length;//长度
	private byte version;//版本号
	private int deviceNo;//设备编号
	private short number;//序号
	private short mark;//保留字段
	private int code;//命令编号
	private byte flag;//应答标志
	List<IpcellBody> body;//消息体

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public int getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(int deviceNo) {
		this.deviceNo = deviceNo;
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public short getMark() {
		return mark;
	}

	public void setMark(short mark) {
		this.mark = mark;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public List<IpcellBody> getBody() {
		return body;
	}

	public void setBody(List<IpcellBody> body) {
		this.body = body;
	}



	public byte[] getBytes(IpcellMessage ipcellMessage){
		int len = getChildrenLength( ipcellMessage );
		byte[] b = new byte[15+len];

		byte[] temp;
		temp = Packet.toLH( 15+len );
			System.arraycopy( temp,0,b,0,4 );
		temp = Packet.toLH( ipcellMessage.getVersion() );
			System.arraycopy( temp,0,b,4,1 );

		temp = Packet.toLH( ipcellMessage.getDeviceNo() );
			System.arraycopy( temp,0,b,5,4 );

		temp = Packet.toLH( ipcellMessage.getNumber() );
			System.arraycopy( temp,0,b,9,2 );

		temp = Packet.toLH( ipcellMessage.getMark() );
			System.arraycopy( temp,0,b,11,2 );

		temp = Packet.toLH( ipcellMessage.getCode() );
			System.arraycopy( temp,0,b,13,1 );

		temp = Packet.toLH( ipcellMessage.getFlag() );
			System.arraycopy( temp,0,b,14,1 );

		b = getChildrenBytes(temp,b,ipcellMessage,15);

		return  b;
	}

	private byte[] getChildrenBytes(byte[] temp,byte[] b,IpcellMessage ipcellMessage,int start) {
		if(ipcellMessage.getBody()!=null && ipcellMessage.getBody().size()>0){
			for (IpcellBody body:ipcellMessage.getBody()){
				//运行状态
				temp = Packet.toLH( 4+body.getConLenth() );
				System.arraycopy( temp,0,b,start,2 );

				start = start +2 ;
				temp = Packet.toLH( body.getCode() );
				System.arraycopy( temp,0,b,start,2 );
				start = start +2 ;
				if(body.getCon()!=null){
					temp = Packet.toLH( body.getCon() );
					System.arraycopy( temp,0,b,start,body.getConLenth() );
				}else {
					byte[] bytes = new byte[body.getConLenth()];
					temp = body.getContent().getBytes();
					System.arraycopy(temp ,0,bytes,0,temp.length );
					System.arraycopy(bytes ,0,b,start,body.getConLenth() );
				}
				start = start +body.getConLenth() ;
			}
		}


		return b;
	}


	private int getChildrenLength(IpcellMessage ipcellMessage) {
		int len = 0;
		if(ipcellMessage.getBody()!=null && ipcellMessage.getBody().size()>0){
			for (IpcellBody body : ipcellMessage.getBody()){
				len += body.getConLenth()+4;
			}
		}
		return len;
	}






	/**
	 * 返回命令编号
	 * @param data
	 * @return
	 */
	public static IpcellMessage decode(byte[] data,IpcellMessage ipcellMessage){
		data = BytesUtils.ReversEndian( data, 15, true );
		ByteBuffer wrap = ByteBuffer.wrap( data,13,1 );
		ipcellMessage.setCode( wrap.get() );
		return ipcellMessage;
	}

	/**
	 * 返回IpcellBody消息体内容
	 * @param data
	 * @return
	 */
	public static int getBodyContent(byte[] data){
		ByteBuffer wrap = ByteBuffer.wrap( data,0,data.length );
		return wrap.get();
	}

	/**
	 * 返回指定位置的命令编号
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	public static int getCode(byte[] data,int offset,int length){
		ByteBuffer wrap = ByteBuffer.wrap( data,offset,length );
		return wrap.get();
	}

	/**
	 * 返回指定位置的字节数组
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	public static byte[] getByte(byte[] data,int offset,int length){
		byte[] bs = new byte[length];
		System.arraycopy(data, offset, bs, 0, length);
		return bs;
	}

	/**
	 * 返回信息长度
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	public static int getLenth(byte[] data,int offset,int length){
		ByteBuffer wrap = ByteBuffer.wrap( data,offset,length );
		return wrap.get();
	}

	public static void main(String[] args) {
		byte[] arr = new byte[]{1,2,3,4,5,6,4,4,4,};
		byte[] aByte = getByte( arr, 3, 3 );
	}

	/**
	 * 返回十六进制字符串
	 * @param data
	 * @param
	 * @param
	 * @return
	 */
	public static String getHexStr(byte[] data){
		String hex = "0x";
		if(data!=null && data.length>0){
			for (int i = data.length-1;i>=0;i--){
				hex += BytesUtils.byteToHex( data[i] );
			}
		}
		return hex;
	}

	/**
	 * 返回十进制数
	 * @param data
	 * @param
	 * @param
	 * @return
	 */
	public static int getInt(byte[] data){
		String hex = "";
		if(data!=null && data.length>0){
			for (int i = data.length-1;i>=0;i--){
				hex += BytesUtils.byteToHex( data[i] );
			}
		}
		if(StringUtils.isNotBlank( hex )){
			return Integer.parseInt( hex,16 );
		}
		return 0;
	}

	/**
	 * 返回消息体内容
	 * @param data
	 * @return
	 */
	public static String getContent(byte[] data){
		String str = "";
		if(data!=null && data.length>0){
			for (int i = 0;i<data.length;i++){
				str += convertHexToString( BytesUtils.byteToHex( data[i] ));
			}
		}
		return str;
	}


	/**
	 * 十六进制转ASCII
	 * @param hex
	 * @return
	 */
	public static String convertHexToString(String hex){

		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		for( int i=0; i<hex.length()-1; i+=2 ){

			String s = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(s, 16);
			sb.append((char)decimal);
			sb2.append(decimal);
		}

		return sb.toString();
	}

}
