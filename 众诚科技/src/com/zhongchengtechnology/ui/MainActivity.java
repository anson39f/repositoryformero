package com.zhongchengtechnology.ui;

import com.zhongchengtechnology.R;
import com.zhongchengtechnology.business.AlertCommand;
import com.zhongchengtechnology.business.GetLocalIp;
import com.zhongchengtechnology.container.BusinessContainer;
import android.os.Bundle;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_click;
	private TextView textView;
	private static final String TAG="record";
	
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();//初始化控件	
	}
	private void init(){
		btn_click=(Button)findViewById(R.id.btn);//实例化按钮
		btn_click.setOnClickListener(this);//为按钮增加监听
		AnimationSet animationSet=new AnimationSet(true);
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
		rotateAnimation.setDuration(1000);
		animationSet.addAnimation(rotateAnimation);
		btn_click.setAnimation(animationSet);
		textView=(TextView)findViewById(R.id.tx_01);
		textView.setText(GetLocalIp.getIp());
		LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
		View view=inflater.inflate(R.layout.toast, null);
		Toast toast=Toast.makeText(MainActivity.this, " ", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 250);
		toast.setView(view);
		toast.show();
		
		
		toast.show();
	}
	@SuppressWarnings("finally")
	@Override
	public void onClick(View v) {
        //执行封装的业务逻辑处理
		runIntent();//第一次跳转到VPN设置界面
		//得到BusinessContainer对象
		final BusinessContainer businessContainer=new BusinessContainer(getApplicationContext());
		//只创建一个执行命令类
			new Thread(new Runnable() {
				@SuppressWarnings("synthetic-access")
				@Override
				public void run() {
					for(;;){
							boolean is = false;//初始化判断值
							try {
								//执行整个注册操作后返回的值，如果返回真，就继续下次循环
								//如果返回假，则结束循环
								is=businessContainer.finishLoop();//将命令对象传入
								if(is==true){
									//跳转到VPN界面
									runIntent();
								}else{
									//退出
								break;
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						

					}
				}
			}).start();
	}
			
	private void runIntent(){
		startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
	}	
}


