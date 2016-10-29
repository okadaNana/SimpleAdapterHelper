package com.owen.adapter.listview;


import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

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
