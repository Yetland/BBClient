package com.yetland.base.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yetland.base.R;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author YETLAND
 * @date 2018/10/9 14:30
 */
public abstract class BaseRecyclerViewFragment extends BaseFragment {

    /**
     * 默认的刷新事件
     */
    private static int DEFAULT_REFRESH_TIME = 3000;
    /**
     * adapter
     */
    protected MultiTypeAdapter mMultiTypeAdapter;
    protected FrameLayout mFlContent;
    /**
     * 下拉刷新view
     */
    protected SmartRefreshLayout mSmartRefreshLayout;
    /**
     * recycler view
     */
    protected RecyclerView mRecyclerView;
    /**
     * 分割线
     */
    private RecyclerView.ItemDecoration mItemDecoration;
    /**
     * 数据为空时，显示的图片
     */
    private ImageView mIvEmpty;
    /**
     * recycler view的layoutManager默认{@link LinearLayoutManager}
     */
    private RecyclerView.LayoutManager mLayoutManager;
    /**
     * 是否可以下拉刷新
     */
    private boolean refreshEnable = true;
    /**
     * 是否可以加载更多
     */
    private boolean loadMoreEnable = true;
    /**
     * 是否显示数据为空时的图片
     */
    private boolean showEmptyView = false;
    /**
     * 设置空图片的id
     */
    @DrawableRes
    private int mEmptyViewResId;
    /**
     * 当前加载的页码
     */
    private int mPage = 1;
    /**
     * 一页加载的条数
     */
    private int mPageSize = 10;

    /**
     * 所有数据的集合
     */
    protected Items mItems = new Items();

    @Override
    @LayoutRes
    public int initLayout() {
        return R.layout.base_fragment_recycler_view;
    }

    @Override
    public void findView(View rootView) {
        mFlContent = rootView.findViewById(R.id.fl_content);
        mSmartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mIvEmpty = rootView.findViewById(R.id.iv_empty);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        init();
    }

    /**
     * init
     */
    private void init() {
        mMultiTypeAdapter = new MultiTypeAdapter();
        mMultiTypeAdapter.setItems(mItems);
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(getActivity());
        }
        if (mItemDecoration != null) {
            mRecyclerView.addItemDecoration(mItemDecoration);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMultiTypeAdapter);

        mSmartRefreshLayout.setEnableRefresh(refreshEnable);
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setEnableAutoLoadMore(false);

        mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mSmartRefreshLayout.finishRefresh(DEFAULT_REFRESH_TIME);
            onBaseRefresh();
        });

        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mSmartRefreshLayout.finishLoadMore(DEFAULT_REFRESH_TIME);
            onBaseLoadMore();
        });
        registerBinder();
    }

    public void onComplete(int page, @Nullable List items) {
        if (page > 1) {
            onLoadMoreComplete(items);
        } else {
            onRefreshComplete(items);
        }
    }

    /**
     * set refresh complete data
     *
     * @param items items
     */
    private void onRefreshComplete(@Nullable List items) {
        mSmartRefreshLayout.finishRefresh();
        if (items != null && items.size() > 0) {
            if (items.size() == mPageSize) {
                mPage = 2;
                mSmartRefreshLayout.setEnableLoadMore(loadMoreEnable);
            } else {
                mPage = 1;
                mSmartRefreshLayout.setEnableLoadMore(false);
            }
            mItems.clear();
            mItems.addAll(items);
            mMultiTypeAdapter.notifyDataSetChanged();
        }
        if (mItems.size() == 0 && showEmptyView) {
            if (mEmptyViewResId != 0) {
                Glide.with(mActivity)
                        .load(mEmptyViewResId)
                        .into(mIvEmpty);
            }
            mIvEmpty.setVisibility(View.VISIBLE);
        } else {
            mIvEmpty.setVisibility(View.GONE);
        }
    }

    /**
     * set load more data
     *
     * @param items data
     */
    private void onLoadMoreComplete(@Nullable List items) {
        mSmartRefreshLayout.finishLoadMore();
        if (items != null) {
            if (items.size() == mPageSize) {
                mPage++;
                mSmartRefreshLayout.setEnableLoadMore(true);
            } else {
                mSmartRefreshLayout.setEnableLoadMore(false);
            }
            mItems.addAll(items);
            mMultiTypeAdapter.notifyDataSetChanged();
        }
    }

    /**
     * set load failed
     *
     * @see #onLoadFailed(String)
     */
    public void onLoadFailed() {
        onLoadFailed("");
    }

    /**
     * @param msg error msg
     */
    public void onLoadFailed(String msg) {
        if (mSmartRefreshLayout.isRefreshing()) {
            mSmartRefreshLayout.finishRefresh();
        }

        if (!mSmartRefreshLayout.isLoadmoreFinished()) {
            mSmartRefreshLayout.finishLoadMore();
        }
    }

    /**
     * register binder for multi type adapter
     */
    protected abstract void registerBinder();

    /**
     * on refresh callback
     */
    protected abstract void onBaseRefresh();

    /**
     * on load more callback
     */
    protected abstract void onBaseLoadMore();

    /**
     * set the layout manager for recycler view
     *
     * @param layoutManager layout manager {@link LinearLayoutManager,android.support.v7.widget.GridLayoutManager}
     */
    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    /**
     * return if smart refresh layout refresh enable state
     *
     * @return true means enable.otherwise disable
     */
    public boolean isRefreshEnable() {
        return refreshEnable;
    }

    /**
     * if should refresh
     *
     * @param refreshEnable true means can refresh. otherwise can not
     */
    public void setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.setEnableRefresh(refreshEnable);
        }
    }

    /**
     * return if smart refresh layout is load more enable
     *
     * @return true means enable. otherwise disable
     */
    public boolean isLoadMoreEnable() {
        return loadMoreEnable;
    }

    /**
     * set if load more enable
     *
     * @param loadMoreEnable true means enable. otherwise disable
     */
    protected void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }

    /**
     * 返回是否显示为空图片
     *
     * @return true代表显示，false不显示
     */
    public boolean isShowEmptyView() {
        return showEmptyView;
    }

    /**
     * 设置费否显示空图片
     *
     * @param showEmptyView true代表显示，false不显示
     */
    protected void setShowEmptyView(boolean showEmptyView) {
        this.showEmptyView = showEmptyView;
    }

    /**
     * get current page number
     *
     * @return page number
     */
    protected int getPage() {
        return mPage;
    }

    /**
     * 设置当前页码
     *
     * @param page 页码
     */
    public void setPage(int page) {
        this.mPage = page;
    }

    /**
     * 获取页面加载一次的条数
     *
     * @return 页面加载一次的条数
     */
    protected int getPageSize() {
        return mPageSize;
    }

    /**
     * 设置一次加载的条数
     *
     * @param pageSize 加载条数
     */
    public void setPageSize(int pageSize) {
        this.mPageSize = pageSize;
    }

    /**
     * 设置分割线
     *
     * @param itemDecoration {@link android.support.v7.widget.RecyclerView.ItemDecoration}
     *                       分割线类型
     */
    protected void setItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        mItemDecoration = itemDecoration;
        if (mRecyclerView != null) {
            mRecyclerView.addItemDecoration(mItemDecoration);
        }
    }

    /**
     * set empty image view resource
     *
     * @param emptyViewResId res id
     */
    public void setEmptyViewResId(int emptyViewResId) {
        mEmptyViewResId = emptyViewResId;
    }
}
