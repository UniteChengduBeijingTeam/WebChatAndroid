package com.unitedeveloper.webchat.view.customview;

import com.unitedeveloper.webchat.R;
import com.unitedeveloper.webchat.base.WebChatBaseActivity;
import com.unitedeveloper.webchat.manager.WebChatActivityStackManager;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WebChatNavigationBar  extends RelativeLayout{
	
	private final static int kNavigationBarItemID_TitleView = 0x00A1;
	private final static int kNavigationBarItemID_LeftItem 	= 0x00A2;
	private final static int kNavigationBarItemID_RightItem = 0x00A3;
	
	private TextView mNavigationBarTitleView = null;
	
	private Button mNavigationBarLeftItem = null;
	private Button mNavigationBarRightItem = null;
	
	private View mNavigationBarLeftView = null;
	private View mNavigationBarRightView = null;
	
	private String mNavigationBarTitle = null;
	
	private WebChatNavigationBarDelegate delegate = null;
	
	private WebChatActivityStackManager mActivityStackManager = null;

	public WebChatNavigationBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.init(context);
	}

	public WebChatNavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.init(context);
	}

	public WebChatNavigationBar(Context context) {
		super(context);
		this.init(context);
	}
	
	public WebChatNavigationBar(Context context,WebChatNavigationBarDelegate delegate) {
		super(context);
		this.init(context);
		this.delegate = delegate;
	}
	
	private void init(Context context){
		this.mActivityStackManager = WebChatActivityStackManager.getActivityStackManagerInstance();
		
		int padding = Math.round(context.getResources().getDimension(R.dimen.padding_defalut));
		super.setPadding(padding, padding, padding, padding);
		
		int leftBtnWidth = Math.round(context.getResources().getDimension(R.dimen.navigationbaritem_width));
		int leftBtnHeight = Math.round(context.getResources().getDimension(R.dimen.navigationbaritem_height));
		
		boolean isShowBackItem = true;
		if(context instanceof WebChatBaseActivity){
			WebChatBaseActivity activity = (WebChatBaseActivity) context;
			if(this.mActivityStackManager.isRootActivity(activity)){
				isShowBackItem = false;
			}
		}
		if(isShowBackItem){
			this.mNavigationBarLeftItem = new Button(context);
			this.mNavigationBarLeftItem.setId(kNavigationBarItemID_LeftItem);
			this.mNavigationBarLeftItem.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					WebChatNavigationBarDelegate delegate = WebChatNavigationBar.this.getWebChatNavigationBarDelegate();
					if(delegate != null){
						delegate.onNavigationBarItemClicked(NavigationBarItemType.NavigationBarLeftItem);
					}
				}
			});
			this.mNavigationBarLeftItem.setBackgroundResource(R.drawable.btn_back);
			RelativeLayout.LayoutParams leftItemParams = new RelativeLayout.LayoutParams(leftBtnWidth,leftBtnHeight);
			leftItemParams.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
			leftItemParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			super.addView(this.mNavigationBarLeftItem, leftItemParams);	
		}
		
		this.mNavigationBarTitleView = new TextView(context);
		this.mNavigationBarTitleView.setId(kNavigationBarItemID_TitleView);
		this.mNavigationBarTitleView.setTextColor(Color.WHITE);
		this.mNavigationBarTitleView.setTextSize(19);
		this.mNavigationBarTitleView.setSingleLine(true);
		this.mNavigationBarTitleView.setLines(1);
		this.mNavigationBarTitleView.setEllipsize(TruncateAt.MIDDLE);
		this.mNavigationBarTitleView.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams titleViewParams = new RelativeLayout.LayoutParams(-2,-2);
		titleViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		this.mNavigationBarTitleView.setLayoutParams(titleViewParams);
		super.addView(this.mNavigationBarTitleView);
		
		this.mNavigationBarRightItem = new Button(context);
		this.mNavigationBarRightItem.setId(kNavigationBarItemID_RightItem);
		this.mNavigationBarRightItem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WebChatNavigationBarDelegate delegate = WebChatNavigationBar.this.getWebChatNavigationBarDelegate();
				if(delegate != null){
					delegate.onNavigationBarItemClicked(NavigationBarItemType.NavigationBarRightItem);
				}
			}
		});
		this.mNavigationBarRightItem.setBackgroundResource(R.drawable.btn_back);
		RelativeLayout.LayoutParams rightItemParams = new RelativeLayout.LayoutParams(leftBtnWidth,leftBtnHeight);
		rightItemParams.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
		rightItemParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		super.addView(this.mNavigationBarRightItem, rightItemParams);
		
		super.setBackgroundColor(Color.argb(128, 16, 16, 128));
	}

	public TextView getNavigationBarTitleView() {
		return mNavigationBarTitleView;
	}

	public void setNavigationBarTitleView(TextView navigationBarTitleView) {
		if(navigationBarTitleView != null){
			this.mNavigationBarTitleView = navigationBarTitleView;
		}
	}

	public Button getNavigationBarLeftItem() {
		return mNavigationBarLeftItem;
	}

	public void setNavigationBarLeftItem(Button mNavigationBarLeftItem) {
		this.mNavigationBarLeftItem = mNavigationBarLeftItem;
	}

	public Button getNavigationBarRightItem() {
		return mNavigationBarRightItem;
	}

	public void setNavigationBarRightItem(Button mNavigationBarRightItem) {
		this.mNavigationBarRightItem = mNavigationBarRightItem;
	}

	public View getNavigationBarLeftView() {
		return mNavigationBarLeftView;
	}

	public void setNavigationBarLeftView(View mNavigationBarLeftView) {
		if(this.getNavigationBarLeftView() != null && super.indexOfChild(this.getNavigationBarLeftView()) > 0){
			super.removeView(this.getNavigationBarLeftView());
		}
		this.mNavigationBarLeftView = mNavigationBarLeftView;
		if(this.mNavigationBarLeftView != null){
			int leftBtnWidth = Math.round(getContext().getResources().getDimension(R.dimen.navigationbaritem_width));
			int leftBtnHeight = Math.round(getContext().getResources().getDimension(R.dimen.navigationbaritem_height));
			this.mNavigationBarLeftView.setClickable(true);
			this.mNavigationBarLeftView.setId(kNavigationBarItemID_LeftItem);
			this.mNavigationBarLeftView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					WebChatNavigationBarDelegate delegate = WebChatNavigationBar.this.getWebChatNavigationBarDelegate();
					if(delegate != null){
						delegate.onNavigationBarItemClicked(NavigationBarItemType.NavigationBarLeftItem);
					}
				}
			});
			this.mNavigationBarLeftView.setBackgroundResource(R.drawable.btn_back);
			RelativeLayout.LayoutParams leftItemParams = new RelativeLayout.LayoutParams(leftBtnWidth,leftBtnHeight);
			leftItemParams.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
			leftItemParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			super.addView(this.mNavigationBarLeftView, leftItemParams);
		}
	}

	public View getNavigationBarRightView() {
		return mNavigationBarRightView;
	}

	public void setNavigationBarRightView(View mNavigationBarRightView) {
		this.mNavigationBarRightView = mNavigationBarRightView;
	}

	public String getNavigationBarTitle() {
		return mNavigationBarTitle;
	}

	public void setNavigationBarTitle(String title) {
		this.mNavigationBarTitle = title;
		if(this.getNavigationBarTitleView() != null){
			this.getNavigationBarTitleView().setText(title);
		}
	}

	public WebChatNavigationBarDelegate getWebChatNavigationBarDelegate() {
		return delegate;
	}

	public void setWebChatNavigationBarDelegate(WebChatNavigationBarDelegate delegate) {
		this.delegate = delegate;
	}

	public enum NavigationBarItemType{
		NavigationBarLeftItem,
		NavigationBarMediumItem,
		NavigationBarRightItem,
	}
	
	public interface WebChatNavigationBarDelegate{
		
		public void onNavigationBarItemClicked(NavigationBarItemType itemType);
		
	}

}
