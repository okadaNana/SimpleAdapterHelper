package com.owen.demo.adapter.recyclerview.multiple;

import android.content.Context;

import com.owen.adapter.recyclerview.BaseRVViewHolder;
import com.owen.adapter.recyclerview.multiple.MultiItemTypeSupport;
import com.owen.adapter.recyclerview.multiple.MultipleRVAdapter;
import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.BeanPerson;

import java.util.List;


public class MultiRVDemoAdapter extends MultipleRVAdapter<BeanPerson> {

    public MultiRVDemoAdapter(Context context, List<BeanPerson> data, MultiItemTypeSupport multiItemTypeSupport) {
        super(context, data, multiItemTypeSupport);
    }

    @Override
    public void bind(BaseRVViewHolder holder, BeanPerson item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_job, item.getJob());
    }
}
