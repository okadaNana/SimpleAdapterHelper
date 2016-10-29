package com.owen.adapter.listview.single;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.owen.adapter.listview.BaseLVViewHolder;

import java.util.List;

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
