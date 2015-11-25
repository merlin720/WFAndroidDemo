package com.whiskeyfei;

import android.app.Application;

public class DPAppContext extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		DPAppClient.get().setupWithContext(getApplicationContext());
	}
}
