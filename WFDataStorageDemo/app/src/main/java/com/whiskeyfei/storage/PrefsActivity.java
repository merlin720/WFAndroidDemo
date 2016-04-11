package com.whiskeyfei.storage;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by whiskeyfei on 16/3/29.
 */
public class PrefsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
