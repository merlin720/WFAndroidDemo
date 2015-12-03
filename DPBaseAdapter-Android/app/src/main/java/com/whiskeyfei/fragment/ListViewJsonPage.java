package com.whiskeyfei.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fei.library.config.AppConstants;
import com.fei.library.fragment.DPBaseFragment;
import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;
import com.fei.library.utils.ListUtils;
import com.whiskeyfei.R;
import com.whiskeyfei.adapter.DPListViewAdapter;
import com.whiskeyfei.model.DPItemModel;
import com.whiskeyfei.utils.VolleyUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewJsonPage extends DPBaseFragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,DPOnItemChildViewByIdClickListener,DPOnItemChildViewByIdLongClickListener {
	
	private static final String TAG = "ListViewDemoPage";
	private ListView mListView;
	private List<DPItemModel> mDataList = new ArrayList<DPItemModel>();
	private DPListViewAdapter mDpListViewAdapter;
	private ProgressBar mProgressBar;
	private View mMainView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMainView = inflater.inflate(R.layout.listview_data, null);
		initView();
		initData();
		return mMainView;
	}

	private void initView() {
		mListView = (ListView) mMainView.findViewById(R.id.list_view);
		mProgressBar = (ProgressBar)mMainView.findViewById(R.id.progress_bar);
	}

	private void initData() {
		VolleyUtil.cancelAllQueue(this);
		JsonObjectRequest request = new JsonObjectRequest(AppConstants.BAIDU_IMAGE2, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						onSuccess(response);
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				onFailure();
			}
		});
		VolleyUtil.addToRequestQueue(request,this);
	}

	private void onSuccess(JSONObject response) {
		try {
			if (!response.has("0")) {
				return;
			}
			mDataList.clear();
			for (int i=0; i<10; i++){
				JSONObject result = response.getJSONObject(String.valueOf(i));
				DPItemModel model = new DPItemModel();
				model.setItemTitle(result.getString("time"));
				model.setItemContent(result.getString("description"));
				model.setItemIconUrl(result.getString("time"));
				mDataList.add(model);
			}
			if (!ListUtils.isEmpty(mDataList)){
				initAdapter();
			}else {
				onFailure();
			}
		} catch (Exception e) {
			onFailure();
		}
	}

	private void onFailure() {
		mListView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		Toast.makeText(getActivity(), "no data", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		VolleyUtil.cancelAllQueue(this);
		super.onDestroy();
	}

	private void initAdapter() {
		mDpListViewAdapter = new DPListViewAdapter(getActivity(), mDataList, R.layout.listview_item);
		mDpListViewAdapter.setOnItemChildViewByIdLongClickListener(this);
		mDpListViewAdapter.setOnItemChildViewByIdClickListener(this);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mListView.setAdapter(mDpListViewAdapter);
		mListView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
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
	public boolean onItemChildViewByIdLongClick(ViewGroup viewGroup,View v, int position) {
		if (v.getId() == R.id.listview_delete) {
			Toast.makeText(getActivity(), "长按了删除 " + mDpListViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
            return true;
        }
		return false;
	}

	@Override
	public void onItemChildViewByIdClick(View v, int position) {
		if (v.getId() == R.id.listview_delete) {
			Toast.makeText(getActivity(), "按了删除 " + mDpListViewAdapter.getItem(position).mItemTitle + position,Toast.LENGTH_SHORT).show();
			mDpListViewAdapter.removeItem(position);
        }
	}
	
}
