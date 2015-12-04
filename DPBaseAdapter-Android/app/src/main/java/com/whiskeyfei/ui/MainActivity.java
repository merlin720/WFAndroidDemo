package com.whiskeyfei.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.whiskeyfei.R;
import com.whiskeyfei.fragment.ChatListDemoPage;
import com.whiskeyfei.fragment.GridViewDemoPage;
import com.whiskeyfei.fragment.ListViewDemoPage;
import com.whiskeyfei.fragment.RecyclerviewDemoPage;
import com.whiskeyfei.utils.ApiConstant;


public class MainActivity extends Activity implements OnClickListener {

    private Button mButtonListView, mButtonGridView, mRecycleBtn, mChatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mButtonListView = (Button) findViewById(R.id.listview_btn);
        mButtonGridView = (Button) findViewById(R.id.gridview_btn);
        mRecycleBtn = (Button) findViewById(R.id.recycle_btn);
        mChatBtn = (Button) findViewById(R.id.chat_btn);
        mButtonListView.setOnClickListener(this);
        mButtonGridView.setOnClickListener(this);
        mRecycleBtn.setOnClickListener(this);
        mChatBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        onButtonClick(v);
    }

    private void onButtonClick(View v) {
        Intent intent = new Intent(MainActivity.this, MainPage.class);
        switch (v.getId()) {
            case R.id.listview_btn:
                intent.putExtra(ApiConstant.FRAGMENT_FLAG, ListViewDemoPage.FRAGMENT_FLAG);
                break;
            case R.id.gridview_btn:
                intent.putExtra(ApiConstant.FRAGMENT_FLAG, GridViewDemoPage.FRAGMENT_FLAG);
                break;
            case R.id.recycle_btn:
                intent.putExtra(ApiConstant.FRAGMENT_FLAG, RecyclerviewDemoPage.FRAGMENT_FLAG);
                break;
            case R.id.chat_btn:
                intent.putExtra(ApiConstant.FRAGMENT_FLAG, ChatListDemoPage.FRAGMENT_FLAG);
                break;
        }
        startActivity(intent);

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                // 进行资源释放操作
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}