package com.example.wpeventbusdemo.observable;

import com.example.wpeventbusdemo.observer.IEventObserver;

public interface IEventObservable {
	 /**
     * 注册观察者
     * @param observer
     */
    public void register(IEventObserver observer);

    /**
     * 反注册观察者
     * @param observer
     */
    public void unregister(IEventObserver observer);

    /**
     * 通知注册的观察者进行数据或者UI的更新
     */
    public void notify(String eventType);
}
