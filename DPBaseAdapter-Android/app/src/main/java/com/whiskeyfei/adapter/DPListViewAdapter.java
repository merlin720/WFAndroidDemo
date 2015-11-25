package com.whiskeyfei.adapter;

import android.content.Context;

import com.fei.library.adapter.DPQuickAdapter;
import com.fei.library.adapter.DPViewHolder;
import com.whiskeyfei.R;
import com.whiskeyfei.model.DPItemModel;

import java.util.List;

public class DPListViewAdapter extends DPQuickAdapter<DPItemModel> {
	
	public DPListViewAdapter(Context context, List<DPItemModel> list,int layoutId) {
		super(context, list, layoutId);
	}

	@Override
	public void convert(DPViewHolder holder, DPItemModel model) {
		//绑定监听
		holder.setItemChildClickListener(R.id.listview_delete);
		holder.setItemChildLongClickListener(R.id.listview_delete);
		
		//绑定数据
		holder.setText(R.id.listview_title, model.mItemTitle);
		holder.setText(R.id.listview_content, model.mItemContent);
		holder.setImageResource(R.id.listview_imageView, model.mItemResId);
//		holder.setImageUrl(R.id.imageView, model.mItemIconUrl);
	}

}
