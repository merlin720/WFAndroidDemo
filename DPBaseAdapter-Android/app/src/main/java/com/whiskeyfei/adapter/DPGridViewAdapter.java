package com.whiskeyfei.adapter;

import android.content.Context;

import com.fei.library.adapter.DPQuickAdapter;
import com.fei.library.adapter.DPViewHolder;
import com.whiskeyfei.R;
import com.whiskeyfei.model.DPItemModel;

import java.util.List;

public class DPGridViewAdapter extends DPQuickAdapter<DPItemModel> {
	
	
	public DPGridViewAdapter(Context context, List<DPItemModel> list,int layoutId) {
		super(context, list, layoutId);
	}

	@Override
	public void convert(DPViewHolder holder, DPItemModel model) {
		//绑定监听
		holder.setItemChildClickListener(R.id.gridview_delete);
		holder.setItemChildLongClickListener(R.id.gridview_delete);
		
		//绑定数据
		holder.setText(R.id.gridview_title, model.mItemTitle);
		holder.setImageResource(R.id.gridview_imageView, model.mItemResId);
	}

}
