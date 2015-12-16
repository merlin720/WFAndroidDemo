package com.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.mvp.R;
import com.mvp.presenter.MainPresenter;

public class MainActivity extends Activity implements IMainView {

	private TextView mTextView;
	private Button mButton;
	private MainPresenter mMainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		mMainPresenter = new MainPresenter(this);//绑定IMainView回调接口,可以单独调用attachView方法来绑定
		
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMainPresenter.show();
			}
		});
	}

	private void initView() {
		mTextView = (TextView) findViewById(R.id.text);
		mButton = (Button) findViewById(R.id.loadButton);
	}

	@Override
	public void showTextView(final String text) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mTextView.setText(text);
			}
		});
	}
	
}