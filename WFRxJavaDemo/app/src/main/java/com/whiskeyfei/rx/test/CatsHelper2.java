package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class CatsHelper2 {
    ApiWrapper2 apiWrapper = new ApiWrapper2();

    public void saveTheCutestCat(String query, final Callback<String> cutestCatCallback){
        apiWrapper.queryCats(query, new Callback<List<Cat>>() {
            @Override
            public void onResult(List<Cat> result) {
                Cat cat = findCutest(result);
                apiWrapper.store(cat,cutestCatCallback);
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallback.onError(e);
            }
        });
    }

    private Cat findCutest(List<Cat> cats) {
        return cats.get(0);
    }
}
