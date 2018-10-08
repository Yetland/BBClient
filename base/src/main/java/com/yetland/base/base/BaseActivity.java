package com.yetland.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yetland.base.app.App;
import com.yetland.base.dagger.component.AppComponent;
import com.yetland.data.dagger.component.DataComponent;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;
    protected Context mContext;
    protected App mApp;
    protected AppComponent mAppComponent;
    protected DataComponent mDataComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (App) getApplication();
        mAppComponent = mApp.getAppComponent();
        mDataComponent = mApp.getDataComponent();
        mActivity = this;
        mContext = this;
        daggerInit();
        inject();
        setContentView(getLayoutId());
        findViews();
        init();
    }

    protected abstract void init();

    protected abstract void findViews();

    protected abstract int getLayoutId();

    private void daggerInit() {

    }

    protected abstract void inject();
}
