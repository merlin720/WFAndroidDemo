package whiskeyfei.com.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import whiskeyfei.com.study.R;
import whiskeyfei.com.study.model.ItemModel;
import whiskeyfei.com.study.utils.ViewHolder;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public class ItemAdapter extends BaseAdapter {

    private List<ItemModel> mList;
    private Context mContext;
    private LayoutInflater mLayoutInfalter;

    public ItemAdapter(List<ItemModel> list, Context context) {
        mContext = context;
        mList = list;
        mLayoutInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item, position);
        ItemModel model = mList.get(position);
        ImageView icon = holder.getView(R.id.imageView);
        icon.setImageResource(model.mItemResId);

        TextView title = holder.getView(R.id.title);
        title.setText(model.mItemTitle);

        TextView content = holder.getView(R.id.content);
        content.setText(model.mItemContent);

        return holder.getConvertView();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
