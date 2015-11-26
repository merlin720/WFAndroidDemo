package com.whiskeyfei.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.whiskeyfei.ui.R;
import com.whiskeyfei.ui.model.ItemModel;

import java.util.List;

/**
 * Created by whiskeyfei on 15-8-27.
 */
public class FirstAdapter extends DPBaseRecyclerViewAdapter<ItemModel>{

    public FirstAdapter(RecyclerView recyclerView,List<ItemModel> list){
        super(recyclerView,list ,R.layout.listview_item);
    }

    @Override
    protected void fillData(DPViewHolder viewHolder, int position, ItemModel model) {
        viewHolder.setText(R.id.listview_title,model.mTitle);
        viewHolder.setText(R.id.listview_content, model.mContent);
        viewHolder.setImageResource(R.id.listview_imageView,model.mRsid);
    }

    @Override
    protected void setListener(DPViewHolder viewHolder) {
//        viewHolder.setItemChildClickListener()
    }
}
