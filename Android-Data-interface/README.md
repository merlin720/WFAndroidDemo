# Android-Data-interface
异步请求网络数据，感谢[Trinea](https://github.com/Trinea/android-common)开源工具的帮助
# Usage
```
HttpHelper.get().call(URL_ACTION, mOnHttpDataListener);
...
    private OnHttpDataListener mOnHttpDataListener = new OnHttpDataListener() {

        @Override
        public void onSuccess(String json) {
           //rqeuet
        }

        @Override
        public void onFild(String code) {
          //error code    
        }
        
        @Override
        public void onPreExecute() {
          //refresh ui    
        }
    };

```

#Instruction

Android的AsyncTask比Handler更轻量级一些,适用于简单的异步处理。
首先明确Android之所以有Handler和AsyncTask，都是为了不阻塞主线程（UI线程），且UI的更新只能在主线程中完成，因此异步处理是不可避免的.
 
Android为了降低这个开发难度，提供了AsyncTask,AsyncTask就是一个封装过的后台任务类-异步任务.

AsyncTask直接继承于Object类，位置为android.os.AsyncTask,使用AsyncTask工作我们要提供三个泛型参数，并重载几个方法(至少重载一个).

AsyncTask定义了三种泛型类型 Params，Progress和Result.
* Params 启动任务执行的输入参数，比如HTTP请求的URL
* Progress 后台任务执行的百分比
* Result 后台执行任务最终返回的结果，比如String

使用AsyncTask类，以下是几条必须遵守的准则
* Task的实例必须在UI thread中创建；
* execute方法必须在UI thread中调用；
* 不要手动的调用onPreExecute(), onPostExecute(Result)，doInBackground(Params...),             onProgressUpdate(Progress...)这几个方法；
* 该task只能被执行一次，否则多次调用时将会出现异常；
