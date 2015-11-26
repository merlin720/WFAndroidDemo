package com.whiskeyfei.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.whiskeyfei.R;
import com.whiskeyfei.adapter.myPagerAdapter;
import com.whiskeyfei.constant.AppConstant;
import com.whiskeyfei.fragment.FirstFragment;
import com.whiskeyfei.widget.PointView;

import java.util.ArrayList;
import java.util.List;


public class MeiTuanActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{

    private int[] ICON_MAP_COMMON = AppConstant.ICON_MAP_COMMON;
    private int ICON_SIZE = ICON_MAP_COMMON.length;
    public static final String KEY_IMAGE = "key_image";
    public static final String KEY_INDEX = "key_index";

    private Context mContext;
    private ViewPager mViewPager;
    private PointView mPonitView;
    private View mView;
    List<Fragment> mFragmentList;

    public MeiTuanActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meituan);
        mContext = this;
        mFragmentList = new ArrayList<Fragment>();
        for (int i = 0; i < ICON_SIZE; i++) {
            FirstFragment firstFragment = new FirstFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_IMAGE, ICON_MAP_COMMON[i]);
            bundle.putString(KEY_INDEX, "index" + i);
            firstFragment.setArguments(bundle);
            mFragmentList.add(firstFragment);
        }
        initView();
    }

    private void initView(){
        mViewPager = (ViewPager)findViewById(R.id.viewpager_meituan);
        mViewPager.setOnPageChangeListener(this);
        mPonitView = (PointView)findViewById(R.id.pointview_meituan);
        mViewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(), mFragmentList));
        mPonitView.setParams(mViewPager,0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        Toast.makeText(getActivity(), "index" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
