package com.whiskeyfei.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whiskeyfei.ui.adapter.FirstAdapter;
import com.whiskeyfei.ui.itemanimator.DividerItemDecoration;
import com.whiskeyfei.ui.model.ItemModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
//    RecyclerViewAdapter mAdapter;
    FirstAdapter mAdapter;
    private List<ItemModel> mList = new ArrayList<ItemModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        mAdapter = new RecyclerViewAdapter(this,mList);
        mAdapter = new FirstAdapter(mRecyclerView,mList);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
//        mAdapter.setOnItemDeleteClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, position + "delete", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
    }

    private void initData(){
        for (int i = 0;i<100;i++){
            ItemModel model = new ItemModel();
            model.mTitle = i+"item";
            model.mContent = i+"item";
            model.mRsid = R.mipmap.ic_launcher;
            mList.add(model);
        }
    }

}
