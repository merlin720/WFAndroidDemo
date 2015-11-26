package com.whiskeyfei.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiskeyfei.constant.AppConstant;
import com.whiskeyfei.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 15-11-21.
 */
public abstract class SilidesBaseFragment extends BaseFragment{

    protected int[] ICON_MAP_COMMON = AppConstant.ICON_MAP_COMMON;
    protected int ICON_SIZE = ICON_MAP_COMMON.length;
    protected List<ImageModel> mList = null;
    protected View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        initView();
        return mView;
    }

    protected List<ImageModel> getList(){
        if (mList == null){
            mList = new ArrayList<ImageModel>();
        }
        return mList;
    }

    protected abstract void initView();
    protected abstract int getLayoutId();

    protected void initData() {
        for(int i=0; i<ICON_SIZE; i++){
            ImageModel model = new ImageModel();
            model.setImageId(ICON_MAP_COMMON[i]);
            model.setContent("Title" + i);
            getList().add(model);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ICON_MAP_COMMON = null;
        if(mList != null){
            mList = null;
        }
    }
}
