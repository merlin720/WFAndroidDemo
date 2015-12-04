package com.whiskeyfei.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fei.library.fragment.DPBaseFragment;
import com.whiskeyfei.R;

/**
 * Created by whiskeyfei on 15-11-25.
 */
public class FragmentList extends DPBaseFragment implements View.OnClickListener {
    protected View mMainView;
    private Button mButtonListView, mButtonGridView, mButtonJson, mRecycleBtn, mChatBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_list, null);
        initView();
        return mMainView;
    }

    private void initView() {
        mButtonListView = (Button) mMainView.findViewById(R.id.listview_btn);
        mButtonGridView = (Button) mMainView.findViewById(R.id.gridview_btn);
        mButtonJson = (Button) mMainView.findViewById(R.id.listview_data_btn);
        mRecycleBtn = (Button) mMainView.findViewById(R.id.recycle_btn);
        mChatBtn = (Button) mMainView.findViewById(R.id.chat_btn);
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
                mIBaseFramentEvent.onSwitchFragment(new ListViewDemoPage(), null);
                break;
            case R.id.gridview_btn:
                mIBaseFramentEvent.onSwitchFragment(new GridViewDemoPage(), null);
                break;
            case R.id.recycle_btn:
                mIBaseFramentEvent.onSwitchFragment(new RecyclerviewDemoPage(), null);
                break;
            case R.id.chat_btn:
                mIBaseFramentEvent.onSwitchFragment(new ChatListDemoPage(), null);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
