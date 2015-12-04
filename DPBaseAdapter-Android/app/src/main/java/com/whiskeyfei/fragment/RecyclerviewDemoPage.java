package com.whiskeyfei.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;
import com.fei.library.inter.DPOnItemClickListener;
import com.fei.library.inter.DPOnItemLongClickListener;
import com.fei.library.itemanimator.DividerItemDecoration;
import com.whiskeyfei.R;
import com.whiskeyfei.adapter.FirstAdapter;
import com.whiskeyfei.model.ItemModel;
import com.whiskeyfei.utils.ApiConstant;


public class RecyclerviewDemoPage extends PageFragment<ItemModel> implements DPOnItemClickListener, DPOnItemLongClickListener, DPOnItemChildViewByIdLongClickListener, DPOnItemChildViewByIdClickListener {
    public static final int FRAGMENT_FLAG = ApiConstant.FRAGMENT_FLAG_4;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FirstAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) mMainView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mAdapter = new FirstAdapter(mRecyclerView, mDataList);
        mAdapter.setOnItemClickLitener(this);
        mAdapter.setOnItemClickLongLitener(this);
        mAdapter.setOnItemChildViewByIdClickListenerr(this);
        mAdapter.setOnItemChildViewByIdLongClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 100; i++) {
            ItemModel model = new ItemModel();
            model.mTitle = i + "item";
            model.mContent = i + "item";
            model.mRsid = R.drawable.ic_launcher;
            mDataList.add(model);
        }
    }

    @Override
    public void onItemChildClick(View view, int position) {
        Toast.makeText(getActivity(), position + "点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup viewGroup, View view, int position) {
        Toast.makeText(getActivity(), position + "常按点击", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onItemChildViewByIdClick(View view, int position) {
        if (view.getId() == R.id.listview_delete) {
            Toast.makeText(getActivity(), position + "点击删除", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemChildViewByIdLongClick(ViewGroup viewGroup, View view, int position) {
        if (view.getId() == R.id.listview_delete) {
            Toast.makeText(getActivity(), position + "常按删除", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
