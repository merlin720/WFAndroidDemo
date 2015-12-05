package com.whiskeyfei.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fei.library.adapter.DPBaseAdapter;
import com.fei.library.adapter.DPViewHolder;
import com.fei.library.layout.BGASwipeItemLayout;
import com.whiskeyfei.R;
import com.whiskeyfei.model.DPItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 15-12-5.
 */
public class DPSwipViewAdapter extends DPBaseAdapter<DPItemModel> {

    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<BGASwipeItemLayout>();

    public DPSwipViewAdapter(Context context, List<DPItemModel> list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void convert(DPViewHolder holder, DPItemModel model) {
        BGASwipeItemLayout swipeItemLayout = holder.getViewByid(R.id.sil_item_bgaswipe_root);
        swipeItemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
            @Override
            public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        holder.setText(R.id.tv_item_bgaswipe_title, model.mItemTitle);
        holder.setText(R.id.tv_item_bgaswipe_detail, model.mItemContent);
        holder.setImageResource(R.id.listview_imageView, model.mItemResId);
        holder.setItemChildClickListener(R.id.tv_item_bgaswipe_delete);
        holder.setItemChildLongClickListener(R.id.tv_item_bgaswipe_delete);
    }

    @Override
    public DPViewHolder getDPViewHolder(int position, View convertView, ViewGroup parent) {
        return DPViewHolder.get(mContext, convertView, parent, mLayoutId, position);
    }

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public void closeOpenedSwipeItemLayout() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.close();
        }
        mOpenedSil.clear();
    }
}
