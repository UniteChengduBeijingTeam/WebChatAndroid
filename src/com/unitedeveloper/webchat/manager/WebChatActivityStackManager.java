package com.unitedeveloper.webchat.manager;

import java.util.Stack;

import android.util.Log;

import com.unitedeveloper.webchat.base.WebChatBaseActivity;

public class WebChatActivityStackManager {
	
	private String TAG = "WebChatActivityStackManager";
	
	private static WebChatActivityStackManager instance = null;
	
	private WebChatActivityStackManager(){
		this.mActivityStack = new Stack<WebChatBaseActivity>();
	}
	
	public static WebChatActivityStackManager getActivityStackManagerInstance(){
		if(instance == null){
			instance = new WebChatActivityStackManager();
		}
		return instance;
	}
	
	private Stack<WebChatBaseActivity> mActivityStack = null;
	
	public void pushActivityToStack(WebChatBaseActivity activity){
		this.mActivityStack.push(activity);
		OnActivityStackChangedListener listener = this.getOnActivityStackChangedListener();
		if(listener != null){
			listener.onActivityStackChanged(ActivityStackChangedState.ActivityPushToStack, activity);
		}
		Log.i(TAG, "pushActivityToStack<" + activity.getClass().getSimpleName() + ">(" + this.mActivityStack.size() + ")");
	}
	
	public boolean popActivityFromStack(WebChatBaseActivity activity){
		if(this.mActivityStack != null && this.mActivityStack.size() > 0){
			WebChatBaseActivity topActivity = this.mActivityStack.peek();
			if(topActivity.equals(activity)){
				if(this.mActivityStack.pop().equals(activity)){
					Log.i(TAG, "popActivityFromStack<" + activity.getClass().getSimpleName() + ">(" + this.mActivityStack.size() + ")");
					OnActivityStackChangedListener listener = this.getOnActivityStackChangedListener();
					if(listener != null){
						listener.onActivityStackChanged(ActivityStackChangedState.ActivityPopFromStack, activity);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isRootActivity(WebChatBaseActivity activity){
		return (this.mActivityStack.contains(activity) && this.mActivityStack.indexOf(activity) == 0);
	}
	
	public OnActivityStackChangedListener listener = null;
	
	public OnActivityStackChangedListener getOnActivityStackChangedListener() {
		return listener;
	}

	public void setOnActivityStackChangedListener(OnActivityStackChangedListener listener) {
		this.listener = listener;
	}
	
	public enum ActivityStackChangedState{
		ActivityPushToStack,
		ActivityPopFromStack
	}

	public interface OnActivityStackChangedListener{
		
		public void onActivityStackChanged(ActivityStackChangedState state,WebChatBaseActivity activity);
		
	}

}
