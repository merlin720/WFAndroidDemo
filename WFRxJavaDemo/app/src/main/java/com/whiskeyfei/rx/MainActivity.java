package com.whiskeyfei.rx;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.whiskeyfei.rx.app.FileAdapter;
import com.whiskeyfei.rx.test.Callback;
import com.whiskeyfei.rx.test.CatsHelper1;
import com.whiskeyfei.rx.test.CatsHelper3;
import com.whiskeyfei.rx.test.CatsHelper4;
import com.whiskeyfei.rx.test.CatsHelper5;
import com.whiskeyfei.rx.test.Data;
import com.whiskeyfei.rx.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    ImageView mImageView;
    private Button mTest1,mTest2,mTest3,mTest4,mTest5,mTest6;
    private String mPath;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FileAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mImageView = (ImageView)findViewById(R.id.image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPath = getFilesDir().toString();
        createFile();
    }

    private void readFile() {
        Observable.just(mPath).map(new Func1<String, File[]>() {

            @Override
            public File[] call(String path) {
                File file = new File(path);
                return file.listFiles();
            }
        }).map(new Func1<File[], List<String>>() {

            @Override
            public List<String> call(File[] files) {
                return Data.changeList(files);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"onError e:" + e);
            }

            @Override
            public void onNext(List<String> list) {
                Log.e(TAG,"onNext list:" + list);
                mAdapter.setDate(list);
            }
        });
    }

    private void createFile() {
        File file=new File(mPath);
        File[] tempList = file.listFiles();
        if(tempList.length > 0){
            Log.e(TAG,"length > 0");
            readFile();
            return;
        }
        Observable.from(Data.getFileList()).map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                return getFilesDir() + "/" + s;
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
        .map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                File file = new File(s);
                if (!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return "name:"+file.getName();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"onCompleted");
                readFile();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"onError e:" + e);
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"onNext s:" + s);
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FileAdapter(this);
//        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mTest1 = (Button) findViewById(R.id.button1);
        mTest2 = (Button) findViewById(R.id.button2);
        mTest3 = (Button) findViewById(R.id.button3);
        mTest4 = (Button) findViewById(R.id.button4);
        mTest5 = (Button) findViewById(R.id.button5);
        mTest6 = (Button) findViewById(R.id.button6);
        mTest1.setText("mTest1");
        mTest2.setText("mTest2");
        mTest3.setText("mTest3");
        mTest4.setText("mTest4");
        mTest5.setText("mTest5");
        mTest6.setText("mTest6");
        mTest1.setOnClickListener(this);
        mTest2.setOnClickListener(this);
        mTest3.setOnClickListener(this);
        mTest4.setOnClickListener(this);
        mTest5.setOnClickListener(this);
        mTest6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                catsHelperTest1();
                break;
            case R.id.button2:
                test2();
                break;
            case R.id.button3:
                catsHelperTest3();
                break;
            case R.id.button4:
                catsHelperTest4();
                break;
            case R.id.button5:
                catsHelperTest5();
                break;
            case R.id.button6:
                test4();
                break;
        }
    }

    private void catsHelperTest5() {
        CatsHelper5 casCatsHelper5 = new CatsHelper5();
        casCatsHelper5.saveTheCutestCat(Constants.BAIDU).start(new Callback<String>() {
            @Override
            public void onResult(String result) {
                Log.e(TAG,"catsHelperTest5 result:"+result);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG,"catsHelperTest5 e:"+e);
            }
        });
    }


    private  void catsHelperTest4(){
        CatsHelper4 catsHelper4 = new CatsHelper4();
        catsHelper4.saveTheCutestCat(Constants.BAIDU).start(new Callback<String>() {
            @Override
            public void onResult(String result) {
                Log.e(TAG,"catsHelperTest4 result:"+result);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG,"catsHelperTest4 e:"+e);
            }
        });
    }

    private  void catsHelperTest3(){
        CatsHelper3 catsHelper3 = new CatsHelper3();
        catsHelper3.saveTheCutestCat(Constants.BAIDU).start(new Callback<String>() {
            @Override
            public void onResult(String result) {
                Log.e(TAG,"catsHelperTest3 result:"+result);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG,"catsHelperTest3 e:"+e);
            }
        });
    }

    private void catsHelperTest1() {
        CatsHelper1 catsHelper = new CatsHelper1();
        catsHelper.saveTheCutestCat(Constants.BAIDU, new CatsHelper1.CutestCatCallback() {
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


    private void test4(){
        Observable.just(R.drawable.ic_launcher).map(new Func1<Integer, Drawable>() {

            @Override
            public Drawable call(Integer integer) {
                return getResources().getDrawable(integer);
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
