package com.owen.demo.adapter.recyclerview.multiple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owen.adapter.recyclerview.multiple.MultiItemTypeSupport;
import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.DataSource;

public class MultipleLayoutRVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultiRVDemoAdapter adapter = new MultiRVDemoAdapter(this, DataSource.generateData(), new MultiItemTypeSupport() {

            private static final int ITEM_VIEW_TYPE_ONE = 1;
            private static final int ITEM_VIEW_TYPE_TWO = 2;

            @Override
            public int getItemViewType(int position) {
                if (position % 2 == 0) {
                    return ITEM_VIEW_TYPE_ONE;
                } else {
                    return ITEM_VIEW_TYPE_TWO;
                }
            }

            @Override
            public int getLayoutId(int itemViewType) {
                if (ITEM_VIEW_TYPE_TWO == itemViewType) {
                    return R.layout.item_another_layout;
                } else {
                    return R.layout.item_single_layout;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
