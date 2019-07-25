package com.doit.net.ipcell.service;

import com.doit.net.ipcell.base.IpcellBody;
import com.doit.net.ipcell.base.IpcellMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wly on 2019/7/11.
 * 数据回调管理
 */
public class IpcellServiceManager {

	private static Map<String,List<IHandlerFinish>> callList = new HashMap<String,List<IHandlerFinish>>();
	private static Map<String,List<IHandlerBodyFinish>> bodyList = new HashMap<String,List<IHandlerBodyFinish>>();


	public static synchronized void addCallBack(String code,IHandlerFinish iHandlerFinish){
		if(callList.containsKey( code )){
			callList.get( code ).add( iHandlerFinish );
		}else {
			ArrayList<IHandlerFinish> list = new ArrayList<IHandlerFinish>();
			list.add( iHandlerFinish );
			callList.put( code,list );
		}
	}

	public static synchronized void addBodyBack(String code,IHandlerBodyFinish iHandlerFinish){
		if(bodyList.containsKey( code )){
			bodyList.get( code ).add( iHandlerFinish );
		}else {
			ArrayList<IHandlerBodyFinish> list = new ArrayList<IHandlerBodyFinish>();
			list.add( iHandlerFinish );
			bodyList.put( code,list );
		}
	}

	public static synchronized  void removeCallBack(String header){
		callList.remove( header );
	}

	public static synchronized  void removeBodyBack(String header){
		bodyList.remove( header );
	}

	public static void handlerFinish(IpcellMessage message){
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
