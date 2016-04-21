package com.zhongchengtechnology.business;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import android.util.Log;
public class GetLocalIp {
	private static final String TAG="iprecord";
	public static String getIp(){
		String ip = null;
		//得到ip操作
		try{
			for(Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();en.hasMoreElements();){
				NetworkInterface intf=en.nextElement();
				for(Enumeration<InetAddress> enumIpAddr=intf.getInetAddresses();enumIpAddr.hasMoreElements();){
					InetAddress inetAddress=enumIpAddr.nextElement();
					if(!inetAddress.isLoopbackAddress()){
						ip=inetAddress.getHostAddress().toString();
						return  ip;
					}
				}
			}
		}catch(SocketException e){
			Log.e(TAG,e.toString());
		}
		return ip;
	}
}
