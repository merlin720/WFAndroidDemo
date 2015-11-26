package whiskeyfei.com.study.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import whiskeyfei.com.study.R;
import whiskeyfei.com.study.model.ItemModel;
import whiskeyfei.com.study.utils.ViewHolder;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public class ItemAdapterGetView extends CommonAdapter<ItemModel> {


    public ItemAdapterGetView(Context context,List<ItemModel> list) {
        super(context, list);
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item, position);
//        ItemModel model = mList.get(position);
//        ImageView icon = holder.getView(R.id.imageView);
//        icon.setImageResource(model.mItemResId);
//
//        TextView title = holder.getView(R.id.title);
//        title.setText(model.mItemTitle);
//
//        TextView content = holder.getView(R.id.content);
//        content.setText(model.mItemContent);
//
//        return holder.getConvertView();
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item, position);
        ItemModel model = mList.get(position);

        holder.setText(R.id.title,model.mItemTitle);
        holder.setText(R.id.content,model.mItemContent);

        holder.setImageResource(R.id.imageView,model.mItemResId);

        return holder.getConvertView();
    }



}
