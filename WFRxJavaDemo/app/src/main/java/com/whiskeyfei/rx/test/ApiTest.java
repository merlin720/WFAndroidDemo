package com.whiskeyfei.rx.test;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.whiskeyfei.rx.utils.Constants;
import com.whiskeyfei.rx.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiskeyfei on 16-1-9.
 */
public class ApiTest implements Api{
    private static final String TAG = "ApiTest";
    @Override
    public void queryCats(String query, final CatsQueryCallback catsQueryCallback) {
        VolleyUtil.cancelAllQueue(this);
        StringRequest mStringRequest = new StringRequest(Constants.BAIDU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //模拟异步
                List<Cat> cats = new ArrayList<>();
                for (int i = 0; i< 10;i++){
                    Cat cat = new Cat("position:"+i);
                    cats.add(cat);
                }
                Log.e(TAG,"testStringRequest() -> onResponse");
                catsQueryCallback.onCatListReceived(cats);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                catsQueryCallback.onError(error);
                Log.e(TAG,"testStringRequest() -> error:"+error.getMessage());
            }
        });
        VolleyUtil.addToRequestQueue(mStringRequest,this);
    }

    @Override
    public void store(Cat cutest, StoreCallback storeCallback) {
        //模拟异步
        if (cutest != null){
            storeCallback.onCatStored(cutest.toCat());
        }else{
            storeCallback.onStoreFailed(null);
        }
    }
}
