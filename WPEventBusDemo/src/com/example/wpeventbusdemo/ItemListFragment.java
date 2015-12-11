package com.example.wpeventbusdemo;

import com.example.wpeventbusdemo.bus.MainEventBus;
import com.example.wpeventbusdemo.observer.IEventObserver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.greenrobot.event.EventBus;

public class ItemListFragment extends ListFragment {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		MainEventBus.get().register(mEventObserver);
	}
	
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
		}else {
			EventBus.getDefault().post(getListView().getItemAtPosition(position));
		}
	}
}
