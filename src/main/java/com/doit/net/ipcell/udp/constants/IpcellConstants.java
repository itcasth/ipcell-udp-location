package com.doit.net.ipcell.udp.constants;

/**
 * Created by wly on 2019/7/11.
 */
public class IpcellConstants {

	public static final String IPCELL_QUERY = "0x01";//查询
	public static final String IPCELL_QUERY_ACK = "0x11";//查询应答
	public static final String IPCELL_SET = "0x02";//设置
	public static final String IPCELL_SET_ACK = "0x12";//设置应答

	public static final String IPCELL_REPORT = "0x13";//上报
	public static final String IPCELL_OPEN_RF = "0x05";//开启射频
	public static final String IPCELL_OPEN_RF_ACK = "0x15";//开启射频应答
	public static final String IPCELL_CLOSE_RF = "0x06";//关闭射频
	public static final String IPCELL_CLOSE_RF_ACK = "0x16";//关闭射频应答

	public static final String IPCELL_REBOOT = "0x07";//重启系统
	public static final String IPCELL_REBOOT_ACK = "0x17";//重启系统应答
	public static final String IPCELL_SET_SCAN_FREQ = "0xA1";//扫描频点设置
	public static final String IPCELL_SET_SCAN_FREQ_ACK = "0xA2";//扫描频点设置应答

	public static final String IPCELL_QUERY_SCAN_FREQ = "0xA3";//扫描频点查询
	public static final String IPCELL_QUERY_SCAN_FREQ_ACK = "0xA4";//扫描频点查询应答
	public static final String IPCELL_QUERY_SCAN_CELL = "0xA5";//扫频小区信息查询
	public static final String IPCELL_QUERY_SCAN_CELL_ACK = "0xA6";//扫频小区信息查询应答
	public static final String IPCELL_QUERY_SCAN_CELL_COMPLETED = "0xA7";//扫频小区信息查询完成

	public static final String IPCELL_SET_IMSI = "0xB1";//IMSI设置请求
	public static final String IPCELL_SET_IMSI_ACK = "0xB2";//IMSI设置请求应答
	public static final String IPCELL_DELETE_IMSI = "0xB3";//扫频小区信息查询
	public static final String IPCELL_DELETE_IMSI_ACK = "0xB4";//扫频小区信息查询应答

	public static final String IPCELL_QUERY_IMSI = "0xB5";//IMSI查询请求
	public static final String IPCELL_QUERY_IMSI_ACK = "0xB6";//IMSI查询请求应答
	public static final String IPCELL_QUERY_ONLINE = "0xCD";//在线用户查询
	public static final String IPCELL_QUERY_ONLINE_ACK = "0xCE";//在线用户查询应答

	public static final String IPCELL_QUERY_RF = "0xD0";//射频状态查询
	public static final String IPCELL_QUERY_RF_ACK = "0xD1";//射频状态查询应答
	public static final String IPCELL_QUERY_STATE= "0xD2";//状态查询
	public static final String IPCELL_QUERY_STATE_ACK = "0xD3";//状态查询应答

	public static final String IPCELL_UPDATE_VERSION = "0xF0";//软件升级请求
	public static final String IPCELL_UPDATE_VERSION_ACK = "0xF1";//软件升级请求应答



	public static final String IPCELL_IMSI = "0x0050";//imsi
	public static final String IPCELL_WORK_FREQ = "0x0120";//工作频点
	public static final String IPCELL_MCC = "0x0121";//国家码
	public static final String IPCELL_MNC = "0x0122";//网络码
	public static final String IPCELL_TAC = "0x0123";//位置区
	public static final String IPCELL_SET_POWER = "0x0124";//下行衰减
	public static final String IPCELL_PSC = "0x0125";//主扰码
	public static final String IPCELL_GSM_CELL = "0x0128";//GSM基站识别码
	public static final String IPCELL_GSM_CHANNEL = "0x0129";//GSM工作信道号
	public static final String IPCELL_WORK_TYPE = "0x012B";//工作模式 0: 重定向到2G  1: 释放UE    2: 重定向到同系统其它小区   3: 保持UE
	public static final String IPCELL_UE_PSC = "0x0134";//UE上行扰码
	public static final String IPCELL_REDIRECT_WCDMA = "0x0137";//重定向频点(WCDMA
	public static final String IPCELL_CPU_TEMP = "0x0141";//CPU温度
}
