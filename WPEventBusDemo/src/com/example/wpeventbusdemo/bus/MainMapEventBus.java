package com.example.wpeventbusdemo.bus;

import com.example.wpeventbusdemo.observable.map.EventMapObservable;
import com.example.wpeventbusdemo.observable.map.IEventMapObserver;

public class MainMapEventBus {
	
	private volatile static MainMapEventBus mInstanceBus;
	private EventMapObservable mEventObservable;
	
	private MainMapEventBus(){
		mEventObservable = new EventMapObservable();
	}
	
	public static MainMapEventBus get() {
		if (mInstanceBus == null) {
			synchronized (MainMapEventBus.class) {
				mInstanceBus = new MainMapEventBus();
			}
		}
		return mInstanceBus;
	}
	
	public void register(String eventType,IEventMapObserver observer){
		mEventObservable.register(eventType,observer);
	}

    public void unregister(String eventType,IEventMapObserver observer){
    	mEventObservable.register(eventType,observer);
    }
    
    public void postEvent(String eventType){
    	mEventObservable.notify(eventType);
    }
}
