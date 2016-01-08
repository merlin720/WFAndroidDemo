package com.whiskeyfei.rx;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.whiskeyfei.rx.test.Callback;
import com.whiskeyfei.rx.test.CatsHelper1;
import com.whiskeyfei.rx.test.CatsHelper3;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView)findViewById(R.id.image);
//        mObservable.subscribe(mObserver);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        test2();

//        catsHelperTest1();
        catsHelperTest3();
    }

    private  void catsHelperTest3(){
        CatsHelper3 catsHelper3 = new CatsHelper3();
        catsHelper3.saveTheCutestCat("").start(new Callback<String>() {
            @Override
            public void onResult(String result) {
                Log.e(TAG,"stringAsyncJob result:"+result);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG,"stringAsyncJob e:"+e);
            }
        });
    }

    private void catsHelperTest1() {
        CatsHelper1 catsHelper = new CatsHelper1();
        catsHelper.saveTheCutestCat("", new CatsHelper1.CutestCatCallback() {
            @Override
            public void onCutestCatSaved(String s) {
                Log.e(TAG,"onCutestCatSaved:" + s );
            }

            @Override
            public void onQueryFailed(Exception e) {
                Log.e(TAG,"onCutestCatSaved e:" + e.getMessage() );
            }
        });
    }

    Observer<String> mObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "Error!");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "mObserver: " + s);
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onStart() {
            super.onStart();
        //可选方法，默认情况下它的实现为空,对线程有要求，不是UI主线程
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }

    };
    //Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件
    Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {

        }
    });


    //将字符串数组 names 中的所有字符串依次打印出来
    private void test1(){
        String [] names ={"111","222","333"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                Log.d(TAG, name);
            }
        });
    }
    //
    private void test2() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        mImageView.setImageDrawable(drawable);
                    }
                });
    }

    private void test3(){
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d(TAG, "number:" + number);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
