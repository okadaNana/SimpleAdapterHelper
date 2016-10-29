package com.owen.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class BaseRVViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private SparseArray<View> mViews;
    private View mItemView;

    public BaseRVViewHolder(Context context, View itemView) {
        super(itemView);

        mContext = context;
        mViews = new SparseArray<>();
        mItemView = itemView;
    }

    public <T extends View> T getView(int viewResId) {
        View view = mViews.get(viewResId);
        if (view == null) {
            view = mItemView.findViewById(viewResId);
            mViews.put(viewResId, view);
        }
        return (T) view;
    }

    public BaseRVViewHolder setText(int resId, String text) {
        TextView textView = getView(resId);
        textView.setText(text);
        return this;
    }
}
