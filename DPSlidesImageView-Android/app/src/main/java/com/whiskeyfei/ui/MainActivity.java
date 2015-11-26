package com.whiskeyfei.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.whiskeyfei.R;
import com.whiskeyfei.fragment.BaseFragment;
import com.whiskeyfei.fragment.SlidesFragment;
import com.whiskeyfei.fragment.TimerFragment;
import com.whiskeyfei.inter.IFragmentEvent;


public class MainActivity extends ActionBarActivity implements IFragmentEvent {
    private static final String TAG = "MainActivity";
    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchFragment(new TimerFragment(), null);//默认fragment
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_main_fragment:
                switchFragment(new TimerFragment(),null);
                return true;
            case R.id.action_Slides_fragment:
                switchFragment(new SlidesFragment(),null);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(BaseFragment fragment, Bundle bundle) {
        Log.e(TAG, "switchFragment() -> fragment:" + fragment.toString());
        if (mCurrentFragment == fragment) {
            Log.e(TAG, TAG + "---switchFragment() -> mCurrentFragment eques fragment");
            return;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onSwitchFragment(BaseFragment fragment, Bundle bundle) {
        switchFragment(fragment, bundle);
    }

    @Override
    public void onAttachActivity(BaseFragment fragment) {
        mCurrentFragment = fragment;
        Log.e(TAG, TAG + "---onAttachActivity()-> mCurrentFragment:"+mCurrentFragment.toString());
    }

    @Override
    public void onDetachActivity(BaseFragment fragment) {
        if (mCurrentFragment == fragment) {
            mCurrentFragment = null;
        }
    }
}
