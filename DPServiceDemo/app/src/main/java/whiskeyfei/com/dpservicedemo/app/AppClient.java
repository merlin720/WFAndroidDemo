package whiskeyfei.com.dpservicedemo.app;


import android.content.Context;

import whiskeyfei.com.dpservicedemo.service.BootService;

public class AppClient {
	private static final String TAG = "AppClient";
	private Context mAppContext = null;
	private static final AppClient mInstance = new AppClient();

	private AppClient() {}

	public static final AppClient getInstance() {
		return mInstance;
	}

	public void setContext(Context context) {
		mAppContext = context;
		BootService.startService();
	}

	private void ensureAppContext() {
		if (mAppContext == null) {
			throw new IllegalStateException("AppClient has not been setup.");
		}
	}

	public Context getApplicationContext() {
		ensureAppContext();
		return mAppContext;
	}
}
