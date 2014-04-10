package com.unitedeveloper.webchat.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import com.unitedeveloper.webchat.R;
import com.unitedeveloper.webchat.activity.MainActivity;
import com.unitedeveloper.webchat.activity.reglog.WebChatLogginAppActivity;
import com.unitedeveloper.webchat.base.WebChatBaseActivity;
import com.unitedeveloper.webchat.view.customview.WebChatNavigationBar.NavigationBarItemType;

public class WebChatLaunchActivity extends WebChatBaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.setNavigationBarHidden(true);
		super.setTabHostBarHidden(true);
		super.setContentView(R.layout.activity_launch_layout);
		
		int time = getResources().getInteger(R.integer.splashscreen_time);
		new CountDownTimer(time,time) {
			
			@Override
			public void onTick(long millisUntilFinished) { }
			
			@Override
			public void onFinish() {
				self.startActivity(new Intent(self, WebChatLogginAppActivity.class));
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				self.finish();
			}
		}.start();
	}

	@Override
	public void onNavigationBarItemClicked(NavigationBarItemType itemType) {
		
	}

}
