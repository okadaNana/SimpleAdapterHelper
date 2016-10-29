package com.owen.adapter.recyclerview.multiple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owen.adapter.recyclerview.BaseRVViewHolder;

import java.util.List;


public abstract class MultipleRVAdapter<T> extends RecyclerView.Adapter<BaseRVViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mData;
    private MultiItemTypeSupport mMultiItemTypeSupport;

    public MultipleRVAdapter(Context context, MultiItemTypeSupport multiItemTypeSupport) {
        this(context, null, multiItemTypeSupport);
    }

    public MultipleRVAdapter(Context context, List<T> data, MultiItemTypeSupport multiItemTypeSupport) {
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
    public BaseRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        View itemView = mInflater.inflate(layoutId, parent, false);
        return new BaseRVViewHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(BaseRVViewHolder holder, int position) {
        bind(holder, mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public abstract void bind(BaseRVViewHolder holder, T item);
}
