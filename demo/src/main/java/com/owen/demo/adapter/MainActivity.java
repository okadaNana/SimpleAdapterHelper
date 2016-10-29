package com.owen.demo.adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.owen.demo.adapter.listview.multiple.MultipleLayoutLVActivity;
import com.owen.demo.adapter.listview.single.SingleLayoutLVActivity;
import com.owen.demo.adapter.recyclerview.multiple.MultipleLayoutRVActivity;
import com.owen.demo.adapter.recyclerview.single.SingleLayoutRVActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_single_layout_listview).setOnClickListener(this);
        findViewById(R.id.btn_multiple_layout_listview).setOnClickListener(this);
        findViewById(R.id.btn_single_layout_recyclerview).setOnClickListener(this);
        findViewById(R.id.btn_multiple_layout_recyclerview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_single_layout_listview:
                intent.setClass(this, SingleLayoutLVActivity.class);
                break;
            case R.id.btn_multiple_layout_listview:
                intent.setClass(this, MultipleLayoutLVActivity.class);
                break;
            case R.id.btn_single_layout_recyclerview:
                intent.setClass(this, SingleLayoutRVActivity.class);
                break;
            case R.id.btn_multiple_layout_recyclerview:
                intent.setClass(this, MultipleLayoutRVActivity.class);
                break;
        }

        startActivity(intent);
    }
}
