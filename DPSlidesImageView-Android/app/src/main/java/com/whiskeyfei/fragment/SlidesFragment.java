package com.whiskeyfei.fragment;

import com.whiskeyfei.R;
import com.whiskeyfei.adapter.SlidesImageAdapter;
import com.whiskeyfei.widget.SlidesImageView;


/**
 * A placeholder fragment containing a simple view.
 */
public class SlidesFragment extends SilidesBaseFragment {

    private SlidesImageView mSlidesImageView;
    private SlidesImageAdapter mAdapter;

    @Override
    protected void initView() {
        mSlidesImageView = (SlidesImageView) mView.findViewById(R.id.slides_imageview);
        mAdapter = new SlidesImageAdapter();
        mSlidesImageView.setAdapter(mAdapter);
        mAdapter.setData(mList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.slides_fragment_main;
    }

        @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter !=null)  mAdapter.stopSwitchImage();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSlidesImageView = null;
        mAdapter = null;
    }
}
