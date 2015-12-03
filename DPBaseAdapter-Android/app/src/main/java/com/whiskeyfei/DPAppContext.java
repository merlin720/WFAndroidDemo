package com.whiskeyfei;

import android.app.Application;
import android.util.Log;

import com.whiskeyfei.utils.VolleyUtil;


public class DPAppContext extends Application {
    private static DPAppContext mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Log.e("DPAppContext","DPAppContext-----");
        VolleyUtil.init(this);
    }

    public static synchronized DPAppContext get() {
        return mInstance;
    }
}
