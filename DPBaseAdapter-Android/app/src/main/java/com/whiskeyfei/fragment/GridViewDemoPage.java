
package com.whiskeyfei.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.fei.library.config.AppConstants;
import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;
import com.whiskeyfei.R;
import com.whiskeyfei.adapter.DPGridViewAdapter;
import com.whiskeyfei.model.DPItemModel;
import com.whiskeyfei.utils.ApiConstant;

public class GridViewDemoPage extends PageFragment<DPItemModel> implements OnItemClickListener,OnItemLongClickListener,DPOnItemChildViewByIdClickListener,DPOnItemChildViewByIdLongClickListener {
	public static final int FRAGMENT_FLAG = ApiConstant.FRAGMENT_FLAG_2;
	private GridView mGridView;
	private DPGridViewAdapter mDPGridViewAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.gridview;
	}

	@Override
	protected void initData() {
		for (int i = 0; i < DATA_SIZE; i++) {
			DPItemModel model = new DPItemModel();
			model.setItemResId(R.drawable.ic_launcher);
			model.setItemTitle("title" + i);
			model.setItemContent("content" + i);
			model.setItemIconUrl(AppConstants.BAIDU_IMAGE2);
			mDataList.add(model);
		}
	}

	@Override
	public void initView() {
		mGridView = (GridView)mMainView.findViewById(R.id.gridview);
		mGridView.setNumColumns(3);
		mDPGridViewAdapter = new DPGridViewAdapter(getActivity(), mDataList, R.layout.gridview_item);
		mGridView.setOnItemClickListener(this);
		mGridView.setOnItemLongClickListener(this);
		mDPGridViewAdapter.setOnItemChildViewByIdClickListener(this);
		mDPGridViewAdapter.setOnItemChildViewByIdLongClickListener(this);
		mGridView.setAdapter(mDPGridViewAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		Toast.makeText(getActivity(), "点击" + position,Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
		Toast.makeText(getActivity(), "长按" + position,Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onItemChildViewByIdClick(View v, int position) {
		if (v.getId() == R.id.gridview_delete) {
			Toast.makeText(getActivity(), "按了删除 " + mDPGridViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
			mDPGridViewAdapter.removeItem(position);
        }
	}

	@Override
	public boolean onItemChildViewByIdLongClick(ViewGroup viewGroup, View v, int position) {
		if (v.getId() == R.id.gridview_delete) {
			Toast.makeText(getActivity(), "长按了删除 " + mDPGridViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
            return true;
        }
		return false;
	}
}
