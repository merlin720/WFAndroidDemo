package com.whiskeyfei.inter;

import android.os.Bundle;

import com.whiskeyfei.fragment.BaseFragment;

/**
 * Created by whiskeyfei on 15-7-29.
 */
public interface IFragmentEvent {
    void onSwitchFragment(BaseFragment fragment, Bundle bundle);
    void onAttachActivity(BaseFragment fragment);
    void onDetachActivity(BaseFragment fragment);
}
