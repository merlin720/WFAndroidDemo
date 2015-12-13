package com.mvp.presenter;

import com.mvp.model.IMainModel;
import com.mvp.model.MainModel;
import com.mvp.view.IMainView;

public class MainPresenter implements IMainPresenter,IMainCallback{
	private IMainView mMainView;
	private IMainModel mUserModel;

	public MainPresenter(IMainView view) {
		mMainView = view;
		mUserModel = new MainModel(this);
	}
	
	@Override
	public void show() {
		mUserModel.show();
	}

	@Override
	public void show(String string) {
		mMainView.showTextView(string);
	}
}
