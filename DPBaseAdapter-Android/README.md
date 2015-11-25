# DPBaseAdapter-Android
> DPBaseAdapter-Android demo中学习BaseAdapter的使用,应用到ListView、GridView、RecyclerView的Adapter适配器，并且添加item的点击和长按点击时间监听，和item中控件的监听并回调,多说无用,try。

**【感谢慕课网的 [Android必学-BaseAdapter的使用与优化](http://www.imooc.com/learn/365) 视频教程】**<br/>
**【感谢[bingoogolapple-BGAAdapter-Android](https://github.com/bingoogolapple/BGAAdapter-Android)】**<br/>
**【感谢[hongyangAndroid/base-adapter-helper](https://github.com/hongyangAndroid/base-adapter-helper)】**<br/>
**【感谢[BaseAdapterHelper 源码分析](http://codekk.com/open-source-project-analysis/detail/Android/hongyangAndroid/BaseAdapterHelper%20%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)】**<br/>

#### 效果图
 <img src="http://7xol9p.com1.z0.glb.clouddn.com/github_basedemo.gif" width = "380" height = "676" />

### 使用方法
  1、目前在Activity当中使用，实现自定义的一些接口，用来接受按键回调，以下为具体作用<br/>
    - DPOnItemClickListener:条目点击事件
    - DPOnItemLongClickListener:条目长按点击事件
    - DPOnItemChildViewByIdClickListener:条目中子view点击事件
    - DPOnItemChildViewByIdLongClickListener:条目中子view长按点击事件
  
  2、注册各种监听,需要Adapter设置监听,以下分别是ListView和RecyclerView的Adapter使用<br/>
  3、在各自监听事件回调中处理<br/>
  4、在原来的ViewHolder基础之上扩展事件监听<br/>

#### RecyclerViewHolder 实现
    继承RecyclerView.ViewHolder，创建构造函数，将自定义的DPOnItemLongClickListener和DPOnItemClickListener进行绑定，并且实现Item的回调（控件里面无监听）

#### 更新内容

* 单数获取数据单例类：DPFakeData，DPItemModel实现序列化
* 添加自定义Application，初始化base context(在程序的任何地方都可以获得)
* 添加工具类：AssetsUtils、ListUtils、StringUtils(已完成)
* 本地json数据（7.14完成 已删除）
* 使用线上的数据，异步任务访问，添加loadding圈 (已完成)
* 使用RecyclerView来实现ListView和GridView (9.1 已完成)
* 使用Fragment替换activity(搁浅其他项目中已添加)
* 添加聊天界面多个Item样式(已完成 9.6)
* 将公共的东西抽成moudle (已完成)
* 整理代码
  
#### 详细说明

1、AssetsUtils：从assets或者raw文件夹中获取文件并读取数据，并转化model<br/>
2、ListUtils：目前只有isEmpty和getCount方法，以后丰富<br/>
3、StringUtils：目前只有isEmpty和isMailAddress，以后丰富<br/>
4、VolleyUtil ：Volley,RequestQueue单例<br/>

