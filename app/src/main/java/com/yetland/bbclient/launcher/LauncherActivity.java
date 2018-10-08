package com.yetland.bbclient.launcher;

import com.yetland.base.base.BaseActivity;
import com.yetland.bbclient.R;
import com.yetland.data.entity.Launcher;

import javax.inject.Inject;

/**
 * @author YETLAND
 * @date 2018/10/8 16:57
 */
public class LauncherActivity extends BaseActivity implements LauncherView {

    @Inject
    LauncherModel mLauncherModel;
    private LauncherPresenter mLauncherPresenter;

    @Override
    protected void init() {
        mLauncherPresenter = new LauncherPresenter(mLauncherModel, this);
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void inject() {

    }

    @Override
    public void getLauncher() {

    }

    @Override
    public void show() {

    }

    @Override
    public void success(Launcher launcher) {

    }

    @Override
    public void failed(String msg) {

    }
}
