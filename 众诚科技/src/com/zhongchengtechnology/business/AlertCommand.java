package com.zhongchengtechnology.business;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * 此处为了防止创建多个窗口故只实例化一次
 * */
public class AlertCommand {
	public  void execShellCmd(String cmd) { 	  
	    try {  
	        // 申请获取root权限，这一步很重要，不然会没有作用  
	        Process process = Runtime.getRuntime().exec("su");  
	        // 获取输出流  
	        OutputStream outputStream = process.getOutputStream();  
	        //将输出流封装
	        DataOutputStream dataOutputStream = new DataOutputStream(  
	                outputStream);  
	        //向输出流中写入字节
	        dataOutputStream.writeBytes(cmd);  
	        dataOutputStream.flush();  
	        dataOutputStream.close();  
	        outputStream.close();  
	    } catch (Throwable t) {  
	        t.printStackTrace();  
	    }  
	}  
	  
}
