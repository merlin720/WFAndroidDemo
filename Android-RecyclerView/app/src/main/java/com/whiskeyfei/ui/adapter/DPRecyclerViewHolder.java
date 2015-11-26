package com.whiskeyfei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.whiskeyfei.ui.inter.OnItemClickLitener;
import com.whiskeyfei.ui.inter.OnItemLongClinckListener;

/**
 * Created by whiskeyfei on 15-7-15.
 */
public class DPRecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener,View.OnLongClickListener{
    private RecyclerView mRecyclerView;
    private Context mContext;

    private OnItemLongClinckListener mOnItemLongClickLitener;
    private OnItemClickLitener mOnItemClickLitener;
    private OnItemClickLitener mOnItemDeleteClickLitener;
    private DPViewHolder mDPViewHolder;

    public DPRecyclerViewHolder(View v, RecyclerView recyclerView){
        super(v);
        mRecyclerView = recyclerView;
        mContext = mRecyclerView.getContext();
        mDPViewHolder = new DPViewHolder(mRecyclerView,this.itemView,this);
    }

    public DPRecyclerViewHolder(View v, RecyclerView recyclerView, OnItemLongClinckListener onItemLongClinckListener, OnItemClickLitener onItemClickLitener) {
        this(v,recyclerView);
        mOnItemLongClickLitener = onItemLongClinckListener;
        mOnItemClickLitener = onItemClickLitener;
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        mDPViewHolder = new DPViewHolder(mRecyclerView,this.itemView,this);
    }

    public DPViewHolder getDPViewHolder(){
        return mDPViewHolder;
    }

    public void setOnItemDeleteClickLitener(OnItemClickLitener l) {
        this.mOnItemDeleteClickLitener = l;
    }

    public void setOnItemLongClickLitener(OnItemLongClinckListener l) {
        this.mOnItemLongClickLitener = l;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnItemLongClickLitener) {
            return mOnItemLongClickLitener.onItemLongClick(mRecyclerView, v, getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnItemClickLitener) {
            mOnItemClickLitener.onItemClick(v, getAdapterPosition());
        }
    }


}
