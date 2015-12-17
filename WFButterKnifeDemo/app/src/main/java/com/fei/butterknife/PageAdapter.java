package com.fei.butterknife;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 15-12-16.
 */
public class PageAdapter extends RecyclerView.Adapter<PageViewHolder>{

    private List<String> mList;

    public PageAdapter(){
        mList = new ArrayList<>();
    }

    public void setList(List<String> list){
        mList = list;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new PageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        String str = mList.get(position);
        holder.nameTextView.setText(str);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
