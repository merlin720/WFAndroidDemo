package com.example.wpeventbusdemo.observable.map;


public interface IEventMapObservable {
	 /**
     * 注册观察者
     * @param observer
     */
    public void register(String eventType,IEventMapObserver observer);

    /**
     * 反注册观察者
     * @param observer
     */
    public void unregister(String eventType,IEventMapObserver observer);

    /**
     * 通知注册的观察者进行数据或者UI的更新
     */
    public void notify(String eventType);
}
