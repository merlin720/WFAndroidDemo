package com.c.wplayoutimp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

public class ViewStubActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewstub);
//		methodOne();
		methodTwo();
	}
	
	private void methodOne(){
		ViewStub root = (ViewStub)findViewById(R.id.viewsutb);
		View view = root.inflate();
		TextView textView = (TextView)view.findViewById(R.id.textview);
		textView.setText("inflate pass ");
	}
	
	private void methodTwo(){
		ViewStub root = (ViewStub)findViewById(R.id.viewsutb);
		root.setVisibility(View.VISIBLE);
		View view = findViewById(R.id.viewsutb_inflated_id); //1 
//		View view = findViewById(R.id.viewsutb_item_main);  //2
		TextView textView = (TextView)view.findViewById(R.id.textview);
		textView.setText("viewsutb_inflated_id pass ");
	}
	
	
	/**如果你设置了viewstub的inflatedid 而且执行了2代码来获取子view就会报以下错误信息，原因就是inflatedid会覆盖layout中的跟view id；
	11-29 00:07:02.555: E/AndroidRuntime(2257): FATAL EXCEPTION: main
11-29 00:07:02.555: E/AndroidRuntime(2257): Process: com.c.wplayoutimp, PID: 2257
11-29 00:07:02.555: E/AndroidRuntime(2257): java.lang.RuntimeException: Unable to start activity ComponentInfo{com.c.wplayoutimp/com.c.wplayoutimp.ViewStubActivity}: java.lang.NullPointerException
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2184)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2233)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.ActivityThread.access$800(ActivityThread.java:135)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1196)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.os.Handler.dispatchMessage(Handler.java:102)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.os.Looper.loop(Looper.java:136)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.ActivityThread.main(ActivityThread.java:5001)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at java.lang.reflect.Method.invokeNative(Native Method)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at java.lang.reflect.Method.invoke(Method.java:515)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:785)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:601)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at dalvik.system.NativeStart.main(Native Method)
11-29 00:07:02.555: E/AndroidRuntime(2257): Caused by: java.lang.NullPointerException
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at com.c.wplayoutimp.ViewStubActivity.methodTwo(ViewStubActivity.java:30)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at com.c.wplayoutimp.ViewStubActivity.onCreate(ViewStubActivity.java:15)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.Activity.performCreate(Activity.java:5231)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1087)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2148)
11-29 00:07:02.555: E/AndroidRuntime(2257): 	... 11 more
	 */
	/**ViewStub源代码
	 public View inflate() {
        final ViewParent viewParent = getParent();

        if (viewParent != null && viewParent instanceof ViewGroup) {
            if (mLayoutResource != 0) {
                final ViewGroup parent = (ViewGroup) viewParent;
                final LayoutInflater factory = LayoutInflater.from(mContext);
                final View view = factory.inflate(mLayoutResource, parent,
                        false);
				这里和include类似，将id覆盖
                if (mInflatedId != NO_ID) {
                    view.setId(mInflatedId);
                }

                final int index = parent.indexOfChild(this);
                parent.removeViewInLayout(this);

                final ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null) {
                    parent.addView(view, index, layoutParams);
                } else {
                    parent.addView(view, index);
                }

                mInflatedViewRef = new WeakReference<View>(view);

                if (mInflateListener != null) {
                    mInflateListener.onInflate(this, view);
                }

                return view;
            } else {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
        } else {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
    } 
	 * 
	 */
}
