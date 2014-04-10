package com.unitedeveloper.webchat;

import android.app.Application;
import android.content.res.Configuration;

public class WebChatApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

//	@Override
//	public void onTrimMemory(int level) {
//		// TODO Auto-generated method stub
//		super.onTrimMemory(level);
//	}

}
