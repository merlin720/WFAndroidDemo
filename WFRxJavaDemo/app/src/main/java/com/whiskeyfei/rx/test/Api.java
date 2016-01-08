package com.whiskeyfei.rx.test;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-7.
 */
public interface Api {
    /**
     * query interface
     */
    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);
        void onError(Exception e);
    }

    /**
     * store interface
     */
    interface StoreCallback{
        void onCatStored(String s);
        void onStoreFailed(Exception e);
    }

    void queryCats(String query, CatsQueryCallback catsQueryCallback);
    void store(Cat cutest,StoreCallback storeCallback);
}
