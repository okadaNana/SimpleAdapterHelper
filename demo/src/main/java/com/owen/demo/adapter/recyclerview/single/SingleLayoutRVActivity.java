package com.owen.demo.adapter.recyclerview.single;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owen.demo.adapter.R;
import com.owen.demo.adapter.model.DataSource;

public class SingleLayoutRVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SingleRVDemoAdapter adapter = new SingleRVDemoAdapter(this, DataSource.generateData(), R.layout.item_single_layout);
        recyclerView.setAdapter(adapter);
    }
}
