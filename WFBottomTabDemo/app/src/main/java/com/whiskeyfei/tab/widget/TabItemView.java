package com.whiskeyfei.tab.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whiskeyfei.tab.R;

/**
 * Created by whiskeyfei on 16/4/12.
 */
public class TabItemView extends LinearLayout {
    private View mView;
    private ImageView mImageView;
    private TextView mTitle;
    private Context mContext;

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mView = LayoutInflater.from(getContext()).inflate(R.layout.tab_item, null);
        mImageView = (ImageView) mView.findViewById(R.id.tab_icon);
        mTitle = (TextView) mView.findViewById(R.id.tab_text);
//        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        setOrientation(LinearLayout.VERTICAL);
//        lp.gravity = Gravity.CENTER;
//        addView(view, lp);
        addView(mView);
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return mView.getLayoutParams();
    }

    public void setText(String text) {
        mTitle.setText(text);
    }

    public void setTabIcon(int tabIcon) {
        mImageView.setImageResource(tabIcon);
    }

    public void setTextColor(int textColor) {
        mTitle.setTextColor(textColor);
    }

    public void setOnTabClickListener(final OnTabClickListener listener){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener !=null)
                listener.onClick(v);
            }
        });
    }

    public interface OnTabClickListener{
        void onClick(View v);
    }
}
