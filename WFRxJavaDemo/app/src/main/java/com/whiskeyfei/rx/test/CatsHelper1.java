package com.whiskeyfei.rx.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public class CatsHelper1 {

    public interface CutestCatCallback {
        void onCutestCatSaved(String s);
        void onQueryFailed(Exception e);
    }

    Api api = new Api() {
        @Override
        public void queryCats(String query, CatsQueryCallback catsQueryCallback) {
            //模拟异步
            List<Cat> cats = new ArrayList<>();
            for (int i = 0; i<10;i++){
                Cat cat = new Cat(i + "");
                cats.add(cat);
            }
            catsQueryCallback.onCatListReceived(cats);
//            catsQueryCallback.onError(null);
        }

        @Override
        public void store(Cat cutest, StoreCallback storeCallback) {
            //模拟异步
            storeCallback.onCatStored(cutest.toCat());
        }
    };

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
