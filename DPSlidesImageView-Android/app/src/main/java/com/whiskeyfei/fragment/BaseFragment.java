package com.whiskeyfei.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiskeyfei.inter.IFragmentEvent;

public class BaseFragment extends Fragment {
	
	private static final String TAG = "BaseFragment";
	protected Context mContext;
	protected IFragmentEvent mOnSwitchFragmentListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
		try {
			mOnSwitchFragmentListener = (IFragmentEvent) activity;
		} catch (Exception e) {
			throw new IllegalStateException("your activity must implements mOnSwitchFragmentListener!");
		}
		mOnSwitchFragmentListener.onAttachActivity(this);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.e(TAG, TAG + "---onDetach()---");
		mContext = null;
		mOnSwitchFragmentListener = null;
	}
	
}
