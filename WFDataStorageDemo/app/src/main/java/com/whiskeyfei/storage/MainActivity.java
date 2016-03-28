package com.whiskeyfei.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whiskeyfei.storage.bean.User;
import com.whiskeyfei.storage.preference.UserPreference;
import com.whiskeyfei.storage.utils.PreferencesUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesUtils.saveString(this,"test","key1","test1");
        PreferencesUtils.saveBoolean(this,"test","key2",true);
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setMoney(1234);
        UserPreference.saveUser(this,user);

        Log.e("TAG","user:" + UserPreference.getUser(this).toString());
    }
}
