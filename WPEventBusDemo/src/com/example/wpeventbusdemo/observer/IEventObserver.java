package com.example.wpeventbusdemo.observer;

/**
 * 观察者接口
 * @author whiskeyfei
 */

public interface IEventObserver {
	/**
	 * 根据eventType刷新
	 * @param eventType
	 */
	 public void update(String eventType);
}
