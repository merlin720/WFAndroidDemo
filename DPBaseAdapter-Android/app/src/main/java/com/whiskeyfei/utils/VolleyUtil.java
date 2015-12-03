package com.whiskeyfei.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.fei.library.volley.utils.LruImageCache;
import com.whiskeyfei.DPAppContext;

public class VolleyUtil {
	private volatile static RequestQueue mRequestQueue;
	private volatile static ImageLoader mImageLoader;

	public static void init(Context context) {
		Log.e("VolleyUtil","VolleyUtil-----1");
		mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
		mRequestQueue.start();
		Log.e("VolleyUtil","VolleyUtil-----2");
	}

	public static void initImageLoder(){
		mImageLoader = new ImageLoader(mRequestQueue,new LruImageCache());
	}

	public static ImageLoader getImageLoader(){
		if (mImageLoader != null){
			return mImageLoader;
		}else{
			throw new IllegalStateException("no init VolleyUtils");
		}
	}

	public static RequestQueue getRequestQueue(){
		if (mRequestQueue != null) {
			return mRequestQueue;
		}else{
			throw new IllegalStateException("no init VolleyUtils");
		}
	}

	public static RequestQueue getQueue() {
		if (mRequestQueue == null) {
			synchronized (VolleyUtil.class) {
				if (mRequestQueue == null) {
					mRequestQueue = Volley.newRequestQueue(DPAppContext.get());
				}
			}
		}
		return mRequestQueue;
	}

	public static <T> void addToRequestQueue(Request<T> request, Object tag){
		if (tag != null) {
			request.setTag(tag);
		}
		getRequestQueue().add(request);
	}

	public static void cancelAllQueue(Object tag) {
		getRequestQueue().cancelAll(tag);
	}

}
