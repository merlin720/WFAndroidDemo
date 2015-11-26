package whiskeyfei.com.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import whiskeyfei.com.study.utils.ViewHolder;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public abstract class CommonAdapterLayout<T> extends BaseAdapter {
    protected List<T> mList;
    protected Context mContext;
    protected int mLayoutId;

    public CommonAdapterLayout(Context context, List<T> list, int layoutId) {
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId, position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T t);
}
