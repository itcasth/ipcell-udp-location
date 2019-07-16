package com.doit.net.ipcell.udp.message;

import com.doit.net.ipcell.udp.base.IpcellBody;
import com.doit.net.ipcell.udp.base.IpcellMessage;
import com.doit.net.ipcell.udp.constants.IpcellConstants;
import com.doit.net.ipcell.udp.server.IpcellSenderThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wly on 2019/7/12.
 * Ipcell消息处理器
 */
public class IpcellMessageCreator {

	/**
	 * 查询
	 * @param ip
	 * @param port
	 */
	public void query(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_QUERY );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 扫描频点查询
	 * @param ip
	 * @param port
	 */
	public void queryScanFcn(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_QUERY_SCAN_FREQ );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 扫频小区信息查询
	 * @param ip
	 * @param port
	 */
	public void queryScanFcnInfo(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_QUERY_SCAN_CELL );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 射频状态查询
	 * @param ip
	 * @param port
	 */
	public void queryState(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_QUERY_STATE );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 查询IMSI
	 * @param ip
	 * @param port
	 */
	public void queryImsi(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_QUERY_IMSI );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 查询国家码
	 * @param ip
	 * @param port
	 */
	public void queryMcc(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_QUERY );
		ipcellMessage.setBody( getMccBody() );
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 查询主扰码
	 * @param ip
	 * @param port
	 */
	public void queryPsc(String ip,int port){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_QUERY);
		ipcellMessage.setBody( getPscBody() );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 查询UE上行扰码
	 * @param ip
	 * @param port
	 */
	public void queryUePsc(String ip,int port){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_QUERY);
		ipcellMessage.setBody( getUePscBody() );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 查询温度
	 * @param ip
	 * @param port
	 */
	public void queryTemp(String ip,int port){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_QUERY);
		ipcellMessage.setBody( getCpuTempBody() );
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 查询初始化参数
	 * @param ip
	 * @param port
	 */
	public void queryInitParam(String ip,int port){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_QUERY);
		ipcellMessage.setBody( getqueryInitParamBody() );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 在线用户查询
	 * @param ip
	 * @param port
	 */
	public void queryOnline(String ip,int port){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_QUERY_ONLINE);
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 扫描频点设置
	 * @param ip
	 * @param port
	 * @param fcn
	 */
	public void setScanFcn(String ip,int port,String fcn){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET_SCAN_FREQ );
		ipcellMessage.setBody( getScanFcnSetBody(fcn) );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 设置国家码
	 * @param ip
	 * @param port
	 * @param mcc
	 */
	public void setMcc(String ip,int port,String mcc){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET );
		ipcellMessage.setBody( getMccSetBody(mcc) );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 设置功率
	 * @param ip
	 * @param port
	 * @param power
	 */
	public void setPower(String ip,int port,String power){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET );
		ipcellMessage.setBody( getPowerSetBody(power) );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 设置参数
	 * @param ip
	 * @param port
	 * @param mcc
	 * @param mnc
	 * @param fcn
	 * @param lac
	 * @param power
	 * @param psc
	 */
	public void setParam(String ip,int port,String mcc,String mnc,String fcn,String lac,String power,String psc){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET );
		ipcellMessage.setBody( getParamsSetBody(mcc,mnc,fcn,lac,power,psc) );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 设置IMSI
	 * @param ip
	 * @param port
	 * @param imsi
	 * @param ue
	 */
	public void setParam(String ip,int port,String imsi,int ue){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET_IMSI );
		ipcellMessage.setBody( getImsiSetBody(imsi,ue) );
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 重定向到2G
	 * @param ip
	 * @param port
	 * @param cellCode
	 * @param channel
	 */
	public void setRedirect2G(String ip,int port,String cellCode,String channel){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET );
		ipcellMessage.setBody( getredirect2GSetBody(cellCode,channel) );
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 重定向到3G
	 * @param ip
	 * @param port
	 * @param fcn
	 */
	public void setRedirect3G(String ip,int port,String fcn){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_SET );
		ipcellMessage.setBody( getredirect3GSetBody(fcn) );
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 开始定位
	 * @param ip
	 * @param port
	 */
	public void startLocate(String ip,int port,String imsi){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_SET_IMSI);
		ipcellMessage.setBody( getImsiSetBody(imsi,3) );
		IpcellSenderThread.put( ipcellMessage );
	}


	/**
	 * 停止定位
	 * @param ip
	 * @param port
	 */
	public void stopLocate(String ip,int port,String imsi){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_SET_IMSI);
		ipcellMessage.setBody( getImsiSetBody(imsi,1) );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 删除IMSI
	 * @param ip
	 * @param port
	 */
	public void deleteImsi(String ip,int port,String imsi){
		IpcellMessage ipcellMessage = getIpcellMessage(ip,port,IpcellConstants.IPCELL_DELETE_IMSI);
		ipcellMessage.setBody( getImsiBody(imsi) );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 心跳
	 * @param ip
	 * @param port
	 */
	public void sendHeartBeat(String ip,int port){
		query( ip,port );
	}

	/**
	 * 开启射频
	 * @param ip
	 * @param port
	 */
	public void openRf(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_OPEN_RF );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 关闭射频
	 * @param ip
	 * @param port
	 */
	public void closeRf(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_CLOSE_RF );
		IpcellSenderThread.put( ipcellMessage );
	}

	/**
	 * 重启
	 * @param ip
	 * @param port
	 */
	public void reboot(String ip,int port){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( IpcellConstants.IPCELL_REBOOT );
		IpcellSenderThread.put( ipcellMessage );
	}

	private List<IpcellBody> getqueryInitParamBody() {
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		list.add( getIpcellBody(IpcellConstants.IPCELL_TAC) );
		list.add( getIpcellBody(IpcellConstants.IPCELL_SET_POWER) );
		list.add( getIpcellBody(IpcellConstants.IPCELL_PSC) );
		return list;
	}

	public List<IpcellBody> getCpuTempBody(){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		list.add( getIpcellBody(IpcellConstants.IPCELL_CPU_TEMP) );
		return list;
	}

	public List<IpcellBody> getUePscBody(){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		list.add( getIpcellBody(IpcellConstants.IPCELL_UE_PSC) );
		return list;
	}

	public List<IpcellBody> getPscBody(){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		list.add( getIpcellBody(IpcellConstants.IPCELL_PSC) );
		return list;
	}


	public List<IpcellBody> getMccBody(){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		list.add( getIpcellBody(IpcellConstants.IPCELL_MCC) );
		return list;
	}

	public List<IpcellBody> getPowerSetBody(String mcc){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody body = new IpcellBody();
		body.setCode( IpcellConstants.IPCELL_SET_POWER );
		body.setContent( mcc );
		list.add( body );
		return list;
	}

	public List<IpcellBody> getMccSetBody(String mcc){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody body = new IpcellBody();
		body.setCode( IpcellConstants.IPCELL_MCC );
		body.setContent( mcc );
		list.add( body );
		return list;
	}

	public List<IpcellBody> getScanFcnSetBody(String fcn){
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody body = new IpcellBody();
		body.setCode( IpcellConstants.IPCELL_WORK_FREQ );
		body.setContent( fcn );
		list.add( body );
		return list;
	}

	private List<IpcellBody> getParamsSetBody(String mcc, String mnc, String fcn, String lac, String power, String psc) {
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody fcnBody = new IpcellBody();
		fcnBody.setCode( IpcellConstants.IPCELL_WORK_FREQ );
		fcnBody.setContent( fcn );
		list.add( fcnBody );

		IpcellBody mccBody = new IpcellBody();
		mccBody.setCode( IpcellConstants.IPCELL_MCC );
		mccBody.setContent( mcc );
		list.add( mccBody );

		IpcellBody mncBody = new IpcellBody();
		mncBody.setCode( IpcellConstants.IPCELL_MNC );
		mncBody.setContent( mnc );
		list.add( mncBody );

		IpcellBody tacBody = new IpcellBody();
		tacBody.setCode( IpcellConstants.IPCELL_TAC );
		tacBody.setContent( lac );
		list.add( tacBody );

		IpcellBody powerBody = new IpcellBody();
		powerBody.setCode( IpcellConstants.IPCELL_SET_POWER );
		powerBody.setContent( power );
		list.add( powerBody );

		IpcellBody pscBody = new IpcellBody();
		pscBody.setCode( IpcellConstants.IPCELL_PSC );
		pscBody.setContent( psc );
		list.add( pscBody );

		return list;
	}

	private List<IpcellBody> getImsiSetBody(String imsi, int ue) {
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody imsiBody = new IpcellBody();
		imsiBody.setCode( IpcellConstants.IPCELL_IMSI );
		imsiBody.setContent( imsi );
		list.add( imsiBody );

		IpcellBody ueBody = new IpcellBody();
		ueBody.setCode( IpcellConstants.IPCELL_WORK_TYPE );
		ueBody.setContent( String.valueOf( ue ) );
		list.add( ueBody );
		return list;
	}

	private List<IpcellBody> getImsiBody(String imsi) {
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody imsiBody = new IpcellBody();
		imsiBody.setCode( IpcellConstants.IPCELL_IMSI );
		imsiBody.setContent( imsi );
		list.add( imsiBody );
		return list;
	}

	private List<IpcellBody> getredirect2GSetBody(String cellCode, String channel) {
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody cellBody = new IpcellBody();
		cellBody.setCode( IpcellConstants.IPCELL_GSM_CELL );
		cellBody.setContent( cellCode );
		list.add( cellBody );

		IpcellBody channelBody = new IpcellBody();
		channelBody.setCode( IpcellConstants.IPCELL_GSM_CHANNEL );
		channelBody.setContent( String.valueOf( channel ) );
		list.add( channelBody );
		return list;
	}
	private List<IpcellBody> getredirect3GSetBody(String fcn) {
		List<IpcellBody> list = new ArrayList<IpcellBody>(  );
		IpcellBody body = new IpcellBody();
		body.setCode( IpcellConstants.IPCELL_REDIRECT_WCDMA );
		body.setContent( fcn );
		list.add( body );
		return list;
	}

	public IpcellBody getIpcellBody(String code){
		IpcellBody body = new IpcellBody();
		body.setCode( code );
		body.setContent( "0" );
		return body;
	}

	public IpcellMessage getIpcellMessage(String ip,int port,String code){
		IpcellMessage ipcellMessage = new IpcellMessage();
		ipcellMessage.setSocketAddress( ip,port );
		ipcellMessage.setCode( code );
		return ipcellMessage;
	}


}
