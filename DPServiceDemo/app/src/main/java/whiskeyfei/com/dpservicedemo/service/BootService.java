package whiskeyfei.com.dpservicedemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import whiskeyfei.com.dpservicedemo.app.AppClient;


public class BootService extends Service {
	private static final String TAG = "BootService";
	
	private Handler mHandler = new Handler(Looper.getMainLooper());
	private static final long DELAY = 1000 * 5;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		 Log.e(TAG, ">>> onCreate");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(mRegularAction);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 Log.e(TAG, ">>> StartupService onStartCommand");
		 startInit();
		 return super.onStartCommand(intent, flags, startId);
	}

	private void startInit() {
		Context context = AppClient.getInstance().getApplicationContext();
		Toast.makeText(context, "show time!", Toast.LENGTH_SHORT).show();
		mHandler.removeCallbacks(mRegularAction);
		mHandler.postDelayed(mRegularAction, DELAY);
	}
	
	private final Runnable mRegularAction = new Runnable() {
		
		@Override
		public void run() {
			startService();
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