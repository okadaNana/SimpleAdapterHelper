package com.owen.demo.adapter.listview.single;

import android.content.Context;

import com.owen.adapter.listview.BaseLVViewHolder;
import com.owen.adapter.listview.single.SingleLVAdapter;
import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.BeanPerson;

import java.util.List;

public class SingleLVDemoAdapter extends SingleLVAdapter<BeanPerson> {

    public SingleLVDemoAdapter(Context context, List<BeanPerson> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    public void convert(BaseLVViewHolder holder, BeanPerson item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_job, item.getJob());
    }
}
