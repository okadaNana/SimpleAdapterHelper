package com.owen.adapter.listview.multiple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.owen.adapter.listview.BaseLVViewHolder;

import java.util.List;


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
