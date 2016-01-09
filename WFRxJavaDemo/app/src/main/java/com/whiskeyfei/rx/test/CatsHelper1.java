package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class CatsHelper1 {
    private static final String TAG = "CatsHelper1";

    public interface CutestCatCallback {
        void onCutestCatSaved(String s);
        void onQueryFailed(Exception e);
    }

    Api api = new ApiTest();

    public void saveTheCutestCat(String query, final CutestCatCallback cutestCatCallback){
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                Cat cutest = findCutest(cats);
                api.store(cutest, new Api.StoreCallback() {

                    @Override
                    public void onCatStored(String s) {
                        cutestCatCallback.onCutestCatSaved(s);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        cutestCatCallback.onQueryFailed(e);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallback.onQueryFailed(e);
            }

        });
    }

    private Cat findCutest(List<Cat> cats) {
        return cats.get(0);
    }
}
