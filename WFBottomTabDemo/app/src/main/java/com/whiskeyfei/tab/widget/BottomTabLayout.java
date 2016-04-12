package com.whiskeyfei.tab.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.whiskeyfei.tab.R;

/**
 * Created by whiskeyfei on 16/4/11.
 */
public class BottomTabLayout extends LinearLayout {
    private static final String TAG = "BottomTabLayout";

    private String mTabNames[] = {"Chats", "Contacts", "Discover", "Me"};
    private int mTabIconRes[][] = {
            {R.drawable.icon_chats_normal, R.drawable.icon_chats_selected},
            {R.drawable.icon_contacts_normal, R.drawable.icon_contacts_selected},
            {R.drawable.icon_discover_normal, R.drawable.icon_discover_selected},
            {R.drawable.icon_me_normal, R.drawable.icon_me_selected}
    };

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
        init(context, attrs, defStyleAttr);
    }

    private void init(){
        mTabTextSelectColor = getResources().getColor(R.color.tab_textcolor_selected);
        mTabTextNormalColor = getResources().getColor(R.color.tab_textcolor_normal);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        int len = mTabNames.length;
        mTabViews = new View[len];
        for (int i = 0; i < len; i++) {
           final TabItemView itemView = new TabItemView(context);
            mTabViews[i] = itemView;
            itemView.setText(mTabNames[i]);
            boolean isZero = (i == 0);
            itemView.setTextColor(isZero ? mTabTextSelectColor :mTabTextNormalColor);
            itemView.setTabIcon(isZero ? mTabIconRes[i][1] : mTabIconRes[i][0]);
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
            if (mTabViews == null || mTabIconRes == null || count <= 0){
                return;
            }
            for (int i = 0; i < count; i++) {
                boolean isSelected = (v == getChildAt(i));
                TabItemView item = (TabItemView) mTabViews[i];
                if (item != null){
                    item.setTabIcon(isSelected ?  mTabIconRes[i][1]: mTabIconRes[i][0]);
                    item.setTextColor(isSelected ? mTabTextSelectColor: mTabTextNormalColor);
                }
            }
        }
    };

    public void onDestory(){
        mTabNames = null;
        mTabIconRes = null;
        mTabOnClickListener = null;
        mTabViews = null;
    }


}
