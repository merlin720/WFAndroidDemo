# DPSlidesImageView-Android
> 自定义Imageview，实现定时切换，类似幻灯片效果

自定义ViewPager实现幻灯片可以查看[android-tv-guide-viewpager](https://github.com/whiskeyfei/android-tv-guide-viewpager.git)<br/>

### 效果图
![](http://7xol9p.com1.z0.glb.clouddn.com/github_SlidesImageView_test1.gif)
![](http://7xol9p.com1.z0.glb.clouddn.com/github_timer_image.gif)

### 使用方法

```java
  mTimerImageView = (TimerImageView)mView.findViewById(R.id.timerimageview);
  mTimerImageAdapter = new TimerImageAdapter();
  mTimerImageView.setAdapter(mTimerImageAdapter);
  mTimerImageAdapter.setData(mList);//数据
```

  xml引入
```java
   <com.whiskeyfei.widget.TimerImageView
        android:id="@+id/timerimageview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>

```

### 关键类

#####[ImageChangeListener](https://github.com/whiskeyfei/DPSlidesImageView-Android/blob/master/app/src/main/java/com/whiskeyfei/impl/ImageChangeListener.java)
定义接口

```java
public interface ImageChangeListener {
    public void updateImage(int id,int position);
}
```

#####[TimerImageView](https://github.com/whiskeyfei/DPSlidesImageView-Android/blob/master/app/src/main/java/com/whiskeyfei/widget/TimerImageView.java)
1、主要功能为刷新图片和过渡动画效果<br/>
2、绑定TimerImageAdapter<br/>
3、点击事件处理(next image)

```java
//...
   private void init(Context context){
        mContext = context;
        setScaleType(ScaleType.FIT_CENTER);
        setOnClickListener(this);
    }


    public void setAdapter(TimerImageAdapter adapter){
        mTimerImageAdapter = adapter;
        mTimerImageAdapter.setImageChangeListener(this);
    }

    @Override
    public void updateImage(int id, int position) {
        Toast.makeText(mContext,"updateImage position:" + position,Toast.LENGTH_SHORT).show();
        setImageResource(id);
        AnimationUtils.alphaAnimation(this, 0.0f, 1.0f, 500);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTimerImageAdapter!= null) mTimerImageAdapter.setImageChangeListener(null);
    }

//...
```

##### [TimerImageAdapter](https://github.com/whiskeyfei/DPSlidesImageView-Android/blob/master/app/src/main/java/com/whiskeyfei/adapter/TimerImageAdapter.java)
1、绑定功能数据<br/>
2、维护图片数据队列<br/>
3、封装开始、停止、切换等操作<br/>

部分代码
```java
    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_FLAG:
                    switchImage();
                    break;
            }
        }
    };
    
    
    public void setData(List<ImageModel> mList){
        this.mList = mList;
//        startTimer();
    }
    
     public void startTimer() {
        if (isStop){
            isStop = false;
        }
        mHandler.removeMessages(CHANGE_FLAG);
        mHandler.sendEmptyMessageDelayed(CHANGE_FLAG, 50);
    }

    /**
     * 停止切换
     */
    public void stopSwitchImage(){
        isStop = true;
        mHandler.removeMessages(CHANGE_FLAG);
    }

    /**
     * 切换下一张
     */
    public void switchNext(){
        mHandler.removeMessages(CHANGE_FLAG);
        mHandler.sendEmptyMessageDelayed(CHANGE_FLAG, 50);
    }

```

###### 更多内容请关注[我的github](https://github.com/whiskeyfei),也欢迎大家到[Issues](https://github.com/whiskeyfei/DPSlidesImageView-Android/issues)提问题
