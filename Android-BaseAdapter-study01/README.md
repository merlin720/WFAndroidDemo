# Android-BaseAdapter-study01

[分支Android-BaseAdapter-study02](https://github.com/whiskeyfei/Android-BaseAdapter-study01/tree/Android-BaseAdapter-study02)<br/>
Android-BaseAdapter-study01 demo中学习BaseAdapter的使用与优化

**【感谢慕课网的 [Android必学-BaseAdapter的使用与优化](http://www.imooc.com/learn/365) 视频教程】**

## 效果图
   ![效果](../master/app/src/main/res/drawable/xiaoguo.jpeg)
   
## Adapter三种方式
1、没有用到ListView的缓存机制<br/>

```java
  View view = mLayoutInfalter.inflate(R.layout.item,null);
  //...得到控件
```

2、利用ListView的缓存机制，节省资源，避免创建多个view<br/>

```java
  if (convertView == null) {
      convertView = mLayoutInfalter.inflate(R.layout.item, null);
      //...得到控件
  }

```

=======
```
3、优化 避免多次findviewbyid 通过tag绑定view<br/>

```java
   class ViewHolder {
        ImageView icon;
        TextView title;
        TextView content;
    }
    
     ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInfalter.inflate(R.layout.item, null);
            //...得到控件
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemModel model = mList.get(position);
        //...填充数据
```

4、添加可变layoutId 可以多个ListView使用一个adapter

```java
  public ItemAdapterLayout(List<ItemModel> list, Context context, int layoutId) {
          super(context,list,layoutId);
      }
  
      @Override
      public void convert(ViewHolder holder, ItemModel model) {
      //...绑定数据
      }
```

5、精简getView

```java
      @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId, position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T t);
```

6、方便使用
```java
   mListView.setAdapter(new CommonAdapterLayout<ItemModel>(MainActivity.this, mList, R.layout.item) {
       @Override
      public void convert(ViewHolder holder, ItemModel model) {
       //绑定数据
      }
    });
```

================
######更多内容请关注[我的github](https://github.com/whiskeyfei),也欢迎大家到[Issues](https://github.com/whiskeyfei/Android-BaseAdapter-study01/issues)提问题
