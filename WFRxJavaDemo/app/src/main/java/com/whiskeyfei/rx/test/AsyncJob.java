package com.whiskeyfei.rx.test;

/**
 * Created by whiskeyfei on 16-1-8.
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);
}
