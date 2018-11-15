package com.yetland.adapterlib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YETLAND
 * @date 2018/11/14 11:05
 */
public class BBAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<?> mItems = new ArrayList<>();
    private BBTypePool mBBTypePool = new BBTypePool();

    public void setItems(List<?> items) {
        mItems.clear();
        mItems = items;
    }

    public <T> void register(Class<? extends T> clazz, BBBinder binder) {
        mBBTypePool.register(clazz, binder);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BBBinder binder = mBBTypePool.getBinder(viewType);
        binder.mAdapter = this;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public int getItemViewType(int position) {
        Class<?> clazz = mItems.get(position).getClass();
        return mBBTypePool.getViewType(clazz);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BBBinder binder = mBBTypePool.getBinder(holder.getItemViewType());
        binder.onBindViewHolder(mItems.get(position), holder, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
