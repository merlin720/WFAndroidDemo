package whiskeyfei.com.dpservicedemo.service;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.greenrobot.event.EventBus;
import whiskeyfei.com.dpservicedemo.R;

/**
 * Created by whiskeyfei on 15-12-10.
 */
public class TestFragment extends Fragment implements View.OnClickListener {
    private Button mStopButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mStopButton = (Button) rootView.findViewById(R.id.stop_service);
        mStopButton.setOnClickListener(this);
        return rootView;
    }

    public void onEventMainThread(FirstEvent item) {
        mStopButton.setText(item.getTime());
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.stop_service) {
//            BootService.stopService();
//        }
    }
}
