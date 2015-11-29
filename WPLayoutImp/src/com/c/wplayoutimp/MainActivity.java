package com.c.wplayoutimp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private View mView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//top_layout_id为include设置的id
		mView = findViewById(R.id.top_layout_id);
//		methodOne();
		methodTwo();
//		mistakeMethod();//错误方式：使用include中的根部layout id
	}
	
	private void mistakeMethod() {
		View view = findViewById(R.id.title_top_main);
		TextView textView = (TextView)view.findViewById(R.id.textview);
		textView.setText("Top Title Mistake");
		//以下是错误信息
	}
	
//	11-28 23:29:54.331: E/AndroidRuntime(1811): java.lang.RuntimeException: Unable to start activity ComponentInfo{com.c.wplayoutimp/com.c.wplayoutimp.MainActivity}: java.lang.NullPointerException
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2184)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2233)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.ActivityThread.access$800(ActivityThread.java:135)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1196)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.os.Handler.dispatchMessage(Handler.java:102)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.os.Looper.loop(Looper.java:136)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.ActivityThread.main(ActivityThread.java:5001)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at java.lang.reflect.Method.invokeNative(Native Method)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at java.lang.reflect.Method.invoke(Method.java:515)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:785)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:601)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at dalvik.system.NativeStart.main(Native Method)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): Caused by: java.lang.NullPointerException
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at com.c.wplayoutimp.MainActivity.mistakeMethod(MainActivity.java:27)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at com.c.wplayoutimp.MainActivity.onCreate(MainActivity.java:21)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.Activity.performCreate(Activity.java:5231)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1087)
//	11-28 23:29:54.331: E/AndroidRuntime(1811): 	at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2148)

	//以下事LayoutInflater的源码，看完一遍你就知道原因了
	/**
	 private void parseInclude(XmlPullParser parser, View parent, AttributeSet attrs)
            throws XmlPullParserException, IOException {

        int type;

        if (parent instanceof ViewGroup) {
            final int layout = attrs.getAttributeResourceValue(null, "layout", 0);
            if (layout == 0) {
                final String value = attrs.getAttributeValue(null, "layout");
                if (value == null) {
                    throw new InflateException("You must specifiy a layout in the"
                            + " include tag: <include layout=\"@layout/layoutID\" />");
                } else {
                    throw new InflateException("You must specifiy a valid layout "
                            + "reference. The layout ID " + value + " is not valid.");
                }
            } else {
                final XmlResourceParser childParser =
                        getContext().getResources().getLayout(layout);

                try {
                    final AttributeSet childAttrs = Xml.asAttributeSet(childParser);

                    while ((type = childParser.next()) != XmlPullParser.START_TAG &&
                            type != XmlPullParser.END_DOCUMENT) {
                        // Empty.
                    }

                    if (type != XmlPullParser.START_TAG) {
                        throw new InflateException(childParser.getPositionDescription() +
                                ": No start tag found!");
                    }

                    final String childName = childParser.getName();

                    if (TAG_MERGE.equals(childName)) {
                        // Inflate all children.
                        rInflate(childParser, parent, childAttrs, false);
                    } else {
                        final View view = createViewFromTag(parent, childName, childAttrs);
                        final ViewGroup group = (ViewGroup) parent;

                        // We try to load the layout params set in the <include /> tag. If
                        // they don't exist, we will rely on the layout params set in the
                        // included XML file.
                        // During a layoutparams generation, a runtime exception is thrown
                        // if either layout_width or layout_height is missing. We catch
                        // this exception and set localParams accordingly: true means we
                        // successfully loaded layout params from the <include /> tag,
                        // false means we need to rely on the included layout params.
                        ViewGroup.LayoutParams params = null;
                        try {
                            params = group.generateLayoutParams(attrs);
                        } catch (RuntimeException e) {
                            params = group.generateLayoutParams(childAttrs);
                        } finally {
                            if (params != null) {
                                view.setLayoutParams(params);
                            }
                        }

                        // Inflate all children.
                        rInflate(childParser, view, childAttrs, true);

                        // Attempt to override the included layout's android:id with the
                        // one set on the <include /> tag itself.
                        TypedArray a = mContext.obtainStyledAttributes(attrs,
                            com.android.internal.R.styleable.View, 0, 0);
                        int id = a.getResourceId(com.android.internal.R.styleable.View_id, View.NO_ID);
                        // While we're at it, let's try to override android:visibility.
                        int visibility = a.getInt(com.android.internal.R.styleable.View_visibility, -1);
                        a.recycle();
						这里进行了偷梁换柱 将子view的 parent id设置成了include中的id，所以我们在使用title_top_main中的id时就会找不到view，发生crash现象
                        if (id != View.NO_ID) {
                            view.setId(id);
                        }

                        switch (visibility) {
                            case 0:
                                view.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                view.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                view.setVisibility(View.GONE);
                                break;
                        }

                        group.addView(view);
                    }
                } finally {
                    childParser.close();
                }
            }
        } else {
            throw new InflateException("<include /> can only be used inside of a ViewGroup");
        }

        final int currentDepth = parser.getDepth();
        while (((type = parser.next()) != XmlPullParser.END_TAG ||
                parser.getDepth() > currentDepth) && type != XmlPullParser.END_DOCUMENT) {
            // Empty
        }
    } 
	 */
	
	/**
	 * 直接通过this查找，因为include已经转换成layout中的布局
	 */
	private void methodTwo() {
		TextView textView = (TextView)findViewById(R.id.textview);
		textView.setText("Top Title Two");
	}
	
	/**
	 *  通过mView查找子view控件
	 */
	private void methodOne() {
		ImageView imageView = (ImageView) mView.findViewById(R.id.imageview);
		TextView textView = (TextView) mView.findViewById(R.id.textview);
		textView.setText("Top Title One");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			return true;
		}else if(id == R.id.action_viewstub) {
			startActivity(new Intent(this, ViewStubActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
