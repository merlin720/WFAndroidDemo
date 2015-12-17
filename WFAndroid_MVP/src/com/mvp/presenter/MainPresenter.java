package com.mvp.presenter;

import com.mvp.base.BasePresenter;
import com.mvp.model.IMainModel;
import com.mvp.model.MainModel;
import com.mvp.view.IMainView;

public class MainPresenter extends BasePresenter<IMainView> implements IMainCallback {
	private IMainModel mUserModel;
	
	/**
	 * 需要手动绑定
	 */
	public MainPresenter() {
		mUserModel = new MainModel(this);
	}
	
	/**
	 * 直接绑定view
	 * @param view
	 */
	public MainPresenter(IMainView view) {
		this();
		attachView(view);
	}

	@Override
	public void attachView(IMainView mvpBaseView) {
		super.attachView(mvpBaseView);
	}

	@Override
	public void detachView() {
		super.detachView();
	}
	
	/**
	 * 加载数据
	 */
	public void load(){
		checkViewAttached();//检查是绑定IMainView
		//data
	}

	public void show() {
		mUserModel.show();
	}

	@Override
	public void show(String string) {
		getMvpBaseView().showTextView(string);
	}
}
