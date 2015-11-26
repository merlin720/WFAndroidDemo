package com.whiskeyfei.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public class DPViewHolder implements OnClickListener,OnLongClickListener {
	
	private final Context mContext;
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private RecyclerView mRecyclerView;
    private DPRecyclerViewHolder mDPRecyclerViewHolder;
//    protected DPOnItemChildLongClickListener mOnItemChildLongClickListener;
//	protected DPOnItemChildClickListener mOnItemChildClickListener;

    public DPViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
    	this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public DPViewHolder(RecyclerView recyclerView, View itemView,DPRecyclerViewHolder d) {
        this.mViews = new SparseArray<View>();
        mRecyclerView = recyclerView;
        this.mConvertView = itemView;
        this.mContext = mRecyclerView.getContext();
        this.mDPRecyclerViewHolder = d;
    }

    public DPRecyclerViewHolder getDPRecyclerViewHolder(){
        return mDPRecyclerViewHolder;
    }


    /**
     * 获取一个DPViewHolder 对象
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static DPViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new DPViewHolder(context, parent, layoutId, position);
        } else {
            DPViewHolder holder = (DPViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public <T extends View> T getViewByid(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition(){
        if (mDPRecyclerViewHolder !=null){
            return mDPRecyclerViewHolder.getAdapterPosition();
        }
        return mPosition;
    }
    
//    public void setOnItemChildLongClickListener(DPOnItemChildLongClickListener l){
//    	mOnItemChildLongClickListener = l;
//    }
//
//    public void setOnItemChildClickListener(DPOnItemChildClickListener l){
//    	mOnItemChildClickListener = l;
//    }
    
    public DPViewHolder setItemChildClickListener(int viewId){
    	View view = getViewByid(viewId);
    	view.setOnClickListener(this);
    	return this;
    }
    
    public DPViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener){
    	View view = getViewByid(viewId);
    	view.setOnTouchListener(listener);
    	return this;
    }
    
    public DPViewHolder setItemChildLongClickListener(int viewId){
    	View view = getViewByid(viewId);
    	view.setOnLongClickListener(this);
    	return this;
    }
    
    public DPViewHolder setVisible(int viewId,boolean visible){
    	View view = getViewByid(viewId);
    	view.setVisibility(visible? View.VISIBLE:View.INVISIBLE);
    	return this;
    }
    
    public DPViewHolder setText(int viewId,String text){
        TextView tv = getViewByid(viewId);
        tv.setText(text);
        return this;
    }

    public DPViewHolder setImageResource(int viewId,int resId){
        ImageView icon = getViewByid(viewId);
        icon.setImageResource(resId);
        return this;
    }

    public DPViewHolder setImageBitmap(int viewId,Bitmap bitmap){
        ImageView icon = getViewByid(viewId);
        icon.setImageBitmap(bitmap);
        return this;
    }
    
    public DPViewHolder setImageDrawable(int viewId,Drawable drawable){
        ImageView icon = getViewByid(viewId);
        icon.setImageDrawable(drawable);
        return this;
    }

    public DPViewHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = getViewByid(viewId);
        return this;
    }

	@Override
	public void onClick(View v) {
//		if (mOnItemChildClickListener != null) {
//			mOnItemChildClickListener.onItemChildClick(v, this.getPosition());
//		}
	}

	@Override
	public boolean onLongClick(View v) {
//		if (mOnItemChildLongClickListener != null) {
//			mOnItemChildLongClickListener.onItemChildLongClick(v, this.getPosition());
//			return true;
//		}
		return false;
	}
    
}