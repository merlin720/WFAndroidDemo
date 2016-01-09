package com.whiskeyfei.rx.app;


import android.app.Application;

import com.whiskeyfei.rx.utils.VolleyUtil;


public class VolleyApplication extends Application {
	
	private static VolleyApplication mInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		VolleyUtil.init(this);
	}
	
	public static synchronized VolleyApplication get(){
		return mInstance;
	}
}
