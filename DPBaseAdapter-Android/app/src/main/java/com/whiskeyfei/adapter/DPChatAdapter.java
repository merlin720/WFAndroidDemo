package com.whiskeyfei.adapter;

import android.content.Context;

import com.fei.library.adapter.DPQuickAdapter;
import com.fei.library.adapter.DPViewHolder;
import com.fei.library.inter.DPMultiItemTypeSupport;
import com.whiskeyfei.R;
import com.whiskeyfei.model.ChatMessage;

import java.util.List;

public class DPChatAdapter extends DPQuickAdapter<ChatMessage> {

    public DPChatAdapter(Context context, List<ChatMessage> list, DPMultiItemTypeSupport multiItemSupport) {
        super(context, list, multiItemSupport);
    }

    @Override
    public void convert(DPViewHolder holder, ChatMessage model){
        switch (holder.mLayoutId) {
            case R.layout.listview_chat_from_msg:
                holder.setText(R.id.chat_from_content, model.getContent());
                holder.setText(R.id.chat_from_name, model.getName());
                holder.setImageResource(R.id.chat_from_icon, model.getIcon());
                holder.setItemChildClickListener(R.id.chat_from_content);
                holder.setItemChildLongClickListener(R.id.chat_from_content);
                break;
            case R.layout.listview_chat_send_msg:
                holder.setText(R.id.chat_send_content, model.getContent());
                holder.setText(R.id.chat_send_name, model.getName());
                holder.setImageResource(R.id.chat_send_icon, model.getIcon());
                holder.setItemChildClickListener(R.id.chat_send_content);
                holder.setItemChildLongClickListener(R.id.chat_send_content);
                break;
        }
    }

}
