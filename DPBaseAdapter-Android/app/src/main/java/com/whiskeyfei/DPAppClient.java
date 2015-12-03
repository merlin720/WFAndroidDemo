package com.whiskeyfei;

import android.content.Context;

import com.whiskeyfei.utils.VolleyUtil;


public class DPAppClient {
	private static final String TAG = "DPAppClient";
	private Context mAppContext = null;
	private static final DPAppClient mInstance = new DPAppClient();

	private DPAppClient() {}

	public static final DPAppClient get() {
		return mInstance;
	}

	public void init(Context context) {
		mAppContext = context;
		VolleyUtil.init(context);
		VolleyUtil.initImageLoder();
	}

	private void ensureAppContext() {
		if (mAppContext == null) {
			throw new IllegalStateException("DPAppClient has not been setup.");
		}
	}

	public Context getApplicationContext() {
		ensureAppContext();
		return mAppContext;
	}
}
