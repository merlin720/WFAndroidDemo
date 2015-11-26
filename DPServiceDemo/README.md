# DPServiceDemo
> 一个定时刷新的Service，使用Handler实现定时出发，目前效果为定时现实Toast消息,以后可能作为更新数据使用,比如:一小时刷新一次数据，即重启Service=

### 效果

![](http://7xol9p.com1.z0.glb.clouddn.com/github_service.gif)

### service初步


### 定时机制
部分代码，根据service的生命周期，再onStartCommand时调用我们自己的方法，并使用mHandler的postDelayed方法来延迟，延迟中的方法再重新startService开启服务，这样就实现了定时刷新，没有使用java的Timer类似
```
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
```

### 配置
```
       <!-- service -->
        <service
            android:name="xx"
            android:exported="false">
            <intent-filter>
                <action android:name="xx"/>
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
        </service>
```

######更多内容请关注[我的github](https://github.com/whiskeyfei),欢迎提交[Issues](https://github.com/whiskeyfei/DPService-Android/issues)
