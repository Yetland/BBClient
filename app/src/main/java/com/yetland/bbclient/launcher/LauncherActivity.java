package com.yetland.bbclient.launcher;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yetland.base.base.BaseMvpActivity;
import com.yetland.base.base.BasePresenter;
import com.yetland.bbclient.MainActivity;
import com.yetland.bbclient.R;
import com.yetland.bbclient.dagger.component.DaggerActivityComponent;
import com.yetland.bbclient.dagger.module.ActivityModule;
import com.yetland.data.entity.Launcher;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * @author YETLAND
 * @date 2018/10/8 16:57
 */
public class LauncherActivity extends BaseMvpActivity implements LauncherView {

    @Inject
    LauncherPresenter mLauncherPresenter;

    private ImageView mImageView;

    @Override
    protected void init() {
        getLauncher();
    }

    @Override
    protected void findViews() {
        mImageView = findViewById(R.id.iv_launcher);
        DelayHandler delayHandler = new DelayHandler(this);
        delayHandler.sendEmptyMessageDelayed(1, 3000);
    }

    public static class DelayHandler extends Handler {

        private WeakReference<LauncherActivity> mReference;
        private LauncherActivity mLauncherActivity;

        DelayHandler(LauncherActivity launcherActivity) {
            mReference = new WeakReference<>(launcherActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLauncherActivity = mReference.get();
            if (mLauncherActivity != null && !mLauncherActivity.isFinishing()) {
                mLauncherActivity.turnToNext(MainActivity.class, true);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void inject() {
        DaggerActivityComponent.builder()
                .dataComponent(mDataComponent)
                .appComponent(mAppComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .injectLauncher(this);
    }

    @Override
    public void getLauncher() {
        mLauncherPresenter.getLauncher();
    }

    @Override
    public void show() {

    }

    @Override
    public void success(Launcher launcher) {
        Glide.with(this)
                .load(launcher.getImgUrl())
                .into(mImageView);
    }

    @Override
    public void failed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setView() {
        mLauncherPresenter.setV(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mLauncherPresenter;
    }
}
