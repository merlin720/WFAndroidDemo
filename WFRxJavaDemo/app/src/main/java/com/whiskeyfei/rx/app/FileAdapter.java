package com.whiskeyfei.rx.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whiskeyfei.rx.R;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-15.
 */
public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    Context mContext;
    private List<String> mData;

    public FileAdapter(Context context) {
        mContext = context;
    }

    public void setDate(List<String> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public FileAdapter.FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.file_item, null);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileAdapter.FileViewHolder holder, int position) {
        holder.mTitle.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class FileViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;

        public FileViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_name);

        }
    }
}
