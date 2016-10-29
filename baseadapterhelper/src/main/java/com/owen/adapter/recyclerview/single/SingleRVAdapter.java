package com.owen.adapter.recyclerview.single;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owen.adapter.recyclerview.BaseRVViewHolder;

import java.util.List;

public abstract class SingleRVAdapter<T> extends RecyclerView.Adapter<BaseRVViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private int mLayoutResId;
    private List<T> mData;

    public SingleRVAdapter(Context context, int layoutResId) {
        this(context, null, layoutResId);
    }

    public SingleRVAdapter(Context context, List<T> data, int layoutResId) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mLayoutResId = layoutResId;
        mData = data;
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
    public BaseRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mLayoutResId, parent, false);
        return new BaseRVViewHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(BaseRVViewHolder holder, int position) {
        bind(holder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected abstract void bind(BaseRVViewHolder holder, T item);
}
