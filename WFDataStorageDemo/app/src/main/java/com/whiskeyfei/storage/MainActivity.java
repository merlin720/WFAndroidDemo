package com.whiskeyfei.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.whiskeyfei.storage.utils.PreferencesUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesUtils.saveString(this,"test","key1","test1");
        PreferencesUtils.saveBoolean(this,"test","key2",true);
    }
}
