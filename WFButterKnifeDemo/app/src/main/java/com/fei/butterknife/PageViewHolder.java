package com.fei.butterknife;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by whiskeyfei on 15-12-16.
 */
public class PageViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text_name)
    TextView nameTextView;

    public PageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}