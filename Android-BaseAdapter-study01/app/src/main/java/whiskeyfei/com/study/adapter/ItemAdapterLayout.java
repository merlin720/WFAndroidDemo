package whiskeyfei.com.study.adapter;

import android.content.Context;

import java.util.List;

import whiskeyfei.com.study.R;
import whiskeyfei.com.study.model.ItemModel;
import whiskeyfei.com.study.utils.ViewHolder;

/**
 * Created by whiskeyfei on 15-7-9.
 * 添加layoutId 可以多个ListView使用一个adapter
 */
public class ItemAdapterLayout extends CommonAdapterLayout<ItemModel> {


    public ItemAdapterLayout(List<ItemModel> list, Context context, int layoutId) {
        super(context,list,layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ItemModel model) {
        holder.setText(R.id.title,model.mItemTitle);
        holder.setText(R.id.content,model.mItemContent);
        holder.setImageResource(R.id.imageView,model.mItemResId);
    }
}
