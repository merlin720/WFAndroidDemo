package com.whiskeyfei.rx.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 16-1-8.
 */
public class ApiWrapper3 {
    Api api = new Api() {
        @Override
        public void queryCats(String query, CatsQueryCallback catsQueryCallback) {
            //模拟异步
            List<Cat> cats = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Cat cat = new Cat("position :" + i);
                cats.add(cat);
            }
            catsQueryCallback.onCatListReceived(cats);
//            catsQueryCallback.onError(null);
        }

        @Override
        public void store(Cat cutest,StoreCallback storeCallback) {
            //模拟异步
            storeCallback.onCatStored(cutest.toCat());
        }
    };

    public AsyncJob<List<Cat>> queryCats(final String query) {
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(final Callback<List<Cat>> catsCallback) {
                api.queryCats(query, new Api.CatsQueryCallback() {

                    @Override
                    public void onCatListReceived(List<Cat> cats) {
                        catsCallback.onResult(cats);
                    }

                    @Override
                    public void onError(Exception e) {
                        catsCallback.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<String> store(final Cat cat) {
        return new AsyncJob<String>() {
            @Override
            public void start(final Callback<String> callback) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(String s) {
                        callback.onResult(s);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }

}
