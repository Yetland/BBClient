package com.yetland.bbclient;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yetland.adapterlib.BBAdapter;
import com.yetland.adapterlib.BBBinder;
import com.yetland.data.entity.Launcher;
import com.yetland.data.entity.resp.LauncherResp;
import com.yetland.data.entity.resp.UserResp;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends Activity {

    BBAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mAdapter = new BBAdapter();
        mAdapter.register(UserResp.class, new BBBinder() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
                View view = inflater.inflate(R.layout.item_string, viewGroup, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(Object o, RecyclerView.ViewHolder holder, int position) {

            }
        });
        mAdapter.register(LauncherResp.class, new BBBinder() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
                View view = inflater.inflate(R.layout.item_int, viewGroup, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(Object o, RecyclerView.ViewHolder holder, int position) {

            }
        });

        List<Object> list = new ArrayList<>();
        list.add(new UserResp());
        list.add(new UserResp());
        list.add(new UserResp());
        list.add(new LauncherResp());
        list.add(new UserResp());
        list.add(new LauncherResp());
        list.add(new LauncherResp());
        mAdapter.setItems(list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
