package com.owen.demo.adapter.recyclerview.single;

import android.content.Context;

import com.owen.adapter.recyclerview.BaseRVViewHolder;
import com.owen.adapter.recyclerview.single.SingleRVAdapter;
import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.BeanPerson;

import java.util.List;


public class SingleRVDemoAdapter extends SingleRVAdapter<BeanPerson> {

    public SingleRVDemoAdapter(Context context, List<BeanPerson> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bind(BaseRVViewHolder holder, BeanPerson item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_job, item.getJob());
    }
}
