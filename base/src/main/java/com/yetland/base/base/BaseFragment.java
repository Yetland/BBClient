package com.yetland.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author YETLAND
 * @date 2018/10/9 14:08
 */
public abstract class BaseFragment  extends Fragment {
    /**
     * 加载刷新
     */
    private boolean mLoad;
    /**
     * 是否强制刷新
     */
    public boolean mForceLoad;
    protected Activity mActivity;
    private View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        /*
          防止Fragment多次切换时调用onCreateView重新加载View
         */
        if (null == mRootView) {
            mRootView = inflater.inflate(initLayout(), null);
            findView(mRootView);
            initView(savedInstanceState);
            initListener();
            /*
              为了保证一开始加载Fragment的时候判断是否需要加载数据
             */
            if (getUserVisibleHint()) {
                updateView();
            }
        } else {
            /*
              缓存的rootView需要判断是否已经被加过parent，
              如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
             */
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    /**
     * 返回布局页面
     *
     * @return 页面布局的resource id
     */
    @LayoutRes
    protected abstract int initLayout();

    /**
     * 绑定控件
     *
     * @param rootView view
     */
    protected abstract void findView(View rootView);

    /**
     * 初始化页面布局
     *
     * @param savedInstanceState .
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 设置监听
     */
    protected abstract void initListener();

    /**
     * 刷新界面(数据)
     */
    public abstract void updateView();

    /**
     * 判断此Fragment是否正在前台显示
     * 通过判断就知道是否要进行数据加载了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible()) {
            if (!mLoad || mForceLoad) {
                updateView();
                mForceLoad = false;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}