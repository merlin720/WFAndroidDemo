package com.whiskeyfei.tab.adapter;

import com.whiskeyfei.tab.R;
import com.whiskeyfei.tab.utils.ResourceUtil;

/**
 * Created by whiskeyfei on 16/4/13.
 */
public class BottomTabAdapter {
    private static final String TAG = "BottomTabAdapter";

    private String mTabNames[];
    private int mTabIconRes[][];

    private int mTabTextSelectColor, mTabTextNormalColor;

    public BottomTabAdapter(){
        mTabTextSelectColor = ResourceUtil.getColor(R.color.tab_textcolor_selected);
        mTabTextNormalColor = ResourceUtil.getColor(R.color.tab_textcolor_normal);
    }

    public void setData(String names[],int icons[][]){
        mTabNames = names;
        mTabIconRes = icons;
    }

    public String getItemName(int position){
        return (mTabNames != null) ? mTabNames[position] : "";
    }

    public boolean hasData(){
        return mTabIconRes != null;
    }

    public int getIcon(int position){
        boolean isZero = (position == 0);
        return isZero ? mTabIconRes[position][1] : mTabIconRes[position][0];
    }

    public int getIcon(boolean selected,int position){
        return selected ? mTabIconRes[position][1] : mTabIconRes[position][0];
    }

    public int getTextColor(int position){
        boolean isZero = (position == 0);
        return isZero ? mTabTextSelectColor :mTabTextNormalColor;
    }

    public int getCount(){
        return (mTabNames != null) ? mTabNames.length : 0;
    }

    public int getTextColor(boolean isSelected) {
        return isSelected ? mTabTextSelectColor : mTabTextNormalColor;
    }
}
