package com.unitedeveloper.webchat.base;

import com.unitedeveloper.webchat.R;
import com.unitedeveloper.webchat.WebChatApplication;
import com.unitedeveloper.webchat.manager.WebChatActivityStackManager;
import com.unitedeveloper.webchat.view.customview.WebChatNavigationBar;
import com.unitedeveloper.webchat.view.customview.WebChatNavigationBar.WebChatNavigationBarDelegate;

import android.app.ActivityGroup;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

@SuppressWarnings("deprecation")
public abstract class WebChatBaseActivity extends ActivityGroup implements WebChatNavigationBarDelegate{
	
	private final static int kViewIdNavigationBar = 0x000A;
	
	private final static int kViewIdCentreContentView = 0x000B;
	
	private final static int kViewIdTabHostBar = 0x000C;
	
	protected String TAG = null;
	
	protected Context mContext 				 = null;
	
	protected WebChatApplication mApplicatoin= null;
	
	protected WebChatBaseActivity self		 = null;
	
	protected LayoutInflater mLayoutInflater = null;
	
	protected DisplayMetrics mDisplayMetrics = null;
	
	protected WebChatActivityStackManager mActivityStackManager = null;
	
	/***/
	protected RelativeLayout mActivityRootView = null;
	/***/
	protected View mContentView = null;
	/***/
	protected WebChatNavigationBar mNavigationBar = null;
	/***/
	protected boolean mNavigationBarHidden = false;
	/***/
	protected String mTitle = null;
	/***/
	protected View mTabHostBar = null;
	/***/
	protected boolean mTabHostBarHidden = false;

	/*===Activity Lifecycle=============================================*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		TAG = this.getClass().getName();
		this.mContext = this;
		this.mApplicatoin = (WebChatApplication) this.getApplication();
		this.self = this;
		this.mLayoutInflater = LayoutInflater.from(this.mContext);
		this.mDisplayMetrics = this.mContext.getResources().getDisplayMetrics();
		this.mActivityStackManager = WebChatActivityStackManager.getActivityStackManagerInstance();
		this.mActivityStackManager.pushActivityToStack(self);
		
		this.mNavigationBar = new WebChatNavigationBar(this.mContext,this);
		this.mTabHostBar = new View(mContext);
	}

	@Override
	public void setContentView(int layoutResID) {
		this.mActivityRootView = new RelativeLayout(this.mContext);
		RelativeLayout.LayoutParams rootViewParams = new RelativeLayout.LayoutParams(-1, -1);
		if(!this.isNavigationBarHidden()){
			if(this.getNavigationBar() != null){
				WebChatNavigationBar navigationBar = this.getNavigationBar();
				navigationBar.setNavigationBarTitle(this.getNavigationBarTitle());
				navigationBar.setId(kViewIdNavigationBar);
				float value = this.mContext.getResources().getDimension(R.dimen.navigationbar_height);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, (int)value);
				params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				this.mActivityRootView.addView(navigationBar, params);
			}
		}
		this.mContentView = this.mLayoutInflater.inflate(layoutResID, null);
		if(this.mContentView != null){
			this.mContentView.setId(kViewIdCentreContentView);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
			params.addRule(RelativeLayout.BELOW, kViewIdNavigationBar);
			params.addRule(RelativeLayout.ABOVE, kViewIdTabHostBar);
			this.mContentView.setLayoutParams(params);
		}
		if(!this.isTabHostBarHidden()){
			if(this.getTabHostBar() != null){
				View tabHostBar = this.getTabHostBar();
				tabHostBar.setId(kViewIdTabHostBar);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
				params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				this.mActivityRootView.addView(tabHostBar, params);
			}
		}
		this.mActivityRootView.addView(this.mContentView);
		super.setContentView(this.mActivityRootView,rootViewParams);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	/*===Variable=============================================*/

	public WebChatActivityStackManager getActivityStackManager() {
		return mActivityStackManager;
	}
	
	public WebChatNavigationBar getNavigationBar() {
		return mNavigationBar;
	}

//	public void setNavigationBar(WebChatNavigationBar mNavigationBar) {
//		this.mNavigationBar = mNavigationBar;
//	}

	public boolean isNavigationBarHidden() {
		return this.mNavigationBarHidden;
	}

	public void setNavigationBarHidden(boolean navigationBarHidden) {
		this.mNavigationBarHidden = navigationBarHidden;
		this.mNavigationBar.setVisibility(navigationBarHidden ? View.GONE : View.VISIBLE);
	}

	protected void setNavigationBarTitle(String title){
		this.mTitle = title;
	}
	
	protected String getNavigationBarTitle(){
		return this.mTitle;
	}

	public View getTabHostBar() {
		return mTabHostBar;
	}

	public void setTabHostBar(View mTabHostBar) {
		this.mTabHostBar = mTabHostBar;
	}

	public boolean isTabHostBarHidden() {
		return mTabHostBarHidden;
	}

	public void setTabHostBarHidden(boolean mTabHostBarHidden) {
		this.mTabHostBarHidden = mTabHostBarHidden;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void finish() {
		super.finish();
		this.mActivityStackManager.popActivityFromStack(self);
	}

}
