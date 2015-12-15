package whiskeyfei.com.dpservicedemo.app;

import android.app.Application;

import whiskeyfei.com.dpservicedemo.service.BootService;

public class AppContext extends Application {
	private static AppContext mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		BootService.startService();
	}

	public static AppContext get(){
		return mInstance;
	}

}
