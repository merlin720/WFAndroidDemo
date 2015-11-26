package whiskeyfei.com.study.model;

/**
 * Created by whiskeyfei on 15-7-9.
 */
public class ItemModel {
    public int mItemResId;
    public String mItemTitle;
    public String mItemContent;

    public ItemModel(int resId, String title, String content) {
        mItemResId = resId;
        mItemTitle = title;
        mItemContent = content;
    }

}
