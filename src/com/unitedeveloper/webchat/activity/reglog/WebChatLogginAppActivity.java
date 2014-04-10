package com.unitedeveloper.webchat.activity.reglog;

import android.os.Bundle;

import com.unitedeveloper.webchat.R;
import com.unitedeveloper.webchat.base.WebChatBaseActivity;
import com.unitedeveloper.webchat.view.customview.WebChatNavigationBar.NavigationBarItemType;

public class WebChatLogginAppActivity extends WebChatBaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setNavigationBarHidden(true);
		super.setTabHostBarHidden(true);
		super.setContentView(R.layout.activity_login_layout);
	}

	@Override
	public void onNavigationBarItemClicked(NavigationBarItemType itemType) {
		
	}

}
