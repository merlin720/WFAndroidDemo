package com.whiskeyfei.tab.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.whiskeyfei.tab.R;
import com.whiskeyfei.tab.adapter.BottomTabAdapter;

/**
 * Created by whiskeyfei on 16/4/11.
 */
public class BottomTabLayout extends LinearLayout {
    private static final String TAG = "BottomTabLayout";

    private BottomTabAdapter mBottomTabAdapter;

    private View[] mTabViews;
    private int mTabTextSelectColor, mTabTextNormalColor;

    public BottomTabLayout(Context context) {
        this(context,null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
//        init(context, attrs, defStyleAttr);
    }

    private void init(){
        mTabTextSelectColor = getResources().getColor(R.color.tab_textcolor_selected);
        mTabTextNormalColor = getResources().getColor(R.color.tab_textcolor_normal);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (mBottomTabAdapter == null){
            throw new IllegalArgumentException("mBottomTabAdapter is null");
        }
        int len = mBottomTabAdapter.getCount();
        if (len <= 0 ){
            return;
        }
        mTabViews = new View[len];
        for (int i = 0; i < len; i++) {
           final TabItemView itemView = new TabItemView(context);
            mTabViews[i] = itemView;
            itemView.setText(mBottomTabAdapter.getItemName(i));
            boolean isZero = (i == 0);
            itemView.setTextColor(mBottomTabAdapter.getTextColor(isZero));
            itemView.setTabIcon(mBottomTabAdapter.getIcon(i));
            itemView.setOnTabClickListener(mTabOnClickListener);
            LinearLayout.LayoutParams lp = (LayoutParams) itemView.getLayoutParams();
            if (lp != null){
                lp.weight = 1;
            }
            addView(itemView);
        }
    }

    private TabItemView.OnTabClickListener mTabOnClickListener = new TabItemView.OnTabClickListener() {
        @Override
        public void onClick(View v) {
            int count = getChildCount();
            if (mTabViews == null || mBottomTabAdapter == null || !mBottomTabAdapter.hasData() || mBottomTabAdapter.getCount() <= 0){
                return;
            }
            for (int i = 0; i < count; i++) {
                boolean isSelected = (v == getChildAt(i));
                TabItemView item = (TabItemView) mTabViews[i];
                if (item != null){
                    item.setTabIcon(mBottomTabAdapter.getIcon(isSelected,i));
                    item.setTextColor(mBottomTabAdapter.getTextColor(isSelected));
                }
            }
        }
    };

    public void onDestory(){
        mTabOnClickListener = null;
        mTabViews = null;
    }


    public void setAdapter(BottomTabAdapter adapter) {
        this.mBottomTabAdapter = adapter;
        init(getContext(),null,0);
    }
}
