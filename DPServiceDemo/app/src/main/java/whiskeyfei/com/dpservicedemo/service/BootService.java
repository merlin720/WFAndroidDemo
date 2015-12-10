package whiskeyfei.com.dpservicedemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import de.greenrobot.event.EventBus;
import whiskeyfei.com.dpservicedemo.app.AppClient;


public class BootService extends Service {
	private static final String TAG = "BootService";
	
	private Handler mHandler = new Handler(Looper.getMainLooper());
	private static final long DELAY = 1000;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		EventBus.getDefault().register(this);
		 Log.e(TAG, ">>> onCreate");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		mHandler.removeCallbacks(mRegularAction);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 startInit();
		 return super.onStartCommand(intent, flags, startId);
	}

	private void startInit() {
		Context context = AppClient.getInstance().getApplicationContext();
		EventBus.getDefault().post(mRegularAction);
	}

	public void onEventMainThread(Runnable r) {
		mHandler.removeCallbacks(r);
		mHandler.postDelayed(r, DELAY);
	}
	
	private  Runnable mRegularAction = new Runnable() {
		
		@Override
		public void run() {
			long time = System.currentTimeMillis();
			EventBus.getDefault().post(new FirstEvent(time+""));
			EventBus.getDefault().post(mRegularAction);
		}
	};
	
	public static void startService() {
		 Log.e(TAG, ">>> startService");
		 Context context = AppClient.getInstance().getApplicationContext();
		 Intent intent = new Intent(context, BootService.class);
		 context.startService(intent);
	}
	
	public static void stopService(){
		 Log.e(TAG, ">>> stopService");
		 Context context = AppClient.getInstance().getApplicationContext();
		 Intent intent = new Intent(context, BootService.class);
		 context.stopService(intent);
	}

}