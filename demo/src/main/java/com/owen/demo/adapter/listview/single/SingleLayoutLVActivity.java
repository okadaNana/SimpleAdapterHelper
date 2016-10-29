package com.owen.demo.adapter.listview.single;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.DataSource;

public class SingleLayoutLVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView listView = (ListView) findViewById(R.id.listview);
        SingleLVDemoAdapter adapter = new SingleLVDemoAdapter(this, DataSource.generateData(),
                R.layout.item_single_layout);
        listView.setAdapter(adapter);
    }
}
