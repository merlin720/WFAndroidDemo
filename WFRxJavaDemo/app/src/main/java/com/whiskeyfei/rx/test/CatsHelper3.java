package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class CatsHelper3 {
    ApiWrapper3 apiWrapper = new ApiWrapper3();

    public AsyncJob<String> saveTheCutestCat(final String query) {
        return new AsyncJob<String>() {
            @Override
            public void start(final Callback<String> callback) {
                apiWrapper.queryCats(query).start(new Callback<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> cats) {
                        Cat cutest = findCutest(cats);
                        apiWrapper.store(cutest).start(new Callback<String>() {
                            @Override
                            public void onResult(String result) {
                                callback.onResult(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                callback.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
    private Cat findCutest(List<Cat> cats) {
        return cats.get(0);
    }
}
