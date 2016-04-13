package com.whiskeyfei.tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.whiskeyfei.tab.adapter.BottomTabAdapter;
import com.whiskeyfei.tab.widget.BottomTabLayout;

public class MainActivity extends AppCompatActivity {

    private String mTabNames[] = {"Chats", "Contacts", "Discover", "Me"};
    private int mTabIconRes[][] = {
            {R.drawable.icon_chats_normal, R.drawable.icon_chats_selected},
            {R.drawable.icon_contacts_normal, R.drawable.icon_contacts_selected},
            {R.drawable.icon_discover_normal, R.drawable.icon_discover_selected},
            {R.drawable.icon_me_normal, R.drawable.icon_me_selected}
    };

    private BottomTabLayout mBottomTabLayout;
    private BottomTabAdapter mBottomTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBottomTabLayout = (BottomTabLayout) findViewById(R.id.tabLayout);
        mBottomTabAdapter = new BottomTabAdapter();
        mBottomTabAdapter.setData(mTabNames,mTabIconRes);
        mBottomTabLayout.setAdapter(mBottomTabAdapter);
    }
}
