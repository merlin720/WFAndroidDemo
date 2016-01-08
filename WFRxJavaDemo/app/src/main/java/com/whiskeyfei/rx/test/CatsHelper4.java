package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class CatsHelper4 {
    ApiWrapper3 apiWrapper = new ApiWrapper3();

    public AsyncJob<String> saveTheCutestCat(final String query) {
       final AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
//        这 16 行代码只有一行是对我们有用（对于逻辑来说）的操作：
//        findCutest(result)

        final AsyncJob<Cat> cutestCatAsyncJob = new AsyncJob<Cat>(){

            @Override
            public void start(final Callback<Cat> callback) {
                catsListAsyncJob.start(new Callback<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> result) {
                        callback.onResult(findCutest(result));
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };

        AsyncJob<String> storedUriAsyncJob = new AsyncJob<String>() {
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

        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return cats.get(0);
    }
}
