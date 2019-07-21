package com.doit.net.test;

/**
 * Created by wly on 2019/7/19.
 */
public class PacketUtil {

	/**
	 * 将int转为低字节在前，高字节在后的byte数组
	 */
	public static byte[] toLH(int n) {
		System.out.println("大小端转换");
		byte[] b = new byte[4];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		b[2] = (byte) (n >> 16 & 0xff);
		b[3] = (byte) (n >> 24 & 0xff);
		return b;
	}

	/**
	 * 将float转为低字节在前，高字节在后的byte数组
	 */
	public static byte[] toLH(float f) {
		return toLH(Float.floatToRawIntBits(f));
	}

}
