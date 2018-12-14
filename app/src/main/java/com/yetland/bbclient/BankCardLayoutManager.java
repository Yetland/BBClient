package com.yetland.bbclient;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YETLAND
 * @date 2018/12/14 10:17
 */
public class BankCardLayoutManager extends RecyclerView.LayoutManager {

    private int mMaxItemCount;
    private List<MainActivity.MainBean> mMainBeans;

    public BankCardLayoutManager(int maxItemCount, List<MainActivity.MainBean> mainBeans) {
        this.mMaxItemCount = maxItemCount;
        mMainBeans = mainBeans;
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    private float ratio = 0.25f;

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        int itemCount = state.getItemCount();
        if (itemCount == 0) {
            return;
        }
        if (itemCount > mMaxItemCount) {
            throw new RuntimeException("...........");
        }
        detachAndScrapAttachedViews(recycler);

        int totalExtraHeight = 0;
        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            measureChild(view, 0, 0);
            addView(view);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int left = layoutParams.leftMargin;
            int viewHeight = view.getMeasuredHeight();
            if (i > 0 && i < itemCount) {
                if (mMainBeans.get(i - 1).isSelected()) {
                    totalExtraHeight += (viewHeight * (1 - ratio));
                }
            }
            int top = (int) (viewHeight * i * ratio) + totalExtraHeight;
            int right = view.getMeasuredWidth() + layoutParams.rightMargin;
            int bottom = top + viewHeight;

            layoutDecorated(view, left, top, right, bottom);
            view.setTag(R.integer.top, top);
        }
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        List<View> viewList = getChildViewList();
        for (int i = 0; i < viewList.size(); i++) {
            View view = viewList.get(i);
            int initTop = (int) view.getTag(R.integer.top);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int viewHeight = view.getMeasuredHeight();

            int left = layoutParams.leftMargin;
            int top = Math.min(Math.max(view.getTop() - dy, (int) (i * viewHeight * ratio)), initTop);
            int right = view.getMeasuredWidth() + layoutParams.rightMargin;
            int bottom = top + viewHeight;
            layoutDecorated(view, left, top, right, bottom);
        }
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private List<View> getChildViewList() {

        List<View> childCountList = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            childCountList.add(getChildAt(i));
        }
        return childCountList;
    }
}
