package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-8.
 */
public class ApiWrapper2 {
    Api api = new ApiTest();

    public void queryCats(String query, final Callback<List<Cat>> catsCallback){
        api.queryCats(query,new Api.CatsQueryCallback(){

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

    public void store(Cat cat, final Callback<String> callback){
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
}
