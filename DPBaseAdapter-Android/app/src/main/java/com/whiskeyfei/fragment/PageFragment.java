package com.whiskeyfei.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fei.library.fragment.DPBaseFragment;
import com.fei.library.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 15-12-4.
 */
public abstract class PageFragment<T> extends DPBaseFragment{
    protected static final int DATA_SIZE = 30;
    protected List<T> mDataList;
    protected View mMainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mMainView = inflater.inflate(getLayoutId(), null);
        getDataList();
        initData();
        initView();
        return mMainView;
    }

    public abstract void initView();
    protected abstract int getLayoutId();
    protected abstract void initData();

//    private void initData() {
//        for (int i = 0; i < DATA_SIZE; i++) {
//            DPItemModel model = new DPItemModel();
//            model.setItemResId(R.drawable.ic_launcher);
//            model.setItemTitle("title" + i);
//            model.setItemContent("content" + i);
//            model.setItemIconUrl(AppConstants.BAIDU_IMAGE2);
//            mDataList.add(model);
//        }
//    }

    private List<T> getDataList(){
        if (ListUtils.isEmpty(mDataList)){
            mDataList = new ArrayList<T>();
        }
        return mDataList;
    }
}
