package com.example.wpeventbusdemo;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wpeventbusdemo.bus.MainEventBus;
import com.example.wpeventbusdemo.bus.MainMapEventBus;
import com.example.wpeventbusdemo.observable.map.IEventMapObserver;
import com.example.wpeventbusdemo.observer.IEventObserver;

import de.greenrobot.event.EventBus;

public class ItemListFragment extends ListFragment {
	private static final String eventType_1 = "eventType_1";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		MainEventBus.get().register(mEventObserver);
		MainMapEventBus.get().register(eventType_1, mIEventMapObserver);
	}
	
	private IEventMapObserver mIEventMapObserver = new IEventMapObserver() {
		
		@Override
		public void update() {
			Toast.makeText(getActivity(), "mIEventMapObserver", 1).show();
		}
	};
	
	private IEventObserver mEventObserver = new IEventObserver() {
		
		@Override
		public void update(String eventType) {
			Toast.makeText(getActivity(), eventType, 1).show();
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		MainEventBus.get().unregister(mEventObserver);
		MainMapEventBus.get().register(eventType_1, mIEventMapObserver);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		EventBus.getDefault().post(new ItemListEvent(Item.ITEMS));
	}

	public void onEventMainThread(ItemListEvent event) {
		setListAdapter(new ArrayAdapter<Item>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, event.getItems()));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (position == 0) {
			MainEventBus.get().postEvent("MainEventBus:" + position);
		}else if(position == 1){
			MainMapEventBus.get().postEvent(eventType_1);
		}else {
			EventBus.getDefault().post(getListView().getItemAtPosition(position));
		}
	}
}
