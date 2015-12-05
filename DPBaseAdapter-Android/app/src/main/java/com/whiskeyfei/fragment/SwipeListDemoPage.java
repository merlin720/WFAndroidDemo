package com.whiskeyfei.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;
import com.whiskeyfei.R;
import com.whiskeyfei.adapter.DPSwipViewAdapter;
import com.whiskeyfei.model.DPItemModel;
import com.whiskeyfei.utils.ApiConstant;

/**
 * Created by whiskeyfei on 15-12-5.
 */
public class SwipeListDemoPage extends PageFragment<DPItemModel>  implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,DPOnItemChildViewByIdClickListener,DPOnItemChildViewByIdLongClickListener {
    public static final int FRAGMENT_FLAG = ApiConstant.FRAGMENT_FLAG_6;
    private static final String TAG = "SwipeListDemoPage";

    private ListView mListView;
    private static final String IMAGEURL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png";
    private DPSwipViewAdapter mDpListViewAdapter;

    @Override
    public void initView() {
        mListView = (ListView) mMainView.findViewById(R.id.list_view);
        mDpListViewAdapter = new DPSwipViewAdapter(getActivity(), mDataList, R.layout.swipe_listview_item);
        mDpListViewAdapter.setOnItemChildViewByIdClickListener(this);
        mDpListViewAdapter.setOnItemChildViewByIdLongClickListener(this);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                    mDpListViewAdapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        mListView.setAdapter(mDpListViewAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.listview;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 30; i++) {
            DPItemModel model = new DPItemModel();
            model.setItemResId(R.mipmap.icon);
            model.setItemTitle("title" + i);
            model.setItemContent("content" + i);
            model.setItemIconUrl(IMAGEURL);
            mDataList.add(model);
        }
    }

    @Override
    public void onItemChildViewByIdClick(View view, int position) {
        if (view.getId() == R.id.tv_item_bgaswipe_delete) {
            Toast.makeText(getActivity(), "按了删除 " + position,Toast.LENGTH_SHORT).show();
            mDpListViewAdapter.closeOpenedSwipeItemLayout();
            mDpListViewAdapter.removeItem(position);
        }
    }

    @Override
    public boolean onItemChildViewByIdLongClick(ViewGroup viewGroup, View view, int position) {
        if (view.getId() == R.id.tv_item_bgaswipe_delete) {
            Toast.makeText(getActivity(), "长按了删除 "  + position,Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "点击" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "长按" + position,Toast.LENGTH_SHORT).show();
        return false;
    }
}
