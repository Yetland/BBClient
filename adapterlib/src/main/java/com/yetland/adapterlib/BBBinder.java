package com.yetland.adapterlib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author YETLAND
 * @date 2018/11/14 11:05
 */
public abstract class BBBinder<T, VH extends RecyclerView.ViewHolder> {

    protected RecyclerView.Adapter mAdapter;

    public abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup viewGroup);

    public abstract void onBindViewHolder(T t, RecyclerView.ViewHolder holder, int position);
}
