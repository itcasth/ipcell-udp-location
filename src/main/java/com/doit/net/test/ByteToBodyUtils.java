package com.doit.net.test;

import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.utils.BytesUtils;
import com.doit.net.ipcell.udp.utils.StringUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wly on 2019/7/19.
 */
public class ByteToBodyUtils {
	private  static int baseMessageLength = 7;//基本消息的固定长度7
	private final static int bodyLength = 3;//消息体固定长度3
	/**
	 * 	网络字节逆序
	 * @param b
	 * @param count
	 * @param big
	 * @return
	 */
	public static byte[] ReversEndian(byte b[],int count, boolean big)
	{
		byte by;
		byte data[] = new byte[count];
		for(int i=0;i<count;i++)
		{
			data[i] = b[i];
		}
		if(big==false)
		{
			for(int i=0;i<count;i++)
			{
				by = b[i];
				data[count-i-1] = by;
			}
		}
		return data;
	}

	/**
	 * 字节数组转对象
	 * @param data
	 * @return
	 */
	public BaseMessage getBaseMessage(byte[] data){
		BaseMessage baseMessage = new BaseMessage();
		baseMessage.setData( data );
		byte[] bytes = ReversEndian( data, 7, true );//获取 截取7个长度 并进行大小端转换后的字节数组
		ByteBuffer wrap = ByteBuffer.wrap( data,6,1 );//将下标从6开始 1个长度的字节数组转成ByteBuffer 即命令编号对应的字节数组
		baseMessage.setCode( wrap.get() );//获取基本消息的命令编号
		baseMessage.setLength( getIntegerContent( bytes,0,4 ) );
		baseMessage.setName( (short) getIntegerContent(bytes,4,2) );
		List<Body> bodyList = getBodyList( baseMessage );
		baseMessage.setBody( bodyList );
		return baseMessage;

	}

	/**
	 * 获取子对象列表
	 * @param baseMessage
	 * @return
	 */
	public List<Body> getBodyList(BaseMessage baseMessage){
		List<Body> list = new ArrayList<Body>(  );
 		byte[] data = baseMessage.getData();
		data = BytesUtils.ReversEndian( data, data.length, true );//大小端转换
		int totalLen = IpcellMessage.getLenth( data, 0, 4 );
		while (baseMessageLength < totalLen){
			int l = getIntegerContent( data, baseMessageLength, 2 );//返回指定位置的消息体对应的长度
			byte[] aByte = getByte( data, baseMessageLength, l );//获取指定位置的消息体字节数组
			byte[] bByte = getByte( aByte, 2, 1 );//返回消息体对应的消息编号字节数组
			byte[] cByte = getByte( aByte, bodyLength, l-bodyLength );//返回消息体内容对应的字节数组
			int code = getInt( bByte );//获取消息体对应的编号
			Body body = new Body();
			if(CodeUtils.contentIsStr( code )){
				//如果消息编号对应的内容是字符串
				String content = getContent( cByte).replace( "\u0000","" );//如果是字符串
				body.setContent( content );
			}else {
				//如果消息编号对应的内容是整形数
				int content = getBodyContent(cByte);
				body.setCon( content );
			}

			body.setCode( (byte) code );
			body.setLength( (short) l );
			body.setData( cByte );

			list.add( body );
			baseMessageLength += l;
		}
		return getBodyList( baseMessage );
	}

	/**
	 * 返回消息体内容（字符串）
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
	 * 返回消息体内容（整形）
	 * @param data
	 * @return
	 */
	public static int getBodyContent(byte[] data){
		ByteBuffer wrap = ByteBuffer.wrap( data,0,data.length );
		return wrap.get();
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
	 * 返回基本信息长度
	 * @param data
	 * @param offset
	 * @param length
	 * @return
	 */
	public static int getIntegerContent(byte[] data,int offset,int length){
		ByteBuffer wrap = ByteBuffer.wrap( data,offset,length );
		return wrap.get();
	}
}
