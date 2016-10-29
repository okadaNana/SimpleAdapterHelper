package com.owen.adapter.listview.multiple;

public interface MultiItemTypeSupport {

    int getItemViewLayoutResId(int itemViewType);

    int getViewTypeCount();

    int getItemViewType(int position);
}