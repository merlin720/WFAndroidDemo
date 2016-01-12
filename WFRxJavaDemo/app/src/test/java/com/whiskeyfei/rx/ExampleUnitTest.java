package com.whiskeyfei.rx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void hello(){
        System.out.print("hello");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    //将字符串数组 names 中的所有字符串依次打印出来
    public void test1(){
        String [] names ={"111","222","333"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                System.out.println("name:"+name);
            }
        });
    }

    @Test
    public void helloWorld(){
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello World");
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError e:"+e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext s:"+s);
            }
        });

        //操作符 用来转换string

        Observable.just("hello World").map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                return s + ":test";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext s:"+s);
            }
        });

        //还可以这么写

        Observable.just("hello World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("call s:"+s);
            }
        });

        //
        Observable.just("Hello, world!").map(new Func1<String, Integer>() {

            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext integer:"+integer);
            }
        });

        //转换三次 string -> int > string > int
        Observable.just("Hello, world!").map(new Func1<String, Integer>() {

            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).map(new Func1<Integer, String>() {

            @Override
            public String call(Integer integer) {
                return Integer.toString(integer);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext s:"+s);
            }
        });
    }

    @Test
    public void ObserverList(){
        Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<>();
                for (int i = 0;i<10;i++){
                    list.add(i + "--");
                }
                subscriber.onNext(list);
            }
        }).subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                System.out.println("flatMapTest onNext strings:"+strings);
            }
        });
    }

    @Test
    public void flatMapTest(){
        //flatMap操作符
        Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<>();
                for (int i = 0;i<10;i++){
                    list.add(i + "--");
                }
                subscriber.onNext(list);
            }
        }).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                //滤掉null值
                return s != null;
            }
            //take只需要5个 获取的意思
        }).take(5).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                //doOnNext()允许我们在每次输出一个元素之前做一些额外的事情
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("flatMapTest onNext s:"+s);
            }
        });
    }
}