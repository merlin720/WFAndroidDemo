package com.example.wpeventbusdemo.observable.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体得被观察者实现，使用map存储 IEventMapObserver
 * 
 * @author whiskeyfei
 * 
 */
public class EventMapObservable implements IEventMapObservable {
	Map<String, List<IEventMapObserver>> mEventMap = new HashMap<String, List<IEventMapObserver>>();

	@Override
	public void register(String eventType, IEventMapObserver observer) {
		synchronized (this) {
			if (eventType != null) {
				List<IEventMapObserver> eventObservers = mEventMap.get(eventType);
				if (eventObservers != null) {
					eventObservers.add(observer);
				} else {
					eventObservers = new ArrayList<IEventMapObserver>();
					eventObservers.add(observer);
					mEventMap.put(eventType, eventObservers);
				}
			}
		}
	}

	@Override
	public void unregister(String eventType, IEventMapObserver observer) {
		synchronized (this) {
			if (eventType != null) {
				List<IEventMapObserver> eventObservers = mEventMap.get(eventType);
				if (eventObservers != null) {
					eventObservers.remove(observer);
				}
			}
		}
	}

	@Override
	public void notify(String eventType) {
		synchronized (this) {
			if (eventType != null) {
				List<IEventMapObserver> eventObservers = mEventMap.get(eventType);
				if (eventObservers != null) {
					for (IEventMapObserver observer : eventObservers) {
						observer.update();
					}
				}
			}
		}
	}

}
