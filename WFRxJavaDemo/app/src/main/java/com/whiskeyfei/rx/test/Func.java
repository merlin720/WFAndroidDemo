package com.whiskeyfei.rx.test;

/**
 * Created by whiskeyfei on 16-1-8.
 */
public interface Func<T, R> {
    R call(T t);
}
