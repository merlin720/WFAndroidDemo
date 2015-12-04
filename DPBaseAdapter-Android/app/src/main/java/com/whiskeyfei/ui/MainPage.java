package com.whiskeyfei.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;

import com.fei.library.activity.DPBaseActivity;
import com.fei.library.fragment.DPBaseFragment;
import com.fei.library.inter.IBaseFragmentEvent;
import com.whiskeyfei.R;
import com.whiskeyfei.fragment.ChatListDemoPage;
import com.whiskeyfei.fragment.GridViewDemoPage;
import com.whiskeyfei.fragment.ListViewDemoPage;
import com.whiskeyfei.fragment.RecyclerviewDemoPage;
import com.whiskeyfei.utils.ApiConstant;

/**
 * Created by whiskeyfei on 15-11-25.
 */
public class MainPage extends DPBaseActivity implements IBaseFragmentEvent {

    private DPBaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        int flag = getIntent().getIntExtra(ApiConstant.FRAGMENT_FLAG, 0);
        DPBaseFragment fragment = null;
        switch (flag) {
            case ListViewDemoPage.FRAGMENT_FLAG:
                fragment = new ListViewDemoPage();
                break;
            case GridViewDemoPage.FRAGMENT_FLAG:
                fragment = new GridViewDemoPage();
                break;
            case RecyclerviewDemoPage.FRAGMENT_FLAG:
                fragment = new RecyclerviewDemoPage();
                break;
            case ChatListDemoPage.FRAGMENT_FLAG:
                fragment = new ChatListDemoPage();
                break;
        }
        switchFragment(fragment, null);
    }

    private int getLayoutId() {
        return R.layout.page_main;
    }

    private void switchFragment(DPBaseFragment fragment, Bundle bundle) {
        if (mCurrentFragment == fragment) {
            return;
        }
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onSwitchFragment(DPBaseFragment fragment, Bundle bundle) {
        switchFragment(fragment, bundle);
    }

    @Override
    public void onAttachActivity(DPBaseFragment fragment) {
        mCurrentFragment = fragment;
    }

    @Override
    public void onDetachActivity(DPBaseFragment fragment) {
        if (mCurrentFragment == fragment) {
            mCurrentFragment = null;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}
