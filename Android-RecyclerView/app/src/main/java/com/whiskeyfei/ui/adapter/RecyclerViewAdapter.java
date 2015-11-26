//package com.whiskeyfei.ui.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.whiskeyfei.ui.R;
//
//import java.util.List;
//
///**
// * Created by whiskeyfei on 15-7-14.
// */
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DPRecyclerViewHolder> {
//    private Context mContext;
//    private List<String> mList;
//    private LayoutInflater mLayoutInflater;
//
//    private OnItemClickLitener mOnItemClickLitener;
//    private OnItemClickLitener mOnItemDeleteClickLitener;
//
//    public void setOnItemDeleteClickLitener(OnItemClickLitener l) {
//        this.mOnItemDeleteClickLitener = l;
//    }
//    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }
//
//    public RecyclerViewAdapter(Context context,List<String> list) {
//        mList = list;
//        mContext = context;
//        mLayoutInflater = LayoutInflater.from(context);
//    }
//
//    /**
//     * 创建DPRecyclerViewHolder
//     * DPRecyclerViewHolder作为缓存的单位了
//     * @param viewGroup
//     * @param position
//     * @return
//     */
//    @Override
//    public DPRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
//        View view = mLayoutInflater.inflate(R.layout.listview_item,null);
//        DPRecyclerViewHolder holder = new DPRecyclerViewHolder(view);
//        holder.mImageView = (ImageView)view.findViewById(R.id.listview_imageView);
//        holder.mTitle = (TextView)view.findViewById(R.id.listview_title);
//        holder.mContent = (TextView)view.findViewById(R.id.listview_content);
//        holder.mDelete = (TextView)view.findViewById(R.id.listview_delete);
//        return holder;
//    }
//
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
//
//    public static class DPRecyclerViewHolder extends RecyclerView.ViewHolder {
//        public TextView mTitle;
//        public TextView mContent;
//        public ImageView mImageView;
//        public TextView mDelete;
//
//        public DPRecyclerViewHolder(View v) {
//            super(v);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
//
//}
//
//
//
//
//
