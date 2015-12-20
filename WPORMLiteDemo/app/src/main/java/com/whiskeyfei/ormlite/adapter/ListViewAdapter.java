package com.whiskeyfei.ormlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whiskeyfei.ormlite.R;
import com.whiskeyfei.ormlite.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 15-12-20.
 */
public class ListViewAdapter extends BaseAdapter {

    private List<Student> mList;
    private Context mContext;

    public ListViewAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public void setList(List<Student> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        }
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Student s = mList.get(position);
        holder.tv.setText(s.getName());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tv;
    }
}
