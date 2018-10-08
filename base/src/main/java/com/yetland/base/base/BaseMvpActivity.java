package com.yetland.base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author: Yet_land
 * Date: 2018/10/8.
 */
public abstract class BaseMvpActivity extends BaseActivity {

    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBasePresenter = getPresenter();
        setView();
    }

    protected abstract void setView();

    protected abstract BasePresenter getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
        }
    }
}
