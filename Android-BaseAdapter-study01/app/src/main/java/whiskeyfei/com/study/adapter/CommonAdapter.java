package whiskeyfei.com.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    protected Context mContext;
    protected LayoutInflater mLayoutInfalter;

    public CommonAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
        mLayoutInfalter = LayoutInflater.from(mContext);
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
    public Object getItem(int position) {
        return mList.get(position);
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);
}
