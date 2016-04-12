package com.whiskeyfei.tab.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private int mTabTextSelectColor,mTabtextNormalColor;

    public BottomTabLayout(Context context) {
        this(context,null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTabTextSelectColor = getResources().getColor(R.color.tab_textcolor_selected);
        mTabtextNormalColor = getResources().getColor(R.color.tab_textcolor_normal);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        int len = mTabNames.length;
        mTabViews = new View[len];
        for (int i = 0; i < len; i++) {
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_item, this, false);
            mTabViews[i] = view;
            ImageView imageView = (ImageView)view.findViewById(R.id.tab_icon);
            TextView textView = (TextView)view.findViewById(R.id.tab_text);
            textView.setText(mTabNames[i]);
            imageView.setImageResource(mTabIconRes[i][0]);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 1;
            if (i == 0){
                textView.setTextColor(mTabTextSelectColor);
                imageView.setImageResource(mTabIconRes[i][1]);
            }
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < getChildCount(); i++) {
                        TextView tex  = ((TextView) mTabViews[i].findViewById(R.id.tab_text));
                        ImageView imageView = (ImageView) mTabViews[i].findViewById(R.id.tab_icon);
                        imageView.setImageResource(v == getChildAt(i) ?  mTabIconRes[i][1]: mTabIconRes[i][0]);
                        tex.setTextColor(v == getChildAt(i) ? mTabTextSelectColor:mTabtextNormalColor);
                    }
                }
            });
            addView(view);
        }
    }


}
