package com.whiskeyfei.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.whiskeyfei.R;
import com.whiskeyfei.fragment.ChatListDemoPage;
import com.whiskeyfei.fragment.GridViewDemoPage;
import com.whiskeyfei.fragment.ListViewDemoPage;
import com.whiskeyfei.fragment.ListViewJsonPage;
import com.whiskeyfei.fragment.RecyclerviewDemoPage;


public class MainActivity extends Activity implements OnClickListener{
	
	private Button mButtonListView,mButtonGridView,mButtonJson,mRecycleBtn,mChatBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mButtonListView = (Button)findViewById(R.id.listview_btn);
		mButtonGridView = (Button)findViewById(R.id.gridview_btn);
		mButtonJson = (Button)findViewById(R.id.listview_data_btn);
		mRecycleBtn = (Button)findViewById(R.id.recycle_btn);
		mChatBtn = (Button)findViewById(R.id.chat_btn);
		mButtonListView.setOnClickListener(this);
		mButtonGridView.setOnClickListener(this);
		mButtonJson.setOnClickListener(this);
		mRecycleBtn.setOnClickListener(this);
		mChatBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.listview_btn:
				start(ListViewDemoPage.class);
				break;
			case R.id.gridview_btn:
				start(GridViewDemoPage.class);
				break;

			case R.id.listview_data_btn:
				start(ListViewJsonPage.class);
				break;
			case R.id.recycle_btn:
				start(RecyclerviewDemoPage.class);
				break;
			case R.id.chat_btn:
				start(ChatListDemoPage.class);
				break;
		}
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		switch (level){
			case TRIM_MEMORY_UI_HIDDEN:
				// 进行资源释放操作
				break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void start(Class<?> class1) {
		Intent intent = new Intent(MainActivity.this, class1);
		startActivity(intent);
	}
	
}