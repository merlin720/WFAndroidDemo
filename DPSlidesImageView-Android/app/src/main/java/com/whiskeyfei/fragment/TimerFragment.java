package com.whiskeyfei.fragment;

import android.view.View;
import android.widget.Button;

import com.whiskeyfei.R;
import com.whiskeyfei.adapter.TimerImageAdapter;
import com.whiskeyfei.widget.TimerImageView;


public class TimerFragment extends SilidesBaseFragment implements TimerImageView.IImageStateEvent {

    private TimerImageView mTimerImageView;
    private TimerImageAdapter mTimerImageAdapter;
    private Button mButton;
    private boolean mState = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mTimerImageView = (TimerImageView) mView.findViewById(R.id.timerimageview);
        mButton = (Button) mView.findViewById(R.id.start_timer);
        mButton.setText("start");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState) {
                    mTimerImageAdapter.startTimer();
                } else {
                    mTimerImageAdapter.stopSwitchImage();
                }
            }
        });
        mTimerImageAdapter = new TimerImageAdapter();
        mTimerImageView.setImageStateEvent(this);
        mTimerImageView.setAdapter(mTimerImageAdapter);
        mTimerImageAdapter.setData(mList);
    }

    @Override
    public boolean updateState(final boolean state) {
        mState = state;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (state) {
                    mButton.setText("start");
                } else {
                    mButton.setText("stop");
                }
            }
        });
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimerImageAdapter != null)
            mTimerImageAdapter.stopSwitchImage();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimerImageView = null;
        if (mTimerImageAdapter != null){
            mTimerImageAdapter = null;
        }
    }

}
