package com.zhongchengtechnology.container;

import java.util.ArrayList;
import java.util.List;

import com.stericson.RootTools.RootTools;
import com.zhongchengtechnology.business.AlertCommand;
import com.zhongchengtechnology.business.GetLocalIp;

import android.content.Context;
import android.util.Log;

public class BusinessContainer {
	public static final String TAG="recordForContainer";
	Context context;
	List<String> listIpId=new ArrayList<String>();//声明已用队列用于ip池处理
	public BusinessContainer(Context context){
		this.context=context;
	}
	//处理业务逻辑
	public boolean finishLoop() throws Exception{
		//得到ip
		String ip=GetLocalIp.getIp();
		//如果ip的长度大于15，即3x4+3个字符长度，那么就是错误的ip
		if(ip.length()>15){
		Log.e(TAG, "检查网络设置");
		return false;
		}else{
			//如果队列中不包含该ip
				if(!listIpId.contains(ip)){
					//断开VPN
					String[] vpn={
							"su",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							//连接VPN
							"adb shell input keyevent 23",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 20",
							"adb shell input keyevent 22",
							"adb shell input keyevent 23"

					};
					RootTools.sendShell(vpn, 1000,30000);

					//连接VPN
					//alertCommand.execShellCmd("adb shell input keyevent 20");
					//alertCommand.execShellCmd("adb shell input keyevent 23");
					//alertCommand.execShellCmd("adb shell input keyevent 20");
					//alertCommand.execShellCmd("adb shell input keyevent 20");
					//alertCommand.execShellCmd("adb shell input keyevent 20");
					//alertCommand.execShellCmd("adb shell input keyevent 20");
					//alertCommand.execShellCmd("adb shell input keyevent 23");
					//得到变化后的ip
					//判断是否连接成功
					String ipcg=GetLocalIp.getIp();
					//判断字符串长度是否大于14
					//判断是否连接成功
					//如果连接失败，点击再次连接
					//重复
					//如果连接成功就跳转到淘宝
					
					//进行淘宝部分的操作
					//完成淘宝部分
					//此处传入一个AlertCommand对象
					//alertCommand.execShellCmd("adb shell input keyevent 3");
					/**
					 * 淘宝界面操作部分*/
					//输入手机号
					//接收验证码等
					//完成
					//跳转到APN设置界面
					//将已经用过的ip加入池中
					listIpId.add(ip);
					return true;
				}else{
					return false;
				}
		}
	}
}
