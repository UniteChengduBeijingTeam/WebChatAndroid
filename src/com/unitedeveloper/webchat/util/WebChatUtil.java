package com.unitedeveloper.webchat.util;

import android.app.ActivityGroup;
import android.content.Context;
import android.util.DisplayMetrics;

public class WebChatUtil {
	
	private static WebChatUtil instance = null;
	
	private Context mContext = null;
	
	private WebChatUtil(Context context){
		this.mContext = context;
	}
	
	public static WebChatUtil getWebChatUtilInstance(Context context){
		if(instance == null){
			instance = new WebChatUtil(context);
		}
		return instance;
	}
	
	

}
