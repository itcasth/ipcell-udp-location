package com.doit.net.ipcell.udp.service;

import com.doit.net.ipcell.udp.base.IpcellBody;
import com.doit.net.ipcell.udp.base.IpcellMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wly on 2019/7/11.
 * 数据回调管理
 */
public class IpcellServiceManager {
	private final static Logger log = LoggerFactory.getLogger(IpcellServiceManager.class);

	private static Map<String,List<IHandlerFinish>> callList = new HashMap<String,List<IHandlerFinish>>();
	private static Map<String,List<IHandlerBodyFinish>> bodyList = new HashMap<String,List<IHandlerBodyFinish>>();


	public static synchronized void addCallBack(String code,IHandlerFinish iHandlerFinish){
		log.info( "register listener :{},class:{}",code,iHandlerFinish.getClass().getName() );
		if(callList.containsKey( code )){
			callList.get( code ).add( iHandlerFinish );
		}else {
			ArrayList<IHandlerFinish> list = new ArrayList<IHandlerFinish>();
			list.add( iHandlerFinish );
			callList.put( code,list );
		}
	}

	public static synchronized void addBodyBack(String code,IHandlerBodyFinish iHandlerFinish){
		log.info( "register listener :{},class:{}",code,iHandlerFinish.getClass().getName() );
		if(bodyList.containsKey( code )){
			bodyList.get( code ).add( iHandlerFinish );
		}else {
			ArrayList<IHandlerBodyFinish> list = new ArrayList<IHandlerBodyFinish>();
			list.add( iHandlerFinish );
			bodyList.put( code,list );
		}
	}

	public static synchronized  void removeCallBack(String header){
		log.info( "remove listener header:{}",header );
		callList.remove( header );
	}

	public static synchronized  void removeBodyBack(String header){
		log.info( "remove listener header:{}",header );
		bodyList.remove( header );
	}

	public static void handlerFinish(IpcellMessage message){
		log.info( "call handler finish code:{}",message.getCode() );
		String key = String.valueOf( message.getCode() );
		if(callList.containsKey(key )){
			for (IHandlerFinish i: callList.get( key )){
				if(i==null){
					continue;
				}
				i.workFinish( message );
			}
		}
	}


	public static void handlerFinish(IpcellBody body){
		log.info( "call handler finish code:{}",body.getCode() );
		String key = String.valueOf( body.getCode() );
		if(bodyList.containsKey(key )){
			for (IHandlerBodyFinish i: bodyList.get( key )){
				if(i==null){
					continue;
				}
				i.workFinish( body );
			}
		}
	}
}
