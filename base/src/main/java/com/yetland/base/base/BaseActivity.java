package com.yetland.base.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.yetland.base.R;
import com.yetland.base.app.App;
import com.yetland.base.dagger.component.AppComponent;
import com.yetland.data.dagger.component.DataComponent;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 */
public abstract class BaseActivity extends Activity {
    protected Activity mActivity;
    protected Context mContext;
    protected App mApp;
    protected AppComponent mAppComponent;
    protected DataComponent mDataComponent;
    protected boolean showTitle;

    protected TextView mTvTitle;
    protected TextView mTvLeft;
    protected TextView mTvRight;
    protected ImageView mIvBack;

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
        showTitle = isShowTitleBar();
        if (showTitle) {
            mTvTitle = findViewById(R.id.base_tv_center);
            mTvLeft = findViewById(R.id.base_tv_left);
            mTvRight = findViewById(R.id.base_tv_right);

            mIvBack = findViewById(R.id.base_iv_back);
            mIvBack.setOnClickListener(v -> onBackClick());

            String title = getTitleText();
            if (!TextUtils.isEmpty(title)) {
                mTvTitle.setText(title);
            }
        }
        findViews();
        init();
    }

    /**
     * 获取title名
     *
     * @return title名
     */
    protected abstract String getTitleText();

    /**
     * 是否显示title
     *
     * @return true or false
     */
    protected abstract boolean isShowTitleBar();

    /**
     * 初始化操作
     */
    protected abstract void init();

    /**
     * 初始化view操作
     */
    protected abstract void findViews();

    /**
     * 获取layout的id
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * dagger注入
     */
    protected abstract void inject();

    /**
     * 设置title名
     *
     * @param title string
     */
    protected void setTitleText(String title) {
        if (showTitle && !TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    private void daggerInit() {

    }

    protected void turnToNext(Class clazz) {
        turnToNext(clazz, false);
    }

    protected void turnToNext(Class clazz, boolean finish) {
        startActivity(new Intent(this, clazz));
        if (finish) {
            this.finish();
        }
    }

    private void onBackClick() {
        onBackPressed();
    }

}
