package com.example.wpeventbusdemo.bus;

import com.example.wpeventbusdemo.observable.EventObservable;
import com.example.wpeventbusdemo.observer.IEventObserver;

public class MainEventBus {
	
	private volatile static MainEventBus mInstanceBus;
	private EventObservable mEventObservable;
	
	private MainEventBus(){
		mEventObservable = new EventObservable();
	}
	
	public static MainEventBus get() {
		if (mInstanceBus == null) {
			synchronized (MainEventBus.class) {
				mInstanceBus = new MainEventBus();
			}
		}
		return mInstanceBus;
	}
	
	public void register(IEventObserver observer){
		mEventObservable.register(observer);
	}

    public void unregister(IEventObserver observer){
    	mEventObservable.register(observer);
    }
    
    public void postEvent(String eventType){
    	mEventObservable.notify(eventType);
    }
}
