package com.mvp.model;

import com.mvp.presenter.IMainCallback;

public class MainModel implements IMainModel {
	IMainCallback call;

	public MainModel(IMainCallback call) {
		this.call = call;
	}

	@Override
	public void show() {
		call.show("123");
	}

}
