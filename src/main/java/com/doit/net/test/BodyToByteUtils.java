package com.doit.net.test;


import com.doit.net.ipcell.udp.base.IpcellBody;
import com.doit.net.ipcell.udp.base.IpcellMessage;

/**
 * Created by wly on 2019/7/19.
 */
public class BodyToByteUtils {

	private final static int baseMessageLength = 7;//基本消息的固定长度7
	private final static int bodyLength = 3;//消息体固定长度3

	/**
	 * 对象转字节数组
	 * @param baseMessage
	 * @return
	 */
	public byte[] getBytes(BaseMessage baseMessage){
		int len = getChildrenLength( baseMessage);
		byte[] b = new byte[baseMessageLength +len];

		byte[] temp;
		temp = PacketUtil.toLH( baseMessageLength +len );//获取长度对应的字节数组
		System.arraycopy( temp,0,b,0,4 );//拷贝长度字节数组到b 4个字节长度
		temp = PacketUtil.toLH( baseMessage.getName() );//获取设备名称对应的字节数组
		System.arraycopy( temp,0,b,4,2 );//拷贝设备名称字节数组到b 2个字节长度

		temp = PacketUtil.toLH( baseMessage.getCode() );//获取命令编号对应的字节数组
		System.arraycopy( temp,0,b,6,1 );//拷贝命令编号字节数组到b 1个字节长度

		b = getChildrenBytes(temp,b,baseMessage,baseMessageLength );

		return  b;
	}

	/**
	 * 获取对象子消息体列表节数组
	 * @param temp
	 * @param b
	 * @param baseMessage
	 * @param start
	 * @return
	 */
	private byte[] getChildrenBytes(byte[] temp, byte[] b, BaseMessage baseMessage, int start) {
		if(baseMessage.getBody()!=null && baseMessage.getBody().size()>0){
			for (Body body:baseMessage.getBody()){
				temp = PacketUtil.toLH( bodyLength+body.getConLenth() );//获取消息体长度对应的字节数组
				System.arraycopy( temp,0,b,start,2 );//拷贝长度字节数组到b 2个字节长度
				start = start +2 ;//长度+2
				temp = PacketUtil.toLH( body.getCode() );//获取命令编号对应的字节数组
				System.arraycopy( temp,0,b,start,1 );//拷贝命令编号字节数组到b 1个字节长度
				start = start +1 ;//长度+1
				if(body.getCon()!=null){
					//消息体内容是整形数据时
					temp = PacketUtil.toLH( body.getCon() );//获取消息体内容对应的字节数组
					System.arraycopy( temp,0,b,start,body.getConLenth() );//拷贝消息体内容字节数组到b getConLenth()个字节长度
				}else {
					//消息体内容是字符串时
					byte[] bytes = new byte[body.getConLenth()];
					temp = body.getContent().getBytes();//消息体内容转字节数组
					System.arraycopy(temp ,0,bytes,0,temp.length );//获取temp.length长度的消息体内容字节数组
					System.arraycopy(bytes ,0,b,start,body.getConLenth() );//拷贝消息体内容字节数组到b getConLenth()个字节长度
				}
				start = start +body.getConLenth() ;//累加start的长度
			}
		}
		return b;
	}

	/**
	 * 获取消息体长度
	 * @param baseMessage
	 * @return
	 */
	private int getChildrenLength(BaseMessage baseMessage) {
		int len = 0;
		if(baseMessage.getBody()!=null && baseMessage.getBody().size()>0){
			for (Body body : baseMessage.getBody()){
				len += body.getConLenth()+bodyLength ;
			}
		}
		return len;
	}
}
