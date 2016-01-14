package com.whiskeyfei.rx;

import com.whiskeyfei.rx.test.Cat;
import com.whiskeyfei.rx.test.Data;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
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


    //将字符串数组 names 中的所有字符串依次打印出来
    //支持不完整得回调

    @Test
    public void test1(){
        String [] names ={"111","222","333"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                System.out.println("test1 name:"+name);
            }
        });
    }

    //just 将传入的参数依次打印
    //    onNext:1
    //    onNext:2
    //    onNext:3
    //    test2 onCompleted
    @Test
    public void test2(){
        Observable.just("1","2","3").subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("test2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("test2 e:"+e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:"+s);
            }
        });
    }

    //将字符串数组 names 中的所有字符串依次打印出来
    //    依次调用onNext("111")
    //    依次调用onNext("222")
    //    依次调用onNext("333")
    @Test
    public void test3(){
        String [] names ={"111","222","333"};
        Observable.from(names).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("test3 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("test3 e:"+e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("test3 onNext:"+s);
            }
        });
    }

    //将字符串数组 names 中的所有字符串依次打印出来
    //    依次调用onNext("111")
    //    依次调用onNext("222")
    //    依次调用onNext("333")
    @Test
    public void test4(){
        //1:被观察者
        String [] names ={"111","222","333"};
        Observable observable = Observable.from(names);

        //2:观察者
        Action1 onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("test4 call"+s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable e) {
                System.out.println("test4 call e:"+e);
            }
        };


        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                System.out.println("test4 call onCompletedAction");
            }
        };
        //3:订阅:被观察者被观察者订阅
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    //循环输出list
    //    test5 call :list:0
    //    test5 call :list:1
    //    test5 call :list:2
    //    test5 call :list:3
    //    test5 call :list:4
    //    test5 call :list:5
    //    test5 call :list:6
    //    test5 call :list:7
    //    test5 call :list:8
    //    test5 call :list:9
    @Test
    public void test5(){
        Observable.from(Data.getCats().get(0).getlist()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("test5 call :"+s);
            }
        });
    }

    @Test
    public void test6(){
        Observable.from(Data.getCats()).subscribe(new Action1<Cat>() {
            @Override
            public void call(Cat cat) {
                System.out.println("test6 cat :"+cat);
            }
        });
    }


    @Test
    public void test7(){
        List<Cat> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            Cat cat = new Cat(i+"");
            list.add(cat);
        }
        Observable.from(list).map(new Func1<Cat, String>() {

            @Override
            public String call(Cat cat) {
                return cat.toCat();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("test7 onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("test7 onCompleted e:"+e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("test7 onNext :"+s);
            }
        });
    }

    //cats -> cat.list -> s
    //1:被观察者

    //2:数据转换

    //3:被观察者被观察者订阅

    //4:观察者
    @Test
    public void test8(){
        Observable.from(Data.getCats()).flatMap(new Func1<Cat, Observable<String>>() {
            @Override
            public Observable<String> call(Cat cat) {
                return Observable.from(cat.getlist());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("test8 call :"+s);
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


    @Test
    public void subscriptionTest(){
        Subscription subscription = Observable.just("").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
        subscription.isUnsubscribed();//检查是否已经取消订阅
        subscription.unsubscribe();//停止整个链
    }
}