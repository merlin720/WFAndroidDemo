package com.whiskeyfei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whiskeyfei.ui.inter.OnItemClickLitener;

import java.util.List;

/**
 * Created by whiskeyfei on 15-7-14.
 */
public abstract  class DPBaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<DPRecyclerViewHolder> {
    private Context mContext;
    private List<T> mList;
    protected LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private int mLayoutId;

//    public interface OnItemClickLitener {
//        void onItemClick(View view, int position);
//    }
//
//    public interface OnItemLongClinckListener{
//        boolean onItemLongClick(ViewGroup parent, View itemView, int position);
//    }

    private OnItemClickLitener mOnItemClickLitener;
    private OnItemClickLitener mOnItemDeleteClickLitener;

    public void setOnItemDeleteClickLitener(OnItemClickLitener l) {
        this.mOnItemDeleteClickLitener = l;
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public DPBaseRecyclerViewAdapter(RecyclerView recyclerView, List<T> list,int layoutId) {
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        mList = list;
        mLayoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建DPRecyclerViewHolder
     * DPRecyclerViewHolder作为缓存的单位了
     * @param viewGroup
     * @param position
     * @return
     */
    @Override
    public DPRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = mLayoutInflater.inflate(mLayoutId,viewGroup,false);
        DPRecyclerViewHolder holder = new DPRecyclerViewHolder(view,mRecyclerView);
//        holder.getDPViewHolder().setItemChildClickListener()
        setListener(holder.getDPViewHolder());
        return holder;
    }

//    /**
//     *  设置结果
//     * @param viewHolder
//     * @param position
//     */
//    @Override
//    public void onBindViewHolder(final DPRecyclerViewHolder viewHolder, final int position) {
//        viewHolder.mImageView.setImageResource(R.mipmap.ic_launcher);
//        viewHolder.mContent.setText(mList.get(position));
//        viewHolder.mTitle.setText(mList.get(position));
//        //如果设置了回调，则设置点击事件
//        if (mOnItemClickLitener != null) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
//                }
//            });
//        }
//            if (mOnItemDeleteClickLitener != null){
//                viewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mOnItemDeleteClickLitener.onItemClick(viewHolder.itemView,position);
//                    }
//                });
//            }
//
//    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(DPRecyclerViewHolder holder, int position) {
        fillData(holder.getDPViewHolder(),position,getItem(position));
    }

    private T getItem(int position) {
        return mList.get(position);
    }

    protected abstract void fillData(DPViewHolder viewHolder, int position, T model);
    protected abstract void setListener(DPViewHolder viewHolder);
}





