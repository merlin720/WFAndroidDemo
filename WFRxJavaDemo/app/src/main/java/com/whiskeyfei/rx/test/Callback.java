package com.whiskeyfei.rx.test;

/**
 * Created by whiskeyfei on 16-1-8.
 */
public interface Callback<T> {
    void onResult(T result);
    void onError(Exception e);
}
