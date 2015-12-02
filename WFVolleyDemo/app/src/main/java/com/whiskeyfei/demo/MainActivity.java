package com.whiskeyfei.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.whiskeyfei.demo.utils.Constants;
import com.whiskeyfei.demo.utils.VolleyUtil;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    NetworkImageView mNetworkImageView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        testStringRequest();//测试StringRequest
        testImageRequest();//测试ImageRequest
        testmNetworkImageView();//测试自带下载图片
        testImageLoader();//测试ImageLoader 需要初始化ImageLoader 和 iamgecache
    }

    private void testmNetworkImageView() {
        mNetworkImageView = (NetworkImageView)findViewById(R.id.network_image);
        mNetworkImageView.setImageUrl(Constants.BAIDU_IMAGE2,VolleyUtil.getImageLoader());
    }

    public void testStringRequest(){
        VolleyUtil.cancelAllQueue(this);
        StringRequest mStringRequest = new StringRequest(Constants.BAIDU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG,"testStringRequest() -> response:"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"testStringRequest() -> error:"+error.getMessage());
            }
        });
        VolleyUtil.addToRequestQueue(mStringRequest,this);
    }

    public void testImageRequest(){
        mImageView  = (ImageView)findViewById(R.id.image);
        mImageView.setImageResource(R.mipmap.ic_launcher);
        VolleyUtil.cancelAllQueue(mImageView);
        ImageRequest request=new ImageRequest(Constants.BAIDU_IMAGE, new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                mImageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        VolleyUtil.addToRequestQueue(request, mImageView);

    }

    public void testImageLoader(){
        VolleyUtil.getImageLoader().get(Constants.BAIDU_IMAGE2, ImageLoader.getImageListener((ImageView) findViewById(R.id.image_loder),R.mipmap.ic_launcher,R.mipmap.ic_launcher));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
