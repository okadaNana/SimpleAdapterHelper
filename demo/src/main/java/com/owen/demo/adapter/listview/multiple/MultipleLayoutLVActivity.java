package com.owen.demo.adapter.listview.multiple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.owen.adapter.listview.multiple.MultiItemTypeSupport;
import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.DataSource;


public class MultipleLayoutLVActivity extends AppCompatActivity {

    private static final int VIEW_TYPE_BLUE = 0;
    private static final int VIEW_TYPE_RED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView listView = (ListView) findViewById(R.id.listview);
        MultiLVDemoAdapter adapter = new MultiLVDemoAdapter(this, DataSource.generateData(), new MultiItemTypeSupport() {
            @Override
            public int getItemViewLayoutResId(int itemViewType) {
                if (VIEW_TYPE_BLUE == itemViewType) {
                    return R.layout.item_single_layout;
                } else {
                    return R.layout.item_another_layout;
                }
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position) {
                if (position % 2 == 0) {
                    return VIEW_TYPE_BLUE;
                } else {
                    return VIEW_TYPE_RED;
                }
            }
        });
        listView.setAdapter(adapter);
    }
}
