# SimpleAdapterHelper

ListView和RecyclerView的BaseAdapter最简单、方便的实现

## 我是如何实现的

ListView和RecyclerView的实现都差不多，ListView会了，RecyclerView也就懂了。因此我这里就说明一下ListView是怎么实现的。在这里打算分为两个部分来说，一个 item 的布局只有一个的情况，还有一个 item 的布局有多个的情况。

## Item的布局只有一种

### 传统写法

```java
public class MyAdapter extends BaseAdapter {
    
    private Context mContext;
    private LayoutInflater mInflater;
    private List<BeanPerson> mData;

    public MyAdapter(Context context, List<BeanPerson> data) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder; 
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_single_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BeanPerson beanPerson = mData.get(position);

        holder.mTvName.setText(beanPerson.getName());
        holder.mTvJob.setText(beanPerson.getJob());
        
        return convertView;
    }
    
    private static class ViewHolder {      
        
        TextView mTvName;
        TextView mTvJob;
        
        public ViewHolder(View convertView) {
            mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            mTvJob = (TextView) convertView.findViewById(R.id.tv_job);
        }
    }
}
```

要减少代码，可以对数据源和 ViewHolder 做文章。

#### 封装数据源
对数据源进行封装，可以使用泛型。

#### 封装视图

ViewHolder 的作用是从 convertView 中通过 findViewById 找到视图，并存储对视图的引用，然后绑定数据到视图上。因此可以使用 Map 把视图存储起来

### 封装一个 BaseAdapter

```java
public abstract class SingleLVAdapter<T> extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mData;
    private int mLayoutResId;

    public SingleLVAdapter(Context context, int layoutResId) {
        this(context, null, layoutResId);
    }

    public SingleLVAdapter(Context context, List<T> data, int layoutResId) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = data;
        mLayoutResId = layoutResId;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (mData == null) {
            mData = data;
        } else {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return  mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseLVViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mLayoutResId, parent, false);
            holder = new BaseLVViewHolder(mContext, convertView);
            convertView.setTag(holder);
        } else {
            holder = (BaseLVViewHolder) convertView.getTag();
        }
        convert(holder, getItem(position));
        return convertView;
    }

    public abstract void convert(BaseLVViewHolder holder, T item);
}
```

### 封装一个ViewHolder

```java
public class BaseLVViewHolder {

    private Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;

    public BaseLVViewHolder(Context context, View convertView) {
        mContext = context;
        mViews = new SparseArray<>();
        mConvertView = convertView;
    }

    public <T extends View> T getView(int viewResId) {
        View view = mViews.get(viewResId);
        if (view == null) {
            view = mConvertView.findViewById(viewResId);
            mViews.put(viewResId, view);
        }
        return (T) view;
    }

    public BaseLVViewHolder setText(int resId, String text) {
        TextView textView = getView(resId);
        textView.setText(text);
        return this;
    }
}
```

### 封装之后的写法

```java
public class MyAdapter extends SingleLVAdapter<BeanPerson> {

    public MyAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    @Override
    public void convert(BaseLVViewHolder holder, BeanPerson item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_job, item.getJob());
    }
}
```

真方便

## Item 的布局有多种

### 传统写法

传统的写法是要在原来的基础上增加两个方法

制定布局的个数，假设有两个布局

```java
@Override
public int getViewTypeCount() {
	return 2;
}
```

指定哪些位置需要显示什么样的布局，假设 position 为偶数是显示一个布局，为奇数时显示另外一个布局

```java
private static final int ITEM_VIEW_TYPE_ONE = 0;
private static final int ITEM_VIEW_TYPE_ANOTHER = 0;    

@Override
public int getItemViewType(int position) {
	if (position % 2 == 0) {
		return ITEM_VIEW_TYPE_ONE;
	} else {
		return ITEM_VIEW_TYPE_ANOTHER;
	}
}
```

如果要封装一个支持多个布局的 BaseAdapter，那么就需要对 `getViewTypeCount` 和 `getItemViewType` 做文章。

### 定义一个接口

要对 `getViewTypeCount` 和 `getItemViewType` 做文章，由接口的实现类来指定布局的种类

```java
public interface MultiItemTypeSupport {

    int getItemViewLayoutResId(int itemViewType);

    int getViewTypeCount();

    int getItemViewType(int position);
}
```

#### 将接口作为参数传入BaseAdapter 的构造函数

```java
public abstract class MultipleLVAdapter<T> extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mData;
    private MultiItemTypeSupport mMultiItemTypeSupport;

    public MultipleLVAdapter(Context context, MultiItemTypeSupport multiItemTypeSupport) {
        this(context, null, multiItemTypeSupport);
    }

    public MultipleLVAdapter(Context context, List<T> data, MultiItemTypeSupport multiItemTypeSupport) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = data;
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        if (mData == null) {
            mData = data;
        } else {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return mMultiItemTypeSupport.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        int layoutResId = mMultiItemTypeSupport.getItemViewLayoutResId(itemViewType);

        BaseLVViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(layoutResId, parent, false);
            holder = new BaseLVViewHolder(mContext, convertView);
            convertView.setTag(holder);
        } else {
            holder = (BaseLVViewHolder) convertView.getTag();
        }

        convert(holder, getItem(position));

        return convertView;
    }

    public abstract void convert(BaseLVViewHolder holder, T item);
}
```

### 封装之后的写法

Activity

```java
ListView listView = (ListView) findViewById(R.id.listview);
MultiLVDemoAdapter adapter = new MultiLVDemoAdapter(this, DataSource.generateData(),
		new MultiItemTypeSupport() {
			@Override
			public int getItemViewLayoutResId(int itemViewType) {
				if (VIEW_TYPE_BLUE == itemViewType) {
					return R.layout.item_single_layout;
				} else {
					return R.layout.item_another_layout;
				}
			}

			@Override
			public int getViewTypeCount() {
				return 2;
			}

			@Override
			public int getItemViewType(int position) {
				if (position % 2 == 0) {
					return VIEW_TYPE_BLUE;
				} else {
					return VIEW_TYPE_RED;
				}
			}
		});
listView.setAdapter(adapter);
```

Adapter

```java
public class MultiLVDemoAdapter extends MultipleLVAdapter<BeanPerson> {

    public MultiLVDemoAdapter(Context context, List<BeanPerson> data,
                              MultiItemTypeSupport multiItemTypeSupport) {
        super(context, data, multiItemTypeSupport);
    }

    @Override
    public void convert(BaseLVViewHolder holder, BeanPerson item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_job, item.getJob());
    }
}
```




