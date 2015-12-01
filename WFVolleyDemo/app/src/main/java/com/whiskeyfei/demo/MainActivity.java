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
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.whiskeyfei.demo.utils.Constants;
import com.whiskeyfei.demo.utils.VolleyUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        testStringRequest();
        testImageRequest();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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


//    public void teset(){
//        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest();
//    }


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
