package whiskeyfei.com.dpservicedemo.app;

import android.app.Application;

public class AppContext extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		AppClient.getInstance().setContext(getApplicationContext());
	}
}
