package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class CatsHelper5 {
    ApiWrapper5 apiWrapper = new ApiWrapper5();

    public AsyncJob2<String> saveTheCutestCat(final String query) {
       final AsyncJob2<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
       final AsyncJob2<Cat> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {

            @Override
            public Cat call(List<Cat> cats) {
                return findCutest(cats);
            }
        });

        AsyncJob2<String> stringAsyncJob = new AsyncJob2<String>() {
            @Override
            public void start(final Callback<String> cutestCatCallback) {
                cutestCatAsyncJob.start(new Callback<Cat>() {
                    @Override
                    public void onResult(Cat result) {
                        apiWrapper.store(result).start(new Callback<String>() {
                            @Override
                            public void onResult(String result) {
                                cutestCatCallback.onResult(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                cutestCatCallback.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        cutestCatCallback.onError(e);
                    }
                });
            }
        };

        return stringAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return cats.get(0);
    }
}
