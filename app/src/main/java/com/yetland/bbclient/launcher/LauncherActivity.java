package com.yetland.bbclient.launcher;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yetland.base.base.BaseMvpActivity;
import com.yetland.base.base.BasePresenter;
import com.yetland.base.data.SPUtil;
import com.yetland.base.view.BBButton;
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
    private Button mButtonSkip;
    private DelayHandler mDelayHandler;
    private static final String KEY = "launcher";
    private static final int DEFAULT = 1;
    private static final int SUCCESS = 2;

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    protected boolean isShowTitleBar() {
        return false;
    }

    @Override
    protected void init() {
        getLauncher();
        mDelayHandler = new DelayHandler(this, mButtonSkip);
        mDelayHandler.sendEmptyMessageDelayed(DEFAULT, 3000);
        mButtonSkip.setOnClickListener(v -> mDelayHandler.sendEmptyMessage(DEFAULT));
    }

    @Override
    protected void findViews() {
        mImageView = findViewById(R.id.iv_launcher);
        mButtonSkip = findViewById(R.id.bt_skip);
    }

    public static class DelayHandler extends Handler {

        private static int SKIP_LIMIT = 2000;
        private static int SKIP_ONE_SECOND = 1000;
        private WeakReference<LauncherActivity> mReference;
        private WeakReference<Button> mReferenceSkip;

        private LauncherActivity mLauncherActivity;
        private Button mButton;

        DelayHandler(LauncherActivity launcherActivity, Button buttonSkip) {
            mReference = new WeakReference<>(launcherActivity);
            mReferenceSkip = new WeakReference<>(buttonSkip);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLauncherActivity = mReference.get();
            mButton = mReferenceSkip.get();
            int what = msg.what;
            switch (what) {
                case DEFAULT:
                    removeCallbacksAndMessages(null);
                    if (mLauncherActivity != null && !mLauncherActivity.isFinishing()) {
                        mLauncherActivity.turnToNext(MainActivity.class, true);
                    }
                    break;
                case SUCCESS:
                    int obj = (Integer) msg.obj;
                    if (mButton != null) {
                        mButton.setText(String.valueOf(obj / 1000));
                    }
                    Message message = new Message();

                    if (obj < SKIP_LIMIT) {
                        message.what = DEFAULT;
                    } else {
                        message.what = SUCCESS;
                    }
                    message.obj = obj - SKIP_ONE_SECOND;
                    sendMessageDelayed(message, 1000);
                    break;
                default:
                    break;
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
    public void show(String imgUrl) {
        Glide.with(this)
                .load(imgUrl)
                .into(mImageView);
    }

    @Override
    public void success(Launcher launcher) {
        SPUtil.save(KEY, launcher);

        Message message = new Message();
        message.what = SUCCESS;
        message.obj = Integer.valueOf(launcher.getSkipTime());
        mDelayHandler.removeCallbacksAndMessages(null);
        mDelayHandler.sendMessage(message);
        show(launcher.getImgUrl());
    }

    @Override
    public void failed(String msg) {
        String value = SPUtil.getString(KEY);
        if (!TextUtils.isEmpty(value)) {
            Launcher launcher = new Gson().fromJson(value, Launcher.class);
            success(launcher);
        }
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
