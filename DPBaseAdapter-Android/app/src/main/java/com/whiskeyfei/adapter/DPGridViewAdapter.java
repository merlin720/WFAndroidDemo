package com.whiskeyfei.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.fei.library.adapter.DPQuickAdapter;
import com.fei.library.adapter.DPViewHolder;
import com.whiskeyfei.R;
import com.whiskeyfei.model.DPItemModel;
import com.whiskeyfei.utils.VolleyUtil;

import java.util.List;

public class DPGridViewAdapter extends DPQuickAdapter<DPItemModel> {
	
	
	public DPGridViewAdapter(Context context, List<DPItemModel> list,int layoutId) {
		super(context, list, layoutId);
	}

	@Override
	public void convert(final DPViewHolder holder, final DPItemModel model) {
		//绑定监听
		holder.setItemChildClickListener(R.id.gridview_delete);
		holder.setItemChildLongClickListener(R.id.gridview_delete);
		
		//绑定数据
		holder.setText(R.id.gridview_title, model.mItemTitle);
		holder.setImageResource(R.id.gridview_imageView, model.mItemResId);
		VolleyUtil.cancelAllQueue(model);
		ImageRequest request=new ImageRequest(model.getItemIconUrl(), new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap) {
				holder.setImageBitmap(R.id.gridview_imageView,bitmap);
			}
		}, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.e("","argo:"+arg0.getMessage());
				holder.setImageResource(R.id.gridview_imageView,R.mipmap.ic_launcher);
			}
		});
		VolleyUtil.addToRequestQueue(request, model);
	}

}
