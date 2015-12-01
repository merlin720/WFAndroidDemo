package com.whiskeyfei.demo.app;


import android.app.Application;

import com.whiskeyfei.demo.utils.VolleyUtil;

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
