# WFRxJavaDemo
练习使用RxJava,demo记录在此,参考文章<br/>
1、[NotRxJava懒人专用指南](http://www.devtf.cn/?p=323)<br/>
2、[深入浅出RxJava(二：操作符)](https://github.com/lzyzsd/Awesome-RxJava?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io)<br/>
3、[给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083#toc_1)<br/>
写文件
```java
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
```
读文件
```java
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
            }
        });

```
