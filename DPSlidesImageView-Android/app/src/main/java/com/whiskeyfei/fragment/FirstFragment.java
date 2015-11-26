package com.whiskeyfei.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.whiskeyfei.model.ImageModel;
import com.whiskeyfei.R;
import com.whiskeyfei.adapter.DPGridViewAdapter;
import com.whiskeyfei.ui.MeiTuanActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by whiskeyfei on 15-7-21.
 */
public class FirstFragment extends Fragment {

    private int mImageResId;
    private String mIndex;
    private GridView mGridView;
    DPGridViewAdapter mDPGridViewAdapter;
    private List<ImageModel> mDataList = new ArrayList<ImageModel>();

    public FirstFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mImageResId = arguments.getInt(MeiTuanActivity.KEY_IMAGE);
            mIndex = arguments.getString(MeiTuanActivity.KEY_INDEX);
        }
        initData();

    }

    private void initData() {
        for (int i = 0; i < 8; i++) {
            ImageModel model = new ImageModel();
            model.setImageId(R.drawable.image01);
            model.setContent("Title" + i);
            mDataList.add(model);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gridview, container, false);
        mGridView = (GridView)view.findViewById(R.id.gridview);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"index"+position,Toast.LENGTH_SHORT).show();
            }
        });
        mGridView.setNumColumns(4);
        mDPGridViewAdapter = new DPGridViewAdapter(getActivity(), mDataList, R.layout.gridview_item);
        mGridView.setAdapter(mDPGridViewAdapter);
        return view;
    }
}
