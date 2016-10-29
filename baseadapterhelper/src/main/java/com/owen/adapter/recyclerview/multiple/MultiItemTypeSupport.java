package com.owen.adapter.recyclerview.multiple;

public interface MultiItemTypeSupport {

    int getItemViewType(int position);

    int getLayoutId(int itemViewType);
}
