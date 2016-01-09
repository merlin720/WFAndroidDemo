package com.whiskeyfei.rx.test;

/**
 * Created by whiskeyfei on 16-1-8.
 */
public abstract class AsyncJob2<T> {
    public abstract void start(Callback<T> callback);

    public <R> AsyncJob2<R> map(final Func<T, R> func){
        final AsyncJob2<T> source = this;
        return new AsyncJob2<R>(){

            @Override
            public void start(final Callback<R> callback) {
                source.start(new Callback<T>() {
                    @Override
                    public void onResult(T result) {
                        R mapped = func.call(result);
                        callback.onResult(mapped);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
}
