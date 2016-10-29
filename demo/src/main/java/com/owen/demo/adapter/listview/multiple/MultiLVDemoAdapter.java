package com.owen.demo.adapter.listview.multiple;

import android.content.Context;

import com.owen.adapter.listview.BaseLVViewHolder;
import com.owen.adapter.listview.multiple.MultiItemTypeSupport;
import com.owen.adapter.listview.multiple.MultipleLVAdapter;
import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.BeanPerson;

import java.util.List;


public class MultiLVDemoAdapter extends MultipleLVAdapter<BeanPerson> {

    public MultiLVDemoAdapter(Context context, List<BeanPerson> data, MultiItemTypeSupport multiItemTypeSupport) {
        super(context, data, multiItemTypeSupport);
    }

    @Override
    public void convert(BaseLVViewHolder holder, BeanPerson item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_job, item.getJob());
    }
}
